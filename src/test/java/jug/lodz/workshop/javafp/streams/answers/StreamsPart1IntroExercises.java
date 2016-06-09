package jug.lodz.workshop.javafp.streams.answers;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by pawel on 07.06.16.
 */
public class StreamsPart1IntroExercises {


    // Level 1
    @Test
    public void simpleMapping_JustAddOneToCreatedStream() throws Exception {
        List<Integer> start = Arrays.asList(1, 2, 3, 4, 5);

        Stream<Integer> result = start.stream().map(e->e+1); //delete map(e->e+1)

        assertThat(result.collect(Collectors.toList())).containsExactly(2,3,4,5,6);
    }

    @Test
    public void singleFunctionObjectMapping_extractName() throws Exception {
        Function<Product,String> getName=p->p.name;

        Stream<String> names = products().map(getName);

        assertThat(names.collect(Collectors.toList())).containsExactly("tv","console","mouse","speakers");
    }


    @Test
    public void multipleFunctionsMapping_extractPriceAndConvertToBigDecimal() throws Exception {
        Function<Product,String> getPrice=p->p.price;
        Function<String,BigDecimal> toBigDecimal=BigDecimal::new;

        Stream<BigDecimal> prices =
                products().map(getPrice).map(toBigDecimal);

        assertThat(prices.collect(Collectors.toList())).containsExactly(
                new BigDecimal("300.0"),new BigDecimal("200.0"),new BigDecimal("20.0"),new BigDecimal("45.5")
        );
    }

    //LEVEL 2

    @Test
    public void sumAllPrices_extractPricesChangeToBigDecimalAndReduce() throws Exception {
        Function<Product,String> getPrice=p->p.price;
        Function<String,BigDecimal> toBigDecimal=BigDecimal::new;

        Function<Product, BigDecimal> bigDecimalPrice = getPrice.andThen(toBigDecimal);

        BigDecimal result = products().map(bigDecimalPrice).reduce(BigDecimal.ZERO, BigDecimal::add);

        assertThat(result).isEqualTo(new BigDecimal("565.5"));
    }

    @Test
    public void rewritewithForeach() throws Exception {
        List<String> names=new LinkedList<>();

        products().forEach(p->names.add(p.name));

        assertThat(names).containsExactly("tv","console","mouse","speakers");
    }


    // Level 3
    @Test
    public void mapReduceTest() throws Exception {
        List<String> input = Arrays.asList("1", "2", "3","4");
        Function<String,Integer> toInt = Integer::parseInt;
        BinaryOperator<Integer> multiply=(i1,i2)->i1*i2;

        Integer result = basicMapReduce(toInt, multiply, 1, input.stream());

        assertThat(result).isEqualTo(24);

    }

    private <A,B> B basicMapReduce(Function<A,B> map, BinaryOperator<B> reduce, B identity, Stream<A> input){
        return input.map(map).reduce(identity,reduce);
    }

    @Test
    public void  mapInTermsOfForEach() throws Exception {
        Collection<String> result = mapInTermsOfForEach(products(), p -> p.name);

        assertThat(result).containsExactly("tv","console","mouse","speakers");
    }

    private <A,B> Collection<B>  mapInTermsOfForEach(Stream<A> input,Function<A,B> f){
        Collection<B> result=new LinkedList<>();

        input.forEach(e->result.add(f.apply(e)));

        return result;
    }

    @Test
    public void mapsInTermsOfReduce() throws Exception {
        Collection<String> result = mapInTermsOfReduce(products(), p -> p.name);

        assertThat(result).containsExactly("tv","console","mouse","speakers");

    }

    private <A,B> Collection<B>  mapInTermsOfReduce(Stream<A> input,Function<A,B> f){
        List<B> identity=new LinkedList<>();
        BiFunction<List<B>,A,List<B>> accumulate=(l,e)->{
            l.add(f.apply(e));
            return l;
        };
        BinaryOperator<List<B>> combine=(l1,l2)->{l1.addAll(l2);return l2;};


        return input.reduce(identity,accumulate,combine);
    }



    //Products
    private Stream<Product> products(){
        Product tv=new Product("tv","300.0");
        Product console=new Product("console","200.0");
        Product mouse=new Product("mouse","20.0");
        Product speakers=new Product("speakers","45.5");

        return Arrays.asList(tv,console,mouse,speakers).stream();
    }

    class Product{
        final String name;
        final String price;

        public Product(String name, String price) {
            this.name = name;
            this.price = price;
        }
    }
}
