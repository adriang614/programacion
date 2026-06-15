package es.securebank.core.ui.commands;

import es.securebank.core.dao.CuentaDao;
import es.securebank.core.dao.TransaccionDao;
import es.securebank.core.exception.CommandException;
import es.securebank.core.exception.CuentaNoEncontradaException;
import es.securebank.core.model.CuentaBancaria;
import es.securebank.core.model.Transaccion;
import es.securebank.core.ui.AppContext;
import es.securebank.core.ui.Command;

import java.util.List;

public class HistorialCommand implements Command {
    private final TransaccionDao transaccionDao;
    private final CuentaDao cuentaDao;

    public HistorialCommand(TransaccionDao transaccionDao, CuentaDao cuentaDao) {
        this.transaccionDao = transaccionDao;
        this.cuentaDao = cuentaDao;
    }

    @Override public String getNombre() { return "historial"; }
    @Override public String getDescripcion() { return "Muestra el historial de una cuenta"; }
    @Override public String getUso() { return "historial <iban> [limite]"; }

    @Override
    public void execute(String[] args, AppContext context) throws CommandException {
        if (!context.getAuthService().estaLogueado())
            throw new CommandException("Debes iniciar sesion primero.");
        if (args.length < 1) throw new CommandException("Uso: " + getUso());

        String iban = args[0];
        int limite = args.length > 1 ? Integer.parseInt(args[1]) : 10;

        CuentaBancaria cuenta = context.getCacheService().getCuenta(iban);
        if (cuenta == null) throw new CuentaNoEncontradaException(iban);

        List<Transaccion> transacciones = transaccionDao.findByCuentaId(cuenta.getId(), limite);
        System.out.println("\n--- HISTORIAL " + iban + " ---");
        for (Transaccion t : transacciones) {
            System.out.printf("  %s | %-25s | %10.2f€ | %s%n",
                    t.getFecha().toLocalDate(), t.getTipo(), t.getImporte(), t.getConcepto());
        }
        System.out.println("----------------------------");
    }
}
