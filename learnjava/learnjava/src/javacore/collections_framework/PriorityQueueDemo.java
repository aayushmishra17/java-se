package javacore.collections_framework;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueDemo {
    public static void main(String[] args) {
        PriorityQueueDemo obj = new PriorityQueueDemo();
        obj.demoPriorityQueue();
    }
    private void demoPriorityQueue() {
        /*
        Orders elements based on their natural ordering, or
        takes a custom comparator for customized ordering.
        Does not allow null elements.
         */
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(15);
        pq.add(10);
        pq.add(30);
        pq.add(5);
        System.out.println(pq); // doesn't store all the elements in sorted order, instead we are concerned with only the head of Q element.
        while (!pq.isEmpty()) {
            System.out.println(pq.poll());
        }

        /*
        Internal working of PriorityQueue:
            Implemented as a min-heap by default(for natural ordering).
         */
        pq = new PriorityQueue<>(Comparator.reverseOrder());
    }
}
