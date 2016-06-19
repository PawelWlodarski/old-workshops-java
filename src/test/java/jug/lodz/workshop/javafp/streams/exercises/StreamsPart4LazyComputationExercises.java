package jug.lodz.workshop.javafp.streams.exercises;

import javaslang.Tuple;
import javaslang.Tuple2;
import org.junit.Test;

import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by pawel on 07.06.16.
 */
public class StreamsPart4LazyComputationExercises {

    @Test
    public void generate7RandomNumbers() throws Exception {
        List<Double> result = Stream
                .<Double>generate(null)
                .limit(7)
                .collect(null);

//        System.out.println(result);

        assertThat(result).hasSize(7);
    }


    @Test
    public void testIsPrime() throws Exception {
        assertThat(isPrime(7)).isTrue();
        assertThat(isPrime(13)).isTrue();
        assertThat(isPrime(21)).isFalse();
    }

    /**
     *
            1)in noneMatch check if candidate can be divided by i
     */
    private boolean isPrime(int candidate) {
        return IntStream.range(2, candidate)
                .noneMatch(null);
    }

    @Test
    public void testFibonacci() throws Exception {
        List<Integer> fib10 = fibonacci(10);

        assertThat(fib10).containsExactly(0,1,1,2,3,5,8,13,21,34);
    }

    private List<Integer> fibonacci(int limit){
        Tuple2<Integer, Integer> seed = null; // tuple of 0 & 1

        UnaryOperator<Tuple2<Integer,Integer>> step= null;   // t[2],t[1]+t[2]

        return Stream.iterate(seed, step)
                .limit(limit)
                .<Integer>map(null)  // tuple -> first element
                .collect(Collectors.toList());
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

