package jug.lodz.workshop.javafp.streams.answers;

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
public class StreamsPart4LazyComputationExercisesAnswer {

    @Test
    public void generate7RandomNumbers() throws Exception {
        List<Double> result = Stream
                .generate(Math::random)
                .limit(7)
                .collect(Collectors.toList());

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
                .noneMatch(i -> candidate % i == 0);
    }

    @Test
    public void testFibonacci() throws Exception {
        List<Integer> fib10 = fibonacci(10);

        assertThat(fib10).containsExactly(0,1,1,2,3,5,8,13,21,34);
    }

    private List<Integer> fibonacci(int limit){
        Tuple2<Integer, Integer> seed = Tuple.of(0, 1);

        UnaryOperator<Tuple2<Integer,Integer>> step=
                t->Tuple.of(t._2,t._1+t._2);

        return Stream.iterate(seed, step)
                .limit(limit)
                .map(t -> t._1)
                .collect(Collectors.toList());
    }
}

