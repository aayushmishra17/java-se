package javacore.collections_framework;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class EnumMapDemo {
    public static void main(String[] args) {
        EnumMapDemo obj = new EnumMapDemo();
        obj.demoEnumMap();
    }
    enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }
    private void demoEnumMap() {
        /*
        It is recommended to use EnumMap as compared to a HashMap when the keys are enum values of a particular
        enum type, as this allows the EnumMap to utilize an array as its internal structure since it knows in
        advance all the possible values of the key.
        Hence, it is faster and memory efficient when compared to a HashMap.
         */
        EnumMap<Day, String> enumMap = new EnumMap<>(Day.class);

        enumMap.put(Day.WEDNESDAY, "Back and Biceps");
        enumMap.put(Day.TUESDAY, "Chest and Triceps");
        enumMap.put(Day.MONDAY, "Legs and Shoulders");
        enumMap.put(Day.FRIDAY, "Chest and Triceps");

        enumMap.get(Day.TUESDAY);

        /*
        Unmodifiable maps: once initialized we can't add, remove or modify any of its entries.
         */
        Map<String, Integer> simpleMap = new HashMap<>();
        simpleMap.put("A", 1);
        simpleMap.put("B", 1);
        Map<String, Integer> simpleMapUnmodifiable = Collections.unmodifiableMap(simpleMap);
        simpleMap.put("C", 1); // we can change the original simpleMap, so it is better to create via Map.ofEntries()
        Map<String, Integer> unmodifiableMap = Map.ofEntries(Map.entry("Kevin", 91), Map.entry("Rakesh", 95), Map.entry("Riddhi", 90), Map.entry("Akash", 93));
    }
}
