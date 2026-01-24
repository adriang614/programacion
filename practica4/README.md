# Práctica 4 – Java II: Sistema de Gestión Hospitalaria

---

## 🔹 Sistema de Gestión Hospitalaria

### Objetivo
Desarrollar una aplicación Java para gestionar hospitales, áreas, médicos y contratos sin usar herencia ni interfaces. Se deben implementar funciones de cálculo, comprobación y gestión de datos, incluyendo un menú de usuario para interactuar con la aplicación.

---

## 🔹 Clases y Modelado

### 1. Clase `Direccion`
- **Atributos:** Calle, Número, Código Postal, Localidad, Provincia.
- **Funcionalidad:** Almacena la dirección de hospitales y médicos.

### 2. Clase `Hospital`
- **Atributos:** Nombre, CIF, lista de Áreas.
- **Métodos principales:**
    - `getNumeroTotalMedicos()`: Suma de médicos de todas las áreas.
    - `getProporcionMedicosArea(String idArea)`: Proporción de médicos de un área respecto al total.
    - `existeArea(String idArea)`: Comprueba si un área con ese ID existe.

### 3. Clase `Area`
- **Atributos:** Nombre, Identificador, Planta, referencia a Hospital, contador `numMedicos`.
- **Métodos principales:**
    - `compararMedicos(Area otraArea)`: Compara el número de médicos con otra área.
    - `calcularCapacidadRestante(int capacidadMaxima)`: Calcula cuántos médicos más puede recibir.

### 4. Clase `Medico`
- **Atributos:** DNI, Nombre, Edad, Sexo, Sueldo Bruto, Fecha de Inicio, referencia a Área.
- **Regla automática:** Al crearse un médico, incrementa `numMedicos` del área asociada.
- **Métodos principales:**
    - `calcularSueldoNeto(double retencion)`
    - `getAniosAntiguedad()`
    - `calcularImpuestosAnuales(double tasaImpositiva)`
    - `esMayorDeEdad(int mayoriaEdad)`
    - `proximoAumento(double porcentajeAumento, int aniosRequeridos)`
    - `cambiarArea(Area nuevaArea)`: Maneja la transferencia entre áreas.

### 5. Clase `Contrato`
- **Atributos:** Fecha de Creación, referencia al Médico y al Hospital.
- **Métodos principales:**
    - `esDeAnio(int anio)`
    - `diasDesdeCreacion()`

---

## 🔹 Clases de Ejecución

### 1. Clase `Main`
- Precarga de datos iniciales (`cargarDatosIniciales()`).
- Ejecución del menú principal (`ejecutarMenuPrincipal()`).

### 2. Clase `CargaDatos`
- Contiene listas estáticas (`ArrayList`) para Hospitales, Áreas, Médicos y Contratos.
- Métodos de búsqueda: `buscarMedico(dni)`, `buscarArea(id)`, etc.

### 3. Clase `MenuGestor`
- Menú interactivo para el usuario con las siguientes secciones:

---

### 🔹 Sección I: Creación
1. **Crear Hospital:** Solicita datos y Dirección.
2. **Crear Área:** Solicita datos y la asocia a un Hospital existente.
3. **Crear Médico:** Solicita datos, asocia a un Área y registra automáticamente el Contrato.

---

### 🔹 Sección II: Modificación
4. **Modificar Médico:** Permite cambiar Sueldo Bruto, Dirección o Área de trabajo.
5. **Modificar Hospital:** Permite cambiar Nombre o Dirección.

---

### 🔹 Sección III: Cálculos
6. **Calcular Antigüedad:** Muestra `getAniosAntiguedad()` del médico.
7. **Calcular Sueldo Neto:** Solicita % de retención y muestra `calcularSueldoNeto()`.
8. **Comprobar Edad:** Comprueba si un médico cumple la mayoría de edad.
9. **Proporción de Médicos:** Muestra `getProporcionMedicosArea()` para un área específica.
10. **Capacidad de Área:** Muestra `calcularCapacidadRestante()` dado un límite máximo.
11. **Comparar Áreas:** Compara dos áreas usando `compararMedicos()`.
12. **Contratos por Año:** Muestra todos los contratos de un año determinado.

---