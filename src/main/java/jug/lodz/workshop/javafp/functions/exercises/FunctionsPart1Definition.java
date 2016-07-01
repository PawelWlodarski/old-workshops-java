package jug.lodz.workshop.javafp.functions.exercises;

import javaslang.Tuple;
import javaslang.Tuple1;
import javaslang.Tuple2;
import javaslang.Tuple3;
import jug.lodz.workshop.Printer;

import java.util.function.Function;

/**
 * Created by pawel on 19.06.16.
 */
public class FunctionsPart1Definition implements Printer{

    private static FunctionsPart1Definition lab= new FunctionsPart1Definition();

    public static void main(String[] args) {
        lab.demo();
    }

    private void demo(){
        println("\n\n JAVA 8 FUNCTIONS INTRO \n");

        println("  +FUNCTIONS DEFINITION");

        //Show anonymous class notation
        //Show notation with brackets
        //Show simplest notation
        Function<Integer,Integer> f= null; //CODE
//        Function<Integer,Integer> f= i -> i+1;

        println("    * apply f to 1 : "+f.apply(1));
        println("    * apply f to 2 : "+f.apply(2));
        println("    * apply f to 3 : "+f.apply(3));

        Function<String,Integer> parseStringLong=new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return Integer.parseInt(s);
            }
        };

        Function<String,Integer> parseStringMedium= (String s1)->{
            int parsed = Integer.parseInt(s1);
            return parsed;
        };

        //SIMPLIFICATION
        //* remove type
        //* make one-liner
        //* remove return and bracers

        Function<String,Integer> parseString= parseStringLong; //CODE
//        Function<String,Integer> parseString= s1->Integer.parseInt(s1);

        println("  +FUNCTIONS COMPOSITION");

        Function<String, Integer> parseStringAddOne = null; //CODE
//        Function<String, Integer> parseStringAddOne = parseString.andThen(f);

        Function<String, Integer> parseStringAddOneComposed = null; //CODE
//        Function<String, Integer> parseStringAddOneComposed = f.compose(parseString);


        println("    * composed of '1' : "+parseStringAddOne.apply("1"));
        println("    * composed of '2' : "+parseStringAddOne.apply("2"));



        println("  +TUPLES");
        Tuple1<String> t1 = Tuple.of("value");
        Tuple2<String, Integer> t2 = Tuple.of("value", 2);
        Tuple3<String, Integer, Boolean> t3 = Tuple.of("value", 3, true);

        println("    * tuple of 3 elements : ["+t3._1+","+t3._2+","+t3._3+"]");

        println("  +CLOSURE AND EFFECTIVELY FINAL");

        double taxRate=0.23;

        Function<Integer,Double> gross=net -> net+(taxRate*net);

        //Illegal // CODE
//        Function<Integer,Double> grossInvalid=net -> {
//            taxRate=0.5;
//            return net+(taxRate*net);
//        };

    }
}
