package javacore.collections_framework;

import java.util.LinkedList;
import java.util.Stack;

public class StackDemo {

    public static void main(String... args) {

        StackDemo obj = new StackDemo();
        obj.demoStack();

    }

    private void demoStack() {

        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        System.out.println(stack);

        Integer removedElement = stack.pop();
        System.out.println(stack);

        stack.peek();
        stack.empty();
        stack.isEmpty();
        stack.size();

        int indexOfElement = stack.search(4);

        // LinkedList as a stack.
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addLast(1); // push
        linkedList.addLast(2);
        linkedList.addLast(3);
        linkedList.addLast(4);
        linkedList.addLast(5);

        linkedList.getLast(); // peek
        linkedList.removeLast(); // pop
        linkedList.isEmpty();
        linkedList.size();

    }

}
