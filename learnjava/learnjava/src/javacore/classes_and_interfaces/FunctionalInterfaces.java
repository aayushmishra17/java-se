package javacore.classes_and_interfaces;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class FunctionalInterfaces {

    public static void main(String... args) {
        Demo.demo();
    }

}

class Demo {

    public static void demo() {
        Predicate<Integer> predicate = x -> x % 2 == 0;
        Function<Integer, Integer> function = x -> x * x;
        Consumer<Integer> consumer = x -> System.out.println(x);
        Supplier<Integer> supplier = () -> 100;

        if (predicate.test(supplier.get())) {
            consumer.accept(function.apply(supplier.get()));
        }

        BiPredicate<Integer, Integer> isSumEven = (x, y) -> x + y % 2 == 0;
        System.out.println(isSumEven.test(5, 5));
        BiConsumer<Integer, Integer> biConsumer = (x, y) -> {
            System.out.println(x);
            System.out.println(y);
        };
        biConsumer.accept(10, 11);
        BiFunction<String, String, Integer> biFUnction = (x, y) -> (x + y).length();
        System.out.println(biFUnction.apply("abc", "def"));

        UnaryOperator<Integer> unaryOperator = x -> x * 2;
        System.out.println(unaryOperator.apply(12));
        BinaryOperator<Integer> binaryOperator = (x, y) -> x + y;
        System.out.println(binaryOperator.apply(12, 12));
    }

}
