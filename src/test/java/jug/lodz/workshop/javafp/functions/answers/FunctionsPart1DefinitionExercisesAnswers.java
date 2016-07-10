package jug.lodz.workshop.javafp.functions.answers;

import javaslang.Tuple;
import javaslang.Tuple2;
import org.junit.Test;

import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by pawel on 19.06.16.
 */
public class FunctionsPart1DefinitionExercisesAnswers {

    // LEVEL 1

    /**
     *
     * Define two simple functions so that test pass
     */
    @Test
    public void defineSimpleFunctions() throws Exception {
        Function<Integer,Integer> square=i->i*i;
        Function<Integer,Boolean> isEven=i->i%2==0;

        assertThat(square.apply(2)).isEqualTo(4);
        assertThat(square.apply(3)).isEqualTo(9);

        assertThat(isEven.apply(1)).isFalse();
        assertThat(isEven.apply(2)).isTrue();

    }

    /**
     * Define two simple functions on tuple so that tests pass
     * tuple._1 - returns first element
     * tuple._2 - returns second element
     */
    @Test
    public void operationsOnTuple() throws Exception {
        Function<Tuple2<String,Integer>,Integer> snd= t->t._2;
        Function<Tuple2<String,Integer>,Tuple2<Integer,String>> swap=t-> Tuple.of(t._2,t._1);


        assertThat(snd.apply(Tuple.of("a",1))).isEqualTo(1);
        assertThat(snd.apply(Tuple.of("b",2))).isEqualTo(2);

        assertThat(swap.apply(Tuple.of("a",1))).isEqualTo(Tuple.of(1,"a"));
        assertThat(swap.apply(Tuple.of("b",2))).isEqualTo(Tuple.of(2,"b"));
    }

    /**
     * Complete 3 functions so their composition passes tests
     * fst -> return first element of a tuple
     * parse -> parse string to int
     * square -> compute square of input
     */
    @Test
    public void composition() throws Exception {
        Function<Tuple2<String,Integer>,String> fst=t->t._1;
        Function<String,Integer> parse=s->Integer.parseInt(s);
        Function<Integer,Integer> square=i->i*i;

        Function<Tuple2<String, Integer>, Integer> squareFst = fst.andThen(parse).andThen(square);

        assertThat(squareFst.apply(Tuple.of("2",1))).isEqualTo(4);
        assertThat(squareFst.apply(Tuple.of("3",1))).isEqualTo(9);


    }

    @Test
    public void closure() throws Exception {
        int counter=20;

        Function<Integer,Integer> addToCounter=i->counter+i;

        assertThat(addToCounter.apply(5)).isEqualTo(25);
        assertThat(addToCounter.apply(7)).isEqualTo(27);

    }

    //LEVEL2

    /**
     * Practice Function as a value
     * @throws Exception
     */
    @Test
    public void testCustomAndThen() throws Exception {
        Function<String, Double> squareStr = andThen(s -> Integer.parseInt(s), i -> Math.sqrt(i));

        assertThat(squareStr.apply("4")).isEqualTo(2.0);
        assertThat(squareStr.apply("9")).isEqualTo(3.0);
    }

    /**
     *
     * You can use "andThen" and "compose" from Function interface
     *
     * @param f1 -> String to int
     * @param f2 -> int to double
     * @return
     */
    private Function<String,Double> andThen(Function<String,Integer> f1,Function<Integer,Double> f2){
            return s->{
                Integer r1=f1.apply(s);
                return f2.apply(r1);
            };
    }


    //LEVEL3


    /**
     *  implement andThen generic
     */
    @Test
    public void testAndThenGeneric() throws Exception {
        Function<String, Boolean> isStrEven = andThenGeneric((String s) -> Integer.parseInt(s), i -> i %2==0);// Explicit Type needed!
        Function<Integer, String> squareAndToString = andThenGeneric((Integer i) -> i * i, i -> "result is : " + i);


        assertThat(isStrEven.apply("2")).isTrue();
        assertThat(isStrEven.apply("3")).isFalse();


        assertThat(squareAndToString.apply(3)).isEqualTo("result is : 9");
        assertThat(squareAndToString.apply(4)).isEqualTo("result is : 16");

    }

    /**
     *
     *define generic andThen function
     *
     */
    // define in comments Function<???,???> f1, Function<???,???> f2
    private <A,B,C> Function<A,C> andThenGeneric(Function<A,B> f1, Function<B,C> f2){
        return (A s)->{
            B r1=f1.apply(s);
            return f2.apply(r1);
        };
    }



}
