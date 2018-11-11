package fauxpas.event;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ProduceConsumeEvent<T> {

    private Supplier<T> valueSupplier;
    private Consumer<T> valueConsumer;

    /**
     * Construct a produce-consume event.
     * @param valueSupplier function to produce a value.
     * @param valueConsumer function to consume the value.
     */
    public ProduceConsumeEvent(Supplier<T> valueSupplier, Consumer<T> valueConsumer) {
        this.valueSupplier = valueSupplier;
        this.valueConsumer = valueConsumer;
    }

    /**
     * Consumer accepts value got from supplier.
     */
    public void process() {
        this.valueConsumer.accept(this.valueSupplier.get());
    }
}
