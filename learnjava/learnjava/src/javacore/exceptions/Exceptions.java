package javacore.exceptions;

import java.text.MessageFormat;

public class Exceptions {
    public static void main(String[] args) {

        Exceptions obj = new Exceptions();
        try {
            obj.demoChainedExceptions();
        } catch (NullPointerException ex) {

            System.out.println(MessageFormat.format("Caught exception - {0}", ex));
            System.out.println(MessageFormat.format("Cause: {0}", ex.getCause()));

        }

        obj.demoMultiCatchStatement();
    }

    private Throwable demoChainedExceptions() throws NullPointerException {
        NullPointerException ex = new NullPointerException("Top level exception!");
        ex.initCause(new ArithmeticException("Causal exception!"));

        throw ex;
    }

    private void demoMultiCatchStatement() {
        int a = 10, b = 0;
        int[] arr = { 10, 20, 30 };

        try {
            int c = a / b;
            System.out.println(c);
            System.out.println(arr[0]);
            // arr[19] = 50;
        } catch (ArithmeticException | IndexOutOfBoundsException ex) {
            System.out.println(MessageFormat.format("Exception caught: {0}", ex));
        }
    }
}
