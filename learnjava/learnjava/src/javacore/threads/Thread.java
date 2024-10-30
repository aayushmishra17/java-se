package javacore.threads;

import java.text.MessageFormat;

public class Thread {
    
    public static void main(String[] args) {

        Thread mainObj = new Thread();
        SampleThread obj1 = mainObj.new SampleThread("SampleThread");
        RunnableThread obj2 = mainObj.new RunnableThread("RunnableThread");

        obj1.start();
        obj2._thread.start();

        try {
            obj1.join();
            obj2._thread.join();
        }
        catch(InterruptedException ex) {
            System.out.println(MessageFormat.format("Exception: {0}", ex));
        }
    }

    /*
     * Thread implementatio using Thread class.
     */
    private class SampleThread extends java.lang.Thread {
        
        public SampleThread(String threadName) {
            super(threadName);
        }

        @Override
        public void run() {
            System.out.println(MessageFormat.format("Inside thread {0}", this.getName()));
        }
        
    }

    /*
     * Thread implementation using Runnable interface.
     */
    private class RunnableThread implements Runnable {

        java.lang.Thread _thread;

        public RunnableThread(String threadName) {
            _thread = new java.lang.Thread(this, threadName);
        }

        @Override
        public void run() {
            System.out.println(MessageFormat.format("Inside thread {0}", this._thread.getName()));
        }
    
    }

}
