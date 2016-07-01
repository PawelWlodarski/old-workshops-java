package jug.lodz.workshop.javafp.functions.answers;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by pawel on 01.07.16.
 */
public class FunctionsPart5EffectsExercisesAnswer {

    //LEVEL1

    @Test
    public void testOptionalGet() throws Exception {
        Map<Integer, String> datastore = new HashMap<>();
        datastore.put(1, "Stefan");

        Optional<String> value1 = getOptional(datastore, 1);
        Optional<String> value2 = getOptional(datastore, 2);

        assertThat(value1).contains("Stefan");
        assertThat(value2).isEmpty();
    }

    private Optional<String> getOptional(Map<Integer, String> map, Integer key) {
        String potentialValue = map.get(key);
        return potentialValue != null ? Optional.of(potentialValue) : Optional.empty();

    }

    @Test
    public void testDivide() throws Exception {
        Optional<Integer> properDivision = divide(8, 2);
        Optional<Integer> divisionByZero = divide(8, 0);

        assertThat(properDivision).contains(4);
        assertThat(divisionByZero).isEmpty();
    }

    private Optional<Integer> divide(Integer a, Integer b) {
        return b != 0 ? Optional.of(a / b) : Optional.empty();
    }



    //LEVEL 4 - BOSS
    /**
     .____                      .__       _____   __________
     |    |    _______  __ ____ |  |     /  |  |  \______   \ ____  ______ ______
     |    |  _/ __ \  \/ // __ \|  |    /   |  |_  |    |  _//  _ \/  ___//  ___/
     |    |__\  ___/\   /\  ___/|  |__ /    ^   /  |    |   (  <_> )___ \ \___ \
     |_______ \___  >\_/  \___  >____/ \____   |   |______  /\____/____  >____  >
       \/         \/          \/            |__|          \/           \/     \/
     .                                           ____    ____  ____________________
      /\|\/\     /\|\/\     /\|\/\     /\|\/\   |    |   |   \_   _____/\__    ___/  /\|\/\     /\|\/\     /\|\/\     /\|\/\
     _)    (__  _)    (__  _)    (__  _)    (__ |    |   |   ||    __)    |    |    _)    (__  _)    (__  _)    (__  _)    (__
     \_     _/  \_     _/  \_     _/  \_     _/ |    |___|   ||     \     |    |    \_     _/  \_     _/  \_     _/  \_     _/
     )      \     )    \     )    \     )    \  |_______ \___|\___  /     |____|      )    \     )    \     )    \     )    \
       \/\|\/     \/\|\/     \/\|\/     \/\|\/          \/        \/                  \/\|\/     \/\|\/     \/\|\/     \/\|\/
     */


    @Test
    public void testLift() throws Exception {
        Function<Integer,Integer> increment=i->i+1;
        Function<String,String> trim=String::trim;

        Function<Optional<Integer>, Optional<Integer>> incrLifted = lift(increment);
        Function<Optional<String>, Optional<String>> trimLifted = lift(trim);

        assertThat(incrLifted.apply(Optional.of(1))).isEqualTo(Optional.of(2));
        assertThat(incrLifted.apply(Optional.empty())).isEmpty();
        assertThat(trimLifted.apply(Optional.of("  aaa  "))).isNotEmpty().hasValue("aaa");
    }


    private <A,B> Function<Optional<A>,Optional<B>> lift(Function<A,B> f){
        return oa->oa.map(f);
    }

}

