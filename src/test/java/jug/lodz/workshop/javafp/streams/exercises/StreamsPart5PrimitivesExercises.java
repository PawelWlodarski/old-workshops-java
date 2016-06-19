package jug.lodz.workshop.javafp.streams.exercises;

import org.junit.Test;

import java.math.BigInteger;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by pawel on 19.06.16.
 */
public class StreamsPart5PrimitivesExercises {


    @Test
    public void sumOfFirstNIntegers() throws Exception {

        assertThat(sum(5)).isEqualTo(15);
        assertThat(sum(6)).isEqualTo(21);
        assertThat(sum(7)).isEqualTo(28);
    }

    private int sum(int n){
        return IntStream
                .iterate(1,null) //<- FIX
                .limit(0)  //<- FIX
                .sum();
    }

    @Test
    public void findMaxInt() throws Exception {
        OptionalInt optionalMax = IntStream
                .of(11, 22, 4, 5, 9, 1, 79, 2, 3)
                .findAny();  // <- FIX

        assertThat(optionalMax).hasValue(79);

    }

    @Test
    public void calculateIntegerSum() throws Exception {
        int sum = Stream.of(
                new BigInteger("10"),
                new BigInteger("20"),
                new BigInteger("30")
        ).mapToInt(null).hashCode();  // <- FIX BOTH

        assertThat(sum).isEqualTo(60);
    }
}
