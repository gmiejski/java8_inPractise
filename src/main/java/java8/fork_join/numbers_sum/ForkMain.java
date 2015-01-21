package java8.fork_join.numbers_sum;

import java.util.concurrent.ForkJoinPool;
import java.util.stream.LongStream;

public class ForkMain {

    public static void main(String[] args) {
        int startingNumber = 0;
        int endingNumber = 10000000;

        long sum = LongStream.range(startingNumber, endingNumber).sum();
        long[] items = LongStream.range(startingNumber, endingNumber).toArray();

        ForkJoinSumCalculator forkJoinSumCalculator = new ForkJoinSumCalculator(items);

        long sumForkJoin = new ForkJoinPool().invoke(forkJoinSumCalculator);
        assert sum == sumForkJoin;
        System.out.println(sumForkJoin);
    }
}
