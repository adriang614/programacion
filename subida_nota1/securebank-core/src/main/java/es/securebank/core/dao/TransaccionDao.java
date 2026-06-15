package es.securebank.core.dao;

import es.securebank.core.model.Transaccion;
import java.util.List;

public interface TransaccionDao extends GenericDao<Transaccion, Integer> {
    List<Transaccion> findByCuentaId(int cuentaId);
    List<Transaccion> findByCuentaId(int cuentaId, int limite);
}
