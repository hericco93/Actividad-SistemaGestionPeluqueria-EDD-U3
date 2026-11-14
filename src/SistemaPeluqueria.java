import java.util.Scanner;

public class SistemaPeluqueria {
    private static Lista listaGeneral = new Lista();
    private static Cola colaPendientes = new Cola();
    private static Pila historialProcesados = new Pila();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion = 0;

        do {
            mostrarMenu();
            try {
                System.out.print("Seleccione una opción: ");
                opcion = Integer.parseInt(scanner.nextLine().trim());

                switch (opcion) {
                    case 1 -> registrarCliente();
                    case 2 -> verTodosRegistrados();
                    case 3 -> verPendientes();
                    case 4 -> procesarSiguiente();
                    case 5 -> verHistorial();
                    case 6 -> buscarPorTurno();
                    case 7 -> cancelarPendiente();
                    case 8 -> deshacerUltimoProcesamiento();
                    case 9 -> verCantidadElementos();
                    case 10 -> System.out.println("Saliendo del sistema de gestión de peluquería. ¡Hasta pronto!");
                    default -> System.out.println("Opción no válida. Ingrese un número entre 1 y 10.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número entero válido.");
            } catch (Exception e) {
                System.out.println("Ocurrió un error inesperado: " + e.getMessage());
            }
        } while (opcion != 10);
    }

    private static void mostrarMenu() {
        System.out.println("\n==============================================");
        System.out.println("   SISTEMA DE GESTIÓN - PELUQUERÍA (EDD)");
        System.out.println("==============================================");
        System.out.println("1. Registrar cliente/servicio");
        System.out.println("2. Ver todos los elementos registrados (Lista)");
        System.out.println("3. Ver elementos pendientes (Cola FIFO)");
        System.out.println("4. Procesar siguiente cliente");
        System.out.println("5. Ver historial de atendidos (Pila LIFO)");
        System.out.println("6. Buscar cliente por número de turno");
        System.out.println("7. Cancelar turno pendiente");
        System.out.println("8. Deshacer último procesamiento");
        System.out.println("9. Ver cantidad de elementos");
        System.out.println("10. Salir");
        System.out.println("==============================================");
    }

    // 1. Registrar elemento
    private static void registrarCliente() {
        System.out.println("\n--- REGISTRO DE NUEVO TURNO ---");
        System.out.print("Número de turno (ej. T-01): ");
        String turno = scanner.nextLine().trim().toUpperCase();

        if (turno.isEmpty()) {
            System.out.println("Error: El número de turno no puede estar vacío.");
            return;
        }

        if (listaGeneral.contiene(new ClienteServicio(turno))) {
            System.out.println("Error: Ya existe un registro con el turno " + turno + ".");
            return;
        }

        System.out.print("Nombre del cliente: ");
        String nombre = scanner.nextLine().trim();
        System.out.print("Tipo de servicio (Corte, Barba, Tintura, Cepillado): ");
        String servicio = scanner.nextLine().trim();
        System.out.print("Estilista asignado: ");
        String estilista = scanner.nextLine().trim();

        if (nombre.isEmpty() || servicio.isEmpty() || estilista.isEmpty()) {
            System.out.println("Error: Todos los campos son obligatorios.");
            return;
        }

        ClienteServicio nuevoCliente = new ClienteServicio(turno, nombre, servicio, estilista, "En espera");

        listaGeneral.agregar(nuevoCliente);
        colaPendientes.encolar(nuevoCliente);

        System.out.println("Turno registrado exitosamente y agregado a la cola de espera.");
    }

    // 2. Ver todos los registrados
    private static void verTodosRegistrados() {
        System.out.println("\n--- REGISTRO HISTÓRICO TOTAL (LISTA) ---");
        listaGeneral.mostrarAdelante();
    }

    // 3. Ver pendientes
    private static void verPendientes() {
        System.out.println("\n--- TURNOS PENDIENTES POR ATENDER (COLA FIFO) ---");
        colaPendientes.mostrar();
    }

    // 4. Procesar siguiente
    private static void procesarSiguiente() {
        System.out.println("\n--- ATENDER SIGUIENTE TURNO ---");
        if (colaPendientes.esVacia()) {
            System.out.println("No hay clientes pendientes en la cola.");
            return;
        }

        ClienteServicio procesado = (ClienteServicio) colaPendientes.desencolar();
        procesado.setEstado("Atendido");
        historialProcesados.apilar(procesado);

        System.out.println("Cliente atendido exitosamente: " + procesado);
    }

    // 5. Ver historial
    private static void verHistorial() {
        System.out.println("\n--- HISTORIAL DE SERVICIOS ATENDIDOS (PILA LIFO) ---");
        historialProcesados.mostrar();
    }

    // 6. Buscar por número de turno
    private static void buscarPorTurno() {
        System.out.println("\n--- BUSCAR TURNO EN EL SISTEMA ---");
        System.out.print("Ingrese el número de turno a buscar: ");
        String turno = scanner.nextLine().trim().toUpperCase();

        ClienteServicio encontrado = (ClienteServicio) listaGeneral.buscarDato(new ClienteServicio(turno));
        if (encontrado != null) {
            System.out.println("Registro encontrado: " + encontrado);
        } else {
            System.out.println("No se encontró ningún cliente con el turno " + turno + ".");
        }
    }

    // 7. Cancelar turno pendiente usando una Cola Auxiliar (sin romper FIFO)
    private static void cancelarPendiente() {
        System.out.println("\n--- CANCELAR TURNO PENDIENTE ---");
        if (colaPendientes.esVacia()) {
            System.out.println("No hay turnos pendientes para cancelar.");
            return;
        }

        System.out.print("Ingrese el número de turno pendiente a cancelar: ");
        String turno = scanner.nextLine().trim().toUpperCase();

        Cola colaAuxiliar = new Cola();
        boolean encontrado = false;

        while (!colaPendientes.esVacia()) {
            ClienteServicio actual = (ClienteServicio) colaPendientes.desencolar();
            if (actual.getNumeroTurno().equalsIgnoreCase(turno)) {
                actual.setEstado("Cancelado");
                encontrado = true;
                System.out.println("Turno cancelado exitosamente: " + actual);
            } else {
                colaAuxiliar.encolar(actual);
            }
        }

        // Restaurar los elementos restantes manteniendo el orden de llegada original
        while (!colaAuxiliar.esVacia()) {
            colaPendientes.encolar(colaAuxiliar.desencolar());
        }

        if (!encontrado) {
            System.out.println("El turno " + turno + " no se encuentra en la cola de pendientes (puede que ya haya sido atendido o no exista).");
        }
    }

    // 8. Deshacer último procesamiento (LIFO -> FIFO)
    private static void deshacerUltimoProcesamiento() {
        System.out.println("\n--- DESHACER ÚLTIMO PROCESAMIENTO ---");
        if (historialProcesados.esVacia()) {
            System.out.println("No hay atenciones recientes en el historial para deshacer.");
            return;
        }

        // Regla obligatoria de deshacer[cite: 8]
        ClienteServicio ultimo = (ClienteServicio) historialProcesados.desapilar();
        ultimo.setEstado("En espera (Reingresado)");
        colaPendientes.encolar(ultimo);

        System.out.println("Se deshizo la atención del turno. El cliente volvió a la cola de espera: " + ultimo);
    }

    // 9. Reporte de cantidades
    private static void verCantidadElementos() {
        System.out.println("\n--- REPORTE DE CANTIDADES ---");
        System.out.println("Total registrados en el sistema (Lista): " + listaGeneral.cuentaElementos());
        System.out.println("Clientes pendientes en espera (Cola):   " + colaPendientes.tamanio());
        System.out.println("Clientes ya atendidos (Pila):           " + historialProcesados.tamanio());
    }
}