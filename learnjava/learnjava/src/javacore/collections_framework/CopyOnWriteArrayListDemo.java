package javacore.collections_framework;

import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListDemo {

    public static void main(String[] args) {

        CopyOnWriteArrayListDemo obj = new CopyOnWriteArrayListDemo();
        obj.demoCopyOnWriteArrayList();

    }

    private void demoCopyOnWriteArrayList() {

        /*
        Copy on write means whenever a write operation like adding or removing elements is performed,
        instead of directly modifying the existing list a new copy of the list is created, and the modification is
        applied to that copy. This ensures other threads reading the list while it's being modified are unaffected.

        Read operation: Fast and direct, since they happen on stable list without interference from any modification.
        Write operation: A new copy of the list is created for every modification. The reference to the list is then
                         updated so that subsequent reads use this new list.

         This is preferred for read extensive scenarios, not write extensive.
         */
        CopyOnWriteArrayList<String> sharedList = new CopyOnWriteArrayList<>();
        sharedList.add("Item1");
        sharedList.add("Item2");
        sharedList.add("Item3");

        Thread readerThread = new Thread(() -> {
            try {
                while(true) {
                    for(String item : sharedList) {
                        System.out.println("Reading item: " + item);
                        Thread.sleep(100);
                    }
                }
            } catch (Exception e) {
                System.out.println("Execption in reader thread - " + e);
            }
        });
        Thread writerThread = new Thread(() -> {
            try {
                Thread.sleep(500);
                sharedList.add("Item4");
                System.out.println("Added Item4 to the list.");

                Thread.sleep(500);
                sharedList.remove("Item1");
                System.out.println("Removed Item1 from the list.");
            } catch (InterruptedException e) {
                System.out.println("Exception in writer thread - " + e);
            }
        });
        readerThread.start();
        writerThread.start();

    }

}
