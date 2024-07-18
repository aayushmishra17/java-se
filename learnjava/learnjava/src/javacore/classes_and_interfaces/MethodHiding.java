package javacore.classes_and_interfaces;

/*
* Method Hiding in Java.
*/
public class MethodHiding {
    public static void main(String[] args) {
        MethodHiding outterClassObj = new MethodHiding();
        /*
         * Method hiding - compile time polymorphism.
         * The actual method called depends on the reference type and not the type of
         * object.
         */
        BaseClass baseClassRefObj1 = outterClassObj.new BaseClass(); // base class obj type
        BaseClass baseClassRefObj2 = outterClassObj.new DerivedClass(); // derived class obj type
        DerivedClass derivedClassObjRef = outterClassObj.new DerivedClass(); // derived class obj type

        baseClassRefObj1.exampleStaticMethod();
        baseClassRefObj2.exampleStaticMethod();
        derivedClassObjRef.exampleStaticMethod();
    }

    public class BaseClass {
        public static void exampleStaticMethod() {
            System.out.println("In BaseClass -> exampleStaticMethod");
        }
    }

    public class DerivedClass extends BaseClass {
        public static void exampleStaticMethod() {
            System.out.println("In DerivedClass -> exampleStaticMethod");
        }

    }
}
