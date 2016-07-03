package jug.lodz.workshop.javafp.functions.exercises;

import javaslang.Tuple;
import javaslang.Tuple2;
import org.junit.Test;

import java.util.*;
import java.util.function.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by pawel on 20.06.16.
 */
public class FunctionsPart3InterfacesExercises {

    //LEVEL1

    @Test
    public void testOtherInterfaces() throws Exception {
        BiFunction<Integer,Integer,Integer> multiply=null;
        UnaryOperator<Integer> square=null;

        assertThat(multiply.apply(2,3)).isEqualTo(6);
        assertThat(multiply.apply(4,3)).isEqualTo(12);
        assertThat(square.apply(2)).isEqualTo(4);
        assertThat(square.apply(3)).isEqualTo(9);
    }

    @Test
    public void testPredicates() throws Exception {
        BiPredicate<Boolean,Boolean> andGate=null;
        BiPredicate<Boolean,Boolean> orGate=null;


        assertThat(andGate.test(true,false)).isFalse();
        assertThat(andGate.test(true,true)).isTrue();
        assertThat(orGate.test(true,false)).isTrue();
        assertThat(orGate.test(false,false)).isFalse();
    }

    @Test
    public void combinePredicates() throws Exception {
        Predicate<Integer> isAdult=age->age>18;  //older than 18
        Predicate<Integer> isRetired=null;          //older than 67

        Predicate<Integer> notRetired = null;  //use negate()

        Predicate<Integer> hasDiscount=null;  //???.and(???).negate()

        assertThat(hasDiscount.test(15)).isTrue();
        assertThat(hasDiscount.test(20)).isFalse();
        assertThat(hasDiscount.test(60)).isFalse();
        assertThat(hasDiscount.test(70)).isTrue();



    }


    //LEVEL2

//    interface TriFunction<???>{
//        ??? apply(???);
//    }
//
//    @Test
//    public void testTriFunction() throws Exception {
//        TriFunction<String,String,String,Integer> parseAndAdd=(s1,s2,s3) ->
//                Integer.parseInt(s1)+Integer.parseInt(s2)+Integer.parseInt(s3);
//
//        assertThat(parseAndAdd.apply("1","2","3")).isEqualTo(6);
//    }

    @Test
    public void testComparator() throws Exception {
        Comparator<Tuple2<Integer,String>> sortByFirst=null;

        List<Tuple2<Integer, String>> listToSort = Arrays.asList(
                Tuple.of(3, "aaa"),
                Tuple.of(1, "ccc"),
                Tuple.of(2, "bbb")
        );

        Collections.sort(listToSort,sortByFirst);

        assertThat(listToSort).containsExactly(
                Tuple.of(1, "ccc"),Tuple.of(2, "bbb"), Tuple.of(3, "aaa")
        );
    }


    //LEVEL3


    @Test
    public void testreduceWithOperator() throws Exception {
        List<Integer> ints = Arrays.asList(1, 2, 3, 4, 5);
        List<String> strings = Arrays.asList("aa", "bb", "cc");


        Integer intsSum = reduce(ints, 0, Integer::sum);
        String concat = reduce(strings, "", (s1, s2) -> s1 + ":" + s2);

        assertThat(intsSum).isEqualTo(15);
        assertThat(concat).isEqualTo(":aa:bb:cc");
    }

    private <A> A reduce(final Iterable<A> c,A init, BinaryOperator<A> f){
       return null;
    }

    @Test
    public void testCollectPrimitives() throws Exception {
        int[] input=new int[]{1,2,3};

        Collection<String> result=new ArrayList<>();

        collectPrimitives(input,result,(Collection<String> r,int e)->r.add(String.valueOf(e)));

        assertThat(result).containsExactly("1","2","3");
    }

    private <A> void collectPrimitives(int[] array, A init, ObjIntConsumer<A> converter){
       //???
    }


    @Test
    public void testLaziness() throws Exception {

        Optional<Integer> ok = executeDangerousAction(() -> 4 / 2);
        Optional<Integer> divisionByZero = executeDangerousAction(() -> 4 / 0);

        assertThat(ok).isPresent().hasValue(2);
        assertThat(divisionByZero).isEmpty();
    }

    private <A> Optional<A> executeDangerousAction(Supplier<A> action){
       return null;
    }

}

