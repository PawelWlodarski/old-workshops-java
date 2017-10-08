Consumer<Object> prn=o->System.out.println(o)

String toUpper(String s){
    return toUpperFunction.apply(s);
}

void checkExercise1(){
    prn.accept("toUpper('aaa')"+toUpper("aaa").equals("AAA"));
    prn.accept("toUpper('abc_de')"+toUpper("abc_de").equals("ABC_DE"));
}