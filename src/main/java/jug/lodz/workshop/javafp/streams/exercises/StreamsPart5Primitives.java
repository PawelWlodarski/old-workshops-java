package jug.lodz.workshop.javafp.streams.exercises;

import javaslang.Tuple;
import jug.lodz.workshop.Printer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.IntUnaryOperator;
import java.util.function.LongBinaryOperator;
import java.util.function.LongUnaryOperator;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Created by pawel on 08.06.16.
 */
public class StreamsPart5Primitives implements Printer {

    private static StreamsPart5Primitives lab = new StreamsPart5Primitives();

    public static void main(String[] args) {
        lab.demo();
    }

    private void demo() {

        print("\n\nPRIMITIVES\n\n");

        println("\n * MEASURES");

//        start();

        long numberOfElements = 1000000000;

        Runnable boxed = () -> {
            Long result = Stream
                    .iterate(0L, i -> i + 1)
                    .map(i->i*i)
                    .limit(numberOfElements)
                    .reduce(0L,Long::sum);


            println(result);
        };

//        measure("boxed",boxed);


        LongUnaryOperator step = i -> i + 1;
        LongUnaryOperator intSquare = i -> i * i;
        LongBinaryOperator sum = (i1, i2) -> i1 + i2;

        Runnable primitives = () -> {
            long primitiveResult = LongStream
                    .iterate(0, step)
                    .map(intSquare)
                    .limit(numberOfElements)
                    .reduce(0, sum);

            println(primitiveResult);
        };

//        measure("primitives",primitives);


        println("\n * DIFFERENT HIERARCHIES AND LACK OF ZIP");

        UnaryOperator<Integer> boxedOperator=i->i+1;
        IntUnaryOperator primitiveOperator=i->i+1;

        ArrayList<Integer> primitivesCollected = IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .map(primitiveOperator)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        List<Integer> boxedCollected = IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .map(primitiveOperator)
//                .map(boxedOperator)   //move boxed
                .boxed()
                .collect(Collectors.toList());

        println("    * primitives : "+primitivesCollected);
        println("    * boxed : "+boxedCollected);

        println("\n * MAP TO INT");
//CODE
//        int sumOfTransactions = Stream.of(
//                Tuple.of("t1", 100),
//                Tuple.of("t2", 200),
//                Tuple.of("t3", 300),
//                Tuple.of("t4", 100)
//        ).mapToInt(t -> t._2)
//                .sum();// no reduce!
//
//
//        println("    * sum of transactions : "+sumOfTransactions);
    }


    private void measure(String label, Runnable action) {
        long start = System.currentTimeMillis();
        action.run();
        long stop = System.currentTimeMillis();

        println("time in : " + label + " is " + (stop - start) / 1000.0 + " seconds");

    }

    private void start() {
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
