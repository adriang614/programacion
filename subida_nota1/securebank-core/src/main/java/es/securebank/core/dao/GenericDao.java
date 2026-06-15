package es.securebank.core.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T, ID> {
    Optional<T> findById(ID id);
    List<T> findAll();
    void save(T entity);
    void update(T entity);
    void delete(ID id);
}
