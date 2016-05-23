package jug.lodz.workshop.javafp.effects.exercises;

import javaslang.collection.Iterator;
import javaslang.control.Option;
import javaslang.control.Try;
import jug.lodz.workshop.javafp.effects.front.HTML;
import jug.lodz.workshop.javafp.effects.model.Customer;
import jug.lodz.workshop.javafp.effects.model.Purchase;

import java.math.BigDecimal;
import java.net.ConnectException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import static javaslang.API.For;
import static jug.lodz.workshop.javafp.effects.data.PurchaseData.data;
import static jug.lodz.workshop.javafp.effects.front.HTML.html;

/**
 * Created by pawel on 01.05.16.
 */
public class EffectsPart4Error {

    public static void demo() {
        print("[DEMONSTRATION]");
        Function<String, Optional<Integer>> parse = s -> {
            try {
                return Optional.of(Integer.parseInt(s));
            } catch (Exception e) {
                return Optional.empty();
            }
        };

        BiFunction<Integer, Integer, Optional<Integer>> divide = (a, b) -> b == 0 ? Optional.empty() : Optional.of(a / b);

        BiFunction<String, String, Optional<Integer>> parseAndDivide = (s1, s2) ->
                parse.apply(s1)
                        .flatMap(a -> parse.apply(s2)
                                .flatMap(b -> divide.apply(a, b)));

        print(" * [PARSE AND DIVIDE WITH OPTION]");
// KATA
//        print("  * 4/2 : " + parseAndDivide.apply("4", "2"));
//        print("  * 4/aaa : " + parseAndDivide.apply("4", "aaa"));
//        print("  * 4/0 : " + parseAndDivide.apply("4", "0"));


        Function<String, Try<Integer>> tryParse = s -> Try.of(() -> Integer.parseInt(s)); //LAZY !! EXPLAIN
        BiFunction<Integer, Integer, Try<Integer>> tryDivide = (a, b) -> Try.of(() -> a / b);


        BiFunction<String, String, Try<Integer>> tryParseAndDivide = (s1, s2) ->
                tryParse.apply(s1)
                        .flatMap(a -> tryParse.apply(s2)
                                .flatMap(b -> tryDivide.apply(a, b)));

        print(" * [PARSE AND DIVIDE WITH TRY]");
// KATA
//        print("  * 4/2 : " + tryParseAndDivide.apply("4", "2"));
//        print("  * 4/aaa : " + tryParseAndDivide.apply("4", "aaa"));
//        print("  * 4/0 : " + tryParseAndDivide.apply("4", "0"));



// KATA
//        tryParseAndDivide.apply("4", "aaa").onSuccess(result -> print("  * success : " + result));
//        tryParseAndDivide.apply("4", "2").onSuccess(result -> print("  * success : " + result));

        BiFunction<String, String, HTML> divideAndRecover = (a, b) -> tryParseAndDivide.apply(a, b)
                .map(i -> "<div>Result is " + i + " </div>")
                .map(HTML::html)
                .getOrElseGet(exception -> (exception instanceof ArithmeticException) ?
                        html("<error>undefined operation </error>") :
                        html("<error>wrong input data </error>")
                );

        print(" * [CALCULATION AND RECOVER]");
//        print("  * 4/2 : " + divideAndRecover.apply("4", "2"));
//        print("  * 4/0 : " + divideAndRecover.apply("4", "0"));
//        print("  * 4/aaa : " + divideAndRecover.apply("4", "aaa"));
    }

    static void exerciseLevel1() {
        print("\n[EXERCISE1]");

        Try<Integer> try1 = Try.success(1);
        Try<Integer> try2 = Try.success(2); //Check what will happen when this one is failure
        Try<Integer> try3 = Try.success(3);

        //EXERCISE - USE flatMap & map
        Try<Integer> flatMapResult = try1.flatMap(null);

        print("  * flatMap Result: " + (flatMapResult.get() == 6));

        //EXERCISE - sum with For - yield
        Iterator<Integer> forResult = For(try1, try2, try3).yield(null);

        print("  * for Result: " + (forResult.get() == 6));

    }


    static void exerciseLevel2() {
        print("\n[EXERCISE2]");
        Function<Integer, Try<Purchase>> readPurchase = id -> Try.failure(new ConnectException("Timeout"));
        Function<Integer, Try<Purchase>> newConnection = id ->
                id == 1 ? Try.success(data().purchase1) : Try.failure(new ConnectException("Timeout"));

        String result1 = readPurchase.apply(1)
                .recoverWith(null)  //EXERCISE - try to recover by using new connection
                .map(p -> "Purchase with id " + p.id + " on " + p.date)
                .getOrElse((String) null);    // EXERCISE - if you were unable to connect return proper message

        print("RESULT 1 : " + result1.equals("Purchase with id 1 on 01-05-2016"));

        String result2 = readPurchase.apply(2)
                .recoverWith(null)  //EXERCISE - try to recover by using new connection
                .map(p -> "Purchase with id " + p.id + " on " + p.date)
                .getOrElse((String) null);    // EXERCISE - if you were unable to connect return proper message

        print("RESULT 2 : " + result2.equals("UNABLE TO CONNECT"));
    }

    static void exerciseLevel3() {
        print("\n[EXERCISE3]");

        //Happy Path Without Effects
        Function<BigDecimal, Function<BigDecimal, BigDecimal>> happyPath = price -> discount ->
                price.subtract(price.multiply(discount));

        //State to store notifications
        List<String> notifications = new LinkedList<>();

        //Stateful function which adds notification to state
        Consumer<Throwable> sendNotification = e -> notifications.add(e.getMessage());

        // Reads discount only for one user
        Function<Customer, Try<BigDecimal>> readDiscount = c ->
                c.name.equals("Joe") ? Try.success(new BigDecimal("0.3")) :
                        Try.failure(new IllegalAccessException("ILLEGAL ACCESS FOR " + c.name));

        // EXERCISE
        //read discount
        //apply happy path  with new BigDecimal("100"))   <------
        //if failure - notify
        //convert to optiion
        Function<Customer, Option<BigDecimal>> applyDiscount = null;

        print(applyDiscount.apply(data().joe).get().equals(new BigDecimal("70.0")));
        print(applyDiscount.apply(data().jane).isEmpty() == true);
        print(notifications.size() == 1);
        print(notifications.get(0).equals("ILLEGAL ACCESS FOR Jane"));

    }

    public static void main(String[] args) {
        demo();

//        print("\n\n[EXERCISES]");
//        exerciseLevel1();
//        exerciseLevel2();
//        exerciseLevel3();
    }

    static void print(Object s) {
        System.out.println(s);
    }

}
