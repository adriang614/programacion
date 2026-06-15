package es.securebank.core.ui.commands;

import es.securebank.core.exception.CommandException;
import es.securebank.core.ui.AppContext;
import es.securebank.core.ui.Command;

public class CobrarComisionesCommand implements Command {
    @Override public String getNombre() { return "cobrar_comisiones"; }
    @Override public String getDescripcion() { return "Cobra 2€ de comision a todas las cuentas (admin)"; }
    @Override public String getUso() { return "cobrar_comisiones"; }

    @Override
    public void execute(String[] args, AppContext context) throws CommandException {
        context.getBatchService().cobrarComisiones();
    }
}
