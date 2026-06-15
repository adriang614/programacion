package es.securebank.core.ui.commands;

import es.securebank.core.exception.CommandException;
import es.securebank.core.ui.AppContext;
import es.securebank.core.ui.Command;
import java.math.BigDecimal;

public class TransferirCommand implements Command {
    @Override public String getNombre() { return "transferir"; }
    @Override public String getDescripcion() { return "Transfiere dinero entre dos cuentas"; }
    @Override public String getUso() { return "transferir <origen> <destino> <importe>"; }

    @Override
    public void execute(String[] args, AppContext context) throws CommandException {
        if (!context.getAuthService().estaLogueado())
            throw new CommandException("Debes iniciar sesion primero.");
        if (args.length < 3) throw new CommandException("Uso: " + getUso());

        String origen = args[0];
        String destino = args[1];
        BigDecimal importe;
        try {
            importe = new BigDecimal(args[2]);
        } catch (NumberFormatException e) {
            throw new CommandException("El importe no es valido.");
        }
        if (importe.compareTo(BigDecimal.ZERO) <= 0)
            throw new CommandException("El importe debe ser mayor que 0.");

        context.getTransaccionService().transferir(origen, destino, importe, "Transferencia");
    }
}
