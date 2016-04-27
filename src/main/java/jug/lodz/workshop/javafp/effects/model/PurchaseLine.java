package jug.lodz.workshop.javafp.effects.model;

/**
 * Created by pawel on 26.04.16.
 */
public final class PurchaseLine {
    public final Product product;
    public final Integer amount;

    public PurchaseLine(Product product, Integer amount) {
        this.product = product;
        this.amount = amount;
    }
}
