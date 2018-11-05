package fauxpas.eventqueue;

import fauxpas.event.Event;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class EventQueue {

    private Executor eventExecutor;

    public EventQueue() {
        this.eventExecutor = Executors.newSingleThreadExecutor();
    }

    public void enqueue(Event event) {
        eventExecutor.execute( () -> event.process() );
    }

}
