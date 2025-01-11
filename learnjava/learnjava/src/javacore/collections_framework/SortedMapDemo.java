package javacore.collections_framework;

import java.util.NavigableMap;
import java.util.SortedMap;
import java.util.TreeMap;

public class SortedMapDemo {
    public static void main(String[] args) {
        SortedMapDemo obj = new SortedMapDemo();
        obj.demoSortedMap();
    }
    private void demoSortedMap () {
        SortedMap<Integer, String> sortedMap = new TreeMap<>();
        sortedMap.put(32, "Ganesh"); // O(logN) since we need to balance the tree after each entry
        sortedMap.put(13, "Patil");
        sortedMap.put(21, "Suresh");
        sortedMap.put(57, "Rakesh");
        sortedMap.put(63, "Binod");
        System.out.println(sortedMap);

        sortedMap.get(57); // O(logN)
        sortedMap.replace(13, "Narendra");
        sortedMap.containsKey(63); // O(logN)
        sortedMap.containsValue("Suresh"); //O(N) since the values are not in sorted order.

        sortedMap.firstKey();
        sortedMap.lastKey();
        System.out.println(sortedMap.headMap(21));
        System.out.println(sortedMap.tailMap(21));

        /*
        NavigableMap
         */
        NavigableMap<Integer, String> navigableMap = new TreeMap<>();
        navigableMap.put(1, "One");
        navigableMap.put(2, "Two");
        navigableMap.put(5, "Five");
        navigableMap.put(4, "Four");
        navigableMap.put(3, "Three");
        System.out.println(navigableMap);

        navigableMap.lowerKey(3);
        navigableMap.ceilingKey(4);
        navigableMap.higherEntry(2);
        navigableMap.descendingMap();
    }
}
