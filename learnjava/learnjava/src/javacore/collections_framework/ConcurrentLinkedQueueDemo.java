package javacore.collections_framework;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentLinkedQueueDemo {
    public static void main(String[] args) {
        ConcurrentLinkedQueueDemo obj = new ConcurrentLinkedQueueDemo();
        obj.demoConcurrentLinkedQueue();
    }
    private void demoConcurrentLinkedQueue() {
        /*
        Implementation of Queue interface that provides lock-free, thread safe non-blocking operation.
         */
        ConcurrentLinkedQueue<String> taskQueue = new ConcurrentLinkedQueue<>();
        Thread producer = new Thread(() -> {
           while (true) {
               taskQueue.add("Task" + System.currentTimeMillis());
           }
        });
        Thread consumer = new Thread(() -> {
           while (true) {
               String task = taskQueue.poll();
               System.out.println("Processing: " + task);
           }
        });
        producer.start();
        consumer.start();
    }
}
