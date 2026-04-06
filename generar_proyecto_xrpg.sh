#!/bin/bash

# ==============================================================================
# Script generador COMPLETO: Entorno Docker (XRPG) + Java + Autodescarga JDBC
# ==============================================================================

BASE_DIR="practica8"

echo "🚀 Iniciando la creación del proyecto completo en: $BASE_DIR"

# 1. Crear la estructura de directorios Java
echo "📁 Creando árbol de carpetas..."
mkdir -p "$BASE_DIR/lib"
mkdir -p "$BASE_DIR/src/rpg/dao"
mkdir -p "$BASE_DIR/src/rpg/exception"
mkdir -p "$BASE_DIR/src/rpg/logic"
mkdir -p "$BASE_DIR/src/rpg/model"
mkdir -p "$BASE_DIR/src/rpg/ui"
mkdir -p "$BASE_DIR/src/rpg/utils"
mkdir -p "$BASE_DIR/entorno_bd"

# 2. Descargar el driver JDBC de PostgreSQL automáticamente
echo "⬇️ Descargando el driver JDBC de PostgreSQL..."
curl -s -L -o "$BASE_DIR/lib/postgresql-42.7.1.jar" https://jdbc.postgresql.org/download/postgresql-42.7.1.jar
echo "✅ Driver descargado en $BASE_DIR/lib/postgresql-42.7.1.jar"

# 3. Generar archivo docker-compose.yml
echo "🐳 Generando docker-compose.yml..."
cat << 'EOF' > "$BASE_DIR/entorno_bd/docker-compose.yml"
version: '3.8'

services:
  db:
    image: postgres:15-alpine
    container_name: xrpg_postgres
    restart: always
    environment:
      POSTGRES_DB: XRPG
      POSTGRES_USER: xrpg_user
      POSTGRES_PASSWORD: xrpg_password
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data
      - ./init.sql:/docker-entrypoint-initdb.d/init.sql

volumes:
  postgres_data:
EOF

# 4. Generar archivo init.sql con inyección automática
echo "📜 Generando script SQL para PostgreSQL..."
cat << 'EOF' > "$BASE_DIR/entorno_bd/init.sql"
-- ==============================================================================
-- Práctica 08: XRPG Guild Master - Script para PostgreSQL
-- ==============================================================================

-- 1. CREACIÓN DE TABLAS (Catálogos y Maestros)
CREATE TABLE Razas (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    bonificador_vida INT DEFAULT 0,
    bonificador_fuerza INT DEFAULT 0
);

CREATE TABLE Clases_RPG (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
);

CREATE TABLE Habilidades (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    dano_base INT NOT NULL,
    usos_maximos INT NOT NULL,
    id_clase INT,
    FOREIGN KEY (id_clase) REFERENCES Clases_RPG(id) ON DELETE CASCADE
);

CREATE TABLE Ciudades (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    nivel_minimo_acceso INT DEFAULT 1
);

CREATE TABLE Items (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    precio_oro INT NOT NULL,
    bonificador_ataque INT DEFAULT 0,
    bonificador_defensa INT DEFAULT 0
);

-- 2. CREACIÓN DE TABLAS (Transaccionales y Jugadores)
CREATE TABLE Personajes (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE,
    nivel INT DEFAULT 1,
    oro INT DEFAULT 100,
    vida_actual INT DEFAULT 100,
    id_raza INT,
    id_clase INT,
    id_ciudad_actual INT,
    FOREIGN KEY (id_raza) REFERENCES Razas(id) ON DELETE RESTRICT,
    FOREIGN KEY (id_clase) REFERENCES Clases_RPG(id) ON DELETE RESTRICT,
    FOREIGN KEY (id_ciudad_actual) REFERENCES Ciudades(id) ON DELETE SET NULL
);

CREATE TABLE Inventarios (
    id_personaje INT,
    id_item INT,
    cantidad INT DEFAULT 1,
    PRIMARY KEY (id_personaje, id_item),
    FOREIGN KEY (id_personaje) REFERENCES Personajes(id) ON DELETE CASCADE,
    FOREIGN KEY (id_item) REFERENCES Items(id) ON DELETE CASCADE
);

CREATE TABLE Personajes_Habilidades (
    id_personaje INT,
    id_habilidad INT,
    equipada_combate BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (id_personaje, id_habilidad),
    FOREIGN KEY (id_personaje) REFERENCES Personajes(id) ON DELETE CASCADE,
    FOREIGN KEY (id_habilidad) REFERENCES Habilidades(id) ON DELETE CASCADE
);

-- 3. POBLACIÓN DE DATOS INICIALES (INSERTS)
INSERT INTO Razas (nombre, bonificador_vida, bonificador_fuerza) VALUES
('Humano', 10, 5), ('Orco', 25, 10), ('Elfo', -5, 2), ('Enano', 20, 8);

INSERT INTO Clases_RPG (nombre) VALUES
('Guerrero'), ('Mago'), ('Picaro'), ('Paladin');

INSERT INTO Habilidades (nombre, dano_base, usos_maximos, id_clase) VALUES
('Golpe Rompehuesos', 35, 3, 1), ('Tajo Giratorio', 25, 5, 1), ('Grito de Guerra', 15, 10, 1),
('Bola de Fuego', 45, 2, 2), ('Dardo de Hielo', 20, 8, 2), ('Relampago', 35, 4, 2),
('Punñalada Trapera', 40, 3, 3), ('Lanzar Cuchillo', 15, 10, 3), ('Ataque Sombra', 30, 4, 3),
('Castigo Divino', 30, 5, 4), ('Golpe de Escudo', 20, 6, 4), ('Luz Cegadora', 25, 4, 4);

INSERT INTO Ciudades (nombre, nivel_minimo_acceso) VALUES
('Villa Inicio', 1), ('Baluarte de Piedra', 5), ('Capital de Cristal', 10), ('Abismo del Dragon', 20);

INSERT INTO Items (nombre, tipo, precio_oro, bonificador_ataque, bonificador_defensa) VALUES
('Espada de Madera', 'Arma', 50, 5, 0), ('Espada de Hierro', 'Arma', 200, 15, 0),
('Baston de Aprendiz', 'Arma', 150, 12, 0), ('Dagas Gemelas', 'Arma', 250, 18, 0),
('Armadura de Cuero', 'Armadura', 100, 0, 10), ('Malla de Acero', 'Armadura', 400, 0, 30),
('Anillo de Fuerza', 'Accesorio', 500, 5, 5);

-- DATOS DE PRUEBA
INSERT INTO Personajes (nombre, nivel, oro, vida_actual, id_raza, id_clase, id_ciudad_actual) VALUES
('Grog el Destructor', 3, 150, 120, 2, 1, 1),
('Eldara Sabia', 5, 800, 80, 3, 2, 2);

INSERT INTO Inventarios (id_personaje, id_item, cantidad) VALUES
(1, 2, 1), (1, 5, 1), (2, 3, 1), (2, 7, 1);

INSERT INTO Personajes_Habilidades (id_personaje, id_habilidad, equipada_combate) VALUES
(1, 1, TRUE), (1, 2, TRUE), (1, 3, FALSE),
(2, 4, TRUE), (2, 5, TRUE), (2, 6, TRUE);
EOF

echo "✅ ¡Proyecto completo generado con éxito!"
echo "--------------------------------------------------------"
echo "👉 1. Entra en el proyecto: cd $BASE_DIR"
echo "👉 2. El driver ya está descargado en la carpeta 'lib/'"
echo "👉 3. Levanta la Base de Datos 'XRPG' ejecutando:"
echo "      cd entorno_bd && docker-compose up -d"
echo "👉 4. Añade el .jar de la carpeta 'lib' al Build Path de tu IDE."
echo "--------------------------------------------------------"