package jug.lodz.workshop.javafp.fp2.exercises;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pwlodarski on 2016-03-09.
 */
public class ConfigurableLoop5Test {

    @Test
    public void testJoinSorted() throws Exception {
            Map<String,Integer> input = new HashMap(){{
                put("user1",3);
                put("user2",5);
                put("user3",1);
                put("user4",2);
            }};

        List<String> output = ConfigurableLoop5.joinSorted.apply(input);

        //LAB - write assertion for output
    }

    //ADDITIONAL - write tests for lift
}
