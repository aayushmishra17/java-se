package javacore.streams;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsDemo {

    public static void main(String[] args) {

        CollectorsDemo obj = new CollectorsDemo();
        obj.DemoCollectors();

    }

    private void DemoCollectors() {
        /*
        Collectors is a utility class that provides a set of methods to create common collectors.
        Parallelism and concurrency are handled by the Collector API itself, so we can safely use Collectors with parallel streams.
         */

        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "Brian", "Alexander");
        List<Integer> nums = Arrays.asList(1, 2, 2, 2, 3, 4, 4, 5);
        List<String> words = Arrays.asList("hello", "world", "java", "streams", "collectors");

        // Collecting to a List
        names.stream()
                .filter(name -> name.startsWith("A"))
                .collect(Collectors.toList());
        /*
        Collectors.toList() returns a modifiable list.
        Stream.toList() returns a unmodifiable list.
         */

        // Collecting to a Set
        Set<Integer> set = nums.stream().collect(Collectors.toSet());

        // Collecting to a specific collection
        ArrayDeque<String> arrayDequeOfString = names.stream().collect(Collectors.toCollection(() -> new ArrayDeque<>()));
        ArrayList<Integer> integerArrayList = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).collect(Collectors.toCollection(() -> new ArrayList<>(10)));

        List<Integer> unmodifiableIntegerList = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).collect(Collectors.toUnmodifiableList());
        unmodifiableIntegerList = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).toList(); // JDK-16+ we should prefer this for performance.

        Set<Integer> integerSet = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).collect(Collectors.toSet());
        Set<Integer> unmodifiableIntegerSet = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).collect(Collectors.toUnmodifiableSet());

        // Collecting to an array
        Integer[] integerArray = Stream.of(1,2,3,4,5,6,7,8,9,10).toArray(Integer[]::new);

        // Joining Strings - concatenates stream of elements into a single String.
        names.stream().map(String::toUpperCase).collect(Collectors.joining(", "));

        // Summarizing data - generates statistical summary.
        IntSummaryStatistics intSummaryStats = nums.stream().collect(Collectors.summarizingInt(x -> x));
        intSummaryStats.getCount();
        intSummaryStats.getSum();
        intSummaryStats.getMin();
        intSummaryStats.getAverage();
        intSummaryStats.getMax();

        // Calculating average
        nums.stream().collect(Collectors.averagingInt(x -> x));

        // Counting elements
        nums.stream().collect(Collectors.counting());

        // Grouping elements
        Map<Integer, List<String>> groupedWords = words.stream().collect(Collectors.groupingBy(String::length));
        Map<Integer, String> groupedWordsJoined = words.stream().collect(Collectors.groupingBy(String::length, Collectors.joining(", ")));
        Map<Integer, Long> groupWordsAndCount = words.stream().collect(Collectors.groupingBy(String::length, Collectors.counting()));
        // The following overload takes a Supplier that tells the collector to collect into a specific Map collection.
        TreeMap<Integer, Long> groupAndCountIntoTreemap = words.stream().collect(Collectors.groupingBy(String::length, TreeMap::new, Collectors.counting()));
        HashMap<Integer, Long> groupAndCountIntoHashmap = words.stream().collect(Collectors.groupingBy(String::length, HashMap::new, Collectors.counting()));

        // Partitioning elements - partitions elements into two groups(true and false) based on a predicate.
        Map<Boolean, List<String>> partitionedByLength = words.stream().collect(Collectors.partitioningBy(x -> x.length() > 5));

        // Mapping and collecting - this can be done instead of calling .map() and then .collect()
        List<String> mapAndCollect = words.stream().collect(Collectors.mapping(x -> x.toUpperCase(), Collectors.toList()));

        // Few examples -

        // Example 1: Grouping names by length
        names.stream().collect(Collectors.groupingBy(String::length));

        // Example 2: Counting word occurrences
        String sentence = "hello world hello java world";
        Arrays.stream(sentence.split(" ")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        // Example 3: Partitioning even and odd numbers.
        List<Integer> listOfNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Map<Boolean, List<Integer>> partitionByEvenOdd = listOfNumbers.stream().collect(Collectors.partitioningBy(x -> x % 2 == 0));

        // Example 4: Summing values in a map.
        Map<String, Integer> itemsMap = new HashMap<>();
        itemsMap.put("Apple", 10);
        itemsMap.put("Banana", 20);
        itemsMap.put("Orange", 15);
        Optional<Integer> sumOfValues = itemsMap.values().stream().reduce(Integer::sum);
        int sum = itemsMap.values().stream().collect(Collectors.summingInt(x -> x));

        // Example 5: Creating a Map from stream elements.
        List<String> fruits = Arrays.asList("Apple", "Banana","Cherry");
        Map<String, Integer> fruitMap = fruits.stream().collect(Collectors.toMap(x -> x.toUpperCase(), x -> x.length()));

        fruits = Arrays.asList("apple", "banana", "apple", "orange", "banana", "apple");
        /*
        This overload of toMap() also takes a mergeFunction which dictates how each duplicate elements are merged.
         */
        Map<String, Integer> countOfEachWord = fruits.stream().collect(Collectors.toMap(Function.identity(), v -> 1, (x, y) -> x + y));
    }

}
