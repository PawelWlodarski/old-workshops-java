package jug.lodz.workshop.javafp.functions.answers;

import jug.lodz.workshop.Printer;

/**
 * Created by pawel on 20.06.16.
 */
public class FunctionsPart6Encapsulation implements Printer{

    private static FunctionsPart6Encapsulation lab=new FunctionsPart6Encapsulation();

    private void demo(){
        println("\n    * ENCAPSULATION, PURE FUNCTIONS & REFERENTIAL TRANSPARENCY\n\n");

        //example 1
        MutableNumber a=new MutableNumber(2);
        MutableNumber b=new MutableNumber(3);


        println("    * BROKEN ENCAPSULATION");
//        println(addMutable(a,b));
//        println("a="+a);
//        println("b="+b);

        //example 2
        int a2=2;
        int b2=3;
        println("    * OPERATION ENCAPSULATED");
//        println(addEncapsulated(a2,b2));
//        println("a2="+a2);
//        println("b2="+b2);


        //example 3
        println("    * VISIBLE EFFECT - REFERENTIAL TRANSPARENCY BROKEN");
//        println(div(4,2));
//        println(div(4,0));

        //example 4
        println("    * RT COMPROMISE AND CONTEXT");
        addWithConsole(3,4);
    }

    public static MutableNumber addMutable(MutableNumber a, MutableNumber b) {
        while (b.getValue() > 0) {
            a.increment();
            b.decrement();
        }
        return a;
    }

    public static int addEncapsulated(int a, int b) {
        while (b > 0) {
            a++;
            b--;
        }
        return a;
    }

    public static int div(int a, int b) {
        return a / b;
    }

    public static int addWithConsole(int a, int b) {
        while (b > 0) {
            a++;
            b--;
        }
        System.out.println(a);
        return a;
    }

    public static void main(String[] args){
            lab.demo();
    }
}

class MutableNumber{
    private int value=0;

    public MutableNumber(int value) {
        this.value = value;
    }

    public void increment(){
        value=value+1;
    }

    public void decrement(){
        value=value-1;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "MutableNumber("+value+")";
    }
}
