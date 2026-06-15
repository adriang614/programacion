package es.securebank.core.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BatchWorkerPool {

    private final ExecutorService executor =
            Executors.newFixedThreadPool(4);

    public void submit(Runnable task) {
        executor.submit(task);
    }

    public void shutdown() {
        executor.shutdown();
    }
}