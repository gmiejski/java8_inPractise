package java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class Flattering {


    public static void main(String[] args) {

        List<String> strings = Arrays.asList("Hello", "World");

        List<String> characters = strings.stream().map(x -> x.split("")).flatMap(Arrays::stream).distinct().collect(toList());
        System.out.println(characters);

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> numbers2 = Arrays.asList(6, 7, 8, 9, 0);

        List<DoubleNumber> collect = numbers.stream().flatMap(i -> numbers2.stream().map(j -> new DoubleNumber(i, j))).collect(toList());
        List<DoubleNumber> dividableBy3 = numbers.stream().flatMap(i -> numbers2.stream().filter(j -> (i + j) % 3 == 0).map(j -> new DoubleNumber(i, j))).collect(toList());

        System.out.println(collect);
        System.out.println(dividableBy3);

        int sum = numbers.stream().reduce(0, (x, y) -> x + y);
        System.out.println("SUM = " + sum);

        Optional<Integer> smallestDivisableBy3 = numbers.stream().map(x -> x * x).filter(x -> x % 3 == 0).findFirst();
        smallestDivisableBy3.ifPresent(System.out::println);

        Optional<Integer> max = numbers.stream().max(Integer::compare);
        max.ifPresent(System.out::println);

    }


    private static class DoubleNumber {

        private int x, y;

        private DoubleNumber(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public String toString() {
            return "{x=" + x + ", y=" + y + '}';
        }
    }


}
