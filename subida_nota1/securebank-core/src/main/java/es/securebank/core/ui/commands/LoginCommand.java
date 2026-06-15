package es.securebank.core.ui.commands;

import es.securebank.core.exception.CommandException;
import es.securebank.core.ui.AppContext;
import es.securebank.core.ui.Command;

public class LoginCommand implements Command {
    @Override public String getNombre() { return "login"; }
    @Override public String getDescripcion() { return "Inicia sesion con tu DNI y PIN"; }
    @Override public String getUso() { return "login <dni> <pin>"; }

    @Override
    public void execute(String[] args, AppContext context) throws CommandException {
        if (args.length < 2) throw new CommandException("Uso: " + getUso());
        String dni = args[0];
        String pin = args[1];
        boolean ok = context.getAuthService().login(dni, pin);
        if (!ok) System.out.println("[LOGIN] No se pudo iniciar sesion.");
    }
}
