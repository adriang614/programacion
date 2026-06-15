#!/bin/bash
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
PROJECT_DIR="$(dirname "$SCRIPT_DIR")"

cd "$PROJECT_DIR"

echo "⚠ ATENCIÓN: Esto eliminará TODOS los datos de la base de datos"
read -p "¿Continuar? (s/N): " confirm

if [[ "$confirm" =~ ^[Ss]$ ]]; then
    echo "Eliminando contenedores y volúmenes..."
    docker compose down -v --remove-orphans
    echo "Reiniciando..."
    ./scripts/deploy.sh
else
    echo "Operación cancelada"
fi
