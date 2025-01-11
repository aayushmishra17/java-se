package javacore.collections_framework;

import java.text.MessageFormat;
import java.util.concurrent.ConcurrentSkipListMap;

public class ConcurrentSkipListMapDemo {
    public static void main(String[] args) {
        ConcurrentSkipListMapDemo obj = new ConcurrentSkipListMapDemo();
        obj.demoConcurrentSkipListMap();
    }
    private void demoConcurrentSkipListMap() {
        /*
        ConcurrentSkipListMap: Map -> sorted -> thread safe
        Concurrent version of TreeMap.
        Internally works with a SkipList data structure.
        Each key-value pair is stored in a skip list.
        SkipList: probabilistic data structure that allows for efficient search, insertion and deletion operation.
                  It is similar to a sorted linked list but with multiple layers that "skip" over portions of the list to
                  provide faster access to elements.
         */
        ConcurrentSkipListMap<Integer,String> skipListMap = new ConcurrentSkipListMap<>();
        Thread thread1 = new Thread(() -> {
            for(int i = 1; i <= 100; i++) {
                skipListMap.put(i, MessageFormat.format("{0}", i));
            }
        });
        Thread thread2 = new Thread(() -> {
            for(int i = 101; i <= 200; i++) {
                skipListMap.put(i, MessageFormat.format("{0}", i));
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
        System.out.println("skipListMap size(): " + skipListMap.size());
    }
}
