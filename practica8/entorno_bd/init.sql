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
