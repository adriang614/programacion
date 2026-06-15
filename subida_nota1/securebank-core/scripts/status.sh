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
docker compose ps

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
