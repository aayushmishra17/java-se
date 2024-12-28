package javacore.threads;

import java.lang.Thread;

public class VolatileKeyword {

    public static void main(String[] args) {
        // Can only be used in field declaration.
        /*
         * Visibility:
         * Changes done by one thread are visible to other threads.
         * Atomicity or Mutual exclusion:
         * Only one thread can execute a block of code at a given time.
         * 
         * Volatile variables ensure visibility without mutual exclusion. These
         * variables are never cached and are read directly from main memory. So a
         * thread must reconsile its value with the master copy every time it accesses
         * the variable.
         * 
         * Why do we need "synchronized" when we already have "volatile"?
         * With variables declared as volatile even though all threads are reading its
         * value from the main memory, volatile doesn't prevent multiple threads from
         * trying to update the value at the same time and this is where we need
         * synchronized to ensure mutual exclusion.
         * 
         * Synchronized ensures both atomicity and visibility.
         * 
         * If we need to synchronize only one field variable for only read operations
         * then we should use volatile over synchronized as it has less overhead.
         * 
         * If we want to synchronize both read and write operations for only one field
         * variable then we should consider using Atomic types like AtomicInteger,
         * AtomicBoolean, AtomicReference, etc.
         */

        VolatileExample obj = new VolatileExample();
        obj.demoVolatile();
    }

}

class VolatileExample {
    private static volatile boolean running = true;

    public void demoVolatile() {
        Thread worker = new Thread(() -> {
            while (running) {
                // Do some work
            }
            System.out.println("Thread stopped.");
        });

        worker.start();

        // Simulate some work in main thread
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Stop the worker thread
        running = false;
    }
}
