package es.securebank.core.ui.commands;

import es.securebank.core.exception.CommandException;
import es.securebank.core.model.CuentaBancaria;
import es.securebank.core.ui.AppContext;
import es.securebank.core.ui.Command;

public class VerCuentasCommand implements Command {
    @Override public String getNombre() { return "ver_cuentas"; }
    @Override public String getDescripcion() { return "Muestra tus cuentas bancarias"; }
    @Override public String getUso() { return "ver_cuentas"; }

    @Override
    public void execute(String[] args, AppContext context) throws CommandException {
        if (!context.getAuthService().estaLogueado())
            throw new CommandException("Debes iniciar sesion primero.");

        int clienteId = context.getAuthService().getClienteActual().getId();
        System.out.println("\n--- TUS CUENTAS ---");
        for (CuentaBancaria c : context.getCacheService().getTodas()) {
            if (c.getClienteId() == clienteId) {
                System.out.printf("  IBAN: %s | Tipo: %-10s | Saldo: %10.2f€ | Estado: %s%n",
                        c.getIban(), c.getTipo(), c.getSaldo(), c.getEstado());
            }
        }
        System.out.println("-------------------");
    }
}
