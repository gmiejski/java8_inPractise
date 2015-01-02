package java8.function;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class FunctionMain {

    private static <T, R> List<R> map(List<T> list, Function<T, R> trFunction) {
        return list.stream().map(trFunction::apply).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("one", "two", "three", "four");

        list.sort(Comparator.comparing(String::toLowerCase));
        list.sort((x, y) -> x.compareToIgnoreCase(y));
        list.sort(String::compareToIgnoreCase);

        list.stream().collect(groupingBy(String::length));

        List<Integer> stringsLengths = map(list, String::length);
        System.out.println(stringsLengths);
    }
}
