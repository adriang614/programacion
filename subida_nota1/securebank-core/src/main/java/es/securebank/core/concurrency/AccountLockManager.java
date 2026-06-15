package es.securebank.core.concurrency;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class AccountLockManager {

    private final ConcurrentHashMap<String, ReentrantLock> locks =
            new ConcurrentHashMap<>();

    public void lockCuenta(String iban) {
        ReentrantLock lock = locks.computeIfAbsent(
                iban,
                k -> new ReentrantLock());

        lock.lock();
    }

    public void unlockCuenta(String iban) {

        ReentrantLock lock = locks.get(iban);

        if (lock != null && lock.isHeldByCurrentThread()) {
            lock.unlock();
        }
    }
}