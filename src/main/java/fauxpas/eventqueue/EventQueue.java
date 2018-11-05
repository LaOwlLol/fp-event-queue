package fauxpas.eventqueue;

import fauxpas.event.Event;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EventQueue {

    private ExecutorService eventExecutor;

    public EventQueue() {
        this.eventExecutor = Executors.newSingleThreadExecutor();
    }

    public void enqueue(Event event) {
        eventExecutor.execute( () -> event.process() );
    }

    public void cleanup() {
        eventExecutor.shutdown();
    }

}
