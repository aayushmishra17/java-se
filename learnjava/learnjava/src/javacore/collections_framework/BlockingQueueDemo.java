package javacore.collections_framework;

import java.util.Comparator;
import java.util.concurrent.*;

public class BlockingQueueDemo {
    public static void main(String[] args) {
        BlockingQueueDemo obj = new BlockingQueueDemo();
        obj.demoBlockingQueue();
    }
    private void demoBlockingQueue() {
        /*
        Thread safe queue.
        Unlike Queue, BlockingQueue has methods that block until the operation can be performed like
        wait for the queue to have some elements before trying to fetch elements,
        wait for space before trying to add elements, etc.
         */

        /*
        ArrayBlockingQueue:
            Array backed blocking queue, must specify the capacity.
            Low memory overhead as it is backed by circular array.
            Uses a single lock for both enqueue and dequeue operation.
         */
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);
        Thread producer = new Thread(new Producer(blockingQueue));
        Thread consumer = new Thread(new Producer(blockingQueue));
        producer.start();
        consumer.start();

        /*
        LinkedBlockingQueue:
            LinkedList backed blocking queue, may not specify its capacity, then default is Integer.MAX_VALUE.
            Utilizes two separate locks for enqueue and dequeue operation.
            Should be used when there are higher number of threads leading to high concurrency
            between producers and consumers.
         */
        blockingQueue = new LinkedBlockingQueue<>(15);
        producer = new Thread(new Producer(blockingQueue));
        consumer = new Thread(new Consumer(blockingQueue));
        producer.start();
        consumer.start();

        /*
        PriorityBlockingQueue:
            Backed by binary heap as an array that can grow dynamically.
            Orders the elements according to their natural order.
            Has a default capacity of 11.
            Like PriorityQueue, here the head element is ordered based on their natural ordering
            or a provided Comparator.
         */
        blockingQueue = new PriorityBlockingQueue<>();
        //blockingQueue = new PriorityBlockingQueue<>(11, Comparator.reverseOrder());

    }
    class Producer implements Runnable {
        private BlockingQueue<Integer> queue;
        private int value = 0;

        public Producer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    System.out.println("Producer produced: " + value);
                    queue.put(value++);
                    // queue.offer(value, 1000L, TimeUnit.MILLISECONDS);
                    Thread.sleep(1000);
                } catch (Exception ignored) {
                    Thread.currentThread().interrupt();
                    System.out.println("Producer interrupted");
                }
            }
        }
    }
    class Consumer implements Runnable {
        private BlockingQueue<Integer> queue;

        public Consumer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Integer value = queue.take();
                    System.out.println("Consumer consumed: " + value);
                    Thread.sleep(2000);
                } catch (Exception ignored) {
                    Thread.currentThread().interrupt();
                    System.out.println("Consumer interrupted");
                }
            }
        }
    }
}
