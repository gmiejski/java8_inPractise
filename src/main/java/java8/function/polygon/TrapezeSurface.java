package java8.function.polygon;

import java.util.function.Function;

public class TrapezeSurface {


    public static void main(String[] args) {

        double surface = trapezeSurface(x -> (double) 10, 3, 5);
        double surface2 = trapezeSurface(x -> (double) (x), 3, 5);
        double surface3 = trapezeSurface(x -> (double) (x + 2), 3, 5);

        assert surface == 100;
        assert surface2 == 13.5;
        assert surface3 == 19.5;

    }

    private static double trapezeSurface(Function<Integer, Double> f, int start, int end) {
        return (f.apply(start) + f.apply(end)) / 2 * Math.abs(start - end);
    }

}
