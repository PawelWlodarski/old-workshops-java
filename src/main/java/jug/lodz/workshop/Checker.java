package jug.lodz.workshop;

/**
 * Created by pawel on 24.05.16.
 */
public class Checker {

    public static void check(String label,Object expected, Object actual){
            if(expected.equals(actual)){
                print("  * "+label+" : is correct : "+expected);
            }else{
                print("  * "+label+" : is NOT correct : [expected : "+expected+"] but is [actual : "+actual+"]");
            }
    }


    static void print(Object s) {
        System.out.println(s);
    }

}
