# 🏦 Práctica subida de nota – Iteradores y BBDD: SecureBank Core Engine

## 🔹 Objetivo

Desarrollar un sistema en Java para un motor bancario en consola con **JDBC, PostgreSQL, iteradores, concurrencia, logs y patrón Command**.

El programa debe gestionar clientes, cuentas, transacciones, tarjetas y préstamos, manteniendo sincronizada la memoria con la base de datos [file:1].

---

## 📦 Estructura del Proyecto

```text
es.securebank.core
├── config
├── model
├── exception
├── dao
├── service
├── cache
├── concurrency
├── ui
│   └── commands
└── util
```

---

## 🔹 Entidades

- **Cliente**.
- **CuentaBancaria**.
- **Transaccion**.
- **TarjetaCredito**.
- **Prestamo**.

---

## 🗄️ Base de Datos

Tablas obligatorias [file:1]:

- `clientes`
- `cuentas_bancarias`
- `transacciones`
- `tarjetas_credito`
- `prestamos`

También se pide una vista `v_resumen_cliente`, triggers, índices y uso de secuencias en lugar de `SERIAL` [file:1].

---

## ⚙️ Funcionalidades

- Carga inicial de cuentas activas en caché.
- Operaciones transaccionales con rollback.
- Proceso batch concurrente para cobrar comisiones.
- Sistema antifraude con ventana temporal de 2 minutos.
- Consola extensible con patrón Command [file:1].

---

## 🔹 Comandos

- `ayuda`
- `salir`
- `login <dni> <pin>`
- `logout`
- `ver_cuentas`
- `ver_saldo <iban>`
- `historial <iban> [limite]`
- `ingresar <iban> <importe>`
- `retirar <iban> <importe>`
- `transferir <origen> <destino> <importe>`
- `cobrar_comisiones`
- `estado_sistema` [file:1]

---

## 🧠 Excepciones

- `TransactionFailedException`
- `FraudeConcurrentException`
- `CuentaNoEncontradaException`
- `SaldoInsuficienteException`
- `CuentaBloqueadaException`
- `CommandException`
- `CommandArgumentException` [file:1]

---

## 📁 Archivos principales

- `docker-compose.yml`
- `pom.xml`
- `README.md`
- `sql/init/01_schema.sql`
- `sql/init/02_triggers.sql`
- `sql/init/03_views.sql`
- `sql/init/04_data.sql` [file:1]

---

## 🛠️ Requisitos

- Java 17+.
- PostgreSQL 15.
- Docker.
- Maven.
- JDBC obligatorio.
- Uso de `Iterator` obligatorio [file:1].