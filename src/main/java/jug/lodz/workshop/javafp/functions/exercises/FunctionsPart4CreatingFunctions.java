package jug.lodz.workshop.javafp.functions.exercises;

import jug.lodz.workshop.Printer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by pawel on 20.06.16.
 */
public class FunctionsPart4CreatingFunctions implements Printer{

    private static FunctionsPart4CreatingFunctions lab=new FunctionsPart4CreatingFunctions();

    public static void main(String[] args) {
        lab.demo();
    }

    private void demo(){
        println("\n\n FUNCTION AS A RESULT \n");

        println(" * CREATE A FUNCTION");

//CODE - defined at the bottom
//        Function<Integer, Integer> createdIncrementFunction = createASimpleFunction();
//        println("     * CREATED FUNCTION RESULT : "+createdIncrementFunction.apply(3));

        println(" * DEPENDENCY INJECTION");

        Map<String,Double> init=new HashMap<>();
        init.put("tax",0.23);

        Map<String, Double> config = Collections.unmodifiableMap(init);


        println(" * VARIANT 1 : CLOSURE");
//CODE
//        Function<Double,Double> grossV1=net -> net+ net*config.get("tax");
//        println(" *     VARIANT 1 : CLOSURE RESULT : "+grossV1.apply(10.0));

        println(" * VARIANT 2 : METHOD INVOCATION");
//CODE
//        Function<Double, Double> grossV2 = injectConfig(config);
//        println(" *     VARIANT 2 : CREATE FUNCTION : "+grossV2.apply(10.0));


        println(" * VARIANT 3 : FUNCTION INVOCATION CURRYING");
//CODE - currying
//        Function<Map<String,Double>,Function<Double,Double>> taxCurrying=
//                configurationMap->net->net+ net*configurationMap.get("tax");
//
//        Function<Double, Double> grossV3 = taxCurrying.apply(config);
//        println(" *     VARIANT 3 : CURRYING : "+grossV3.apply(10.0));


        println(" * MORE ON CURRYING");
// EXPLAIN SYNTAX
        Function<Integer,Integer> incrementX=x->x+1;
        Function<Integer,
                Function<Integer,Integer>> addYtoX=
                y->
                    x->x+y;
        Function<Integer,
                Function<Integer,
                        Function<Integer,Integer>>> addZtoYtoX=
                z->
                        y->
                                x->z+x+y;

        println(" *     CURRYING (increment x=5): "+incrementX.apply(5) );
        println(" *     CURRYING (addY to x=5): "+addYtoX.apply(4).apply(5) );
        println(" *     CURRYING (addZtoY to x=5): "+addZtoYtoX.apply(3).apply(4).apply(5) );


        println(" * INJECTION");
//PARSER
        Function<String,Integer> parser=Integer::parseInt;

//CODE
//        Function<Function<String,Integer>,
//                BiFunction<String,String,Integer>> parseAndAdd=p->(s1,s2)->p.apply(s1)+p.apply(s2);
//
//        BiFunction<String, String, Integer> addStrings = parseAndAdd.apply(parser);
//        println(" *     ADD Strings: "+addStrings.apply("1","2") );

        println(" * DECORATOR");
        Function<Integer, Integer> decorated = withLogging((Integer i) -> i + 1);
        decorated.apply(665);

        //// Memoization
    }

    //FIRST TIME WE RETUR A FUNCTION
    //show notation with one return
    //show notation with two returns and explain
    private Function<Integer,Integer> createASimpleFunction(){
        return i->i+1;
    }

    private Function<Double,Double> injectConfig(Map<String,Double> config){
        return net -> net+ net*config.get("tax");
    }

    private <A,B> Function<A,B> withLogging(Function<A,B> original){
        return a->{
            System.out.println("INFO: calling with arg : "+a);
            return original.apply(a);
        };
    }

}
