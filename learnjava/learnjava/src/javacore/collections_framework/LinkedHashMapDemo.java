package javacore.collections_framework;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapDemo {

    public static void main(String[] args) {

        LinkedHashMapDemo obj = new LinkedHashMapDemo();
        obj.demoLinkedHashMap();
    }

    private void demoLinkedHashMap() {

        /*
        LinkedHashMap maintains its insertion order.
        It maintains a doubly linked list to store its entries.

        accessOrder parameter in the constructor is by default false, meaning maintain insertion order.
        When set to true, it would move the last used element to the end effectively making the map behave like LRU cache.
         */
        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>(15, 0.75f, true);
        linkedHashMap.put("Mango", 50);
        linkedHashMap.put("Orange", 30);
        linkedHashMap.put("Apple", 20);
        linkedHashMap.put("Guava", 40);
        linkedHashMap.put("Pineapple", 70);
        linkedHashMap.get("Mango");
        for(Map.Entry<String, Integer> entry : linkedHashMap.entrySet()) {
            System.out.println(entry);
        }

    }

}
