package jug.lodz.workshop.javafp.functions.answers;

import javaslang.Tuple;
import javaslang.Tuple2;
import jug.lodz.workshop.Printer;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by pawel on 20.06.16.
 */
public class FunctionsPart5EffectsAnswer implements Printer{

    private static FunctionsPart5EffectsAnswer lab=new FunctionsPart5EffectsAnswer();

    public static void main(String[] args) {
        lab.demo();
    }


    private void demo(){
        println("\n\n FP & EFFECTS \n");

        println(" * CONCEPT OF PARTIAL FUNCTION");

        Function<String,Integer> parse=Integer::parseInt;

        println(" * defined for '1' : "+parse.apply("1"));
//        println(" * NOT defined for 'aaa' : "+parse.apply("aaa"));

        Map<Integer, Tuple2<String,String>> datastore=new HashMap<>();
        datastore.put(1, Tuple.of("Stefan","st200@gmail.com"));

        Function<Integer, Tuple2<String,String>> lookup=id->datastore.get(id);
        Function<Tuple2<String,String>,String> second=t->t._2;
        Consumer<String> sendEmail=emailAdress->System.out.println(emailAdress);

        Function<Integer, String> lookupEmail = lookup.andThen(second);// :(:( not working andThen(sendEmail);

        String email = lookupEmail.apply(1);  //defined for 1
        sendEmail.accept(email);

//        lookupEmail.apply(2);  //not defined for two


        println("\n * OPTIONAL");

        Optional<Integer> optional10 = Optional.of(10);
        optional10.map(i->i+2).map(i->i/2).ifPresent(i->println("Option some result : "+i));

        println(optional10.map(i->i+2).map(i->i/2));
        println(Optional.<Integer>empty().map(i->i+2).map(i->i/2));


        println("\n * HANDLING EFFECTS WITH OPTIONAL");

        Function<String,Optional<Integer>> safeParse=s->{
          try{
              return Optional.of(Integer.parseInt(s));
          }catch(Exception e){ //what happens with an exception?
              return Optional.empty();
          }
        };

        Function<Double,Double> gross=net->net+net*0.23;

        Optional<Double> optionalGross = safeParse.apply("10").map(Integer::doubleValue).map(gross);
        println("optional gross : "+optionalGross);

        Optional<Double> empty = safeParse.apply("aaa").map(Integer::doubleValue).map(gross);
        println("empty for 'aaa' : "+empty);

        //other Effects
        //* Try
        //* Time/Future
        //* NonDeterminism/Lists
        //* Laziness/Streams

        //FUNCTOR THEORY
        //Optional.map(f o g) == Optional.map(f).map(g)

    }

}
