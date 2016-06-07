package jug.lodz.workshop;

/**
 * Created by pawel on 07.06.16.
 */
public interface Printer {

    default void println(Object o){
        System.out.println(o);
    }

    default void print(Object o){
        System.out.print(o);
    }

}
