Consumer<Object> prn=o->System.out.println(o)

//create stream which returns array new int[]{ 2, 4, 6, 8, 10, 12, 14, 16, 18 }
// IntStream createIntStream{???}

//.toArray() is invoked on returned IntStream
void checkExercise3(){
    prn.accept("createIntStream() : "+Arrays.equals(createIntStream().toArray(),new int[]{ 2, 4, 6, 8, 10, 12, 14, 16, 18 } ));
}