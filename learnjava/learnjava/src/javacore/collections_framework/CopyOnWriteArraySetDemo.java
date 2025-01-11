package javacore.collections_framework;

import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArraySet;

public class CopyOnWriteArraySetDemo {
    public static void main(String[] args) {
        CopyOnWriteArraySetDemo obj = new CopyOnWriteArraySetDemo();
        obj.demoCopyOnWriteArraySet();
    }
    private void demoCopyOnWriteArraySet() {
        /*
        When we don't require a SkipList concurrent implementation of ConcurrentSkipListSet,
        we can instead use CopyOnWriteArraySet.
         */
        CopyOnWriteArraySet<Integer> copyOnWriteSet = new CopyOnWriteArraySet<>();
        ConcurrentSkipListSet<Integer> concurrentSkipListSet = new ConcurrentSkipListSet<>();

        for(int i = 1; i<=5; i++) {
            copyOnWriteSet.add(i);
            concurrentSkipListSet.add(i);
        }

        System.out.println("Initial CopyOnWriteArraySet: " + copyOnWriteSet);
        System.out.println("Initial ConcurrentSkipListSet: " + concurrentSkipListSet);

        System.out.println("Iterating and modifying CopyOnWriteArraySet: ");
        for(Integer num : copyOnWriteSet) {
            System.out.println("Reading from CopyOnWriteArraySet: " + num);
            // Attempting to modify the set during iteration.
            copyOnWriteSet.add(6);
        }

        System.out.println("Iterating and modifying ConcurrentSkipListSet: ");
        for(Integer num : concurrentSkipListSet) {
            System.out.println("Reading from ConcurrentSkipListSet: " + num);
            // Attempting to modify the set during iteration.
            concurrentSkipListSet.add(6);
        }
        // ConcurrentSkipListSet is called weakly consistent as we can't be sure in every scenario that
        // the set would show the newly added element, for e.g. in the below case.
        System.out.println("Iterating and modifying ConcurrentSkipListSet again: ");
        for(Integer num : concurrentSkipListSet) {
            System.out.println("Reading from ConcurrentSkipListSet: " + num);
            // Attempting to modify the set during iteration.
            if(num == 6) { // if last element then add a new element.
                concurrentSkipListSet.add(7);
            }
        }
    }
}
