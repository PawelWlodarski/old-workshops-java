package jug.lodz.workshop.javafp.effects.data;

import jug.lodz.workshop.javafp.effects.model.*;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * Created by pawel on 26.04.16.
 */
public class PurchaseData {
    public final Customer joe=new Customer(1L,"Joe","Joe@workshops.com","Lodz",30);
    public final Customer jane=new Customer(2L,"Jane","Jane@gmail.com","Zakopane",25);

    public final Consultant fullConsultant=new Consultant("Full Consultant","123456","consultant@system.com",new Picture(":)"));
    public final Consultant facelessConsultant=new Consultant("Full Consultant","123456","consultant@system.com",null);
    public final Consultant phoneConsultant=new Consultant("Full Consultant","123456",null,null);

    public final Product tv=new Product("tv",new BigDecimal("300"), ProductCategory.ELECTRONICS, "Great Tv");
    public final Product console=new Product("console",new BigDecimal("200"), ProductCategory.ELECTRONICS, null);
    public final Product speaker=new Product("speaker",new BigDecimal("70"), ProductCategory.ELECTRONICS, null);
    public final Product beer=new Product("beer",new BigDecimal("10"), ProductCategory.FOOD, "tasty berer");
    public final Product book=new Product("book",new BigDecimal("15"), ProductCategory.EDUCATION, "very interesting book");
    public final Product lamp=new Product("lamp",new BigDecimal("25"), ProductCategory.ELECTRONICS, null);
    public final Product sugar=new Product("sugar",new BigDecimal("3"), ProductCategory.FOOD, null);
    public final Product yeast=new Product("yeast",new BigDecimal("4"), ProductCategory.FOOD, null);



    private List<PurchaseLine> lines= asList(
            new PurchaseLine(tv,1),
            new PurchaseLine(console,1),
            new PurchaseLine(speaker,4),
            new PurchaseLine(beer,6)
    );
    public final Purchase purchase1=new Purchase(1, lines,"01-05-2016",joe,fullConsultant);


    private List<PurchaseLine> lines2= asList(
            new PurchaseLine(book,2),
            new PurchaseLine(lamp,1)
    );
    public final Purchase purchase2=new Purchase(2, lines2,"01-05-2016",jane,phoneConsultant);


    private List<PurchaseLine> lines3= asList(
            new PurchaseLine(sugar,20),
            new PurchaseLine(yeast,30)
    );
    public final Purchase purchase3=new Purchase(3, lines3,"01-05-2016",joe,null);


    public static PurchaseData data(){return new PurchaseData();}
}
