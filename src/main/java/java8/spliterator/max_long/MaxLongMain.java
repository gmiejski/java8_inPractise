package java8.spliterator.max_long;

import java.util.Arrays;
import java.util.Random;
import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class MaxLongMain {

    public static void main(String[] args) {
        Random random = new Random();

        long totalNumbers = 10000000;

        long[] longs = Stream.generate(random::nextLong).mapToLong(Long::valueOf).limit(totalNumbers).toArray();
        long max = Arrays.stream(longs).max().getAsLong();

        Stream<Long> stream = Arrays.stream(longs).boxed();

        long max1 = getMax(stream);

        Spliterator<Long> spliterator = new MaxLongSpliterator(longs);
        Stream<Long> spliteratorStream = StreamSupport.stream(spliterator, true);
        long max2 = getMax(spliteratorStream);

        System.out.println("From normal stream : " + max);
        System.out.println("From parallel stream : " + max1);
        System.out.println("From splitter stream : " + max2);

    }

    private static long getMax(Stream<Long> stream) {
        MaxLongReducer reduce = stream.reduce(new MaxLongReducer(), MaxLongReducer::accumulate, MaxLongReducer::combine);
        return reduce.getMaxFound();
    }
}
