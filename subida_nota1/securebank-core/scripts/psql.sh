#!/bin/bash
echo "Conectando a PostgreSQL..."
docker exec -it securebank_postgres psql -U securebank_admin -d securebank_core
