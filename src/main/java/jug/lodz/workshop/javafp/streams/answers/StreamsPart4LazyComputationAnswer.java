package jug.lodz.workshop.javafp.streams.answers;

import jug.lodz.workshop.Printer;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by pawel on 07.06.16.
 */
public class StreamsPart4LazyComputationAnswer implements Printer {

    private static StreamsPart4LazyComputationAnswer lab = new StreamsPart4LazyComputationAnswer();

    public static void main(String[] args) {
        lab.demo();
    }


    private void demo() {
        print("\n\nLAZY STREAMS\n\n");

        println("\n * LAZY NATURE");


        Predicate<Integer> greaterThanThreeWithLogging = i -> {
            println("    * filtering i>3 : " + i);
            return i > 3;
        };
        Function<Integer, Integer> squareWithLogging = i -> {
            println("    * mapping i*i : " + i);
            return i * i;
        };


        println("\ncalculating result : 1) filter 2) map");

        List<Integer> resultFilterMap = Stream.of(1, 2, 3, 4, 5)
                .filter(greaterThanThreeWithLogging)
                .map(squareWithLogging).collect(Collectors.toList());


        println("  *  calculated result filter map: " + resultFilterMap);


        println("\n\ncalculating result : 1) map 2) filter");

        List<Integer> resultMapFilter = Stream.of(1, 2, 3, 4, 5)
                .map(squareWithLogging)
                .filter(greaterThanThreeWithLogging)
                .collect(Collectors.toList());

        println("  *  calculated result map: filter " + resultMapFilter);


        println("\n * EAGER EXAMPLE");

        println("\ncalculating eager example : 1) filter 2) map");

        javaslang.collection.List<Integer> slangResult = javaslang.collection.List.of(1, 2, 3, 4, 5)
                .filter(greaterThanThreeWithLogging)
                .map(squareWithLogging);


        println("\n * SHORT CIRCUIT");

        println("\ncalculating firts five squares");

        List<Integer> firstFiveSquares = Stream
                .iterate(1, i -> i + 1)
                .map(squareWithLogging)
                .limit(5)
                .collect(Collectors.toList());

        println("  *  calculated first five squares " + firstFiveSquares);


        println("\n * PEEK");

        Stream
                .iterate(1, i -> i + 1)
                .map(i -> i * i)
                .peek(i -> println("peeking :" + i))   // SHOw FUNCTION DEFINITION
                .filter(i -> i > 10)
                .limit(5)
                .forEach(i -> println("displaying : " + i));

    }


}



/**
 * Stream.iterate(0, n -> n + 2)
 * .limit(10)
 * .forEach(System.out::println);
 * <p>
 * Fibonacci
 * Stream.iterate(new int[]{0, 1},
 * t -> new int[]{t[1],t[0] + t[1]})
 * .limit(10)
 * .map(t -> t[0])
 * .forEach(System.out::println);
 * <p>
 * Stream.generate(Math::random)
 * .limit(5)
 * .forEach(System.out::println)
 * <p>
 * public boolean isPrime(int candidate) {
 * return IntStream.range(2, candidate)
 * .noneMatch(i -> candidate % i == 0);
 * }
 */

/**
 Fibonacci
 Stream.iterate(new int[]{0, 1},
 t -> new int[]{t[1],t[0] + t[1]})
 .limit(10)
 .map(t -> t[0])
 .forEach(System.out::println);
 */

/**
 Stream.generate(Math::random)
 .limit(5)
 .forEach(System.out::println)
 */

/**
 public boolean isPrime(int candidate) {
 return IntStream.range(2, candidate)
 .noneMatch(i -> candidate % i == 0);
 }
 */