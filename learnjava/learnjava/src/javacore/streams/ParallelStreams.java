package javacore.streams;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class ParallelStreams {

    public static void main(String... args) {

        ParallelStreams obj = new ParallelStreams();
        obj.ExampleOfParallelStreams();

    }

    private void ExampleOfParallelStreams() {

        /*
        Type of streams that enable parallel processing of its elements,
        allowing multiple threads to process parts of the stream simultaneously.
        Should be used when working with CPU intensive operation or large datasets where tasks are independent.
         */
        long starTime = System.currentTimeMillis();
        Stream<Integer> list = Stream.iterate(1, x -> x + 1).limit(20000L);

        // Calculating factorial of a number is an independent task so it can be done in parallel.
        Function<Integer, Long> factorial = (n) -> {
          long result = 1;
          for(int i = 2; i<= n; i++) {
              result *= i;
          }
          return result;
        };
        List<Long> factorialList = list.map(factorial::apply).toList();
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken with sequential stream: " + (endTime - starTime) + " ms");

        starTime = System.currentTimeMillis();
        list = Stream.iterate(1, x -> x + 1).limit(20000L);
        factorialList = list.parallel().map(factorial::apply).toList();
        endTime = System.currentTimeMillis();
        System.out.println("Time taken with parallel stream: " + (endTime - starTime) + " ms");
    }
}
