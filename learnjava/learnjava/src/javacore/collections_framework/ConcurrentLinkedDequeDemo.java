package javacore.collections_framework;

import java.util.concurrent.ConcurrentLinkedDeque;

public class ConcurrentLinkedDequeDemo {
    public static void main(String[] args) {
        ConcurrentLinkedDequeDemo obj = new ConcurrentLinkedDequeDemo();
        obj.demoConcurrentLinkedDeque();
    }
    private void demoConcurrentLinkedDeque() {
        /*
        Non-blocking, thread-safe double ended queue.
        Uses the compare and swap strategy.
         */
        ConcurrentLinkedDeque<String> deque = new ConcurrentLinkedDeque<>();
        deque.add("Element1");
        deque.addFirst("Element0");
        deque.addLast("Element2");
        System.out.println(deque);

        String first = deque.removeFirst();
        String last = deque.removeLast();
    }
}
