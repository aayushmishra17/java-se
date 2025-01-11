package javacore.collections_framework;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class DequeDemo {
    public static void main(String[] args) {
        DequeDemo obj = new DequeDemo();
        obj.demoDeque();
    }
    private void demoDeque() {
        /*
        Double ended queue: insertion and deletion at both ends.
        Deque should be the preferred over the legacy Stack implementation.
         */
        Deque<Integer> deque = new ArrayDeque<>(); // no null allowed, faster iteration, low memory.
        /*
        ArrayDeque has a circular array, so removing elements from the head
        doesn't require shifting the remaining elements.
         */
        deque.addFirst(10);
        deque.addLast(20);
        deque.offerFirst(5);
        deque.offerLast(25); // 5 10 20 25
        System.out.println(deque);
        System.out.println("First element:" + deque.getFirst()); // 5
        System.out.println("Last element:" + deque.getLast()); // 25
        deque.removeFirst(); // 5
        deque.pollLast(); // 25
        // Current deque: [10, 20]
        for(Integer num : deque) {
            System.out.println(num);
        }
        Deque<Integer> deque2 = new LinkedList<>(); // Should prefer ArrayDeque unless LinkedList is required like for insertion/deletion in the middle.

    }
}
