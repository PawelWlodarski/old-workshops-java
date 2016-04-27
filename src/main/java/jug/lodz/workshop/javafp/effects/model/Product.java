package jug.lodz.workshop.javafp.effects.model;

import java.math.BigDecimal;

/**
 * Created by pawel on 26.04.16.
 */
public final class Product {

    public final String name;
    public final BigDecimal price;
    public final ProductCategory productCategory;

    public Product(String name, BigDecimal price, ProductCategory productCategory) {
        this.name = name;
        this.price = price;
        this.productCategory = productCategory;
    }
}
