package java8.function.matrix;

import java.util.Random;
import java.util.function.Supplier;

public class MatrixMain {

    private static Supplier<Double> randomDoubleSupplier = () -> new Random().nextDouble();

    public static void main(String[] args) {

        Matrix suppliedMatrix = new Matrix(3, 4, randomDoubleSupplier);

        double[][] array = {{1, 0}, {2, 0}, {1, 2}};
        Matrix matrix = new Matrix(array);
        matrix.print();

        double[][] array2 = {{2, 3, 4}, {5, 6, 7}};
        Matrix matrix2 = new Matrix(array2);
        matrix2.print();

        Matrix multiply = matrix.multiply(matrix2);
        multiply.print();
    }
}
