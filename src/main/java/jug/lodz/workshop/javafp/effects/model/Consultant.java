package jug.lodz.workshop.javafp.effects.model;

/**
 * Created by pawel on 27.04.16.
 */
public class Consultant {
    public final String name;
    public final String phoneNumber;
    public final String email;
    public final Picture picture;

    public Consultant(String name, String phoneNumber, String email, Picture picture) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.picture = picture;
    }
}
