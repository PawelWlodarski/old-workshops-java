package jug.lodz.workshop.javafp.effects.front;

/**
 * Created by pawel on 28.04.16.
 */
public class HTML {
    public final String content;


    private HTML(String content) {
        this.content = content;
    }

    public static HTML html(String content){
        return new HTML(content);
    }

    @Override
    public String toString() {
        return content;
    }
}
