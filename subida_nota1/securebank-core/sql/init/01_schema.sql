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
