package jug.lodz.workshop.javafp.streams.answers;

/**
 * Created by pawel on 08.06.16.
 */
public class StreamsPart5Primitives {
}


//int calories = menu.stream().mapToInt(Dish::getCalories).sum();

//mapToInt & sum , min, max , average
//toLong
//LongUnaryFunction
//pomiary pamięci

// .boxed() - range example
// optionalInt

/**
 IntStream.rangeClosed(1, 100)
 .filter(b -> Math.sqrt(a*a + b*b) % 1 == 0)
 .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)});
 */