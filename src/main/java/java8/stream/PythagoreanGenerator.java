package java8.stream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PythagoreanGenerator {

    public static void main(String[] args) {

        List<Triangle> triangles = find(1, 100);
        triangles.stream().forEach(System.out::println);

    }

    private static List<Triangle> find(int min, int max) {
        return IntStream.rangeClosed(min, max).boxed().flatMap(a -> IntStream.rangeClosed(min, max).filter(b -> Math.sqrt(a * a + b * b) % 1 == 0).mapToObj(b -> new Triangle(a, b))).distinct().collect(Collectors.toList());
    }

    private static class Triangle {

        private final int i;
        private final int j;
        private final int k;

        private Triangle(int i, int j) {

            int smaller = i < j ? i : j;
            int bigger = i > j ? i : j;

            this.i = smaller;
            this.j = bigger;
            this.k = (int) Math.sqrt(i * i + j * j);
        }

        @Override
        public String toString() {
            return "Triangle{" +
                    "i=" + i +
                    ", j=" + j +
                    ", k=" + k +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Triangle triangle = (Triangle) o;

            return IntStream.of(i, j, k).allMatch(x -> IntStream.of(triangle.i, triangle.j, triangle.k).anyMatch(y -> x == y));
        }

        @Override
        public int hashCode() {
            int result = i;
            result = 31 * result + j;
            result = 31 * result + k;
            return result;
        }
    }
}
