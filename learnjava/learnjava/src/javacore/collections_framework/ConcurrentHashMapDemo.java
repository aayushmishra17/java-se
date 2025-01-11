package javacore.collections_framework;

import java.text.MessageFormat;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class ConcurrentHashMapDemo {
    public static void main(String[] args) {
        ConcurrentHashMapDemo concurrentMap = new ConcurrentHashMapDemo();
        concurrentMap.demoConcurrentHashMap();
    }
    private void demoConcurrentHashMap() {

        // Concurrent version of HashMap.
        ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();
        /*
        In Java 7 this works via segment based locking.
        The collection is divided into 16 segments(by default) where each individual segment is a HashMap.
        Each smaller individual segment has individual locking.
        Only the segment being written to or read from is locked.
        Read: doesn't require locking unless there is a write operation in progress on the segment.
        Write: would lock the segment.

        Java 8 onwards segmentation is removed, as we can't scale 16 fixed segments.
        Now it uses compare-and-swap approach, it don't lock the collection unless resizing or handling collision.
        Lets, say Thread_A last saw value of x = 45 and now Thread_A needs to modify x to 50
        while trying to modify x's value it will check if x is till 45, if yes then modify its value else it will
        not modify its value and put itself to sleep to some time and retry after that duration.
         */
        Thread thread1 = new Thread(() -> {
            for(int i = 1; i <= 100; i++) {
                concurrentHashMap.put(MessageFormat.format("{0}", i), i);
            }
        });
        Thread thread2 = new Thread(() -> {
            for(int i = 101; i <= 200; i++) {
                concurrentHashMap.put(MessageFormat.format("{0}", i), i);
            }
        });
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("concurrentHashMap size(): " + concurrentHashMap.size());


    }
}
