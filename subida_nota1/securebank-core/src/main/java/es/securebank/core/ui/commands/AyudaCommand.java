package es.securebank.core.ui.commands;

import es.securebank.core.ui.AppContext;
import es.securebank.core.ui.Command;

public class AyudaCommand implements Command {
    private final es.securebank.core.ui.CommandRegistry registry;

    public AyudaCommand(es.securebank.core.ui.CommandRegistry registry) {
        this.registry = registry;
    }

    @Override public String getNombre() { return "ayuda"; }
    @Override public String getDescripcion() { return "Muestra todos los comandos disponibles"; }
    @Override public String getUso() { return "ayuda"; }

    @Override
    public void execute(String[] args, AppContext context) {
        System.out.println("\n--- COMANDOS DISPONIBLES ---");
        for (Command cmd : registry.listarTodos()) {
            System.out.printf("  %-30s %s%n", cmd.getUso(), cmd.getDescripcion());
        }
        System.out.println("----------------------------");
    }
}
