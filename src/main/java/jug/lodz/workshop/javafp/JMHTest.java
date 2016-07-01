package jug.lodz.workshop.javafp;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Random;
import java.util.function.*;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Created by pawel on 20.06.16.
 */
@State(Scope.Benchmark)
public class JMHTest {

    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(JMHTest.class.getSimpleName())
                .warmupIterations(5)
                .measurementIterations(5)
                .forks(1)
                .build();


        new Runner(opt).run();
    }



    private long numberOfElements = 1000000000;

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void primitive(){

        LongUnaryOperator step = i -> i + 1;
        LongUnaryOperator intSquare = i -> i * i;
        LongBinaryOperator sum = (i1, i2) -> i1 + i2;

        long primitiveResult = LongStream
                .iterate(0, step)
                .map(intSquare)
                .limit(numberOfElements)
                .reduce(0, sum);

        System.out.println(primitiveResult);
    }

    static Random random=new Random();

//    @Benchmark
//    @BenchmarkMode(Mode.AverageTime)
    public void boxed(){
        long numberOfElements = 1000000000;

        UnaryOperator<Integer> f1=i->i+1;
        IntUnaryOperator f2=i->i+1;
        LongUnaryOperator f3=i->i+1;
        DoubleUnaryOperator f4=i->i+1;


        Long result = Stream
                .iterate(0L, i -> i + 1)
                .map(i->i*i)
                .limit(numberOfElements)
                .reduce(0L,Long::sum);


        System.out.println(result);
    }
}
