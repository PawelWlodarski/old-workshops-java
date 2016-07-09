package jug.lodz.workshop.javafp.functions.exercises;

import javaslang.Tuple;
import javaslang.Tuple2;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by pawel on 21.06.16.
 */
public class FunctionsPart2FunctionAsValueExercises {

    //LEVEL1

    @Test
    public void testSpecificMap() throws Exception {
        List<String> input = Arrays.asList("word", "longer", "sh");
        Function<String,Integer> length=s->s.length();

        Collection<Integer> result = specificMap(input, length);

        assertThat(result).containsExactly(4,6,2);
    }

    private Collection<Integer> specificMap(Collection<String> c, Function<String,Integer> f){
        Collection<Integer> result=new ArrayList<>(); //keep this in exercises
        for (String element : c) {
            //???
        }
        return result;
    }

    @Test
    public void testSpecificFilter() throws Exception {
        List<String> input = Arrays.asList("word", "longer", "sh","three");

        Collection<String> result = specificFilter(input, s -> s.length() > 4);

        assertThat(result).containsExactly("longer","three");
    }

    private Collection<String> specificFilter(Collection<String> c, Function<String,Boolean> f){
        Collection<String> result=new ArrayList<>(); //keep this in exercises
        for (String element : c) {
            //????
        }
        return result;
    }

    @Test
    public void testNewMapFunctionality() throws Exception {
        Map<String,Integer> cache=new HashMap<>();
        cache.put("2",8);

        Function<String,Integer> parseInt=null;

        Function<String, Integer> computeNewValue = parseInt.andThen(null);  //f(x) = x ^ 3

        cache.computeIfAbsent("3",computeNewValue);
        cache.computeIfAbsent("4",computeNewValue);

        assertThat(cache)
                .containsEntry("2",8)
                .containsEntry("3",27)
                .containsEntry("4",64);

    }


    @Test
    public void practiceFunctionReceivingFunction() throws Exception {
        Function<Function<Integer,Integer>,Integer> executeWithTwo=null; //???
        Function<Function<Integer,Integer>,String> reportExecution=null; //???

        // implement execute with two so the result of result2(x->x*5) is 10
        Integer result1 = executeWithTwo.apply(x -> x * 5);

        //implement report execution so that apply(x->x*5) is "RESULT : 10"
        String result2 = reportExecution.apply(x -> x * 5);

        assertThat(result1).isEqualTo(10);
        assertThat(result2).isEqualTo("RESULT : 10");


    }

    //LEVEL2

    @Test
    public void testGenericMap() throws Exception {
        List<Tuple2<String, BigDecimal>> transactions = Arrays.asList(
                Tuple.of("t1", new BigDecimal("20")),
                Tuple.of("t2", new BigDecimal("30")),
                Tuple.of("t3", new BigDecimal("60"))

        );

        BigDecimal tax = new BigDecimal("0.23");

        Collection<BigDecimal> netMoney = genericMap(transactions, t -> t._2);
        Collection<BigDecimal> grossMoney = genericMap(netMoney, m -> m.add(m.multiply(tax)));

        assertThat(grossMoney).containsExactly(
                new BigDecimal("24.60"),
                new BigDecimal("36.90"),
                new BigDecimal("73.80")
        );

    }

    private <A,B> Collection<B> genericMap(Collection<A> input, Function<A,B> f){
       return null;
    }


    // REMOVE COMMENT AND COMPLETE CODE
//    @Test
//    public void testGenericFilter() throws Exception {
//        List<Tuple2<String, BigDecimal>> transactions = Arrays.asList(
//                Tuple.of("t1", new BigDecimal("20")),
//                Tuple.of("t2", new BigDecimal("30")),
//                Tuple.of("t1", new BigDecimal("60"))
//
//        );
//
//        Function<Tuple2<String,BigDecimal>,Boolean> t1s=t -> t._1.equals("t1");
//
//        Collection<Tuple2<String,BigDecimal>> result = genericFilter(transactions,t1s);
//
//        assertThat(result).containsExactly(
//                Tuple.of("t1", new BigDecimal("20")),
//                Tuple.of("t1", new BigDecimal("60"))
//        );
//
//    }
//
//    private ??? genericFilter(Collection<???> c, Function<???,???> f){
//       return null;
//    }




}
