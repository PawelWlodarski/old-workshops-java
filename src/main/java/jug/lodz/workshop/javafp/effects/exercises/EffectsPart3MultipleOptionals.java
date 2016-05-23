package jug.lodz.workshop.javafp.effects.exercises;

import javaslang.API;
import javaslang.collection.Iterator;
import javaslang.control.Option;
import jug.lodz.workshop.javafp.effects.front.HTML;
import jug.lodz.workshop.javafp.effects.model.Consultant;
import jug.lodz.workshop.javafp.effects.model.Purchase;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static javaslang.API.For;
import static jug.lodz.workshop.javafp.effects.data.PurchaseData.data;

/**
 * Created by pawel on 30.04.16.
 */
public class EffectsPart3MultipleOptionals {

    static void demo() {
        print("[DEMONSTRATION]");
        print("  * MULTIPLE OPTIONALS");

        Function<String, Optional<Integer>> parse = s -> {
            try {
                return Optional.of(Integer.parseInt(s));
            } catch (Exception e) {
                return Optional.empty();
            }
        };

        Function<Integer, Optional<String>> lookup = id -> (id == 5) ? Optional.of("Zygfryd") : Optional.empty();
//KATA
//        Optional<Optional<String>> result1 = parse.apply("10").map(lookup);
//        print("  * MAP : parse & lookup : " + result1);


        print("  * [MAP : phases]");
//        Optional<Integer> parsingResult = parse.apply("10");
//        print("  * MAP : parsing result : " + parsingResult);
//
//        Optional<Optional<String>> lookupResult = parsingResult.map(lookup);
//        print("  * MAP : lookup result : " + lookupResult);


//KATA
//        Optional<String> flatMapResult = parse.apply("10").flatMap(lookup);
//        print("  * FLATMAP : " + flatMapResult);


        print("\n  * [FLAT MAP COMBINATIONS]");
//        print("  * Some.flatMap[Some] : " + Optional.of(1).flatMap(i -> Optional.of(i + 1)));
//        print("  * Some.flatMap[None] : " + Optional.of(1).flatMap(i -> Optional.empty()));
//        print("  * None.flatMap[Some] : " + Optional.<Integer>empty().flatMap(i -> Optional.of(i + 1)));

        print("\n  * [JAVA SLANG FOR]");

        Iterator<Integer> javaslangResult = For(
                Option.of(1), Option.of(2), Option.of(3)
        ).yield((a, b, c) -> a + b + c);

        javaslangResult.forEach(r -> print("  * Javaslang Result : " + r));


        Iterator<Integer> javaslangNoneResult = For(
                Option.of(1), Option.<Integer>none(), Option.of(3)
        ).yield((a, b, c) -> a + b + c);

        javaslangNoneResult.forEach(r -> print("  * Javaslang Result With One None: " + r));

        Iterator<String> sequentialResult = For(Option.of(1), i1 ->
                For(Option.of(i1 * 2), i2 ->
                        For(Option.of(i2 * 2)).yield(result -> "final result is : " + result)
                )
        );

        sequentialResult.forEach(r -> print("  * Javaslang sequential result : " + r));


    }

    static <A, B> Optional<B> findInMap(Map<A, B> map, A key) {
        return Optional.ofNullable(map.get(key));
    }

    static void exerciseLevel1() {
        print("\n[EXERCISE1]");
        Map<String, Integer> searchId = new HashMap<String, Integer>() {{
            put("Joe", 1);
            put("Jane", 2);
        }};
        Map<Integer, BigDecimal> discountForUser = new HashMap<Integer, BigDecimal>() {{
            put(1, new BigDecimal("0.3"));
        }};

        //EXERCISE - USE BOTH MAPS TO SEARCH FOR DISCOUNT 1)FIRST FIND ID, 2) THEN FIND DISCOUNT
        Optional<BigDecimal> discountForJoe = null;
        Optional<BigDecimal> discountForJane = null;

        print("  * existing discount : " + discountForJoe.get().equals(new BigDecimal("0.3")));
        print("  * none discount : " + (discountForJane.isPresent() == false));

        //FORMULA =  PRICE - (PRICE * DISCOUNT)
        Function<BigDecimal, Function<BigDecimal, BigDecimal>> calculateFinalPrice = null;

        Optional<BigDecimal> finalPrice = discountForJoe.map(calculateFinalPrice.apply(new BigDecimal("100")));

        finalPrice.ifPresent(i -> print("  * final price with discount : " + i.equals(new BigDecimal("70.0"))));

        Option.ofOptional(discountForJane)
                .orElse(Option.of(BigDecimal.ZERO))
                .map(calculateFinalPrice.apply(new BigDecimal("100")))
                .forEach(price -> print("  * final price without discount : " + price.equals(new BigDecimal("100"))));

    }

    static void exerciseLevel2() {
        print("\n[EXERCISE2]");

        //retrieve consultant from purchase - Optional because it can be empty
        Function<Purchase, Optional<Consultant>> getConsultant = null;

        // If consultant has an email then return email, otherwise return phone number
        Function<Consultant, Optional<String>> getContactInfo = null;

        Function<Purchase, HTML> displayConsultant = p ->
                getConsultant.apply(p)
                        .flatMap(getContactInfo)
                        .map(info -> "You can contact our consultant : " + info)
                        .map(HTML::html)
                        .orElse(HTML.html("In case of any problems contact [MAIN NUMBER]"));

        print("  * CONSULTANT WITH EMAIL : " +
                displayConsultant.apply(data().purchase1).content.equals("You can contact our consultant : consultant@system.com"));
        print("  * CONSULTANT WITH PHONE : " +
                displayConsultant.apply(data().purchase2).content.equals("You can contact our consultant : 123456"));

        print("  * PURCHASE WITHOUT CONSULTANT : " +
                displayConsultant.apply(data().purchase3).content.equals("In case of any problems contact [MAIN NUMBER]"));

    }

    static void exerciseLevel3() {
        print("\n[EXERCISE3]");
        //EXERICISE - USE JAVASLANG API TO RETRIEVE CONSULTANT INFO
        Function<Purchase, String> consultantInfo = p -> API.For(Option.of(p.consultant), (Consultant c) ->
                For(null).<String>yield(null)
        ).getOrElse("In case of any problems contact [MAIN NUMBER]");


        print("CONSULTANT WITH CONTACT DATA : " + consultantInfo.apply(data().purchase1).equals("You can contact our consultant : consultant@system.com"));
        print("CONSULTANT WITHOUT CONTACT DATA : " + consultantInfo.apply(data().purchase3).equals("In case of any problems contact [MAIN NUMBER]"));

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
