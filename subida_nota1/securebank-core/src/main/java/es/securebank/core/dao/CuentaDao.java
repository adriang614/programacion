package es.securebank.core.dao;

import es.securebank.core.model.CuentaBancaria;
import java.util.List;
import java.util.Optional;

public interface CuentaDao extends GenericDao<CuentaBancaria, Integer> {
    Optional<CuentaBancaria> findByIban(String iban);
    List<CuentaBancaria> findByClienteId(int clienteId);
    List<CuentaBancaria> findAllActivas();
    void updateSaldo(String iban, java.math.BigDecimal nuevoSaldo);
    void updateEstado(String iban, String estado);
}
