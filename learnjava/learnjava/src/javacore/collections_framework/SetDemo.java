package javacore.collections_framework;

import java.util.*;

public class SetDemo {
    public static void main(String[] args) {
        SetDemo obj = new SetDemo();
        obj.demoSets();
    }
    private void demoSets() {
        /*
        Set is a collection that cannot contain duplicate elements.
        Map -> HashMap, LinkedHashMap, NavigableHashMap, TreeMap, EnumMap.
        Set -> HashSet, LinkedHashSet, NavigableHashSet, TreeSet, EnumSet.

        ConcurrentMap -> Collections.synchronizedMap(any map interface).
        ConcurrentSet -> Collections.synchronizedSet(any set interface).
                         These introduce external synchronization i.e. wraps all methods in synchronized method, degrading performance.
                         Instead, utilize ConcurrentSkipListSet
         */

        Set<Integer> set = new HashSet<>();
        set.addAll(List.of(1,2,2,4,4,4,4,3,3,3));
        set.add(53);
        set.add(49);
        set.add(32);
        System.out.println("HashSet: " + set);

        set = new LinkedHashSet<>(); // maintains order of insertion.
        set.addAll(List.of(1,2,2,4,4,4,4,3,3,3));
        set.add(53);
        set.add(49);
        set.add(32);
        System.out.println("LinkedHashSet: " + set);

        set = new TreeSet<>(); // ordered set.
        set.addAll(List.of(1,2,2,4,4,4,4,3,3,3));
        set.add(53);
        set.add(49);
        set.add(32);
        System.out.println("TreeSet: " + set);

        /*
        Unmodifiable set
         */
        Set<Integer> unmodifiableSet = Set.of(34, 34, 35, 67, 89, 91, 1, 2, 3, 7, 8, 9, 11);
        Set<Integer> unmodifiableSet2 = Collections.unmodifiableSet(set);
    }
}
