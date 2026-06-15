package es.securebank.core.service;

import es.securebank.core.dao.CuentaDao;
import es.securebank.core.model.CuentaBancaria;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// Este servicio mantiene las cuentas activas en memoria para no tener que ir a la base de datos cada vez
public class CacheService {

    // Mapa clave=IBAN, valor=CuentaBancaria
    private final Map<String, CuentaBancaria> cacheCuentas = new ConcurrentHashMap<>();
    private final CuentaDao cuentaDao;

    public CacheService(CuentaDao cuentaDao) {
        this.cuentaDao = cuentaDao;
    }

    // Carga todas las cuentas activas al arrancar la app
    public void warmUp() {
        long inicio = System.currentTimeMillis();
        System.out.println("[CACHE] Cargando cuentas activas en memoria...");

        cacheCuentas.clear();
        for (CuentaBancaria cuenta : cuentaDao.findAllActivas()) {
            cacheCuentas.put(cuenta.getIban(), cuenta);
        }

        long tiempo = System.currentTimeMillis() - inicio;
        System.out.println("[CACHE] " + cacheCuentas.size() + " cuentas cargadas en " + tiempo + "ms");
    }

    public CuentaBancaria getCuenta(String iban) {
        return cacheCuentas.get(iban);
    }

    public void putCuenta(CuentaBancaria cuenta) {
        cacheCuentas.put(cuenta.getIban(), cuenta);
    }

    public void removeCuenta(String iban) {
        cacheCuentas.remove(iban);
    }

    public boolean existeCuenta(String iban) {
        return cacheCuentas.containsKey(iban);
    }

    public Collection<CuentaBancaria> getTodas() {
        return cacheCuentas.values();
    }

    public Map<String, CuentaBancaria> getCacheCuentas() {
        return cacheCuentas;
    }

    public int size() {
        return cacheCuentas.size();
    }
}
