package es.securebank.core.ui.commands;

import es.securebank.core.exception.CommandException;
import es.securebank.core.exception.CuentaNoEncontradaException;
import es.securebank.core.model.CuentaBancaria;
import es.securebank.core.ui.AppContext;
import es.securebank.core.ui.Command;

public class VerSaldoCommand implements Command {
    @Override public String getNombre() { return "ver_saldo"; }
    @Override public String getDescripcion() { return "Muestra el saldo de una cuenta"; }
    @Override public String getUso() { return "ver_saldo <iban>"; }

    @Override
    public void execute(String[] args, AppContext context) throws CommandException {
        if (!context.getAuthService().estaLogueado())
            throw new CommandException("Debes iniciar sesion primero.");
        if (args.length < 1) throw new CommandException("Uso: " + getUso());

        String iban = args[0];
        CuentaBancaria cuenta = context.getCacheService().getCuenta(iban);
        if (cuenta == null) throw new CuentaNoEncontradaException(iban);

        System.out.printf("[SALDO] %s -> %.2f€ (%s)%n", iban, cuenta.getSaldo(), cuenta.getEstado());
    }
}
