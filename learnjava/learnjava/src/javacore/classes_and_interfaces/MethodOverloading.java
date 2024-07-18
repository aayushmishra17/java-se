package javacore.classes_and_interfaces;

public class MethodOverloading {
    public static void main(String[] args) {
        MethodOverloading outterClassObj = new MethodOverloading();
        /*
         * Method overloading in Java - run time polymorphism.
         * The actual method called depends on the type of object and not the reference
         * type. This is called dynamic method dispatch.
         */
        BaseClass baseClassObjRef1 = outterClassObj.new BaseClass();
        BaseClass baseClassObjRef2 = outterClassObj.new DerivedClass();
        DerivedClass derivedClassObjRef = outterClassObj.new DerivedClass();

        baseClassObjRef1.exampleMethod(); // base class obj type
        baseClassObjRef2.exampleMethod(); // derived class obj type
        derivedClassObjRef.exampleMethod(); // derived class obj type
    }

    public class BaseClass {
        public void exampleMethod() {
            System.out.println("In BaseClass -> exampleMethod");
        }
    }

    public class DerivedClass extends BaseClass {
        @Override
        public void exampleMethod() {
            System.out.println("In DerivedClass -> exampleMethod");
        }
    }
}
