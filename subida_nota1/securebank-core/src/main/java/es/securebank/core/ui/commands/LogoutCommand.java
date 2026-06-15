package es.securebank.core.ui.commands;

import es.securebank.core.ui.AppContext;
import es.securebank.core.ui.Command;

public class LogoutCommand implements Command {
    @Override public String getNombre() { return "logout"; }
    @Override public String getDescripcion() { return "Cierra la sesion actual"; }
    @Override public String getUso() { return "logout"; }

    @Override
    public void execute(String[] args, AppContext context) {
        context.getAuthService().logout();
    }
}
