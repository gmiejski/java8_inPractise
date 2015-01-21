package java8.spliterator.max_long;

import java.util.Arrays;
import java.util.Spliterator;
import java.util.function.Consumer;

public class MaxLongSpliterator implements Spliterator<Long> {

    private long[] longs;
    private int index = 0;

    public MaxLongSpliterator(long[] longs) {
        this.longs = longs;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Long> action) {
        action.accept(longs[index++]);
        return index < longs.length;
    }

    @Override
    public Spliterator<Long> trySplit() {
        int currentSize = longs.length - index;
        if (currentSize > 100) {
            MaxLongSpliterator maxLongSpliterator = new MaxLongSpliterator(Arrays.copyOfRange(longs, index, index + currentSize / 2));
            index = index + currentSize / 2;
            return maxLongSpliterator;
        } else {
            return null;
        }
    }

    @Override
    public long estimateSize() {
        return longs.length - index;
    }

    @Override
    public int characteristics() {
        return ORDERED | SIZED | NONNULL | IMMUTABLE;
    }

}
