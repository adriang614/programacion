package es.securebank.core.dao;

import es.securebank.core.model.Cliente;
import java.util.Optional;

public interface ClienteDao extends GenericDao<Cliente, Integer> {
    Optional<Cliente> findByDni(String dni);
    Optional<Cliente> findByEmail(String email);
}
