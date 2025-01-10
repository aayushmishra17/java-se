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

        list.add(77); // add an element at the end of list. Avg: O(1), Worst: O(N) when array resizing is needed.
        list.add(3, 12); // add element at specific index. O(N)
        list.addAll(List.of(111,112,113,114,115));
        list.addAll(7, List.of(111,112,113,114,115));

        list.get(0); // get element at specific index. O(1)
        list.size(); // number of elements currently present in the list.
        list.indexOf(5); // returns -1 if element not present in list.
        list.lastIndexOf(5);
        list.isEmpty(); // check if list contains any elements

        list.contains(5);
        list.containsAll(List.of(33,43,53));

        list.remove(2);
        list.remove(Integer.valueOf(7));

        list.replaceAll(num -> num * 2);
        list.retainAll(List.of(1,2,3,4,5)); // remove all elements that are not present in specific collection.

        list.set(3, 69); // modify an element at specific index.

        list.trimToSize(); // trims the internal array's size to the current size.

        // List to array
        Integer[] array = list.toArray(new Integer[0]);

        list.subList(5, 10).clear(); // returns a SubList view of the list b/t [inclusiveIndx, exclusiveIndx), changes made to the sublist is visible in the actual list.

        // Sort an arraylist
        Collections.sort(list);
        list.sort(null);

        // Sorting list based on custom Comparator
        // We need to implement custom Comparator when a user defined type doesn't implement Comparable interface.
        list.sort((o1, o2) -> o1 - o2); // ascending order.
        list.sort((o1, o2) -> o2 - o1); // descending order.

        list.clear(); // empty a list.

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
