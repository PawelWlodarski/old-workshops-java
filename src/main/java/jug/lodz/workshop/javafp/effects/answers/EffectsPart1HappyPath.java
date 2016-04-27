package jug.lodz.workshop.javafp.effects.answers;

import javaslang.Tuple;
import javaslang.Tuple2;
import javaslang.Tuple3;
import jug.lodz.workshop.javafp.effects.model.Customer;
import jug.lodz.workshop.javafp.effects.model.Purchase;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;

import static jug.lodz.workshop.javafp.effects.data.PurchaseData.data;

/**
 * Created by pawel on 26.04.16.
 */
public class EffectsPart1HappyPath {

    Function<Customer, BigDecimal> discountForCustomer =
            customer -> customer.name.equals("Joe") ? new BigDecimal("0.3") : BigDecimal.ZERO;

    Function<String, BigDecimal> discountForACity = city -> city.equals("Lodz") ? new BigDecimal("0.2") : BigDecimal.ZERO;


    Function<Customer, BigDecimal> discountV1 = c -> {
        BigDecimal customerDiscount = discountForCustomer.apply(c);
        if(customerDiscount.compareTo(BigDecimal.ZERO) != 0) return customerDiscount;

        BigDecimal cityDiscount = discountForACity.apply(c.city);
        if (cityDiscount.compareTo(BigDecimal.ZERO) != 0) return cityDiscount;

        return BigDecimal.ZERO;
    };

    Function<BigDecimal,Function<Customer, BigDecimal>> discountV2 = defaultDiscount->c -> {
        BigDecimal customerDiscount = discountForCustomer.apply(c);
        if(customerDiscount.compareTo(BigDecimal.ZERO) != 0) return customerDiscount;

        BigDecimal cityDiscount = discountForACity.apply(c.city);
        if (cityDiscount.compareTo(BigDecimal.ZERO) != 0) return cityDiscount;

        return defaultDiscount;
    };

    Function<Supplier<BigDecimal>,Function<Customer, BigDecimal>> discountV3 = defaultDiscount-> c -> {
        BigDecimal customerDiscount = discountForCustomer.apply(c);
        if(customerDiscount.compareTo(BigDecimal.ZERO) != 0) return customerDiscount;

        BigDecimal cityDiscount = discountForACity.apply(c.city);
        if (cityDiscount.compareTo(BigDecimal.ZERO) != 0) return cityDiscount;

        return defaultDiscount.get();
    };

    Function<Purchase,BigDecimal> calculatePrice= p->
        p.getLines().stream().map(line->line.product.price).reduce(BigDecimal.ZERO, BigDecimal::add);


    Function<Purchase,BigDecimal> charge = p-> Tuple
            .of(calculatePrice.apply(p),discountV1.apply(p.customer))
            .transform(BigDecimal::subtract);


    void exerciseLevel1(){
        print("\n[EXERCISE1]");
        Tuple2<Integer, Integer> t1 = Tuple.of(1, 2);
        Tuple2<Integer, Integer> t1final = t1.map1(i -> i + 10);
//        Tuple2<Integer, Integer> t1final2 = t1.map(i -> i + 10,Function.identity());
        print("* Tuple 1 : "+t1final.equals(Tuple.of(11,2)));
//        print("* Tuple 12 : "+t1final2.equals(Tuple.of(11,2)));

        Tuple3<Integer, Integer, Integer> t2 = Tuple.of(1, 2, 3);
        Tuple3<Integer, Integer, Integer> t2final = t2.map((x, y, z) -> Tuple.of(x + 10, y + 10, z + 10));
        print("* Tuple 2 : "+t2final.equals(Tuple.of(11,12,13)));


        Tuple2<Integer, Integer> t3 = Tuple.of(3, 5);
        Integer t3result = t3.transform((x, y) -> x + y);
        print("* Tuple 3 : "+(t3result == 8));
    }

    void exerciseLevel2(){
        print("\n[EXERCISE2]");

        Integer sum = Arrays.asList(1, 2, 3, 4, 5).stream().reduce(0, Integer::sum);
        print("* SUM : "+(sum==15));
        Integer sumParsed = Arrays.asList("1", "2", "3").stream().map(Integer::parseInt).reduce(0, Integer::sum);
        print("* SUM PARSED: "+(sumParsed==6));

        String joinedStrings = Arrays.asList("1", "2", "3").stream().reduce("PREFIX", (s1, s2) -> s1 + ":" + s2);
        print("* JOINED STRINGS: "+(joinedStrings.equals("PREFIX:1:2:3")));
    }

    void exerciseLevel3(){
        print("\n[EXERCISE2]");
        print(" * DiscountV1 : "+ (discountV1.apply(data().joe).compareTo(new BigDecimal("0.3")) == 0));
    }

    public static void main(String[] args) {
        print("[DEMONSTRATION]");
        print("* TUPLE");
        Tuple2<String, Integer> tuple = Tuple.of("word", 10);

        print("MAP1 : " + tuple.map1(s -> s + "_mapped"));
        print("MAP2 : " + tuple.map2(i -> i + 5));

        print("TRANSFORM : Tuple.of(10,3)" );
        Integer result = Tuple.of(10, 3).transform((e1, e2) -> e1 - e2);
        print("TRANSFORM RESULT : " +result);

        print("\n* MAPPING COLLECTION");
        print("* PRODUCTS IN PURCHASE 1");
        data().purchase1.getLines().stream()
                .map(line -> Tuple.of(line.product.name,line.amount))
                .forEach(System.out::println);

        print("* COUNTING TOTAL PRODUCTS IN PURCHASE 1");
        Integer numberOfProductsBought=
                data().purchase1.getLines().stream().map(line -> line.amount).reduce(0,Integer::sum);
        print("Products Bought in Purchase1 : " + numberOfProductsBought);


        print("\n\n[EXERCISES]");
        part1().exerciseLevel1();
        part1().exerciseLevel2();
        part1().exerciseLevel3();
    }

    static void print(Object s) {
        System.out.println(s);
    }

    static EffectsPart1HappyPath part1(){return new EffectsPart1HappyPath();}

}
