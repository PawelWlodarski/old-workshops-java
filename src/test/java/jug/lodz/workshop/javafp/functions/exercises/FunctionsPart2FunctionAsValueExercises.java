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
        Map<String,Integer> map=new HashMap<>();
        map.put("present",1);

        Function<String,Integer> computeNewValue=null; //?????

        map.computeIfAbsent("ten",computeNewValue);
        map.computeIfAbsent("one_hundred",computeNewValue);

        assertThat(map)
                .containsEntry("present",1)
                .containsEntry("ten",10)
                .containsEntry("one_hundred",0);

    }


    @Test
    public void practiceFunctionReceivingFunction() throws Exception {
        Function<Function<Integer,Integer>,Integer> executeWithTwo=null; //???
        Function<Function<Integer,Integer>,String> reportExecution=null; //???


        assertThat(executeWithTwo.apply(x->x*5)).isEqualTo(10);
        assertThat(reportExecution.apply(x->x*5)).isEqualTo("RESULT : 10");


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
