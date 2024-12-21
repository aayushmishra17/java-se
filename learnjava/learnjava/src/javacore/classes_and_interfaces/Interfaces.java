package javacore.classes_and_interfaces;

public class Interfaces {
    public static void main(String[] args) {
        Interfaces outterObj = new Interfaces();
        ExampleClass innerClassObj = outterObj.new ExampleClass();
        innerClassObj.staticMethod();
        ExampleClass.staticMethod();
        ExampleInterface.staticMethod();
    }

    public interface ExampleInterface {
        void someMethod();

        static void staticMethod() {
            System.out.println("Inside ExampleInterface static method!");
        }
    }

    public class ExampleClass implements ExampleInterface {

        @Override
        public void someMethod() {
            System.out.println("SomeMethod implementation inside class ExampleClass!");
        }

        public static void staticMethod() {
            System.out.println("Inside ExampleClass static method!");
        }

        public void demoNestedInterface() {
            InnerInterface interfaceObj = new InnerInterface() {

                @Override
                public int myMethod() {
                    return i;
                }

            };
            interfaceObj.myMethod();

            InnerInterface.NestedInnerInterface nestedIntf = new InnerInterface.NestedInnerInterface() {

                @Override
                public int innerMethod() {
                    System.out.println("Inside innerMethod of InnerInterface.NestedInnerInterface.");
                    System.out.println("i = " + j);
                    return 0;
                }

            };
            nestedIntf.innerMethod();
        }
    }

    private interface InnerInterface {

        int i = 7;

        int myMethod();

        public interface NestedInnerInterface {

            int j = 77;

            int innerMethod();

        }

    }

}

interface PI1 {
    default void show() {
        System.out.println("Default method of PI1");
    }
}

interface PI2 {
    default void show() {
        System.out.println("Default method of PI2");
    }

    default void showAll() {
        System.out.println("Default showAll of PI2");
    }
}

/*
 * Demonstrates how to explicitly call a method from an implemented interface.
 * Syntax: InterfaceName.super.methodName()
 */
class TestClass implements PI1, PI2 {

    public void show() {
        PI1.super.show();
        PI2.super.show();
        PI2.super.showAll();
    }

}