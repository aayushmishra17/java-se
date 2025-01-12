package javacore.collections_framework;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueDemo {
    public static void main(String[] args) {
        SynchronousQueueDemo obj = new SynchronousQueueDemo();
        obj.demoSynchronousQueue();
    }
    private void demoSynchronousQueue() {
        /*
        SynchronousQueue:
            Each insert operation must wait for a corresponding remove operation by another thread and vice versa.
            Capacity of at most 1 element.
         */
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();
        Thread producer = new Thread(() -> {
           try {
               System.out.println("Producer is waiting to transfer...");
               blockingQueue.put("Hello from producer!");
               System.out.println("Producer has transferred the message.");
           } catch (InterruptedException e) {
               Thread.currentThread().interrupt();
               System.out.println("Producer was interrupted.");
           }
        });
        Thread consumer = new Thread(() -> {
            try {
                System.out.println("Consumer is waiting to receive...");
                String message = blockingQueue.take();
                System.out.println("Consumer received: " + message);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Consumer was interrupted.");
            }
        });
        producer.start();
        consumer.start();
    }
}
