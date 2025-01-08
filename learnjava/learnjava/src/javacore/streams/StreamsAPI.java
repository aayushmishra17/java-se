package javacore.streams;

import java.util.*;
import java.util.stream.*;

public class StreamsAPI {

    public static void main(String... args) {
        StreamsAPI obj = new StreamsAPI();
        obj.waysToCreateStreams();
        obj.examplesOfIntermediateOps();
        obj.examplesOfOptional();
        obj.examplesOfTerminalOps();
        obj.examplesOfPrimitiveStreams();
    }

    List<String> strList = new ArrayList<>(Arrays.asList("a", "bb", "ccc", "dddd", "eeeee"));
    List<Integer> intList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
    List<List<String>> listOfStringLists = List.of(
            Arrays.asList("apple", "banana", "cranberry"),
            Arrays.asList("dates", "eggplant", "fig"),
            Arrays.asList("grapes", "hazelnut", "ice apple"));

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
        Stream<Integer> infiniteIntStream = Stream.generate(() -> 7); // Infinite unordered sequential stream.
        infiniteIntStream.limit(100L);
        Stream.iterate(10, x -> x + 1).limit(100L); // Infinite ordered sequential stream.
        Stream.iterate(10, x -> x < 100, x -> x + 1);

        // 5. Empty stream
        Stream<Integer> emptyIntegerStreamObj = Stream.<Integer>empty();

        // 6. Stream builder
        Stream.Builder<String> streamBuilder = Stream.<String>builder();
        Stream<String> builtStream = streamBuilder.add("one").add("two").add("three").add("four").build();
    }

    private void examplesOfIntermediateOps() {

        Stream<Integer> streamOfInteger = Stream.of(1, 2, 3, 4, 5);
        /*
         * Cannot call more than one intermediate or terminal method on an already used
         * stream instance streamOfInteger.
         */
        // Stream<Integer> s2 = streamOfInteger.map(n -> n + 1);
        List<Integer> l1 = streamOfInteger.toList(); // IllegalStateException: stream has already been operated upon or closed.
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
         * Handles streams of collections, lists, or arrays where each element is itself a collection.
         * Flattens nested structures (e.g. lists within lists) so that they can be processed as a single sequence of elements.
         */
        Stream<String> stringStream = listOfStringLists.stream().flatMap(list -> list.stream());
        System.out.println(stringStream.toList());

        listOfStringLists.stream().flatMap(Collection::stream).map(String::toUpperCase);
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

        Stream<Integer> streamOfInts = strList.stream().<Integer>mapMulti((value, consumer) -> {
            consumer.accept(value.length());
        });

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
        Map<String, Map<String, Integer>> groupedCars = carsList.stream().collect(Collectors.groupingBy(
                car -> car.type,
                Collectors.toMap(car -> car.make, car -> car.engineCapacity)));
        System.out.println("Grouped cards: " + groupedCars);

        // Filter
        strList.stream().filter(str -> str.length() > 3);

        // Limit
        Stream.iterate(1, x -> x + 1).limit(100L);

        // Skip
        Stream.iterate(1, x -> x + 1).skip(10L).limit(100L);

        // Distinct
        intList.stream().distinct();

        // Peek - performs an action on each element as it is consumed.
        Stream.iterate(1, x -> x + 1).skip(10L).limit(100L).peek(System.out::println).count();

        // dropWhile(predicate)
        Stream<Integer> droppedElementsStream = intList.stream().dropWhile(num -> num < 2);
        System.out.println("droppedElementsStream = " + droppedElementsStream.toList());

        // takeWhile(predicate)
        Stream<Integer> takeWhileElementsStream = intList.stream().takeWhile(num -> num < 3);
        System.out.println("takeWhileElementsStream = " + takeWhileElementsStream.toList());
    }

    private void examplesOfOptional() {
        /*
         * Optional<T>
         * Any operation that doesn't have an identity element will return optional object.
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
        System.out.println(optional.orElse("substitute value")); // returns passed substitute value in case the optional is null.

        System.out.println(optional.orElseGet(() -> "100")); // if optional value is null then call the passed Supplier.

        optional = Optional.of("some value");
        optional.orElseThrow(); // return the value if present else throw exception.
        optional.orElseThrow(NoSuchElementException::new); // return the value if present else throw exception provided
                                                           // by Supplier.

        optional = optional.map(str -> str.toUpperCase()); // Optional also has map, flatMap, filter, stream.
    }

    private void examplesOfTerminalOps() {

        List<Integer> list = Arrays.asList(1,2,3);

        // 1. Stream.collect()
        list.stream().collect(Collectors.toList()); // returns modifiable list.
        list.stream().toList(); // returns un-modifiable list.

        // 2. Stream.foreach()
        list.stream().forEach(System.out::println);

        // 3. Stream.reduce() combines elements to produce a single result.
        Optional<Integer> reducedValue = list.stream().reduce((x, y) -> x + y);

        // 4. Stream.count()
        list.stream().count();

        // 5. anyMatch, allMatch, noneMatch
        boolean anyMatch = list.stream().anyMatch(val -> val == 1);
        boolean allMatch = list.stream().allMatch(val -> val > 0);
        boolean noneMatch = list.stream().noneMatch(val -> val > 10);

        // 6. findFirst, findAny
        Optional<Integer> firstElement = list.stream().findFirst(); // returns the first element of the stream.
        Optional<Integer> anyElement = list.stream().findAny(); // returns any element from the stream.

        // 7. toArray() - converts a stream to an array.
        Object[] array = Stream.of(1, 2, 3).toArray();

        // 8. min/max
        Stream.of(2,44,69).max(Comparator.naturalOrder());
        Stream.of(2,44,69).max((o1, o2) -> o1 - o2);

        Stream.of(2,44,69).min(Comparator.naturalOrder());

        Stream.of(2,44,69).max(Comparator.reverseOrder());
        Stream.of(2,44,69).max((o1, o2) -> o2 - o1); // will return Optional[2].

        // 9. forEachOrdered
        /*
        Operates on the elements in "encounter order"(which simply means the order in which the stream encounters data),
        so it maintains the order of data.
         */
        intList.parallelStream().forEach(System.out::println);
        intList.parallelStream().forEachOrdered(System.out::println);

        // Few examples of terminal operation in use.
        list.stream().mapToInt(val -> val).sum();
        list.stream().mapToInt(x -> x).reduce(0, Integer::sum);

        String s = "Hello World"; // Count number of 'l'
        s.chars().filter(c -> c == 'l').count(); // String has a chars() method that returns IntStream representing the characters in the stream.

    }

    private void examplesOfPrimitiveStreams() {

        IntStream.range(1,5).boxed().collect(Collectors.toList());
        IntStream.rangeClosed(1,5).boxed().collect(Collectors.toList());

        IntStream.of(1,2,3,4,5);

        DoubleStream doubles = new Random().doubles(5);
        System.out.println(doubles.boxed().toList());

        IntStream intStream = new Random().ints(5);
        System.out.println(intStream.boxed().toList());

    }
}
