# fp-event-queue
Very-very basic wrappers around Java ExecutorService with Supplier, Consumer functional interfaces wrapped in a ProducerConsumerEvent functional interface for enqueuing.

## v0.1.0 :

Create a single thread event queue for linear event processing.

```java
SingleQueue myEventQueue = new SingleQueue()

myEventQueue.enqueue( () -> System.out.println("Hello!") );

myEventQueue.enqueue( new ProduceConsumeEvent<String>( () -> "hello", System.out::println ) );
```

Or create a multi-thread work sharing queue.

```java
SharedQueue myEventQueue = new SharedQueuePool(2);
```

#### Important Note:

You must call clean-up or your application may not terminate as expected.

```java
myEventQueue.cleanup();
``` 