package fauxpas.event;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ProduceConsumeEvent<T> {

    private Supplier<T> valueSupplier;
    private Consumer<T> valueConsumer;

    public ProduceConsumeEvent(Supplier<T> valueSupplier, Consumer<T> valueConsumer) {
        this.valueSupplier = valueSupplier;
        this.valueConsumer = valueConsumer;
    }

    public void process() {
        this.valueConsumer.accept(this.valueSupplier.get());
    }
}
