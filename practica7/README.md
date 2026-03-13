# Práctica 7 – Ficheros y Excepciones: RPG Data Engine

---

## 🔹 Objetivo

Desarrollar un sistema en **Java** que gestione datos de un mundo RPG utilizando **ficheros JSON y TXT**, aplicando **excepciones personalizadas**, **logs de errores** y **colecciones de Java**.

El programa carga información de **personajes, ciudades e ítems**, permite crear nuevos personajes y guarda los cambios en archivos.

---

# 📦 Estructura de Paquetes

```
com.rpg
│
├── utils
│   ├── JsonHelper
│   ├── TxtHelper
│   └── LoggerCustom
│
├── model
│   ├── Ciudad
│   ├── Item
│   └── Personaje
│
├── handler
│   ├── RPGDataException
│   ├── FormatoInvalidoException
│   ├── DatoInvalidoException
│   └── RecursoNoEncontradoException
│
├── services
│   └── GestionMundo
│
└── main
    └── Main
```

---

# 🔹 Entidades del Sistema

- **Ciudad** → nombre, población, clima, nivel de riesgo
- **Item** → id, nombre, tipo, valor
- **Personaje** → nombre, raza, nivel, lista de ítems equipados

---

# 📂 Archivos de Datos

### personajes.json
```json
[
  {
    "nombre": "Aragorn",
    "raza": "Humano",
    "nivel": 20,
    "equipoIds": ["E01"]
  }
]
```

### ciudades.txt
```
Minas Tirith;20000;Soleado;3
Isengard;5000;Nublado;8
```

### items.json
```json
[
  { "id": "W01", "nombre": "Hacha Doble", "tipo": "ARMA", "valor": 100 },
  { "id": "P01", "nombre": "Pocion", "tipo": "CONSUMIBLE", "valor": 25 }
]
```

---

# 🔹 Flujo del Programa

1. **Carga inicial** de archivos (`ciudades.txt`, `items.json`, `personajes.json`).
2. **Validación y vinculación** de ítems con personajes.
3. **Creación de nuevos personajes** por el usuario.
4. **Guardado de cambios** en `personajes.json`.

---

# 🔹 Manejo de Errores

- Uso de **excepciones personalizadas**.
- Registro de errores en:

```
errores.log
```

Ejemplo de log:

```
[2026-02-20 10:30:15] ERROR: DatoInvalidoException en Personaje 'Legolas': Nivel -1 no permitido.
```

---

# 🔹 Requisitos

- El programa **no debe detenerse si hay errores en los archivos**.
- Validar que los **ítems equipados existan en el catálogo**.
- Usar **Map<String, Item>** para búsquedas eficientes.