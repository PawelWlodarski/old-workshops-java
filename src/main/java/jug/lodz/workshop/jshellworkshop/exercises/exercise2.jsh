Consumer<Object> prn=o->System.out.println(o)

String trimAndUpper(String s){
    return trimAndUpperMethod(s);
}

void checkExercise2(){
    prn.accept("trimAndUpper('aaa')"+trimAndUpper("    bbb   ").equals("BBB"));
    prn.accept("trimAndUpper('    hello world    ')"+trimAndUpper("    hello world    ").equals("HELLO WORLD"));
}