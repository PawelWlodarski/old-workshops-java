package jug.lodz.workshop.javafp.streams.exercises;

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
public class StreamsPart3Collectors implements Printer{

    private static StreamsPart3Collectors lab=new StreamsPart3Collectors();

    public static void main(String[] args) {
        lab.demo();
    }


    private void demo(){
        print("\n\nSTREAM COLLECTORS\n\n");

        println("\n *** SECTION : COLLECT TO COLLECTIONS ***");

        List<Integer> someList = Stream.of(1, 2, 2, 3).collect(toList());
        Set<Integer> someSet = Stream.of(1, 2, 2, 3).collect(Collectors.toSet());

        //custom collection
        LinkedList<Integer> linkedList = Stream.of(1, 2, 2, 3).collect(Collectors.toCollection(LinkedList::new));


        println("   * some list : "+someList +", class : "+someList.getClass());
        println("   * some set : "+someSet+", class : "+someSet.getClass());
        println("   * linked list : "+linkedList+", class : "+linkedList.getClass());

        //CODE - collectors composition
//        List<String> mapped = Stream.of(1, 2, 2, 3).collect(mapping(i -> i.toString()+"str", toList()));
//        println("   * mapped : "+mapped+", class : "+mapped.getClass());


        println("\n *** SECTION :  JOINING STRING ***");

        //CODE - simple join
//        String joined = Stream.of(1, 2, 3, 4, 5)
//                .map(i -> i.toString()).collect(joining(","));
//
//        println("   * numbers joined : "+joined);

        //composition -> mapping & joining
        String collectorsComposed = Stream.of(1, 2, 3, 4, 5)
                .map(i -> i.toString()).collect(mapping(i->i.toString(), joining(",")));

        println("   * collectors composed : "+collectorsComposed);

        // with prefix and suffix
        String withPrefixAndSuffix = Stream.of(1, 2, 3, 4, 5)
                .map(i -> i.toString()).collect(joining(",","{","}"));

        println("   * with prefix and suffix : "+withPrefixAndSuffix);

        //integer statistics

        println("\n *** SECTION :  STATISTICS ***");

        //CODE
//        Long count = Stream.of("aaa", "bbbb", "ccccccc").collect(Collectors.counting());
//        Integer sum = Stream.of("aaa", "bbbb", "ccccccc").collect(Collectors.summingInt(w -> w.length()));
        IntSummaryStatistics statistics = Stream.of("aaa", "bbbb", "ccccccc").collect(Collectors.summarizingInt(String::length));


        //CODE
//        println("   * count : "+count);
//        println("   * sum : "+sum);
        println("   * statistics : "+statistics);

        println("\n *** SECTION :  GROUPING ***");

        // PARTITION - two sets {true|false}
        Map<Boolean, List<Tuple2<Integer, String>>> partitioned = Stream.of(
                Tuple.of(1, "aa"),
                Tuple.of(2, "bb"),
                Tuple.of(3, "cc"),
                Tuple.of(4, "dd")
        ).collect(partitioningBy(t -> t._1 % 2 == 0));   // EXPLAIN TUPLE


        println("   * partitioned : "+partitioned);

        Map<NumberSize, List<Integer>> grouped = Stream.of(1, 20000, 3, 400, 5, 6000, 700).collect(groupingBy(i -> {
            if (i > 1000) return NumberSize.BIG;
            else if (i > 100) return NumberSize.MEDIUM;
            else return NumberSize.SMALL;
        }));  // CAN/SHOULD BE EXTERNAL LAMBDA

        println("   * grouped : "+grouped);

        //EXAMPLE WHEN WE WANT MAPPING IN COLLECTORS AND NOT STREAMS
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
