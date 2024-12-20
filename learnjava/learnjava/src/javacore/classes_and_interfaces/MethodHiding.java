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
        BaseLogger logger1 = new DevLogger();
        BaseLogger logger2 = new ProdLogger();

        // Calls the configure method from BaseLogger
        logger1.configure(); // Output: Configuring Base Logger
        logger2.configure(); // Output: Configuring Base Logger

        // Direct calls to the specific logger configurations
        DevLogger.configure(); // Output: Configuring Development Logger
        ProdLogger.configure(); // Output: Configuring Production Logger
    }
}

class BaseLogger {
    static void configure() {
        System.out.println("Configuring base logger...");
    }
}

class DevLogger extends BaseLogger {
    static void configure() {
        System.out.println("Configuring dev logger...");
    }
}

class ProdLogger extends BaseLogger {
    static void configure() {
        System.out.println("Configuring production logger...");
    }
}