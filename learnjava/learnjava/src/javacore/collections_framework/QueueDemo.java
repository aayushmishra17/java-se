package javacore.collections_framework;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class QueueDemo {
    public static void main(String[] args) {
        QueueDemo obj = new QueueDemo();
        obj.demoQueue();
    }
    private void demoQueue() {
        Queue<Integer> q = new LinkedList<>();
        q.add(1); // throws exception if capacity is full.
        q.add(2);
        q.add(3);
        q.add(null); // Allows null elements.
        System.out.println(q);

        q.remove(); // throws exception if queue is empty.
        System.out.println(q);

        q.peek(); // returns null if empty queue, doesn't throw exception if queue is empty.

        q.offer(4); // doesn't throw exception if capacity is full.
        q.poll(); // doesn't throw exception if queue is empty.
        q.element(); // throws exception if queue is empty.

        /*
        ArrayBlockingQueue
         */
        Queue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(2);
        arrayBlockingQueue.add(1);
        arrayBlockingQueue.add(2);
        //arrayBlockingQueue.add(3); // throws exception
        arrayBlockingQueue.offer(3); // no exception, element no enqueued as capacity is full.
    }
}
