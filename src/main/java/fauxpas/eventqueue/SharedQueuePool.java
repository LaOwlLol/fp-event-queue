package fauxpas.eventqueue;

import fauxpas.event.ProduceConsumeEvent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SharedQueuePool {

    private final ExecutorService eventExecutor;

    public SharedQueuePool() {
        this(3);
    }

    public SharedQueuePool(int poolSize) {
        this.eventExecutor = Executors.newWorkStealingPool(poolSize);
    }

    public void enqueue(ProduceConsumeEvent event) {
        this.eventExecutor.execute( () -> event.process() );
    }

    public void enqueue(Runnable event) {
        this.eventExecutor.execute(event);
    }

    public void cleanup() {
        this.eventExecutor.shutdown();
    }
}
