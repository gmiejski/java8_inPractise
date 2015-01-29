package java8.function.matrix;


import java.util.function.Supplier;

public class Matrix {

    private final int rows;
    private final int cols;
    private final double[][] values;

    public Matrix(double[][] values) {
        this(values.length, values[0].length);
        for (int i = 0; i < rows; i++) {
            System.arraycopy(values[i], 0, this.values[i], 0, cols);
        }
    }

    public Matrix(int rows, int cols) {
        this.values = new double[rows][cols];
        this.rows = rows;
        this.cols = cols;
    }

    public Matrix(int rows, int cols, Supplier<Double> supplier) {
        this(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.values[i][j] = supplier.get();
            }
        }
    }

    public void print() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(this.values[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public Matrix multiply(Matrix otherMatrix) {
        if (this.cols != otherMatrix.rows) {
            throw new IllegalStateException();
        }
        int resultingRows = this.rows;
        int resultingCols = otherMatrix.cols;

        double[][] result = new double[resultingRows][resultingCols];
        for (int i = 0; i < resultingRows; i++) {
            for (int j = 0; j < resultingCols; j++) {
                double[] row = this.getRow(i);
                double[] col = otherMatrix.getCol(j);
                result[i][j] = sumOfMultiplication(row, col);
            }
        }
        return new Matrix(result);
    }

    private double sumOfMultiplication(double[] row, double[] col) {
        if (row.length != col.length) {
            throw new IllegalStateException();
        }
        double sum = 0;
        for (int i = 0; i < row.length; i++) {
            sum += row[i] * col[i];
        }
        return sum;
    }

    private double[] getRow(int i) {
        double[] result = new double[cols];
        System.arraycopy(values[i], 0, result, 0, cols);
        return result;
    }

    private double[] getCol(int columnToTake) {
        double[] result = new double[this.rows];
        for (int i = 0; i < rows; i++) {
            result[i] = this.values[i][columnToTake];
        }
        return result;
    }

}