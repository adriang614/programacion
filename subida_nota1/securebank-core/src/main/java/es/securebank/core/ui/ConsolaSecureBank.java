package es.securebank.core.ui;

import es.securebank.core.exception.CommandException;
import java.util.Arrays;
import java.util.Scanner;

public class ConsolaSecureBank {

    private final CommandRegistry registry;
    private final AppContext context;
    private boolean activa = true;

    public ConsolaSecureBank(CommandRegistry registry, AppContext context) {
        this.registry = registry;
        this.context = context;
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("===========================================");
        System.out.println("   SECUREBANK - Sistema Bancario v1.0");
        System.out.println("   Escribe 'ayuda' para ver los comandos");
        System.out.println("===========================================");

        while (activa) {
            System.out.print("\nsecurebank> ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) continue;

            String[] partes = input.split("\\s+");
            String nombreComando = partes[0];
            String[] argumentos = Arrays.copyOfRange(partes, 1, partes.length);

            registry.buscar(nombreComando).ifPresentOrElse(
                cmd -> ejecutarComando(cmd, argumentos),
                () -> System.out.println("[ERROR] Comando no reconocido: " + nombreComando + ". Escribe 'ayuda'.")
            );
        }
        scanner.close();
    }

    private void ejecutarComando(Command cmd, String[] args) {
        try {
            cmd.execute(args, context);
        } catch (CommandException e) {
            System.out.println("[ERROR] " + e.getMessage());
        } catch (Exception e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    public void detener() {
        activa = false;
    }
}
