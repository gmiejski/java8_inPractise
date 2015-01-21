package java8.fork_join.numbers_sum;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

public class ForkJoinSumCalculator extends RecursiveTask<Long> {

    public static final int MIN_NUMBERS_COUNT_FOR_SEQUENTIAL_COMPUTATION = 10000;
    private final long[] items;
    private final int start;
    private final int end;

    public ForkJoinSumCalculator(long[] items) {
        this(items, 0, items.length);
    }

    private ForkJoinSumCalculator(long[] items, int start, int end) {
        this.items = items;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length = end - start;
        if (length > MIN_NUMBERS_COUNT_FOR_SEQUENTIAL_COMPUTATION) {
            return sequentialSum();
        } else {
            ForkJoinSumCalculator leftFork = new ForkJoinSumCalculator(items, start, start + end / 2);
            leftFork.fork();

            ForkJoinSumCalculator rightJoin = new ForkJoinSumCalculator(items, start + end / 2, end);
            Long rightSum = rightJoin.compute();
            Long leftSum = leftFork.join();

            return leftSum + rightSum;
        }
    }

    private Long sequentialSum() {
        return Arrays.stream(items, start, end).parallel().sum();
    }
}
