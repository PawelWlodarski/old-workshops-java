package jug.lodz.workshop.javafp.effects.exercises;

import javaslang.control.Try;
import jug.lodz.workshop.javafp.effects.model.Customer;
import jug.lodz.workshop.javafp.effects.model.Purchase;
import jug.lodz.workshop.javafp.effects.model.PurchaseLine;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static jug.lodz.workshop.javafp.effects.data.PurchaseData.data;

/**
 * Created by pawel on 02.05.16.
 */
public class EffectsPart5Time {

    public static void demo() {
        print("[DEMONSTRATION]");

        Function<Integer, Purchase> readPurchase = timeout -> {
            sleep(timeout);
            return data().purchase1;
        };

        Function<Purchase, List<PurchaseLine>> getLines = Purchase::getLines;

        Function<List<PurchaseLine>, String> extractNames = lines ->
                lines.stream()
                        .map(p -> p.product)
                        .map(p -> p.name)
                        .collect(Collectors.joining(","));

        Function<Purchase, String> happyPath = getLines.andThen(extractNames);

        print("\n * Time example 1 - waiting");

        String result1 = readPurchase.andThen(happyPath).apply(500);
        print(result1);

//        print(" * Time example 2 - waiting... forever");
//        String result2 = readPurchase.andThen(happyPath).apply(500000000);
//        print("  * this will never happen: "+result2);


        print("\n * Time example 3 - composableFuture");

        print("  * submiting completable future");
        CompletableFuture<Purchase> readingPurchase = CompletableFuture.supplyAsync(() -> readPurchase.apply(500));

        print("  * doing something else............");
        Try<String> happyPathApplied = Try.of(() -> readingPurchase.thenApply(happyPath).get(1, TimeUnit.SECONDS));

        print("  * result is : " + happyPathApplied);

        Try<String> timeoutError = Try.of(() ->
                CompletableFuture.supplyAsync(() -> readPurchase.apply(5000000))
                        .thenApply(happyPath)
                        .get(1, TimeUnit.SECONDS));

        print("  * timeout result : " + timeoutError);
    }


    static void exerciseLevel1() {
        print("\n[EXERCISE1]");
        Supplier<CompletableFuture<BigDecimal>> discountOfTheDay = () -> CompletableFuture.supplyAsync(
                () -> {
                    sleep(500);
                    return new BigDecimal("0.1");
                }
        );

        Function<Customer, CompletableFuture<BigDecimal>> discountForUser = c -> CompletableFuture.supplyAsync(
                () -> {
                    sleep(600);
                    return c.name.equals("Joe") ? new BigDecimal("0.3") : BigDecimal.ZERO;
                }
        );


        //EXERCISE combine two discounts with BigDecimal::add
        Try<BigDecimal> discount = Try.of(
                () -> discountForUser.apply(data().joe)
                        .<BigDecimal, BigDecimal>thenCombine(null, null)
                        .get(2, TimeUnit.SECONDS)
        );

        print("  *Total Discount : " + discount.get().equals(new BigDecimal("0.4")));

    }

    static void exerciseLevel2() {
        print("\n[EXERCISE2]");

        Function<String, CompletableFuture<Integer>> parseService = input ->
                CompletableFuture.supplyAsync(() -> {
                            sleep(500);
                            return Integer.parseInt(input);
                        }
                );


        //EXERCISE - Map possible future values into try
        BiFunction<Integer, Throwable, Try<Integer>> handleFuture = null;


        CompletableFuture<Try<Integer>> parseCorrect = parseService.apply("1").handle(handleFuture);
        CompletableFuture<Try<Integer>> parseIncorrect = parseService.apply("aaa").handle(handleFuture);
        CompletableFuture<Try<Integer>> parseIncorrectAndTimeout = parseService.apply("1").handle(handleFuture);

        Try<Integer> timeoutIncorrectResult = Try.of(() -> parseIncorrectAndTimeout.get(100, TimeUnit.MILLISECONDS)).getOrElseGet(Try::failure);
        Try<Integer> correctResult = Try.of(() -> parseCorrect.get(1, TimeUnit.SECONDS)).getOrElseGet(Try::failure);
        Try<Integer> incorrectResult = Try.of(() -> parseIncorrect.get(1, TimeUnit.SECONDS)).getOrElseGet(Try::failure);

        print("Parsing correct result : " + correctResult);
        print("Parsing correct result : " + correctResult.get().equals(1));
        print("Parsing incorrect result : " + incorrectResult);
        print("Parsing incorrect result : " + (incorrectResult.getCause() instanceof CompletionException));
        print("Parsing incorrect result : " + (incorrectResult.getCause().getCause() instanceof NumberFormatException));
        print("Parsing timeout incorrect result : " + timeoutIncorrectResult);
        print("Parsing timeout incorrect result : " + (timeoutIncorrectResult.getCause() instanceof TimeoutException));

    }

    //EXERCISE
    static <A> Try<A> join(Try<Try<A>> input) {
        return null;
    }


    static void exerciseLevel3() {
        print("\n[EXERCISE3]");
        Function<String, CompletableFuture<Try<Integer>>> parse = input ->
                CompletableFuture.supplyAsync(
                        () -> {
                            sleep(500);
                            return Try.of(() -> Integer.parseInt(input));
                        }
                );

        Try<Try<Integer>> parsingResultTimeout = Try.of(() -> parse.apply("aaa").get(1, TimeUnit.MILLISECONDS));
        Try<Try<Integer>> parsingResultWrongInput = Try.of(() -> parse.apply("aaa").get(1, TimeUnit.SECONDS));

        Try<Integer> joinTimeout = join(parsingResultTimeout);
        Try<Integer> joinWrongInput = join(parsingResultWrongInput);

        //EXERCISE - "with your eyes validation"
        print("timeout case  : " + joinTimeout);
        print("wrong input case : " + joinWrongInput);

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

    static void sleep(Integer miliseconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(miliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
