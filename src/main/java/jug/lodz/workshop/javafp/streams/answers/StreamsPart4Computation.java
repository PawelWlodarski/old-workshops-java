package jug.lodz.workshop.javafp.streams.answers;

/**
 * Created by pawel on 07.06.16.
 */
public class StreamsPart4Computation {
}

//IntStream.rangeClosed(1, 100)

// Stream.of("a", "b", "c")
// Stream iterate
// peek

// short-circuiting - anyMatch, noneMatch

/**
 * pokazac kolejnośc :
 *  - wyfiltrować cos przed mapowaniem
 *  - odwrocic kolejnosc i pokazac mapowanie
 *
 * List<String> names =
 menu.stream()
 .filter(d -> {
 Printing the
 dishes as
 they’re filtered
 System.out.println("filtering" + d.getName());
 return d.getCalories() > 300;
 })
 .map(d -> {
 System.out.println("mapping" + d.getName());
 return d.getName();
 })
 .limit(3)
 .collect(toList())
 */

/**
 Stream<String> stream = Stream.of("Java 8 ", "Lambdas ", "In ", "Action");
 Stream<String> emptyStream = Stream.empty();
 */

/**
 int[] numbers = {2, 3, 5, 7, 11, 13};
 int sum = Arrays.stream(numbers).sum();
 */

/**
 Stream.iterate(0, n -> n + 2)
 .limit(10)
 .forEach(System.out::println);
 */

/**
 Fibonacci
 Stream.iterate(new int[]{0, 1},
 t -> new int[]{t[1],t[0] + t[1]})
 .limit(10)
 .map(t -> t[0])
 .forEach(System.out::println);
 */

/**
 Stream.generate(Math::random)
 .limit(5)
 .forEach(System.out::println)
 */