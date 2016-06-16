package jug.lodz.workshop.javafp.streams.answers;

/**
 * Created by pawel on 07.06.16.
 */
public class StreamsPart3Collectors {
}


// toList
// toSet
// joining
//long howManyDishes = menu.stream().collect(Collectors.counting());
// .collect(summingInt(Dish::getCalories));
//IntSummaryStatistics menuStatistics =menu.stream().collect(summarizingInt(Dish::getCalories));
// stream.collect(toCollection(TreeSet::new))
//return albums.stream().collect(averagingInt(album -> album.getTrackList().size()));
// artists.collect(partitioningBy(artist -> artist.isSolo()))
// groupingBy
//.collect(Collectors.joining(", ", "[", "]"));
//artists.collect(maxBy(comparing(getCount)));
// albums.collect(groupingBy(Album::getMainMusician,mapping(Album::getName, toList())));