package javacore.threads;

import java.text.MessageFormat;

public class InterThreadCommunication {
    
    public static void main(String[] args) {

        Q q = new Q();
        Producer p = new Producer(q);
        Consumer c = new Consumer(q);

        p._thread.start();
        c._thread.start();

        System.out.println("Press ctrl + c to stop.");

    }

}

class Q {
    private int _n;
    private boolean valueSet = false;

    synchronized int get() {
        while(valueSet == false)
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(MessageFormat.format("{0}", e));
            }

        System.out.println("Got: " + _n);

        try {
            java.lang.Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(MessageFormat.format("{0}", e));
        }

        valueSet = false;
        notify();

        return _n;
    }

    synchronized void put(int n) {

        while(valueSet == true)
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(MessageFormat.format("{0}", e));
            }

        _n = n;
        valueSet = true;
        System.out.println("Put: " + n);
        notify();
    }
}

class Producer implements Runnable {

    Q _q;
    java.lang.Thread _thread;

    public Producer(Q q) {
        _q = q;
        _thread = new java.lang.Thread(this, "Producer");
    }

    @Override
    public void run() {

        int i = 0;

        while(true) {
            _q.put(i++);
        }

    }

}

class Consumer implements Runnable {

    Q _q;
    java.lang.Thread _thread;

    public Consumer(Q q) {
        _q = q;
        _thread = new java.lang.Thread(this, "Consumer");
    }

    @Override
    public void run() {

        while (true) {
            _q.get();
        }

    }

}
