Function<Integer,Integer> f = i-> i+1;

Function<Integer,Integer> f2 = (Integer i)-> {
    return i+1;
}


Consumer<Object> prn = System.out::println