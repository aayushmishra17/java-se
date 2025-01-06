package javacore.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.*;

public class StreamsAPI {

    public static void main(String... args) {
        StreamsAPI obj = new StreamsAPI();
        obj.waysToCreateStreams();
        obj.Examples();
        obj.ExamplesOfOptional();
    }

    List<String> strList = new ArrayList<>(Arrays.asList("a", "bb", "ccc", "dddd", "eeeee"));
    List<Integer> intList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
    List<List<String>> listOfStringLists = List.of(
            Arrays.asList("a", "b", "c"),
            Arrays.asList("d", "e", "f"),
            Arrays.asList("g", "h", "i"));

    record Car(String type, String make, String model, Integer engineCapacity) {
    }

    List<Car> carsList = List.of(
            new Car("sedan", "BMW", "530", 1998),
            new Car("sedan", "Audi", "A5", 1998),
            new Car("sedan", "Mercedes", "E-Class", 2500),
            new Car("hatchback", "Skoda", "Octavia", 1600),
            new Car("hatchback", "Toyota", "TypeR", 1450));

    private void waysToCreateStreams() {
        // 1. From Collections
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Stream<Integer> streamOfInteger = list.stream();
        streamOfInteger.count();

        // 2. From Arrays
        String[] strArr = new String[] { "a", "b", "c" };
        Stream<String> streamOfString = Arrays.stream(strArr);
        streamOfString.count();

        // 3. Using Stream.of()
        Stream<String> strStream = Stream.of("abc", "def", "ghi");
        strStream.count();

        // 4. Infinite streams
        Stream<Integer> infiniteIntStream = Stream.generate(() -> 7);
        infiniteIntStream.limit(100L);
        Stream.iterate(10, x -> x + 1).limit(100L);
        Stream.iterate(10, x -> x < 100, x -> x + 1);
    }

    private void Examples() {

        Stream<Integer> streamOfInteger = Stream.of(1, 2, 3, 4, 5);
        /*
         * Cannot call more than one intermediate or terminal method on an already used
         * stream instance s1.
         */
        // Stream<Integer> s2 = s1.map(n -> n + 1);
        List<Integer> l1 = streamOfInteger.toList(); // IllegalStateException: stream has already been operated upon or
                                                     // closed.
        System.out.println(l1);

        /*
         * map - one to one mapping
         * Converts one object to another object.
         * <R> Stream<R> map(Function<? super T, ? extends R> mapper)
         */
        streamOfInteger = strList.stream().map(String::length);
        l1 = streamOfInteger.collect(Collectors.toList());
        System.out.println(l1);

        IntStream intStream = strList.stream().mapToInt(String::length);
        IntSummaryStatistics intSummStats = intStream.summaryStatistics();
        System.out.println(intSummStats);

        /*
         * flatMap - one to many mapping
         * Converts one object into multiple objects
         * 
         */
        Stream<String> stringStream = listOfStringLists.stream().flatMap(list -> list.stream());
        System.out.println(stringStream.toList());

        /*
         * mapMulti JDK 16+
         * With mapMulti we can choose to not pass any object or pass more than one
         * object.
         * Unlike flatMap(), which creates a new stream for each element's
         * transformation, mapMulti() avoids this overhead by using a shared buffer.
         * Combines map(), filter() and flatMap()
         */
        streamOfInteger = intList.stream().mapMulti((val, consumer) -> {
            consumer.accept(val);
            consumer.accept(val * val);
            consumer.accept(val * val * val);
        });
        System.out.println(streamOfInteger.collect(Collectors.toList()));

        /*
         * Collectors.partitioningBy
         */
        Map<Boolean, List<Car>> partitionedCars = carsList.stream()
                .collect(Collectors.partitioningBy(car -> car.type.equals("sedan")));
        System.out.println("Partitioned cards: " + partitionedCars);

        /*
         * Collectors.groupingBy
         */
        // ( type, (make, engineCapacity) )
        Map<String, Map<String, Integer>> grouppedCars = carsList.stream().collect(Collectors.groupingBy(
                car -> car.type,
                Collectors.toMap(car -> car.make, car -> car.engineCapacity)));
        System.out.println("Groupped cards: " + grouppedCars);

    }

    private void ExamplesOfOptional() {
        /*
         * Optional<T>
         * 
         * Optional.of() works only with non-null values.
         */
        Optional<String> optional = Optional.of("name");
        if (optional.isPresent()) {
            System.out.println(optional.get()); // get() returns a value if present otherwise throws exception.
        }
        optional.ifPresent(val -> System.out.println(val));

        /*
         * Optional.ofNullable() returns empty optional for null value.
         */
        optional = Optional.ofNullable(null);
        optional = Optional.empty(); // returns an empty optional.
        System.out.println(optional.orElse("substitute value")); // returns passed substitute value in case the optional
                                                                 // is null.

        System.out.println(optional.orElseGet(() -> "100")); // if optional value is null then call the passed Supplier.

        optional.orElseThrow(); // return the value if present else throw exception.
        optional.orElseThrow(NoSuchElementException::new); // return the value if present else throw exception provided
                                                           // by Supplier.

        optional = optional.map(str -> str.toUpperCase()); // Optional also has map, flatMap, filter, stream.
    }

}
