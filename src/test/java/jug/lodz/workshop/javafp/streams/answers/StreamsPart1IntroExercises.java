package jug.lodz.workshop.javafp.streams.answers;

import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by pawel on 07.06.16.
 */
public class StreamsPart1IntroExercises {


    // Level 1
    @Test
    public void simpleMapping() throws Exception {
        List<Integer> start = Arrays.asList(1, 2, 3, 4, 5);

        Stream<Integer> result = start.stream().map(e->e+1); //delete map(e->e+1)

        assertThat(result.collect(Collectors.toList())).containsExactly(2,3,4,5,6);
    }

    // Level 2
    @Test
    public void mapReduceTest() throws Exception {
        List<String> input = Arrays.asList("1", "2", "3","4");
        Function<String,Integer> toInt = Integer::parseInt;
        BinaryOperator<Integer> multiply=(i1,i2)->i1*i2;

        Integer result = basicMapReduce(toInt, multiply, 1, input.stream());

        assertThat(result).isEqualTo(24);

    }

    private <A,B> B basicMapReduce(Function<A,B> map, BinaryOperator<B> reduce, B identity, Stream<A> input){
        return input.map(map).reduce(identity,reduce);
    }
}
