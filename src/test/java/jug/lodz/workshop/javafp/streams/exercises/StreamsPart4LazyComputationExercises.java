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

    //DO THOSe EXERCISES TOGETHER

    @Test
    public void generate7RandomNumbers() throws Exception {
        List<Double> result = Stream
                .<Double>generate(null)    // Math.rand
                .limit(7)
                .collect(null);       // we need a list

//        System.out.println(result);

        assertThat(result).hasSize(7);
    }


    /**
     * don't modify test - implement 'isPrime' method
     */
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


