package jug.lodz.workshop.javafp.streams;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/**
 * Created by pawel on 07.06.16.
 */
public class Transactions {

    static String header="ID,FROM,TO,AMOUNT,DATE";
    static String line1="1,1,2,100,01-02-2016";
    static String line2="2,1,3,200,01-02-2016";
    static String line3="3,2,3,50,01-02-2016";
    static String line4="4,2,1,666,01-02-2016";
    static String line5="5,3,2,500,15-02-2016";
    static String line6wrongStructure ="7";
    static String line7="6,2,2,100,15-02-2016";
    static String line8="7,1,4,100,23-06-2016";
    static String line9="8,1,4,100,23-06-2016";
    static String line10wrongSyntax ="aaa,bbb,4,200,23-06-2016";
    static String line11="9,3,4,700,23-06-2016";
    static String line12empty=",,,,";


    public static List<String> transactions=Arrays.asList(
            header,
            line1,
            line2,
            line3,
            line4,
            line5,
            line6wrongStructure,
            line7,
            line8,
            line9,
            line10wrongSyntax,
            line11,
            line12empty);


    public static class FlatTransaction{
        public final Integer id;
        public final Integer accountFrom;
        public final Integer accountTo;
        public final Integer amount;
        public final LocalDate date;

        private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        public FlatTransaction(String[] line){
                id=Integer.parseInt(line[0]);
                accountFrom=Integer.parseInt(line[1]);
                accountTo=Integer.parseInt(line[2]);
                amount=Integer.parseInt(line[3]);
                date= LocalDate.parse(line[4],formatter);
        }

        @Override
        public String toString() {
            return "FlatTransaction{" +
                    "date=" + date +
                    ", amount=" + amount +
                    ", accountTo=" + accountTo +
                    ", accountFrom=" + accountFrom +
                    ", id=" + id +
                    '}';
        }
    }

}
