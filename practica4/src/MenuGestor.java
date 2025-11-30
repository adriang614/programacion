import java.util.Scanner;

public class MenuGestor {

    private static Scanner sc = new Scanner(System.in);

    // Ejecutar la opción seleccionada en el menú
    public static void ejecutar(int opcion) {

        switch (opcion) {

            case 1:
                crearHospital();
                break;

            case 2:
                crearArea();
                break;

            case 3:
                crearMedico();
                break;

            case 4:
                modificarMedico();
                break;

            case 5:
                modificarHospital();
                break;

            case 6:
                calcularAntiguedad();
                break;

            case 7:
                calcularSueldoNeto();
                break;

            case 8:
                comprobarEdad();
                break;

            case 9:
                proporcionMedicos();
                break;

            case 10:
                capacidadArea();
                break;

            case 11:
                compararAreas();
                break;

            case 12:
                contratosPorAnio();
                break;

            case 0:
                System.out.println("Saliendo del menú...");
                break;

            default:
                System.out.println("Opción no válida.");
        }
    }

    // ===================== OPCIONES =====================

    private static void crearHospital() {
        System.out.println("Crear Hospital:");

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("CIF: ");
        String cif = sc.nextLine();

        System.out.print("Calle: ");
        String calle = sc.nextLine();

        System.out.print("Número: ");
        int numero = sc.nextInt();

        System.out.print("Código Postal: ");
        int cp = sc.nextInt();
        sc.nextLine();

        System.out.print("Localidad: ");
        String localidad = sc.nextLine();

        System.out.print("Provincia: ");
        String provincia = sc.nextLine();

        Direccion d = new Direccion(calle, numero, cp, localidad, provincia);
        Hospital h = new Hospital(nombre, cif, d, new java.util.ArrayList<>());

        Main.hospitales.add(h);
        System.out.println("Hospital creado correctamente.");
    }

    private static void crearArea() {
        System.out.println("Crear Área:");

        System.out.print("Nombre del Área: ");
        String nombre = sc.nextLine();

        System.out.print("Identificador: ");
        String id = sc.nextLine();

        System.out.print("Planta: ");
        int planta = sc.nextInt();
        sc.nextLine();

        System.out.print("Hospital (nombre): ");
        String hospitalNombre = sc.nextLine();

        Hospital h = buscarHospitalPorNombre(hospitalNombre);
        if (h != null) {
            Area a = new Area(nombre, id, planta, h);
            Main.areas.add(a);
            h.getAreas().add(a);
            System.out.println("Área creada y asociada al hospital correctamente.");
        } else {
            System.out.println("Hospital no encontrado.");
        }
    }

    private static void crearMedico() {
        System.out.println("Crear Médico:");

        System.out.print("DNI: ");
        String dni = sc.nextLine();

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Sexo: ");
        String sexo = sc.nextLine();

        System.out.print("Sueldo bruto: ");
        int sueldo = sc.nextInt();

        System.out.print("Año inicio: ");
        int anioInicio = sc.nextInt();
        sc.nextLine(); // limpiar buffer

        System.out.print("Área (ID): ");
        String idArea = sc.nextLine();

        Area a = buscarAreaPorId(idArea);
        if (a != null) {
            Medico m = new Medico(dni, nombre, sexo, sueldo, anioInicio, a);
            Main.medicos.add(m);
            a.sumaMedicos();

            // Crear contrato
            Contrato c = new Contrato(anioInicio, m, a.getHospital());
            Main.contratos.add(c);

            System.out.println("Médico creado y contrato registrado.");
        } else {
            System.out.println("Área no encontrada.");
        }
    }

    private static void modificarMedico() {
        System.out.print("Introduzca DNI del médico a modificar: ");
        String dni = sc.nextLine();

        Medico m = buscarMedicoPorDni(dni);
        if (m != null) {
            System.out.println("1. Cambiar sueldo");
            System.out.println("2. Cambiar área");
            System.out.print("Opción: ");
            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    System.out.print("Nuevo sueldo: ");
                    int sueldo = sc.nextInt();
                    sc.nextLine();
                    m.setSueldoBruto(sueldo);
                    System.out.println("Sueldo actualizado.");
                    break;

                case 2:
                    System.out.print("Nuevo ID de área: ");
                    String idArea = sc.nextLine();
                    Area nuevaArea = buscarAreaPorId(idArea);
                    if (nuevaArea != null) {
                        m.cambiarArea(nuevaArea);
                        System.out.println("Área cambiada correctamente.");
                    } else {
                        System.out.println("Área no encontrada.");
                    }
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        } else {
            System.out.println("Médico no encontrado.");
        }
    }

    private static void modificarHospital() {
        System.out.print("Introduzca nombre del hospital a modificar: ");
        String nombre = sc.nextLine();

        Hospital h = buscarHospitalPorNombre(nombre);
        if (h != null) {
            System.out.println("1. Cambiar nombre");
            System.out.println("2. Cambiar dirección");
            System.out.print("Opción: ");
            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    System.out.print("Nuevo nombre: ");
                    String nuevoNombre = sc.nextLine();
                    h.setNombre(nuevoNombre);
                    System.out.println("Nombre actualizado.");
                    break;

                case 2:
                    System.out.print("Nueva calle: ");
                    String calle = sc.nextLine();
                    System.out.print("Número: ");
                    int num = sc.nextInt();
                    System.out.print("CP: ");
                    int cp = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Localidad: ");
                    String loc = sc.nextLine();
                    System.out.print("Provincia: ");
                    String prov = sc.nextLine();

                    Direccion d = new Direccion(calle, num, cp, loc, prov);
                    h.setD(d);
                    System.out.println("Dirección actualizada.");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        } else {
            System.out.println("Hospital no encontrado.");
        }
    }

    private static void calcularAntiguedad() {
        System.out.print("DNI del médico: ");
        String dni = sc.nextLine();

        Medico m = buscarMedicoPorDni(dni);
        if (m != null) {
            System.out.println("Años de antigüedad: " + m.getAniosAntiguedad());
        } else {
            System.out.println("Médico no encontrado.");
        }
    }

    private static void calcularSueldoNeto() {
        System.out.print("DNI del médico: ");
        String dni = sc.nextLine();

        Medico m = buscarMedicoPorDni(dni);
        if (m != null) {
            System.out.print("Porcentaje de retención (%): ");
            double ret = sc.nextDouble();
            sc.nextLine();
            System.out.println("Sueldo neto: " + m.calcularSueldo(ret));
        } else {
            System.out.println("Médico no encontrado.");
        }
    }

    private static void comprobarEdad() {
        System.out.print("DNI del médico: ");
        String dni = sc.nextLine();
        System.out.print("Edad mínima: ");
        int edadMin = sc.nextInt();
        sc.nextLine();

        Medico m = buscarMedicoPorDni(dni);
        if (m != null) {
            if (m.esMayorDeEdad(edadMin)) {
                System.out.println("El médico cumple la edad mínima.");
            } else {
                System.out.println("El médico NO cumple la edad mínima.");
            }
        } else {
            System.out.println("Médico no encontrado.");
        }
    }

    private static void proporcionMedicos() {
        System.out.print("Nombre del hospital: ");
        String nombreH = sc.nextLine();

        Hospital h = buscarHospitalPorNombre(nombreH);
        if (h != null) {
            System.out.print("ID del área: ");
            String idArea = sc.nextLine();
            double prop = h.getProporcionMedicosArea(idArea);
            System.out.println("Proporción de médicos en el área: " + prop);
        } else {
            System.out.println("Hospital no encontrado.");
        }
    }

    private static void capacidadArea() {
        System.out.print("ID del área: ");
        String idArea = sc.nextLine();
        Area a = buscarAreaPorId(idArea);
        if (a != null) {
            System.out.print("Capacidad máxima de médicos: ");
            int max = sc.nextInt();
            sc.nextLine();
            int restante = a.calcularCapacidadRestante(max);
            System.out.println("Número de médicos que puede recibir el área: " + restante);
        } else {
            System.out.println("Área no encontrada.");
        }
    }

    private static void compararAreas() {
        System.out.print("ID del primer área: ");
        String id1 = sc.nextLine();
        System.out.print("ID del segundo área: ");
        String id2 = sc.nextLine();

        Area a1 = buscarAreaPorId(id1);
        Area a2 = buscarAreaPorId(id2);

        if (a1 != null && a2 != null) {
            System.out.println(a1.compararMedicos(a2));
        } else {
            System.out.println("Alguna de las áreas no existe.");
        }
    }

    private static void contratosPorAnio() {
        System.out.print("Año: ");
        int anio = sc.nextInt();
        sc.nextLine();

        System.out.println("Contratos de ese año:");
        for (Contrato c : Main.contratos) {
            if (c.esDeAnio(anio)) {
                System.out.println("Médico: " + c.getMedico().getNombre() + ", Hospital: " + c.getHospital().getNombre());
            }
        }
    }

    // ===================== MÉTODOS DE BÚSQUEDA =====================

    private static Medico buscarMedicoPorDni(String dni) {
        for (Medico m : Main.medicos) {
            if (m.getDni().equals(dni)) {
                return m;
            }
        }
        return null;
    }

    private static Area buscarAreaPorId(String idArea) {
        for (Area a : Main.areas) {
            if (a.getIdentificador().equals(idArea)) {
                return a;
            }
        }
        return null;
    }

    private static Hospital buscarHospitalPorNombre(String nombre) {
        for (Hospital h : Main.hospitales) {
            if (h.getNombre().equals(nombre)) {
                return h;
            }
        }
        return null;
    }

}