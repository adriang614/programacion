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
docker compose down --remove-orphans 2>/dev/null || true

# Construir e iniciar
echo "[2/4] Levantando contenedor PostgreSQL..."
docker compose up -d

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
