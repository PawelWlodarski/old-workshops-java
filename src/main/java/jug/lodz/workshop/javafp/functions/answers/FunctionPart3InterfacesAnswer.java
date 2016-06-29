package jug.lodz.workshop.javafp.functions.answers;

import jug.lodz.workshop.Printer;

import java.util.Comparator;
import java.util.function.*;

/**
 * Created by pawel on 19.06.16.
 */
public class FunctionPart3InterfacesAnswer implements Printer{

    /**
     * ALL INTERFACES :
     * https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html
     */

    private static FunctionPart3InterfacesAnswer lab=new FunctionPart3InterfacesAnswer();

    public static void main(String[] args) {
        lab.demo();
    }

    private void demo(){
        println("\n\n FUNCTIONAL INTERFACES \n");

        println(" * FUNCTION & BIFUNCTION");

        Function<String,Integer> parse=s->Integer.parseInt(s);
        BiFunction<String,Integer,Integer> parseAdd= (s,i) -> Integer.parseInt(s) + i;


        println("    * BiFunction Result : "+parseAdd.apply("3",2));

        println(" * OPERATORS");

        Function<Integer,Integer> negate=i->-i;
        UnaryOperator<Integer> negateUnary=i->-i;

        negate=negateUnary;   // Hierarchy

        println("    * Negate Unary Operator of 7: "+negateUnary.apply(7));

        BiFunction<Integer,Integer,Integer> add=(i1,i2) -> i1+i2;
        BinaryOperator<Integer> addBi=(i1,i2)->i1+i2;

        println("    * Add Binary Operator of (7,4): "+addBi.apply(7,4));

        println(" * PREDICATES");

        Function<Integer,Boolean> isPositive=i->i>0;
        Predicate<Integer> isPositivePredicate=i->i>0;

        BiPredicate<Integer,Integer> equals=(i1,i2) -> i1.equals(i2);

        println("    * predicate (is 7 positive) : "+isPositivePredicate.test(7));
        println("    * bi predicate (are 3 and 4 equals) : "+equals.test(3,4));


        Predicate<Integer> isPositiveAndEven = isPositivePredicate.and(i -> i % 2 == 0);
        println("    * combined predicate (is 7 positive and even) : "+isPositiveAndEven.test(7));
        println("    * combined predicate (is 8 positive and even) : "+isPositiveAndEven.test(8));

        println(" * FUNCTIONS WITH EFFECTS");

//      Function<Object,???> print = s -> System.out.println(s);
        Consumer<Object> print = s -> System.out.println(s);

        print.accept("    * printing in consumer!!");

        Supplier<StringBuilder> newStringBuilder=()->new StringBuilder();

        println("    * supplier : "+newStringBuilder.get().append("string buffer ").append("created by supplier"));


        println("   * (BONUS) LAZINESS");

        Supplier<Integer> parseLater=()->Integer.parseInt("aaa");
//CODE - add get()
        println("    * parsing done wrong (but not now): "+parseLater);

        println(" * PRIMITIVES");

        IntFunction<String> primiteIntToString=i->String.valueOf(i);
        IntToDoubleFunction square=i->i*i;
        double squareResult = square.applyAsDouble(2);

        ObjIntConsumer<StringBuilder> toString=(buffer, i)-> buffer.append("added : "+i);
        StringBuilder builder=new StringBuilder();
        toString.accept(builder,7);

        println("    * builder with primitive " + builder);


        println(" * OOP --> FUNCTIONS");
        BiPredicate<Integer,Integer> equalsMethod=Integer::equals;
        BiFunction<Integer,Integer,Integer> addMethod=Integer::sum;
        BiFunction<String, Integer, Integer> parseMethod = Integer::parseInt;

        Supplier<StringBuilder> newBufferMethod=StringBuilder::new;

        println("    * add from method " + addMethod.apply(1,3));
        println("    * new buffer from method " + newBufferMethod.get().append("StringBuilder from method"));


        println(" * SAM INTERFACES");
        Runnable r=()->System.out.println();
        Comparator<Integer> c=(i1,i2) -> i1-i2;

        println("    * Runnable : ");
        r.run();

        println("    * Comparator :  ");
        println("    * Comparator [1,1] :  "+c.compare(1,1));
        println("    * Comparator [1,2] :  "+c.compare(1,2));
        println("    * Comparator [1,2] :  "+c.compare(2,1));


        println(" * CUSTOM FUNCTIONAL INTERFACES");
        ThreeIntegers addThree=(i1,i2,i3) -> i1+i2+i3;

        println("    * Custom interface :  "+addThree.apply(1,2,3));
    }

    interface ThreeIntegers {
        Integer apply(Integer a,Integer b, Integer c);
    }


}
