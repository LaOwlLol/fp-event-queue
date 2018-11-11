package fauxpas.eventqueue;

import fauxpas.event.ProduceConsumeEvent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleQueue {

    private ExecutorService eventExecutor;

    /**
     * Construct a queue with single worker thread.
     */
    public SingleQueue() {
        this.eventExecutor = Executors.newSingleThreadExecutor();
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
