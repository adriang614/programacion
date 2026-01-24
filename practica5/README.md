# Práctica 5 – Java POO II: RPG Manager (MVP)

## 🔹 Descripción
Aplicación Java en consola que permite **crear personajes**, **asignar razas y clases**, y **simular combates por turnos** siguiendo el patrón **Modelo–Vista–Presentador (MVP)**.  
Incluye equilibrio de atributos, habilidades y vida según las tablas de la práctica.

---

## 🔹 Arquitectura del Proyecto
- **com.juego.modelo** → Personaje, Combate, GestorPersonaje
- **com.juego.clases** → Clases de combate y bonificadores
- **com.juego.razas** → Razas y atributos base
- **com.juego.habilidades** → Habilidades (Daño CC, Daño LD, Curación)
- **com.juego.presentacion** → Vista (consola) y Presentador

---

## 🔹 Funcionamiento del Sistema

### ✔ Creación de Personajes
- El usuario introduce nombre, raza y clase
- Se aplican:
    - Atributos base de la raza
    - Bonificadores de la clase
    - Vida máxima según tabla
    - 3 habilidades obligatorias:
        - Daño CC (5 usos)
        - Curación CC (3 usos)
        - Daño LD (1 uso)

---

### ✔ Combate por Turnos
- Se eligen dos personajes
- Cada turno:
    - Se muestra el estado del combate
    - Cada jugador elige una habilidad
    - Se aplica daño o curación
- El combate termina cuando uno llega a 0 de vida

---

## 🔹 Reglas de Equilibrio
- **Clases:** bonificadores y vida máxima según Tabla A
- **Habilidades:** valores y usos según Tabla B
- **Razas:** atributos base según Tabla C  

---
