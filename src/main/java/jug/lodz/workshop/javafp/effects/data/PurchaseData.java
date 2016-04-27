package jug.lodz.workshop.javafp.effects.data;

import jug.lodz.workshop.javafp.effects.model.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * Created by pawel on 26.04.16.
 */
public class PurchaseData {
    public final Customer joe=new Customer(1L,"Joe","Joe@workshops.com","Lodz",30);
    public final Customer jane=new Customer(2L,"Jane","Jane@gmail.com","Zakopane",25);

    Product tv=new Product("tv",new BigDecimal("300"), ProductCategory.ELECTRONICS);
    Product console=new Product("console",new BigDecimal("200"), ProductCategory.ELECTRONICS);
    Product speaker=new Product("console",new BigDecimal("70"), ProductCategory.ELECTRONICS);
    Product beer=new Product("console",new BigDecimal("10"), ProductCategory.FOOD);
    Product book=new Product("book",new BigDecimal("15"), ProductCategory.EDUCATION);
    Product lamp=new Product("lamp",new BigDecimal("25"), ProductCategory.ELECTRONICS);
    Product sugar=new Product("sugar",new BigDecimal("3"), ProductCategory.FOOD);
    Product yeast=new Product("yeast",new BigDecimal("4"), ProductCategory.FOOD);



    private List<PurchaseLine> lines= Arrays.asList(
            new PurchaseLine(tv,1),
            new PurchaseLine(console,1),
            new PurchaseLine(speaker,4),
            new PurchaseLine(beer,6)
    );
    public final Purchase purchase1=new Purchase(lines,"01-05-2016",joe);


    private List<PurchaseLine> lines2=Arrays.asList(
            new PurchaseLine(book,2),
            new PurchaseLine(lamp,1)
    );
    public final Purchase purchase2=new Purchase(lines2,"01-05-2016",jane);


    private List<PurchaseLine> lines3=Arrays.asList(
            new PurchaseLine(sugar,20),
            new PurchaseLine(yeast,30)
    );
    public final Purchase purchase3=new Purchase(lines3,"01-05-2016",joe);


    public static PurchaseData data(){return new PurchaseData();}
}
