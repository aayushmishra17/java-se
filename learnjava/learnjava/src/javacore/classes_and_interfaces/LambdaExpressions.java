package javacore.classes_and_interfaces;

import java.util.function.Consumer;

public class LambdaExpressions {

    public static void main(String[] args) {
        new LambdaExpressions().lambdaExample();
    }

    void lambdaExample() {

        Consumer<int[]> printer = ids -> {

            int printedCount = 0;
            for (int id : ids) {
                if (id % 2 != 0) {
                    continue;
                }
                System.out.println(id);
                printedCount++;
                if (printedCount == 3) {
                    break;
                }
            }

        };

        printer.accept(new int[] { 1, 2, 3, 4, 5, 6, 7, 8 });
    }

    void test() {

        for (int i = 0; i < 5; i++) {
            Consumer<Integer> evenIdPrinter = id -> {
                if (id < 0) {
                    // break; // Cannot attempt to jump out of a lambda expression.
                }
            };
            evenIdPrinter.accept(10);
        }

    }

}

/*
 * Lambdas and anonymous classes are also reffered to as closures in java.
 */
class ClosureExample {
    void demoClosure() {
        int outerVariable = 10; // This variable will be captured by the closure

        // Lambda expression as a closure
        Runnable closure = () -> {
            System.out.println("Outer variable: " + outerVariable);
        };

        // Execute the closure
        closure.run(); // Outputs: Outer variable: 10
    }
}
