package es.securebank.core.ui.commands;

import es.securebank.core.ui.AppContext;
import es.securebank.core.ui.Command;

public class EstadoSistemaCommand implements Command {
    @Override public String getNombre() { return "estado_sistema"; }
    @Override public String getDescripcion() { return "Muestra estadisticas de la cache"; }
    @Override public String getUso() { return "estado_sistema"; }

    @Override
    public void execute(String[] args, AppContext context) {
        System.out.println("\n--- ESTADO DEL SISTEMA ---");
        System.out.println("  Cuentas en cache: " + context.getCacheService().size());
        System.out.println("  Usuario activo:   " + 
            (context.getAuthService().estaLogueado() ? 
             context.getAuthService().getClienteActual().getNombre() : "ninguno"));
        System.out.println("--------------------------");
    }
}
