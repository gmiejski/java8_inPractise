package java8.spliterator.max_long;

public class MaxLongReducer {

    private long maxFound = Long.MIN_VALUE;

    public MaxLongReducer(long maxFound) {
        this.maxFound = maxFound;
    }

    public MaxLongReducer() {

    }

    public MaxLongReducer accumulate(Long value) {
        return new MaxLongReducer(Long.max(this.maxFound, value));
    }

    public MaxLongReducer combine(MaxLongReducer maxLongReducer1) {
        return new MaxLongReducer(Long.max(this.maxFound, maxLongReducer1.maxFound));
    }

    public long getMaxFound() {
        return maxFound;
    }
}
