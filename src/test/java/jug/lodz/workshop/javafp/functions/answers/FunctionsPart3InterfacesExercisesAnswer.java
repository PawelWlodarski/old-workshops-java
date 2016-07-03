package jug.lodz.workshop.javafp.functions.answers;

import javaslang.Tuple;
import javaslang.Tuple2;
import org.junit.Test;

import java.util.*;
import java.util.function.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by pawel on 20.06.16.
 */
public class FunctionsPart3InterfacesExercisesAnswer {

    //LEVEL1

    @Test
    public void testOtherInterfaces() throws Exception {
        BiFunction<Integer,Integer,Integer> multiply=(i1,i2) -> i1*i2;
        UnaryOperator<Integer> square=i->i*i;

        assertThat(multiply.apply(2,3)).isEqualTo(6);
        assertThat(multiply.apply(4,3)).isEqualTo(12);
        assertThat(square.apply(2)).isEqualTo(4);
        assertThat(square.apply(3)).isEqualTo(9);
    }

    @Test
    public void testPredicates() throws Exception {
        BiPredicate<Boolean,Boolean> andGate=(i1,i2)->i1 && i2;
        BiPredicate<Boolean,Boolean> orGate=(i1,i2)->i1 || i2;


        assertThat(andGate.test(true,false)).isFalse();
        assertThat(andGate.test(true,true)).isTrue();
        assertThat(orGate.test(true,false)).isTrue();
        assertThat(orGate.test(false,false)).isFalse();
    }

    @Test
    public void combinePredicates() throws Exception {
        Predicate<Integer> isAdult=age->age>18;  //older than 18
        Predicate<Integer> isRetired=age->age>67;          //older than 67

        Predicate<Integer> notRetired = isRetired.negate();  //use negate()

        Predicate<Integer> hasDiscount=isAdult.and(notRetired).negate();  //! (is adult and not retired)

        assertThat(hasDiscount.test(15)).isTrue();
        assertThat(hasDiscount.test(20)).isFalse();
        assertThat(hasDiscount.test(60)).isFalse();
        assertThat(hasDiscount.test(70)).isTrue();



    }


    //LEVEL2

    interface TriFunction<A,B,C,D>{
        D apply(A a, B b , C c);
    }

    @Test
    public void testTriFunction() throws Exception {
        TriFunction<String,String,String,Integer> parseAndAdd=(s1,s2,s3) ->
                Integer.parseInt(s1)+Integer.parseInt(s2)+Integer.parseInt(s3);

        assertThat(parseAndAdd.apply("1","2","3")).isEqualTo(6);
    }

    @Test
    public void testComparator() throws Exception {
        Comparator<Tuple2<Integer,String>> sortByFirst=(t1,t2)->t1._1 -  t2._1;

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
        A result=init;
        for (A i : c) {
            result = f.apply(result,i);
        }
        return result;
    }

    @Test
    public void testCollectPrimitives() throws Exception {
        int[] input=new int[]{1,2,3};

        Collection<String> result=new ArrayList<>();

        collectPrimitives(input,result,(Collection<String> r,int e)->r.add(String.valueOf(e)));

        assertThat(result).containsExactly("1","2","3");
    }

    private <A> void collectPrimitives(int[] array, A init, ObjIntConsumer<A> converter){
        for (int i : array) {
            converter.accept(init,i);
        }
    }


    @Test
    public void testLaziness() throws Exception {

        Optional<Integer> ok = executeDangerousAction(() -> 4 / 2);
        Optional<Integer> divisionByZero = executeDangerousAction(() -> 4 / 0);

        assertThat(ok).isPresent().hasValue(2);
        assertThat(divisionByZero).isEmpty();
    }

    private <A> Optional<A> executeDangerousAction(Supplier<A> action){
        try {
            return Optional.of(action.get());
        }catch (Exception e){
            return Optional.empty();
        }
    }

}

