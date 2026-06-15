package es.securebank.core.cache;

public class CacheSnapshot<T> {

    private final T value;

    public CacheSnapshot(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}