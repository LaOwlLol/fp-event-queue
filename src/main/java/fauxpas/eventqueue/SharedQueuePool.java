package fauxpas.eventqueue;

import fauxpas.event.ProduceConsumeEvent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SharedQueuePool {

    private final ExecutorService eventExecutor;

    /**
     * Construct shared queue pool with three threads.
     */
    public SharedQueuePool() {
        this(3);
    }

    /**
     * Construct shared queue pool with given thread count.
     * @param poolSize threads to create.
     */
    public SharedQueuePool(int poolSize) {
        this.eventExecutor = Executors.newWorkStealingPool(poolSize);
    }

    /**
     * Add event to worker queues.
     * @param event to add to work.
     */
    public void enqueue(ProduceConsumeEvent event) {
        this.eventExecutor.execute( () -> event.process() );
    }

    /**
     * Add event to worker queues.
     * @param event to add to work.
     */
    public void enqueue(Runnable event) {
        this.eventExecutor.execute(event);
    }

    /**
     * Shutdown thread pool.
     */
    public void cleanup() {
        this.eventExecutor.shutdown();
    }
}
