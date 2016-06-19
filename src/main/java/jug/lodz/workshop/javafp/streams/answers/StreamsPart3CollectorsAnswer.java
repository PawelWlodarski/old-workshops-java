package jug.lodz.workshop.javafp.streams.answers;

import javaslang.Tuple;
import javaslang.Tuple2;
import jug.lodz.workshop.Printer;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * Created by pawel on 07.06.16.
 */
public class StreamsPart3CollectorsAnswer implements Printer{

    private static StreamsPart3CollectorsAnswer lab=new StreamsPart3CollectorsAnswer();

    public static void main(String[] args) {
        lab.demo();

    }


    private void demo(){
        print("\n\nSTREAM COLLECTORS\n\n");

        println("\n * To COLLECTIONS");

        List<Integer> someList = Stream.of(1, 2, 2, 3).collect(toList());
        Set<Integer> someSet = Stream.of(1, 2, 2, 3).collect(Collectors.toSet());
        LinkedList<Integer> linkedList = Stream.of(1, 2, 2, 3).collect(Collectors.toCollection(LinkedList::new));
        List<String> mapped = Stream.of(1, 2, 2, 3).collect(mapping(i -> i.toString()+"str", toList()));


        println("   * some list : "+someList +", class : "+someList.getClass());
        println("   * some set : "+someSet+", class : "+someSet.getClass());
        println("   * linked list : "+linkedList+", class : "+linkedList.getClass());
        println("   * mapped : "+mapped+", class : "+mapped.getClass());


        println("\n * JOINING STRING");

        String joined = Stream.of(1, 2, 3, 4, 5)
                .map(i -> i.toString()).collect(joining(","));

        println("   * numbers joined : "+joined);

        String collectorsComposed = Stream.of(1, 2, 3, 4, 5)
                .map(i -> i.toString()).collect(mapping(i->i.toString(), joining(",")));

        println("   * collectors composed : "+collectorsComposed);

        String withPrefixAndSuffix = Stream.of(1, 2, 3, 4, 5)
                .map(i -> i.toString()).collect(joining(",","{","}"));

        println("   * with prefix and suffix : "+withPrefixAndSuffix);

        //integer statistics

        Long count = Stream.of("aaa", "bbbb", "ccccccc").collect(Collectors.counting());
        Integer sum = Stream.of("aaa", "bbbb", "ccccccc").collect(Collectors.summingInt(w -> w.length()));
        IntSummaryStatistics statistics = Stream.of("aaa", "bbbb", "ccccccc").collect(Collectors.summarizingInt(String::length));

        println("\n * STATISTICS");

        println("   * count : "+count);
        println("   * sum : "+sum);
        println("   * statistics : "+statistics);

        println("\n * GROUPING");

        Map<Boolean, List<Tuple2<Integer, String>>> partitioned = Stream.of(
                Tuple.of(1, "aa"),
                Tuple.of(2, "bb"),
                Tuple.of(3, "cc"),
                Tuple.of(4, "dd")
        ).collect(partitioningBy(t -> t._1 % 2 == 0));


        println("   * partitioned : "+partitioned);

        Map<NumberSize, List<Integer>> grouped = Stream.of(1, 20000, 3, 400, 5, 6000, 700).collect(groupingBy(i -> {
            if (i > 1000) return NumberSize.BIG;
            else if (i > 100) return NumberSize.MEDIUM;
            else return NumberSize.SMALL;
        }));

        println("   * grouped : "+grouped);

        //misleadign error when there is wrong result type
        Map<NumberSize, String> groupedCountedAndMapped = Stream.of(1, 20000, 3, 400, 5, 6000, 700,9000,10000).collect(
                groupingBy(i -> {
                            if (i > 1000) return NumberSize.BIG;
                            else if (i > 100) return NumberSize.MEDIUM;
                            else return NumberSize.SMALL;
                        }, collectingAndThen(counting(), i -> "number of elements  : "+ i )
                ));


        println("   * groupedCount : "+groupedCountedAndMapped);
    }

    enum NumberSize{SMALL,MEDIUM,BIG}
}
