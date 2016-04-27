package jug.lodz.workshop.javafp.effects.model;

import java.util.Collections;
import java.util.List;

/**
 * Created by pawel on 26.04.16.
 */
public final class Purchase {
    private final List<PurchaseLine> lines;
    public final String date;
    public final Customer customer;

    public Purchase(List<PurchaseLine> lines, String date, Customer customer) {
        this.lines = lines;
        this.date = date;
        this.customer = customer;
    }

    public List<PurchaseLine> getLines() {
        return Collections.unmodifiableList(lines);
    }
}
