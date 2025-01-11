package javacore.collections_framework;

import java.util.HashMap;
import java.util.IdentityHashMap;

public class IdentityHashMapDemo {
    public static void main(String[] args) {
        IdentityHashMapDemo obj = new IdentityHashMapDemo();
        obj.demoIdentityHashMap();
    }
    private void demoIdentityHashMap() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        String key1 = new String("key");
        String key2 = new String("key");
        hashMap.put(key1, 1);
        hashMap.put(key2, 2);
        System.out.println(hashMap);

        /*
        IdentityHashMap only utilizes the Object.hashcode() method compare two keys for equality.
        It works with Object.hashcode()(identityHashCode) and ==(in case the hash of two objects are same, then compare their memory address reference)
         */
        IdentityHashMap<String, Integer> identityMap = new IdentityHashMap<>();
        identityMap.put(key1, 1);
        identityMap.put(key2, 2);
        System.out.println(identityMap);

        System.out.println("key1 String.hashcode: " + key1.hashCode());
        System.out.println("key2 String.hashcode: " + key2.hashCode());

        System.out.println("key1 identityHashCode: " + System.identityHashCode(key1));
        System.out.println("key1 identityHashCode: " + System.identityHashCode(key2));

    }
}
