package es.securebank.core.cache;

public interface TransactionalCache<T> {

    T get(String key);

    void put(String key, T value);

    void remove(String key);

    boolean contains(String key);
}