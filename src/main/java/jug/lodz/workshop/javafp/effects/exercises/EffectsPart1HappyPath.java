package jug.lodz.workshop.javafp.effects.exercises;

import javaslang.Tuple;
import javaslang.Tuple2;
import javaslang.Tuple3;
import jug.lodz.workshop.javafp.effects.front.HTML;
import jug.lodz.workshop.javafp.effects.model.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static jug.lodz.workshop.javafp.effects.data.PurchaseData.data;
import static jug.lodz.workshop.javafp.effects.front.HTML.html;

/**
 * Created by pawel on 26.04.16.
 */
public class EffectsPart1HappyPath {

    static class FrontEnd{


        static Function<Consultant,HTML> consultantContactInfo = c -> html(
                "<div> Contact your consultant : "+c.name+" "+c.picture.asciiArt+"  <br/>" +
                        "by email : "+c.email+" or by phone "+c.phoneNumber+"</div>"
        );

        //EXERCISE3 - decorate result of an inner function so it is always wrapped in div
        //check Exercise 3 for test assertions
        static <A> Function<A,HTML> insideDiv(Function<A,HTML> toHtml){
            return null;
        }

        static Function<Customer,HTML> customerContactInfo = c -> html(
                "Customer contact : "+c.name+" with email : "+c.email+""
        );

        //EXERCISE3 - decorate customerContactInfo so it is wrapped in div block
        static Function<Customer,HTML> customerToHtml=null;

        static Function<Product,String> productToString= p -> p.name +" : "+p.price+"$";

        //EXERCISE3 - use productToString to build purchase line representation
        // check Exercise 3 for assertions - "<div>tv : 300$ : 2</div>"
        static Function<PurchaseLine,HTML> purchaseLineToHtml= insideDiv(
                null
        );

        //1)What if there is no consultant? What if there is a consultant but it doesn't have a phone?
        static Function<Purchase,HTML> displayPurchase = insideDiv(p->html(
          "purchase "+p.id+"<br/>\n" +
                  "made by : " + customerToHtml.apply(p.customer) + "<br/>\n"+
                  "consultant : "+consultantContactInfo.apply(p.consultant)  + "<br/>\n"+
                  "made on : "+p.date+ "<br/>\n"+
                  p.getLines().stream()
                          .map(purchaseLineToHtml)
                          .map(html->html.content)
                          .collect(Collectors.joining("<br/>\n","PRODUCTS : <br/>\n",""))



        ));
    }


    static class BusinessLogic {
        static Function<Set<Customer>,Function<Customer, BigDecimal>> discountsConfiguration = customersWithDiscount->
                customer -> customersWithDiscount.contains(customer.name)? new BigDecimal("0.3") : BigDecimal.ZERO;

        static Function<Customer, BigDecimal> discountForCustomer=discountsConfiguration.apply(new HashSet(Arrays.asList("Joe")));

        static Function<String, BigDecimal> discountForACity = city -> city.equals("Lodz") ? new BigDecimal("0.2") : BigDecimal.ZERO;


        //EXERCISE4
        // if there is discount for given customer - then return it
        //if there is not check if there is discount for city
        //if there is none of them then return zero
        static Function<Customer, BigDecimal> discountV1 = null;


        //EXERCISE4
        //similar to previous but instead zero return default value
        static Function<BigDecimal,Function<Customer, BigDecimal>> discountV2 = null;

        //EXERCISE
        //similar to previous but use suuplier to provide default value
        //this may be handy if calculation of default value is time consuming
        static Function<Supplier<BigDecimal>,Function<Customer, BigDecimal>> discountV3 = null;

        //EXERCISE
        //add prices from all products in purchase
        static Function<Purchase,BigDecimal> calculatePrice= null;

        //1)what if I want to postpone decision about discount? Currently it is hardcoded or provided
        //at the very begining
        //2) How do I know if given value was a default value?
        static Function<Purchase,Tuple3<BigDecimal,BigDecimal,BigDecimal>> charge = p-> Tuple
                .of(calculatePrice.apply(p),discountV1.apply(p.customer))
                .map((price,discount)->Tuple.of(price,price.multiply(discount)))
                .transform((price,totalDiscount)->Tuple.of(price,totalDiscount,price.subtract(totalDiscount)));
    }




    void exerciseLevel1(){
        print("\n[EXERCISE1]");
        Tuple2<Integer, Integer> t1 = Tuple.of(1, 2);
        Tuple2<Integer, Integer> t1final = t1.map1(null);
        print("* Tuple 1 : "+t1final.equals(Tuple.of(11,2)));

        Tuple3<Integer, Integer, Integer> t2 = Tuple.of(1, 2, 3);
        Tuple3<Integer, Integer, Integer> t2final = t2.map(null);
        print("* Tuple 2 : "+t2final.equals(Tuple.of(11,12,13)));


        Tuple2<Integer, Integer> t3 = Tuple.of(3, 5);
        Integer t3result = t3.transform(null);
        print("* Tuple 3 : "+(t3result == 8));
    }

    void exerciseLevel2(){
        print("\n[EXERCISE2]");

        Integer sum = Arrays.asList(1, 2, 3, 4, 5).stream().reduce(null, null);
        print("* SUM : "+(sum==15));
        Integer sumParsed = Arrays.asList("1", "2", "3").stream().map((Function<String,Integer>)null).reduce(null, null);
        print("* SUM PARSED: "+(sumParsed==6));

        String joinedStrings = Arrays.asList("1", "2", "3").stream().reduce(null, null);
        print("* JOINED STRINGS: "+(joinedStrings.equals("PREFIX:1:2:3")));
    }

    void exerciseLevel3(){
        print("\n[EXERCISE3]");
        Function<Integer,HTML> exerciseFunction= i -> html("number is "+i);
        print(" * INSIDE DIV CHECK : "+
                FrontEnd.insideDiv(exerciseFunction).apply(5).content.equals("<div>number is 5</div>"));

        Customer customer=new Customer(1L,"Exercise3Customer","e3@workshops.com","Lodz",20);
        String expectedCustomerHtml="<div>Customer contact : Exercise3Customer with email : e3@workshops.com</div>";
        print(" * CUSTOMER TO HTML : "+ FrontEnd.customerToHtml.apply(customer).content.equals(expectedCustomerHtml));

        Product tv=new Product("tv",new BigDecimal("300"), ProductCategory.ELECTRONICS, "Great Tv");
        PurchaseLine exerciseLine=new PurchaseLine(tv,2);
        String expectedLineHTML="<div>tv : 300$ : 2</div>";
        print(" * PURCHASE LINE TO HTML : "+ FrontEnd.purchaseLineToHtml.apply(exerciseLine).content.equals(expectedLineHTML));

        print("\n DISPLAY PURCHASE");
        print(FrontEnd.displayPurchase.apply(data().purchase1));
    }

    void exerciseLevel4(){
        print("\n[EXERCISE4]");
        print(" * DiscountV1 : "+ (BusinessLogic.discountV1.apply(data().joe).compareTo(new BigDecimal("0.3")) == 0));
        print(" * DiscountV2 : "+ (BusinessLogic.discountV2.apply(new BigDecimal("0.5"))
                .apply(data().jane).compareTo(new BigDecimal("0.5")) == 0));
        print(" * DiscountV3 : "+ (BusinessLogic.discountV3.apply(() -> new BigDecimal("0.4"))
                .apply(data().jane).compareTo(new BigDecimal("0.4")) == 0));


        print("\n CHARGE PURCHASE 1 "+ BusinessLogic.charge.apply(data().purchase1));
    }

    public static void main(String[] args) {
        print("[DEMONSTRATION]");
        print("* TUPLE");
        Tuple2<String, Integer> tuple = Tuple.of("word", 10);

// KATA
//        print("MAP1 : " + tuple.map1(s -> s + "_mapped"));
//        print("MAP2 : " + tuple.map2(i -> i + 5));

        print("TRANSFORM : Tuple.of(10,3)" );
//KATA
//        Integer result = Tuple.of(10, 3).transform((e1, e2) -> e1 - e2);
//        print("TRANSFORM RESULT : " +result);

        print("\n* MAPPING COLLECTION");
        print("* PRODUCTS IN PURCHASE 1");
//KATA
//        data().purchase1.getLines().stream()
//                .map(line -> Tuple.of(line.product.name,line.amount))
//                .forEach(System.out::println);

        print("* COUNTING TOTAL PRODUCTS IN PURCHASE 1");
//KATA
//        Integer numberOfProductsBought=
//                data().purchase1.getLines().stream().map(line -> line.amount).reduce(0,Integer::sum);
//        print("Products Bought in Purchase1 : " + numberOfProductsBought);

        print("\n\n* DOMAIN EXAMPLES");
        Product beer=new Product("beer",new BigDecimal("10"), ProductCategory.FOOD, "tasty berer");
//KATA
//        Integer gramsOfAlco=Tuple.of(beer,7).transform((b,amount)->amount*4);
//        print(" * GRAMS OF ALCO : Tuple.of(beer,7).transform((b,amount)->amount*4) : "+gramsOfAlco);

        print("CONSULTANT TO HTML ");
        print(FrontEnd.consultantContactInfo.apply(data().fullConsultant).content);

        print("DISCOUNT CONFIGURATION (JOE): "+ BusinessLogic.discountForCustomer.apply(data().joe));
        print("DISCOUNT CONFIGURATION (JANE): "+ BusinessLogic.discountForCustomer.apply(data().jane));

//        print("\n\n[EXERCISES]");
//        part1().exerciseLevel1();
//        part1().exerciseLevel2();
//        part1().exerciseLevel3();
//        part1().exerciseLevel4();

        //Questions to exercise 3
        //1)What if there is no consultant? What if there is a consultant but it doesn't have a phone?

        //Questions to Exercise 4
        //1)what if I want to postpone decision about discount? Currently it is hardcoded or provided
        //at the very begining
        //2) How do I know if given value was a default value?
    }

    static void print(Object s) {
        System.out.println(s);
    }

    static EffectsPart1HappyPath part1(){return new EffectsPart1HappyPath();}

}
