package es.securebank.core;

import es.securebank.core.config.DatabaseConfig;
import es.securebank.core.dao.impl.ClienteDaoJdbc;
import es.securebank.core.dao.impl.CuentaDaoJdbc;
import es.securebank.core.dao.impl.TransaccionDaoJdbc;
import es.securebank.core.service.*;
import es.securebank.core.ui.*;
import es.securebank.core.ui.commands.*;
import javax.sql.DataSource;

public class Main {
    public static void main(String[] args) {
        // Conexion a la base de datos
        DataSource dataSource = DatabaseConfig.getDataSource();

        // DAOs
        ClienteDaoJdbc clienteDao = new ClienteDaoJdbc(dataSource);
        CuentaDaoJdbc cuentaDao = new CuentaDaoJdbc(dataSource);
        TransaccionDaoJdbc transaccionDao = new TransaccionDaoJdbc(dataSource);

        // Servicios
        CacheService cacheService = new CacheService(cuentaDao);
        cacheService.warmUp();

        AuthService authService = new AuthService(clienteDao);
        AntiFraudeService antiFraudeService = new AntiFraudeService(cacheService, dataSource);
        TransaccionService transaccionService = new TransaccionService(cacheService, cuentaDao, transaccionDao, dataSource);
        BatchService batchService = new BatchService(cacheService, dataSource);

        // Contexto compartido entre comandos
        AppContext context = new AppContext(cacheService, transaccionService, authService, batchService, antiFraudeService, dataSource);

        // Registro de comandos
        CommandRegistry registry = new CommandRegistry();
        registry.registrar(new AyudaCommand(registry));
        registry.registrar(new SalirCommand());
        registry.registrar(new LoginCommand());
        registry.registrar(new LogoutCommand());
        registry.registrar(new VerCuentasCommand());
        registry.registrar(new VerSaldoCommand());
        registry.registrar(new HistorialCommand(transaccionDao, cuentaDao));
        registry.registrar(new IngesarCommand());
        registry.registrar(new RetirarCommand());
        registry.registrar(new TransferirCommand());
        registry.registrar(new CobrarComisionesCommand());
        registry.registrar(new EstadoSistemaCommand());

        // Arrancar consola
        ConsolaSecureBank consola = new ConsolaSecureBank(registry, context);
        consola.iniciar();
    }
}
