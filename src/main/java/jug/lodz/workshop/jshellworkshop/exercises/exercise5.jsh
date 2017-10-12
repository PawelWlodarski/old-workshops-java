Consumer<Object> prn=o->System.out.println(o)

void checkExercise5(){
    LongStream fibo100=fibo(100);
    LongStream fibo5000=fibo(5000);
    prn.accept("fibo(100) : "+Arrays.equals(fibo100.toArray(),new long[] { 1, 2, 3, 5, 8, 13, 21, 34, 55, 89 }));

    prn.accept("fibo(5000) : "+Arrays.equals(fibo5000.toArray(),
    new long[] { 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181 }));
}