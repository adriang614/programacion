#!/bin/bash

# =============================================================================
# SecureBank Core Engine - Generador de Infraestructura
# Ejecutar: chmod +x setup_securebank.sh && ./setup_securebank.sh
# =============================================================================

set -e

CYAN='\033[0;36m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m'

echo -e "${CYAN}"
echo "╔═══════════════════════════════════════════════════════════════╗"
echo "║         SECUREBANK CORE ENGINE - SETUP GENERATOR              ║"
echo "╚═══════════════════════════════════════════════════════════════╝"
echo -e "${NC}"

# -----------------------------------------------------------------------------
# Crear estructura de directorios
# -----------------------------------------------------------------------------
echo -e "${YELLOW}[1/6] Creando estructura de directorios...${NC}"

mkdir -p securebank-core/{sql/init,src/main/java/es/securebank/core/{config,model/enums,exception,dao/impl,service,cache,concurrency,ui/commands,util},src/main/resources,src/test/java/es/securebank/core,logs,scripts}

cd securebank-core

# -----------------------------------------------------------------------------
# docker-compose.yml
# -----------------------------------------------------------------------------
echo -e "${YELLOW}[2/6] Generando docker-compose.yml...${NC}"

cat > docker-compose.yml << 'EOF'
version: '3.9'

services:
  securebank-db:
    image: postgres:15
    container_name: securebank_postgres
    restart: unless-stopped
    environment:
      POSTGRES_USER: securebank_admin
      POSTGRES_PASSWORD: Sb@nk2024$ecure
      POSTGRES_DB: securebank_core
      PGDATA: /var/lib/postgresql/data/pgdata
      TZ: Europe/Madrid
    ports:
      - "5432:5432"
    volumes:
      - securebank_data:/var/lib/postgresql/data
      - ./sql/init:/docker-entrypoint-initdb.d:ro
    healthcheck:
      test: ["CMD-SHELL", "pg_isready -U securebank_admin -d securebank_core"]
      interval: 10s
      timeout: 5s
      retries: 5
      start_period: 30s
    networks:
      - securebank_network
    logging:
      driver: "json-file"
      options:
        max-size: "10m"
        max-file: "3"

networks:
  securebank_network:
    driver: bridge

volumes:
  securebank_data:
    driver: local
EOF

# -----------------------------------------------------------------------------
# Scripts de despliegue
# -----------------------------------------------------------------------------
echo -e "${YELLOW}[3/6] Generando scripts de despliegue...${NC}"

# --- deploy.sh ---
cat > scripts/deploy.sh << 'EOF'
#!/bin/bash
set -e

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
PROJECT_DIR="$(dirname "$SCRIPT_DIR")"

cd "$PROJECT_DIR"

echo "══════════════════════════════════════════════════════════"
echo "  SECUREBANK - DESPLIEGUE"
echo "══════════════════════════════════════════════════════════"

# Verificar Docker
if ! command -v docker &> /dev/null; then
    echo "ERROR: Docker no está instalado"
    exit 1
fi

if ! docker info &> /dev/null; then
    echo "ERROR: El servicio Docker no está corriendo"
    exit 1
fi

# Detener contenedores previos
echo "[1/4] Deteniendo contenedores previos..."
docker-compose down --remove-orphans 2>/dev/null || true

# Construir e iniciar
echo "[2/4] Levantando contenedor PostgreSQL..."
docker-compose up -d

# Esperar a que PostgreSQL esté listo
echo "[3/4] Esperando a que PostgreSQL esté operativo..."
RETRIES=30
until docker exec securebank_postgres pg_isready -U securebank_admin -d securebank_core > /dev/null 2>&1; do
    RETRIES=$((RETRIES-1))
    if [ $RETRIES -le 0 ]; then
        echo "ERROR: PostgreSQL no respondió a tiempo"
        exit 1
    fi
    echo "    Esperando... ($RETRIES intentos restantes)"
    sleep 2
done

echo "[4/4] Verificando conexión..."
docker exec securebank_postgres psql -U securebank_admin -d securebank_core -c "SELECT 1;" > /dev/null

echo ""
echo "✓ DESPLIEGUE COMPLETADO"
echo ""
echo "Conexión JDBC:"
echo "  URL:      jdbc:postgresql://localhost:5432/securebank_core"
echo "  Usuario:  securebank_admin"
echo "  Password: Sb@nk2024\$ecure"
echo ""
EOF

# --- stop.sh ---
cat > scripts/stop.sh << 'EOF'
#!/bin/bash
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
PROJECT_DIR="$(dirname "$SCRIPT_DIR")"

cd "$PROJECT_DIR"

echo "Deteniendo SecureBank..."
docker-compose down

echo "✓ Contenedores detenidos"
EOF

# --- reset.sh ---
cat > scripts/reset.sh << 'EOF'
#!/bin/bash
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
PROJECT_DIR="$(dirname "$SCRIPT_DIR")"

cd "$PROJECT_DIR"

echo "⚠ ATENCIÓN: Esto eliminará TODOS los datos de la base de datos"
read -p "¿Continuar? (s/N): " confirm

if [[ "$confirm" =~ ^[Ss]$ ]]; then
    echo "Eliminando contenedores y volúmenes..."
    docker-compose down -v --remove-orphans
    echo "Reiniciando..."
    ./scripts/deploy.sh
else
    echo "Operación cancelada"
fi
EOF

# --- logs.sh ---
cat > scripts/logs.sh << 'EOF'
#!/bin/bash
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
PROJECT_DIR="$(dirname "$SCRIPT_DIR")"

cd "$PROJECT_DIR"

echo "Mostrando logs de PostgreSQL (Ctrl+C para salir)..."
docker-compose logs -f securebank-db
EOF

# --- psql.sh ---
cat > scripts/psql.sh << 'EOF'
#!/bin/bash
echo "Conectando a PostgreSQL..."
docker exec -it securebank_postgres psql -U securebank_admin -d securebank_core
EOF

# --- status.sh ---
cat > scripts/status.sh << 'EOF'
#!/bin/bash
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
PROJECT_DIR="$(dirname "$SCRIPT_DIR")"

cd "$PROJECT_DIR"

echo "══════════════════════════════════════════════════════════"
echo "  SECUREBANK - ESTADO DEL SISTEMA"
echo "══════════════════════════════════════════════════════════"
echo ""

# Estado del contenedor
echo "▸ Contenedor Docker:"
docker-compose ps

echo ""
echo "▸ Conexión a base de datos:"
if docker exec securebank_postgres pg_isready -U securebank_admin -d securebank_core > /dev/null 2>&1; then
    echo "  ✓ PostgreSQL operativo"
    
    # Estadísticas
    echo ""
    echo "▸ Estadísticas de la base de datos:"
    docker exec securebank_postgres psql -U securebank_admin -d securebank_core -c "
        SELECT 
            (SELECT COUNT(*) FROM information_schema.tables WHERE table_schema = 'public') as tablas,
            pg_size_pretty(pg_database_size('securebank_core')) as tamanio;
    " 2>/dev/null || echo "  (Tablas aún no creadas)"
else
    echo "  ✗ PostgreSQL no responde"
fi
echo ""
EOF

chmod +x scripts/*.sh

# -----------------------------------------------------------------------------
# Scripts SQL
# -----------------------------------------------------------------------------
echo -e "${YELLOW}[4/6] Generando scripts SQL...${NC}"

# --- 01_schema.sql ---
cat > sql/init/01_schema.sql << 'EOF'
-- =============================================================================
-- SECUREBANK CORE ENGINE - ESQUEMA DE BASE DE DATOS
-- =============================================================================

-- Secuencias
CREATE SEQUENCE seq_clientes START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_cuentas START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_transacciones START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_tarjetas START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_prestamos START WITH 1 INCREMENT BY 1;

-- -----------------------------------------------------------------------------
-- TABLA: clientes
-- -----------------------------------------------------------------------------
CREATE TABLE clientes (
    id              INTEGER PRIMARY KEY DEFAULT nextval('seq_clientes'),
    dni             VARCHAR(9) NOT NULL UNIQUE,
    nombre          VARCHAR(100) NOT NULL,
    apellidos       VARCHAR(150) NOT NULL,
    email           VARCHAR(255) NOT NULL UNIQUE,
    telefono        VARCHAR(15),
    pin_hash        VARCHAR(256) NOT NULL,
    fecha_alta      TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fecha_modificacion TIMESTAMP WITH TIME ZONE,
    estado          VARCHAR(20) NOT NULL DEFAULT 'ACTIVO',
    
    CONSTRAINT chk_cliente_dni CHECK (dni ~ '^[0-9]{8}[A-Z]$'),
    CONSTRAINT chk_cliente_email CHECK (email ~ '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$'),
    CONSTRAINT chk_cliente_estado CHECK (estado IN ('ACTIVO', 'BLOQUEADO', 'BAJA')),
    CONSTRAINT chk_cliente_fecha_alta CHECK (fecha_alta <= CURRENT_TIMESTAMP)
);

-- -----------------------------------------------------------------------------
-- TABLA: cuentas_bancarias
-- -----------------------------------------------------------------------------
CREATE TABLE cuentas_bancarias (
    id              INTEGER PRIMARY KEY DEFAULT nextval('seq_cuentas'),
    iban            VARCHAR(24) NOT NULL UNIQUE,
    cliente_id      INTEGER NOT NULL,
    tipo            VARCHAR(20) NOT NULL DEFAULT 'CORRIENTE',
    saldo           DECIMAL(15, 2) NOT NULL DEFAULT 0.00,
    saldo_retenido  DECIMAL(15, 2) NOT NULL DEFAULT 0.00,
    fecha_apertura  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fecha_cierre    TIMESTAMP WITH TIME ZONE,
    estado          VARCHAR(20) NOT NULL DEFAULT 'ACTIVA',
    
    CONSTRAINT fk_cuenta_cliente FOREIGN KEY (cliente_id) 
        REFERENCES clientes(id) ON DELETE RESTRICT,
    CONSTRAINT chk_cuenta_iban CHECK (iban ~ '^ES[0-9]{22}$'),
    CONSTRAINT chk_cuenta_tipo CHECK (tipo IN ('CORRIENTE', 'AHORRO', 'NOMINA')),
    CONSTRAINT chk_cuenta_estado CHECK (estado IN ('ACTIVA', 'BLOQUEADA', 'CERRADA')),
    CONSTRAINT chk_cuenta_saldo CHECK (saldo >= 0),
    CONSTRAINT chk_cuenta_saldo_retenido CHECK (saldo_retenido >= 0)
);

-- -----------------------------------------------------------------------------
-- TABLA: transacciones
-- -----------------------------------------------------------------------------
CREATE TABLE transacciones (
    id                  INTEGER PRIMARY KEY DEFAULT nextval('seq_transacciones'),
    cuenta_id           INTEGER NOT NULL,
    cuenta_destino_id   INTEGER,
    tipo                VARCHAR(30) NOT NULL,
    importe             DECIMAL(15, 2) NOT NULL,
    saldo_posterior     DECIMAL(15, 2) NOT NULL,
    concepto            VARCHAR(255),
    fecha               TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    referencia          VARCHAR(50),
    
    CONSTRAINT fk_transaccion_cuenta FOREIGN KEY (cuenta_id) 
        REFERENCES cuentas_bancarias(id) ON DELETE RESTRICT,
    CONSTRAINT fk_transaccion_cuenta_destino FOREIGN KEY (cuenta_destino_id) 
        REFERENCES cuentas_bancarias(id) ON DELETE RESTRICT,
    CONSTRAINT chk_transaccion_tipo CHECK (tipo IN (
        'INGRESO', 'RETIRO', 'TRANSFERENCIA_ENTRADA', 'TRANSFERENCIA_SALIDA', 
        'COMISION', 'PAGO_PRESTAMO', 'INTERES', 'DEVOLUCION'
    )),
    CONSTRAINT chk_transaccion_importe CHECK (importe <> 0)
);

-- -----------------------------------------------------------------------------
-- TABLA: tarjetas_credito
-- -----------------------------------------------------------------------------
CREATE TABLE tarjetas_credito (
    id                  INTEGER PRIMARY KEY DEFAULT nextval('seq_tarjetas'),
    cuenta_id           INTEGER NOT NULL,
    numero              VARCHAR(16) NOT NULL UNIQUE,
    cvv_hash            VARCHAR(256) NOT NULL,
    limite_credito      DECIMAL(15, 2) NOT NULL,
    saldo_dispuesto     DECIMAL(15, 2) NOT NULL DEFAULT 0.00,
    fecha_emision       DATE NOT NULL DEFAULT CURRENT_DATE,
    fecha_caducidad     DATE NOT NULL,
    estado              VARCHAR(20) NOT NULL DEFAULT 'ACTIVA',
    
    CONSTRAINT fk_tarjeta_cuenta FOREIGN KEY (cuenta_id) 
        REFERENCES cuentas_bancarias(id) ON DELETE RESTRICT,
    CONSTRAINT chk_tarjeta_numero CHECK (numero ~ '^[0-9]{16}$'),
    CONSTRAINT chk_tarjeta_limite CHECK (limite_credito > 0),
    CONSTRAINT chk_tarjeta_saldo CHECK (saldo_dispuesto >= 0 AND saldo_dispuesto <= limite_credito),
    CONSTRAINT chk_tarjeta_fechas CHECK (fecha_caducidad > fecha_emision),
    CONSTRAINT chk_tarjeta_estado CHECK (estado IN ('ACTIVA', 'BLOQUEADA', 'CADUCADA'))
);

-- -----------------------------------------------------------------------------
-- TABLA: prestamos
-- -----------------------------------------------------------------------------
CREATE TABLE prestamos (
    id                  INTEGER PRIMARY KEY DEFAULT nextval('seq_prestamos'),
    cliente_id          INTEGER NOT NULL,
    cuenta_id           INTEGER NOT NULL,
    capital_inicial     DECIMAL(15, 2) NOT NULL,
    capital_pendiente   DECIMAL(15, 2) NOT NULL,
    interes_anual       DECIMAL(5, 2) NOT NULL,
    cuotas_totales      INTEGER NOT NULL,
    cuotas_pagadas      INTEGER NOT NULL DEFAULT 0,
    cuota_mensual       DECIMAL(15, 2) NOT NULL,
    fecha_concesion     DATE NOT NULL DEFAULT CURRENT_DATE,
    fecha_vencimiento   DATE NOT NULL,
    estado              VARCHAR(20) NOT NULL DEFAULT 'VIGENTE',
    
    CONSTRAINT fk_prestamo_cliente FOREIGN KEY (cliente_id) 
        REFERENCES clientes(id) ON DELETE RESTRICT,
    CONSTRAINT fk_prestamo_cuenta FOREIGN KEY (cuenta_id) 
        REFERENCES cuentas_bancarias(id) ON DELETE RESTRICT,
    CONSTRAINT chk_prestamo_capital_inicial CHECK (capital_inicial > 0),
    CONSTRAINT chk_prestamo_capital_pendiente CHECK (capital_pendiente >= 0 AND capital_pendiente <= capital_inicial),
    CONSTRAINT chk_prestamo_interes CHECK (interes_anual BETWEEN 0 AND 30),
    CONSTRAINT chk_prestamo_cuotas_totales CHECK (cuotas_totales > 0),
    CONSTRAINT chk_prestamo_cuotas_pagadas CHECK (cuotas_pagadas >= 0 AND cuotas_pagadas <= cuotas_totales),
    CONSTRAINT chk_prestamo_estado CHECK (estado IN ('VIGENTE', 'LIQUIDADO', 'IMPAGADO'))
);

-- -----------------------------------------------------------------------------
-- TABLA: auditoria_fraude
-- -----------------------------------------------------------------------------
CREATE TABLE auditoria_fraude (
    id                  SERIAL PRIMARY KEY,
    cliente_id          INTEGER NOT NULL,
    cuenta_id           INTEGER NOT NULL,
    tipo_fraude         VARCHAR(50) NOT NULL,
    descripcion         TEXT,
    transacciones_ids   INTEGER[],
    fecha_deteccion     TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    accion_tomada       VARCHAR(100),
    
    CONSTRAINT fk_fraude_cliente FOREIGN KEY (cliente_id) 
        REFERENCES clientes(id) ON DELETE RESTRICT,
    CONSTRAINT fk_fraude_cuenta FOREIGN KEY (cuenta_id) 
        REFERENCES cuentas_bancarias(id) ON DELETE RESTRICT
);

-- -----------------------------------------------------------------------------
-- ÍNDICES
-- -----------------------------------------------------------------------------
CREATE INDEX idx_clientes_dni ON clientes(dni);
CREATE INDEX idx_clientes_email ON clientes(email);
CREATE INDEX idx_clientes_estado ON clientes(estado);

CREATE INDEX idx_cuentas_iban ON cuentas_bancarias(iban);
CREATE INDEX idx_cuentas_cliente ON cuentas_bancarias(cliente_id);
CREATE INDEX idx_cuentas_estado ON cuentas_bancarias(estado);

CREATE INDEX idx_transacciones_cuenta ON transacciones(cuenta_id);
CREATE INDEX idx_transacciones_fecha ON transacciones(fecha DESC);
CREATE INDEX idx_transacciones_tipo ON transacciones(tipo);

CREATE INDEX idx_tarjetas_numero ON tarjetas_credito(numero);
CREATE INDEX idx_tarjetas_cuenta ON tarjetas_credito(cuenta_id);

CREATE INDEX idx_prestamos_cliente ON prestamos(cliente_id);
CREATE INDEX idx_prestamos_estado ON prestamos(estado);

CREATE INDEX idx_fraude_cliente ON auditoria_fraude(cliente_id);
CREATE INDEX idx_fraude_fecha ON auditoria_fraude(fecha_deteccion DESC);
EOF

# --- 02_triggers.sql ---
cat > sql/init/02_triggers.sql << 'EOF'
-- =============================================================================
-- TRIGGERS Y FUNCIONES
-- =============================================================================

-- Función para actualizar fecha_modificacion en clientes
CREATE OR REPLACE FUNCTION fn_actualizar_fecha_modificacion()
RETURNS TRIGGER AS $$
BEGIN
    NEW.fecha_modificacion = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_clientes_modificacion
    BEFORE UPDATE ON clientes
    FOR EACH ROW
    EXECUTE FUNCTION fn_actualizar_fecha_modificacion();

-- Función para validar saldo suficiente antes de transacción
CREATE OR REPLACE FUNCTION fn_validar_transaccion()
RETURNS TRIGGER AS $$
DECLARE
    v_saldo_actual DECIMAL(15, 2);
    v_estado_cuenta VARCHAR(20);
BEGIN
    -- Obtener estado y saldo actual
    SELECT saldo, estado INTO v_saldo_actual, v_estado_cuenta
    FROM cuentas_bancarias
    WHERE id = NEW.cuenta_id;
    
    -- Verificar que la cuenta no está bloqueada
    IF v_estado_cuenta != 'ACTIVA' THEN
        RAISE EXCEPTION 'La cuenta no está activa. Estado actual: %', v_estado_cuenta;
    END IF;
    
    -- Para retiros y transferencias salientes, verificar saldo
    IF NEW.tipo IN ('RETIRO', 'TRANSFERENCIA_SALIDA', 'COMISION', 'PAGO_PRESTAMO') THEN
        IF v_saldo_actual < ABS(NEW.importe) THEN
            RAISE EXCEPTION 'Saldo insuficiente. Disponible: %, Requerido: %', 
                v_saldo_actual, ABS(NEW.importe);
        END IF;
    END IF;
    
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_validar_transaccion
    BEFORE INSERT ON transacciones
    FOR EACH ROW
    EXECUTE FUNCTION fn_validar_transaccion();

-- Función para actualizar saldo tras transacción
CREATE OR REPLACE FUNCTION fn_actualizar_saldo()
RETURNS TRIGGER AS $$
BEGIN
    -- Actualizar saldo de la cuenta origen
    IF NEW.tipo IN ('INGRESO', 'TRANSFERENCIA_ENTRADA', 'DEVOLUCION', 'INTERES') THEN
        UPDATE cuentas_bancarias 
        SET saldo = saldo + ABS(NEW.importe)
        WHERE id = NEW.cuenta_id;
    ELSIF NEW.tipo IN ('RETIRO', 'TRANSFERENCIA_SALIDA', 'COMISION', 'PAGO_PRESTAMO') THEN
        UPDATE cuentas_bancarias 
        SET saldo = saldo - ABS(NEW.importe)
        WHERE id = NEW.cuenta_id;
    END IF;
    
    -- Actualizar saldo_posterior en la transacción
    SELECT saldo INTO NEW.saldo_posterior
    FROM cuentas_bancarias
    WHERE id = NEW.cuenta_id;
    
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_actualizar_saldo
    AFTER INSERT ON transacciones
    FOR EACH ROW
    EXECUTE FUNCTION fn_actualizar_saldo();
EOF

# --- 03_views.sql ---
cat > sql/init/03_views.sql << 'EOF'
-- =============================================================================
-- VISTAS
-- =============================================================================

-- Vista resumen de cliente
CREATE OR REPLACE VIEW v_resumen_cliente AS
SELECT 
    c.id AS cliente_id,
    c.dni,
    c.nombre || ' ' || c.apellidos AS nombre_completo,
    c.estado AS estado_cliente,
    COUNT(DISTINCT cb.id) AS total_cuentas,
    COALESCE(SUM(cb.saldo), 0) AS saldo_global,
    COUNT(DISTINCT CASE WHEN p.estado = 'VIGENTE' THEN p.id END) AS prestamos_activos,
    COALESCE(SUM(CASE WHEN p.estado = 'VIGENTE' THEN p.capital_pendiente ELSE 0 END), 0) AS deuda_total,
    COUNT(DISTINCT tc.id) AS total_tarjetas
FROM clientes c
LEFT JOIN cuentas_bancarias cb ON c.id = cb.cliente_id AND cb.estado = 'ACTIVA'
LEFT JOIN prestamos p ON c.id = p.cliente_id
LEFT JOIN tarjetas_credito tc ON cb.id = tc.cuenta_id AND tc.estado = 'ACTIVA'
GROUP BY c.id, c.dni, c.nombre, c.apellidos, c.estado;

-- Vista de movimientos recientes
CREATE OR REPLACE VIEW v_movimientos_recientes AS
SELECT 
    t.id AS transaccion_id,
    c.dni AS cliente_dni,
    cb.iban,
    t.tipo,
    t.importe,
    t.saldo_posterior,
    t.concepto,
    t.fecha
FROM transacciones t
JOIN cuentas_bancarias cb ON t.cuenta_id = cb.id
JOIN clientes c ON cb.cliente_id = c.id
ORDER BY t.fecha DESC;

-- Vista de alertas de fraude
CREATE OR REPLACE VIEW v_alertas_fraude AS
SELECT 
    af.id,
    c.dni,
    c.nombre || ' ' || c.apellidos AS cliente,
    cb.iban,
    af.tipo_fraude,
    af.descripcion,
    af.fecha_deteccion,
    af.accion_tomada
FROM auditoria_fraude af
JOIN clientes c ON af.cliente_id = c.id
JOIN cuentas_bancarias cb ON af.cuenta_id = cb.id
ORDER BY af.fecha_deteccion DESC;
EOF

# --- 04_data.sql ---
cat > sql/init/04_data.sql << 'EOF'
-- =============================================================================
-- DATOS DE PRUEBA
-- =============================================================================

-- Clientes (PIN: 1234 para todos - hash SHA256 de ejemplo)
INSERT INTO clientes (dni, nombre, apellidos, email, telefono, pin_hash, estado) VALUES
('12345678A', 'Juan', 'García López', 'juan.garcia@email.com', '600111222', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'ACTIVO'),
('23456789B', 'María', 'Fernández Ruiz', 'maria.fernandez@email.com', '600222333', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'ACTIVO'),
('34567890C', 'Carlos', 'Martínez Sanz', 'carlos.martinez@email.com', '600333444', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'ACTIVO'),
('45678901D', 'Ana', 'López Vega', 'ana.lopez@email.com', '600444555', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'ACTIVO'),
('56789012E', 'Pedro', 'Sánchez Gil', 'pedro.sanchez@email.com', '600555666', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'ACTIVO'),
('67890123F', 'Laura', 'Rodríguez Paz', 'laura.rodriguez@email.com', '600666777', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'BLOQUEADO'),
('78901234G', 'Miguel', 'Hernández Sol', 'miguel.hernandez@email.com', '600777888', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'ACTIVO'),
('89012345H', 'Sara', 'Jiménez Luna', 'sara.jimenez@email.com', '600888999', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'ACTIVO'),
('90123456J', 'David', 'Moreno Cruz', 'david.moreno@email.com', '600999000', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'ACTIVO'),
('01234567K', 'Elena', 'Díaz Ramos', 'elena.diaz@email.com', '601000111', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'BAJA');

-- Cuentas bancarias
INSERT INTO cuentas_bancarias (iban, cliente_id, tipo, saldo, estado) VALUES
('ES1234567890123456789012', 1, 'CORRIENTE', 5000.00, 'ACTIVA'),
('ES2345678901234567890123', 1, 'AHORRO', 15000.00, 'ACTIVA'),
('ES3456789012345678901234', 2, 'CORRIENTE', 3500.00, 'ACTIVA'),
('ES4567890123456789012345', 2, 'NOMINA', 2800.00, 'ACTIVA'),
('ES5678901234567890123456', 3, 'CORRIENTE', 12000.00, 'ACTIVA'),
('ES6789012345678901234567', 4, 'CORRIENTE', 850.00, 'ACTIVA'),
('ES7890123456789012345678', 5, 'AHORRO', 25000.00, 'ACTIVA'),
('ES8901234567890123456789', 5, 'CORRIENTE', 4200.00, 'ACTIVA'),
('ES9012345678901234567890', 6, 'CORRIENTE', 150.00, 'BLOQUEADA'),
('ES0123456789012345678901', 7, 'CORRIENTE', 8900.00, 'ACTIVA'),
('ES1111222233334444555566', 7, 'AHORRO', 45000.00, 'ACTIVA'),
('ES2222333344445555666677', 8, 'CORRIENTE', 3200.00, 'ACTIVA'),
('ES3333444455556666777788', 8, 'NOMINA', 1800.00, 'ACTIVA'),
('ES4444555566667777888899', 9, 'CORRIENTE', 6500.00, 'ACTIVA'),
('ES5555666677778888999900', 9, 'AHORRO', 18000.00, 'ACTIVA'),
('ES6666777788889999000011', 3, 'AHORRO', 7500.00, 'ACTIVA'),
('ES7777888899990000111122', 4, 'AHORRO', 2100.00, 'ACTIVA'),
('ES8888999900001111222233', 1, 'NOMINA', 3200.00, 'ACTIVA'),
('ES9999000011112222333344', 2, 'AHORRO', 9800.00, 'ACTIVA'),
('ES0000111122223333444455', 5, 'NOMINA', 2750.00, 'ACTIVA');

-- Tarjetas de crédito
INSERT INTO tarjetas_credito (cuenta_id, numero, cvv_hash, limite_credito, saldo_dispuesto, fecha_caducidad, estado) VALUES
(1, '4111111111111111', 'hash_cvv_1', 3000.00, 500.00, '2028-12-31', 'ACTIVA'),
(3, '4222222222222222', 'hash_cvv_2', 5000.00, 1200.00, '2027-06-30', 'ACTIVA'),
(5, '4333333333333333', 'hash_cvv_3', 10000.00, 3500.00, '2028-03-31', 'ACTIVA'),
(7, '4444444444444444', 'hash_cvv_4', 2000.00, 0.00, '2026-09-30', 'ACTIVA'),
(10, '4555555555555555', 'hash_cvv_5', 8000.00, 2800.00, '2027-12-31', 'BLOQUEADA');

-- Préstamos
INSERT INTO prestamos (cliente_id, cuenta_id, capital_inicial, capital_pendiente, interes_anual, cuotas_totales, cuotas_pagadas, cuota_mensual, fecha_vencimiento, estado) VALUES
(1, 1, 10000.00, 8500.00, 5.5, 24, 6, 450.00, '2028-01-15', 'VIGENTE'),
(3, 5, 25000.00, 22000.00, 4.8, 60, 12, 480.00, '2030-06-01', 'VIGENTE'),
(5, 7, 50000.00, 0.00, 3.9, 120, 120, 520.00, '2025-03-01', 'LIQUIDADO'),
(7, 10, 15000.00, 14200.00, 6.2, 36, 4, 465.00, '2029-02-15', 'VIGENTE'),
(9, 14, 8000.00, 8000.00, 7.5, 18, 0, 495.00, '2027-12-01', 'IMPAGADO');

-- Transacciones de ejemplo (últimos 30 días simulados)
INSERT INTO transacciones (cuenta_id, tipo, importe, saldo_posterior, concepto, fecha) VALUES
(1, 'INGRESO', 2500.00, 2500.00, 'Nómina Junio', NOW() - INTERVAL '25 days'),
(1, 'RETIRO', -200.00, 2300.00, 'Cajero automático', NOW() - INTERVAL '22 days'),
(1, 'TRANSFERENCIA_SALIDA', -150.00, 2150.00, 'Pago alquiler', NOW() - INTERVAL '20 days'),
(3, 'INGRESO', 1800.00, 1800.00, 'Transferencia recibida', NOW() - INTERVAL '18 days'),
(3, 'COMISION', -2.00, 1798.00, 'Comisión mantenimiento', NOW() - INTERVAL '15 days'),
(5, 'INGRESO', 5000.00, 5000.00, 'Ingreso en ventanilla', NOW() - INTERVAL '12 days'),
(5, 'RETIRO', -500.00, 4500.00, 'Cajero automático', NOW() - INTERVAL '10 days'),
(7, 'INGRESO', 3000.00, 3000.00, 'Nómina', NOW() - INTERVAL '8 days'),
(7, 'PAGO_PRESTAMO', -450.00, 2550.00, 'Cuota préstamo #1', NOW() - INTERVAL '5 days'),
(10, 'INGRESO', 4500.00, 4500.00, 'Ingreso efectivo', NOW() - INTERVAL '3 days'),
(1, 'RETIRO', -100.00, 5000.00, 'Cajero', NOW() - INTERVAL '2 days'),
(1, 'INGRESO', 2600.00, 7600.00, 'Nómina Julio', NOW() - INTERVAL '1 day');

-- Más transacciones para simular actividad
DO $$
DECLARE
    i INTEGER;
    cuenta_random INTEGER;
    tipo_random TEXT;
    importe_random DECIMAL;
BEGIN
    FOR i IN 1..88 LOOP
        cuenta_random := (SELECT id FROM cuentas_bancarias WHERE estado = 'ACTIVA' ORDER BY RANDOM() LIMIT 1);
        tipo_random := (ARRAY['INGRESO', 'RETIRO', 'COMISION'])[floor(random() * 3 + 1)];
        importe_random := round((random() * 500 + 10)::numeric, 2);
        
        IF tipo_random != 'INGRESO' THEN
            importe_random := -importe_random;
        END IF;
        
        INSERT INTO transacciones (cuenta_id, tipo, importe, saldo_posterior, concepto, fecha)
        VALUES (
            cuenta_random,
            tipo_random,
            importe_random,
            0, -- Se actualizará por trigger
            'Operación automática #' || i,
            NOW() - (random() * 60 || ' days')::interval
        );
    END LOOP;
END $$;

-- Actualizar saldos_posteriores basándose en el saldo actual
UPDATE transacciones t
SET saldo_posterior = (SELECT saldo FROM cuentas_bancarias WHERE id = t.cuenta_id);
EOF

# -----------------------------------------------------------------------------
# application.properties
# -----------------------------------------------------------------------------
echo -e "${YELLOW}[5/6] Generando archivos de configuración...${NC}"

cat > src/main/resources/application.properties << 'EOF'
# =============================================================================
# SECUREBANK - CONFIGURACIÓN
# =============================================================================

# Base de datos
db.url=jdbc:postgresql://localhost:5432/securebank_core
db.user=securebank_admin
db.password=Sb@nk2024$ecure
db.driver=org.postgresql.Driver

# Pool de conexiones (implementación propia)
db.pool.min=2
db.pool.max=10
db.pool.timeout.ms=5000

# Caché
cache.warmup.enabled=true
cache.stats.interval.seconds=60

# Antifraude
antifraude.enabled=true
antifraude.ventana.segundos=120
antifraude.max.operaciones=3
antifraude.importe.minimo=400.00

# Batch
batch.comisiones.importe=2.00
batch.pool.size=4

# Modo debug (tiempo acelerado para pruebas)
debug.tiempo.acelerado=false
debug.factor.aceleracion=60
EOF

# -----------------------------------------------------------------------------
# README.md
# -----------------------------------------------------------------------------
cat > README.md << 'EOF'
# SecureBank Core Engine

Motor bancario transaccional con persistencia JDBC y control de concurrencia.

## Requisitos

- Java 17+
- Docker y Docker Compose
- Maven 3.8+

## Despliegue rápido

```bash
# Dar permisos y desplegar
chmod +x scripts/*.sh
./scripts/deploy.sh

# Verificar estado
./scripts/status.sh

# Compilar y ejecutar aplicación
mvn clean compile exec:java -Dexec.mainClass="es.securebank.core.Main"
