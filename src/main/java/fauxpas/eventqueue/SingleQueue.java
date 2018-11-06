package fauxpas.eventqueue;

import fauxpas.event.ProduceConsumeEvent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleQueue {

    private ExecutorService eventExecutor;

    public SingleQueue() {
        this.eventExecutor = Executors.newSingleThreadExecutor();
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
