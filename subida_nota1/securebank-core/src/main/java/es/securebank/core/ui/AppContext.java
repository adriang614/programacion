package es.securebank.core.ui;

import es.securebank.core.service.*;
import javax.sql.DataSource;

// Contiene todos los servicios disponibles para los comandos
public class AppContext {
    private final CacheService cacheService;
    private final TransaccionService transaccionService;
    private final AuthService authService;
    private final BatchService batchService;
    private final AntiFraudeService antiFraudeService;
    private final DataSource dataSource;

    public AppContext(CacheService cacheService, TransaccionService transaccionService,
                      AuthService authService, BatchService batchService,
                      AntiFraudeService antiFraudeService, DataSource dataSource) {
        this.cacheService = cacheService;
        this.transaccionService = transaccionService;
        this.authService = authService;
        this.batchService = batchService;
        this.antiFraudeService = antiFraudeService;
        this.dataSource = dataSource;
    }

    public CacheService getCacheService() { return cacheService; }
    public TransaccionService getTransaccionService() { return transaccionService; }
    public AuthService getAuthService() { return authService; }
    public BatchService getBatchService() { return batchService; }
    public AntiFraudeService getAntiFraudeService() { return antiFraudeService; }
    public DataSource getDataSource() { return dataSource; }
}
