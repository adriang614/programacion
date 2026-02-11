# Práctica 6 – Estructuras de Datos: El Motor de la Ciudadela

---

## 🔹 El Motor de la Ciudadela

### Objetivo
Desarrollar una serie de ejercicios en Java que simulan la gestión lógica de una gran Ciudadela de fantasía. Para ello se utilizan colecciones como **ArrayList**, **HashSet** y **HashMap**, aplicadas a sistemas de combate, censos, gremios, estadísticas, comercio, raids y más.

La práctica se divide en tres bloques: operaciones básicas, lógica anidada y desafíos avanzados.

---

## 🔹 Bloque 1: Operaciones Básicas

### 1. Registro de Muertes (ArrayList)
- Creación de `ArrayList<String>` llamado `registroCombate`.
- Inserción de 5 eventos.
- Impresión del **tercer evento** registrado.

### 2. Censo Único (HashSet)
- Creación de `HashSet<String>` para villanos.
- Intento de añadir dos veces a *Morgoth*.
- Impresión del tamaño del set para demostrar que no hay duplicados.

### 3. Bolsa de Oro (HashMap)
- Creación de `HashMap<String, Integer>` con héroes y su oro.
- Inserción de 3 aventureros.
- Consulta del oro de uno de ellos mediante su nombre.

### 4. Limpieza del Calabozo
- Eliminación del evento más antiguo del `ArrayList`.
- Añadir un nuevo evento: `"Dragón avistado"`.

### 5. Mercado de Hechizos
- Creación de `HashMap<String, Double>` con hechizos y su coste de maná.
- Impresión de los hechizos cuyo coste supera 50.

### 6. Expulsión del Reino
- Comprobación de si `"Sauron"` está en el HashSet.
- Eliminación en caso afirmativo.

---

## 🔹 Bloque 2: Desafíos de Lógica Anidada

### 7. Repositorio de Gremios
- `HashMap<String, ArrayList<String>>` donde cada gremio tiene una lista de miembros.
- Creación de 2 gremios con 3 miembros cada uno.
- Función para imprimir los miembros de un gremio concreto.

### 8. Sistema de Loot (Botín)
- `HashMap<String, HashSet<String>>` donde cada monstruo tiene un set de objetos.
- El reto consiste en evitar duplicados automáticamente (p. ej., *Espada Oxidada* del Trasgo).

### 9. Rastreador de Estadísticas
- `HashMap<String, HashMap<String, Integer>>` para jugadores y sus atributos.
- Reto: localizar a **Conan** y sumarle +2 a su estadística de **Fuerza**.

### 10. Buscador de Traidores
- Recorrido de todos los gremios buscando a `"Judas"`.
- Si aparece, se indica en qué gremio está y se elimina de inmediato.

---

## 🔹 Bloque 3: Desafíos Avanzados

### 11. Sistema de Comercio y Precios Dinámicos
- `HashMap<String, Double>` con precios base de items.
- `HashMap<String, ArrayList<String>>` con ciudades y su stock.
- Si una ciudad tiene más de 5 items, se aplica un **impuesto de lujo del 10%** a los precios durante la transacción.

### 12. Árboles de Habilidades (Nested Maps)
- `HashMap<String, HashMap<String, Boolean>>` para clases y habilidades.
- Reto: al intentar desbloquear **Golpe Divino**, comprobar si **Enfoque** está desbloqueada.
- Si no lo está, se muestra un mensaje de error.

### 13. Historial de Incursiones (Raid Tracker)
- `HashMap<String, List<HashSet<String>>>` donde cada mazmorra tiene una lista de raids.
- Cada raid es un `HashSet` de jugadores (sin duplicados).
- Reto: encontrar al **Jugador Más Valioso**, es decir, el que más veces aparece en todas las incursiones.

### 14. Sistema de Mensajería Global (Filtro de Spam)
- `HashMap<String, List<String>>` con los mensajes enviados por cada jugador.
- Si un jugador repite 2 mensajes iguales entre sus últimos 3, se añade a `jugadoresSilenciados` y se limpia su historial.

### 15. Simulador de Economía de Subastas
- `HashMap<String, PriorityQueue<Double>>` o `ArrayList` ordenado para las pujas.
- Método `procesarVenta`:
    1. Tomar la puja más alta.
    2. Verificar si el postor tiene suficiente oro en un `HashMap<String, Double>`.
    3. Si puede pagar, se transfiere el oro y se elimina el item del mercado.
    4. Si no, se descarta esa puja y se pasa a la siguiente.

---
