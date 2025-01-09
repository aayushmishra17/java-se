package javacore.collections_framework;

import java.util.*;

public class ArrayListDemo {

    public static void main(String... args) {

        ArrayListDemo obj = new ArrayListDemo();
        obj.demoArrayList();

    }

    private void demoArrayList() {

        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15));
        /*
        Note:
            Arrays.asList() returns a fixed size list.
            List.of() returns a unmodifiable list.
            To make it modifiable:
                new ArrayList<>(fixed size or unmodifiable list);
         */

        list.add(77);

        list.get(0);
        list.size();

        list.contains(5);

        list.remove(2);
        list.remove(Integer.valueOf(7));

        list.add(3, 12);
        list.addAll(List.of(111,112,113,114,115));
        list.set(3, 69);

        list.trimToSize(); // trims the internal array's size to the current size.

        // List to array
        Integer[] array = list.toArray(new Integer[0]);

        // Sort an arraylist
        Collections.sort(list);
        list.sort(null);

        // Sorting list based on custom Comparator
        // We need to implement custom Comparator when a user defined type doesn't implement Comparable interface.
        list.sort((o1, o2) -> o1 - o2); // ascending order.
        list.sort((o1, o2) -> o2 - o1); // descending order.

        class Student {

            private String name;
            public String getName() {
                return  name;
            }
            public void setName(String name) {
                this.name = name;
            }

            private double gpa;
            public double getGpa() {
                return gpa;
            }
            public void setGpa(double gpa) {
                this.gpa = gpa;
            }

            public Student(String name, double gpa) {
                this.name = name;
                this.gpa = gpa;
            }

        }
        List<Student> studentsList = new ArrayList<>(Arrays.asList(
                new Student("Charlie", 3.4),
                new Student("Jonas", 2.9),
                new Student("Nick", 3.5),
                new Student("Betty", 3.9),
                new Student("Emma", 3.1),
                new Student("Alex", 3.4)
        ));
        // Sort by GPA ascending
        studentsList.sort((o1, o2) -> {
            if(o1.getGpa() - o2.getGpa() < 0) {
                return -1;
            } else if(o1.getGpa() - o2.getGpa() > 0) {
                return 1;
            } else {
                return 0;
            }
        });
        // Sort by GPA descending
        studentsList.sort((o1, o2) -> {
            if(o2.getGpa() - o1.getGpa() < 0) {
                return -1;
            } else if(o1.getGpa() - o2.getGpa() > 0) {
                return 1;
            } else {
                return 0;
            }
        });

        System.out.println();
        studentsList.sort(Comparator.comparing(Student::getGpa));
        studentsList.forEach(obj -> System.out.print(" " + obj.name + " "+ obj.gpa + " "));

        System.out.println();
        studentsList.sort(Comparator.comparing(Student::getGpa, Comparator.reverseOrder()));
        studentsList.sort(Comparator.comparing(Student::getGpa).reversed()); // same as above.
        studentsList.forEach(obj -> System.out.print(" " + obj.name + " "+ obj.gpa + " "));

        System.out.println();
        studentsList.sort(Comparator.comparing(Student::getGpa).thenComparing(Student::getName)); // Compare by name for students with equal gpa.
        studentsList.forEach(obj -> System.out.print(" " + obj.name + " "+ obj.gpa + " "));
    }
}
