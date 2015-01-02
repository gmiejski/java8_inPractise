package java8;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FibonacciGenerator {


    public static void main(String[] args) {
        List<Integer> collect = Stream.iterate(new int[]{0, 1}, x -> new int[]{x[1], x[0] + x[1]}).limit(30).map(x -> x[0]).collect(Collectors.toList());
        collect.forEach(System.out::println);


        List<Integer> collect1 = Stream.generate(getFibonacciSuplier()).limit(20).collect(Collectors.toList());
        List<Integer> collect2 = getOldFibonacciStream(20);

        assert assertEqual(collect, collect1);
        assert assertEqual(collect1, collect2);
        assert assertEqual(collect, collect2);

    }

    private static Supplier<Integer> getFibonacciSuplier() {
        return new Supplier<Integer>() {
            int current = 0;
            int next = 1;

            @Override
            public Integer get() {
                int valueToReturn = current;
                int temp = next;
                next = current + next;
                current = temp;
                return valueToReturn;
            }
        };
    }

    private static List<Integer> getOldFibonacciStream(int count) {
        return IntStream.rangeClosed(0, count).boxed().map(FibonacciGenerator::oldFibo).collect(Collectors.toList());
    }

    private static int oldFibo(int x) {
        if (x == 0) return 0;
        if (x == 1 || x == 2) return 1;
        return oldFibo(x - 1) + oldFibo(x - 2);
    }

    private static boolean assertEqual(List<Integer> collect, List<Integer> collect2) {
        return collect.containsAll(collect2) && collect2.size() == collect.size();
    }
}