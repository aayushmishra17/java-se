package javacore.collections_framework;

import java.util.*;

public class HashMapDemo {

    public static void main(String... args) {

        HashMapDemo obj = new HashMapDemo();
        obj.demoHashMap();

    }

    private void demoHashMap() {

        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "Alex"); // O(1)
        map.put(2, "Bob");
        map.put(3, "Marley");
        map.put(null, null); // key can be null.
        map.put(5, null);
        map.put(6, null);
        map.put(7, "Luffy");

        // Note: HashMap does not maintain the order of entered elements.

        System.out.println(map);

        map.get(3); // O(1)
        map.get(420); // null

        map.containsKey(3);
        map.containsValue("Alex");

        Set<Integer> keys = map.keySet();
        Collection<String> values = map.values();
        Set<Map.Entry<Integer, String>> entries = map.entrySet();
        for(Map.Entry<Integer, String> entry : entries) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        for(Map.Entry<Integer, String> entry : entries) {
            entry.setValue(entry.getValue().toUpperCase());
        }
        System.out.println(map);

        map.remove(5);
        map.remove(7, "Luffy"); // removes this entry only if both key and value match.

        map.replace(6, "Maxine");

        // We can also specify initial capacity and load factor.
        HashMap<Integer, String> map2 = new HashMap<>(20, 0.5f);

    }

}
