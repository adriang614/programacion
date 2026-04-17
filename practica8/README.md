# ⚔️ Práctica 8 – Iteradores y BBDD: XRPG 

## 🔹 Objetivo

Desarrollar un sistema en Java que gestione un mundo RPG utilizando **JDBC y PostgreSQL**, aplicando persistencia en base de datos, uso de **iteradores**, excepciones personalizadas, logs y colecciones tradicionales.

El programa permite crear personajes, viajar entre ciudades, comprar ítems, combatir por turnos y generar estadísticas, guardando todos los cambios directamente en la base de datos.

---

## 📦 Estructura de Paquetes

```text
rpg
│
├── dao
│   ├── PersonajeDAO
│   ├── CiudadDAO
│   ├── ItemDAO
│   └── ConnectionDB
│
├── exception
│   ├── NivelInsuficienteException
│   ├── FondosInsuficientesException
│   └── LimiteHabilidadesException
│
├── model
│   ├── Personaje
│   ├── Ciudad
│   ├── Item
│   └── Habilidad
│
├── logic
│   ├── MotorCombate
│   └── GestionMundo
│
├── utils
│   └── Log
│
└── main
    └── Main
```

---

## 🔹 Entidades del Sistema

- **Personaje** → nombre, raza, clase, nivel, vida, oro, ciudad actual
- **Ciudad** → nombre, nivel mínimo de acceso
- **Item** → nombre, bonificador de ataque y defensa
- **Habilidad** → nombre, daño base, usos máximos

---

## 🗄️ Base de Datos

Tablas principales:

- `personajes`
- `ciudades`
- `items`
- `inventario`
- `habilidades`

Relaciones mediante claves foráneas para mantener la integridad de los datos.

---

## 🔹 Funcionalidades

- Creación de personajes (`INSERT`)
- Viaje entre ciudades (`UPDATE`)
- Compra de ítems
- Gestión de inventario
- Cobro de impuestos usando `Iterator`
- Combate PvP por turnos
- Equipamiento de habilidades
- Top 3 jugadores más ricos
- Censo de clases con `HashMap`

---

## ⚔️ Sistema de Combate

El combate se realiza por turnos entre dos personajes cargados desde la BBDD.

Incluye:

- ataque básico
- habilidades especiales
- defensa por equipamiento
- límite de 3 habilidades equipadas
- robo del 20% del oro al finalizar

**Cálculo de daño**

```text
daño = ataque - (defensa / 2)
```

---

## 🔹 Flujo del Programa

1. Conexión a PostgreSQL mediante JDBC
2. Carga de personajes y datos relacionados
3. Ejecución de acciones del menú
4. Persistencia automática en base de datos
5. Registro de eventos en log

---

## 🔹 Manejo de Errores

Uso de excepciones personalizadas:

- `NivelInsuficienteException`
- `FondosInsuficientesException`
- `LimiteHabilidadesException`

Registro de errores y eventos en:

```text
info.log
```

Ejemplo:

```text
[2026-04-17 18:20:10] ERROR: FondosInsuficientesException al comprar item
```

---

## 🔹 Requisitos

- Java 17+
- PostgreSQL 14+
- Driver JDBC añadido al classpath
- Docker para entorno de base de datos
- Uso obligatorio de `Iterator` y colecciones tradicionales