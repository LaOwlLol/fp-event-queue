package fauxpas.eventqueues;

import fauxpas.events.Processable;

import java.util.LinkedList;



/**
 * A list of events which will be destroyed once processed.
 */
public class VolatileEventQueue implements Runnable {

    private LinkedList<Processable> events;
    private long processRate;

    /**
     * Construct and empty queue.
     */
    public VolatileEventQueue() {
        this(100);
    }

    public VolatileEventQueue(long _processRate) {
        this.events = new LinkedList<>();
        this.processRate = _processRate;
    }

    /**
     * Add a Processable event to the end of the list.
     * @param event - Processable object to be enqueued.
     */
    public void enqueue(Processable event) {
        events.addLast(event);
    }

    /**
     * Peek at the head of the list and if there is an Processable event there process it.
     */
    private void update()  {
        if (events.peek() != null) {
            events.poll().process();
        }
    }

    /**
     * Process events unless interrupted.
     */
    @Override
    public void run() {

        try {
            while (true) {
                this.update();
                Thread.sleep(this.processRate);
            }
        }
        catch (InterruptedException e) {
            //TODO handle better.
            System.err.println("VolatileEventQueue on Thread "+Thread.currentThread().getId()+"Interrupted " +
                  "and may require restart to continue processing.");
        }

    }
}
