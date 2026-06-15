package es.securebank.core.ui.commands;

import es.securebank.core.ui.AppContext;
import es.securebank.core.ui.Command;
import es.securebank.core.config.DatabaseConfig;

public class SalirCommand implements Command {
    @Override public String getNombre() { return "salir"; }
    @Override public String getDescripcion() { return "Cierra la aplicacion"; }
    @Override public String getUso() { return "salir"; }

    @Override
    public void execute(String[] args, AppContext context) {
        System.out.println("Cerrando SecureBank...");
        DatabaseConfig.close();
        System.exit(0);
    }
}
