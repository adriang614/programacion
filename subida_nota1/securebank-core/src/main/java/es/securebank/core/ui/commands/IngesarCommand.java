package es.securebank.core.ui.commands;

import es.securebank.core.exception.CommandException;
import es.securebank.core.ui.AppContext;
import es.securebank.core.ui.Command;
import java.math.BigDecimal;

public class IngesarCommand implements Command {
    @Override public String getNombre() { return "ingresar"; }
    @Override public String getDescripcion() { return "Ingresa dinero en una cuenta"; }
    @Override public String getUso() { return "ingresar <iban> <importe>"; }

    @Override
    public void execute(String[] args, AppContext context) throws CommandException {
        if (!context.getAuthService().estaLogueado())
            throw new CommandException("Debes iniciar sesion primero.");
        if (args.length < 2) throw new CommandException("Uso: " + getUso());

        String iban = args[0];
        BigDecimal importe;
        try {
            importe = new BigDecimal(args[1]);
        } catch (NumberFormatException e) {
            throw new CommandException("El importe no es valido.");
        }
        if (importe.compareTo(BigDecimal.ZERO) <= 0)
            throw new CommandException("El importe debe ser mayor que 0.");

        context.getTransaccionService().ingresar(iban, importe, "Ingreso manual");
    }
}
