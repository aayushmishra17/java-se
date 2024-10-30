package javacore.threads;

import java.text.MessageFormat;

public class ThreadSynchronization {
    
    public static void main(String[] args) {

        CallMe target = new CallMe();
        Caller obj1 = new Caller(target, "Hello");
        Caller obj2 = new Caller(target, "Synchronized");
        Caller obj3 = new Caller(target, "World");

        obj1._thread.start();
        obj2._thread.start();
        obj3._thread.start();

        try {
            obj1._thread.join();
            obj2._thread.join();
            obj3._thread.join();
        } catch (InterruptedException e) {
            System.out.println(MessageFormat.format("Thread interrupted {0}", e));
        }
    }

}
/**
 * Caller
 */
class Caller implements Runnable {

    String _msg;
    CallMe _target;
    java.lang.Thread _thread;

    public Caller(CallMe targ, String str) {
        _target = targ;
        _msg = str;
        _thread = new java.lang.Thread(this);
    }

    @Override
    public void run() {
        
        _target.call(_msg);
        
        // synchronized(_target) {
        //     _target.call(_msg);
        // }

    }
    
}

/**
 * CallMe
 */
class CallMe {

    synchronized public void call(String msg) {
        System.out.print("[" + msg);
        
        try {
            java.lang.Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }
        
        System.out.println("]");

        try {
            java.lang.Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }
    }
    
}
