package jug.lodz.workshop.javafp.effects.model;

/**
 * Created by pawel on 26.04.16.
 */
public final class Customer {
    public final Long id;
    public final String name;
    public final String email;
    public final String city;
    public final Integer age;

    public Customer(Long id, String name, String email, String city, Integer age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.city = city;
        this.age = age;
    }
}
