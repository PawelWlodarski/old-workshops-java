package jug.lodz.workshop.javafp.functions.answers;

import javaslang.Tuple;
import javaslang.Tuple2;
import org.junit.Test;

import java.util.HashMap;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by pawel on 30.06.16.
 */
public class FunctionsPart4CreatingFunctionsExercisesAnswer {
    @Test
    public void testDecrement() throws Exception {
        Function<Integer, Integer> decrement = createDecrement();

        assertThat(decrement.apply(10)).isEqualTo(9);
        assertThat(decrement.apply(1)).isEqualTo(0);
        assertThat(decrement.apply(0)).isEqualTo(-1);

    }

    private Function<Integer, Integer> createDecrement() {
        return i -> i - 1;
    }

    @Test
    public void testAdd() throws Exception {
        BiFunction<Integer, Integer, Integer> add = createAdd();

        assertThat(add.apply(2, 3)).isEqualTo(5);
        assertThat(add.apply(7, 3)).isEqualTo(10);
    }

    private BiFunction<Integer, Integer, Integer> createAdd() {
        return (i1, i2) -> i1 + i2;
    }

    @Test
    public void testGreaterThan() throws Exception {
        Predicate<Integer> greaterThan20 = createGreaterThan(20);

        assertThat(greaterThan20.test(25)).isTrue();
        assertThat(greaterThan20.test(15)).isFalse();
    }

    private Predicate<Integer> createGreaterThan(Integer comparison) {
        return input -> input > comparison;
    }


    //Level2

    @Test
    public void testCurrying() throws Exception {
        Function<Integer,Function<Integer,Integer>> returnGreater=i1->i2->i1>i2?i1:i2;

        assertThat(returnGreater.apply(7).apply(5)).isEqualTo(7);
        assertThat(returnGreater.apply(7).apply(13)).isEqualTo(13);
    }

    // gross=net + net*tax
    @Test
    public void testNetToGross() throws Exception {
        Function<Double,Function<Integer,Double>> gross= tax->net->net+tax*net;

        Function<Integer, Double> netToGross = gross.apply(0.23);

        assertThat(netToGross.apply(10)).isEqualTo(12.3);
    }

    @Test
    public void testGreaterThanCurrying() throws Exception {
        Function<Integer,Predicate<Integer>> greaterThanCurrying=comparison->number->number>comparison;

        assertThat(greaterThanCurrying.apply(50).test(51)).isTrue();
        assertThat(greaterThanCurrying.apply(50).test(49)).isFalse();

    }


    @Test
    public void testCurry() throws Exception {
        BinaryOperator<Integer> add=(i1,i2)->i1+i2;

        Function<Integer, Function<Integer, Integer>> curried = curry(add);

        assertThat(curried.apply(1).apply(2)).isEqualTo(3);
    }

    private <A,B,C> Function<A,Function<B,C>> curry(BiFunction<A,B,C> f){return a->b->f.apply(a,b);}

    @Test
    public void testUncurry() throws Exception {
        Function<Integer, Function<Integer, Integer>> f=i1->i2->i1+i2;

        BiFunction<Integer, Integer, Integer> uncurried = unCurry(f);

        assertThat(uncurried.apply(1,2)).isEqualTo(3);
    }

    private <A,B,C> BiFunction<A,B,C>  unCurry(Function<A,Function<B,C>> f){return (a,b)->f.apply(a).apply(b);}

    //LEVEL 3 - HARD

    @Test
    public void testMemoization() throws Exception {
        Function<String,Double> sumOfSquares=s->{
            int parsed = Integer.parseInt(s);
            double squared=Math.sqrt(parsed);
            return squared+squared;
        };

        Function<String, Tuple2<Double, Boolean>> memoized = memoize(sumOfSquares);

        memoized.apply("9"); // add to cache

        assertThat(memoized.apply("9")).isEqualTo(Tuple.of(6.0,true));
        assertThat(memoized.apply("16")).isEqualTo(Tuple.of(8.0,false));

    }

    /**
     *
     * @param f - original one parameter function
     * @param <A> - parameter type
     * @param <B> - original result type
     * @return Tuple(result : B,wasInCache : Boolean) where was in cache = True|False
     */
    private <A,B> Function<A, Tuple2<B,Boolean>> memoize(Function<A,B> f){
        HashMap<A, B> cache = new HashMap<>();
        return a->{
            if(cache.containsKey(a)){
                return Tuple.of(cache.get(a),true);
            }else{
                B newValue = f.apply(a);
                cache.put(a,newValue);
                return Tuple.of(newValue,false);
            }

        };
    }

    //MEMOIZATION
}
