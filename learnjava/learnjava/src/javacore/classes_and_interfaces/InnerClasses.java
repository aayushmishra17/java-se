package javacore.classes_and_interfaces;

import java.util.function.Predicate;

public class InnerClasses {

    int _val = 7;

    class Inner {
        int _innerVal = 17;
        static int _staticVal = 31; // Allowed from JDK-19 onwards.

        void showValue() {

            System.out.println("Value = " + _innerVal);
            System.out.println("Static value = " + _staticVal);
            System.out.println("Outer class value = " + _val);

            // Another way to access outer class value.
            // This way is necessary when a local variable shadows the outer scope variable.
            System.out.println("Outer class value = " + InnerClasses.this._val);
        }
    }

    class Shadow {
        int _val = 21;

        void shadowingExample(int _val) {
            System.out.println(_val);
            System.out.println(this._val);
            System.out.println(InnerClasses.this._val);
        }
    }

    public static void main(String[] args) {
        InnerClasses.Inner obj = new InnerClasses().new Inner();
        obj.showValue();

        Shadow shadowObj = new InnerClasses().new Shadow();
        shadowObj.shadowingExample(51);

        MethodLocalInnerClass methodLocalObj = new MethodLocalInnerClass();
        methodLocalObj.demoMethodLocalClass(89);
    }

}

class StaticNestedClass {
    int _outerVal = 19;
    static int _staticOuterVal = 32;

    static class StaticInnerClass {
        void accessMembers(StaticNestedClass outerObj) {
            System.out.println(outerObj._outerVal);
            System.out.println(_staticOuterVal);
        }
    }

    void demoStaticNestedClasses() {
        /*
         * Static nested classes can be accessed just like any other outer level class
         * without requiring to create an instance of the enclosing class.
         */
        StaticInnerClass staticInnerClassObj = new StaticInnerClass();
        staticInnerClassObj.accessMembers(new StaticNestedClass());
    }
}

class MethodLocalInnerClass {
    int _outerClassVariable = 91;

    void demoMethodLocalClass(int methodParameter) {
        int methodLocalVariable = 19;

        // Method local class.
        class LocalClass {
            int _val;
            static int _staticVal = 17;
            static {
                System.out.println("statically initialize something...");
            }

            void showValue() {
                System.out.println(_val);

                /*
                 * Local class can only access local variables of the enclosign scope that are
                 * declared final or effectively final. This is called variable capture.
                 * 
                 * This restriction ensures that the captured variable retains a consistent
                 * value even if the enclosing method completes execution and its local
                 * variables go out of scope.
                 * 
                 * When a nested class captures a variable, Java effectively makes a copy of
                 * that variable's value at the time of capture. This copy is stored in the heap
                 * as part of the nested class instance, allowing the nested class to use it
                 * independently of the original variable.
                 * 
                 * Note that this variable capture applies only to the variables declared in the
                 * enclosing method and not to any variable declared in the enclosing class or
                 * to the variable declared inside the local class.
                 * 
                 * Captured variables enable closures, which are functions that can remember
                 * their surrounding context (the values of captured variables) even after that
                 * context has finished executing.
                 */
                System.out.println(methodLocalVariable); // captured variable
                System.out.println(methodParameter); // captured variable
                _staticVal = 12;
                System.out.println(_staticVal);
                System.out.println(_outerClassVariable);
            }

            interface LocalInterface {
                void DoSomething();
            }
        }

        LocalClass obj = new LocalClass();
        obj.showValue();

        LocalClass.LocalInterface interfaceObj = new LocalClass.LocalInterface() {

            @Override
            public void DoSomething() {
                System.out.println("Doing something...");
            }

        };
        interfaceObj.DoSomething();

        // Method local interface.
        interface MethodLocalInterface {
            void Example();
        }
        MethodLocalInterface someObj = new MethodLocalInterface() {

            @Override
            public void Example() {
                System.out.println(12);
                System.out.println(methodLocalVariable);
            }

        };
        someObj.Example();
    }

}

class AnonymousClass {
    abstract class Animal {
        abstract void speak();
    }

    void demoAnonymousClasses() {
        Animal animalObj = new Animal() {

            static {
                System.out.println("Static initializer inside a anaynymous class.");
            }

            @Override
            void speak() {
                System.out.println("Whooff!!");
            }
        };
        animalObj.speak();
    }
}

class LambdaExpression {
    void demoLambda() {
        Predicate<Integer> p = (Integer x) -> x == 7;
        p.test(7);
    }
}
