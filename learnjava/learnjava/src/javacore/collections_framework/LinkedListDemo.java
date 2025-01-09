package javacore.collections_framework;

import java.util.Arrays;
import java.util.LinkedList;

public class LinkedListDemo {

    public static void main(String[] args) {

        LinkedListDemo obj = new LinkedListDemo();

    }

    private void demoLinkedList() {

        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);

        linkedList.get(2); // O(N) operation.

        linkedList.add(3, 77);
        linkedList.addLast(4); // O(1) operation, Java's implementation maintains a reference to the first and last nodes.
        linkedList.addFirst(5); // O(1) operation when inserting node at the beginning.

        linkedList.getFirst();
        linkedList.getLast();

        linkedList.remove(); // removes the first node.
        linkedList.remove(3);
        linkedList.remove(Integer.valueOf(4));
        linkedList.removeFirst();
        linkedList.removeLast();

        linkedList.removeIf(x -> x % 2 == 0);
        linkedList.removeAll(Arrays.asList(1,2,3)); // removes all elements from the ll that are also present in the given collection.


    }

}
