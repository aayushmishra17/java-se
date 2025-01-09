package javacore.collections_framework;

import java.util.Arrays;
import java.util.Vector;

public class VectorDemo {

    public static void main(String[] args) {

        VectorDemo obj = new VectorDemo();
        obj.demoVector();
    }

    private void demoVector() {

        Vector<Integer> vector = new Vector<>(5, 15);
        vector.add(1);
        vector.add(2);
        vector.add(3);
        vector.add(4);
        vector.add(5);
        System.out.println(vector.capacity()); // 5
        vector.add(6);
        System.out.println(vector.capacity()); // 5 + 15

        Vector<Integer> vector2 = new Vector<>();
        vector2.addAll(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
        System.out.println("vector2.capacity= " + vector2.capacity()); // default capacity is 10.
        vector2.add(11);
        System.out.println("vector2.capacity= " + vector2.capacity()); // by default the capacity doubles.


    }

}
