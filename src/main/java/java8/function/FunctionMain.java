package java8.function;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FunctionMain {

    private static <T, R> List<R> map(List<T> list, Function<T, R> trFunction) {
        return list.stream().map(trFunction::apply).collect(Collectors.toList());
    }


    public static void main(String[] args) {

        List<Integer> stringsLengths = map(Arrays.asList("one", "two", "three", "four"), String::length);

        System.out.println(stringsLengths);

    }

}
