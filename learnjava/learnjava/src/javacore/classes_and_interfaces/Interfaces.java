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
            InnerInterface.NestedInnerInterface nestedIntf = new InnerInterface.NestedInnerInterface() {

                @Override
                public int innerMethod() {
                    System.out.println("Inside innerMethod of InnerInterface.NestedInnerInterface.");
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
