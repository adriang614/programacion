import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    // ----------------------------------------------------------
    // PRECARGA DE OBJETOS
    // ----------------------------------------------------------
    public static void cargarDatosIniciales() {

        // Crear direcciones
        Direccion d1 = new Direccion("Av. Salud", 12, 28080, "Jerez de la frontera", "Cadiz");
        Direccion d2 = new Direccion("C/ Hospital", 44, 41001, "Sevilla", "Sevilla");

        // Crear hospitales
        Hospital h1 = new Hospital("Hospital Central", "A12345678", d1, new ArrayList<>());
        Hospital h2 = new Hospital("Hospital del Sur", "B87654321", d2, new ArrayList<>());

        hospitales.add(h1);
        hospitales.add(h2);

        // Crear áreas
        Area a1 = new Area("Cardiología", "1a", 1, h1);
        Area a2 = new Area("Pediatría",   "2a",  2, h1);
        Area a3 = new Area("Urgencias",   "3a", 3, h2);

        areas.add(a1);
        areas.add(a2);
        areas.add(a3);

        // Asignar áreas a hospitales
        h1.getAreas().add(a1);
        h1.getAreas().add(a2);
        h2.getAreas().add(a3);

        // Crear médicos
        Medico m1 = new Medico("89865435D", "Ana Torres", "F", 3000, 2018, a1);
        Medico m2 = new Medico("20975472G", "Luis Pérez", "M", 2800, 2020, a2);

        medicos.add(m1);
        medicos.add(m2);

        // Crear contratos
        Contrato c1 = new Contrato(2018, m1, h1);
        Contrato c2 = new Contrato(2020, m2, h1);

        contratos.add(c1);
        contratos.add(c2);

        // Ajustar número de médicos en las áreas
        a1.sumaMedicos();
        a2.sumaMedicos();

    }

    // CARGA DE DATOS
    public static ArrayList<Hospital> hospitales = new ArrayList<>();
    public static ArrayList<Area> areas = new ArrayList<>();
    public static ArrayList<Medico> medicos = new ArrayList<>();
    public static ArrayList<Contrato> contratos = new ArrayList<>();

    public static void main(String[] args) {

        cargarDatosIniciales();
        ejecutarMenuPrincipal();

    }

    // ----------------------------------------------------------
    // MENÚ PRINCIPAL
    // ----------------------------------------------------------
    public static void ejecutarMenuPrincipal() {

        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("====== MENU PRINCIPAL ======");
            System.out.println("1. Crear Hospital");
            System.out.println("2. Crear Área");
            System.out.println("3. Crear Médico");
            System.out.println("4. Modificar Médico");
            System.out.println("5. Modificar Hospital");
            System.out.println("6. Calcular Antigüedad");
            System.out.println("7. Calcular Sueldo Neto");
            System.out.println("8. Comprobar Edad");
            System.out.println("9. Proporción de Médicos en un Área");
            System.out.println("10. Capacidad Restante del Área");
            System.out.println("11. Comparar Áreas");
            System.out.println("12. Ver Contratos por Año");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");

            opcion = sc.nextInt();

            // llamar al gestor
            MenuGestor.ejecutar(opcion);

        } while (opcion != 0);

        System.out.println("Fin del programa.");
    }
}
