package ray;

public class Matrix {
    public int rows;
    public int cols;
    public double[][] value;

    public Matrix(double[][] value) {
        this.value = value;
        this.rows = value.length;
        this.cols = value[0].length;
    }

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.value = new double[rows][cols];
    }

    public static Matrix identity(int size) {
        Matrix matrix = new Matrix(size, size);
        for (int i = 0; i < size; i++)
            matrix.value[i][i] = 1.0;
        return matrix;
    }

    public Matrix add(Matrix other) throws Exception {
        if (rows != other.rows || cols != other.cols) {
            throw new Exception("Matrix dimensions different -- can't add");
        }

        Matrix matrix = new Matrix(rows, cols);
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                matrix.value[r][c] = value[r][c] + other.value[r][c];
            }
        }
        return matrix;
    }

    public Matrix mult(Matrix other) throws Exception {
        if (cols != other.rows) throw new Exception("Invalid matrix dimenstions for multiplication");
        Matrix matrix = new Matrix(rows, other.cols);
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < other.cols; c++) {
                matrix.value[r][c] = 0.0;
                for (int i = 0; i < cols; i++) {
                    matrix.value[r][c] += value[r][i] * other.value[i][c];
                }
            }
        }
        return matrix;
    }

    public Matrix mult(double scalar) {
        Matrix other = new Matrix(rows, cols);
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                other.value[r][c] = value[r][c] * scalar;
            }
        }
        return other;
    }

    @Override
    public String toString() {
        String output = "";
        for (double[] line : this.value) {
            output += "|";
            for (double val : line) {
                output += "" + val + "\t";
            }
            output += "|\n";
        }
        return output;
    }

    public static void main(String[] arg0) {
        Matrix m1 = new Matrix(new double[][] {
            {1.0, 2.0, 3.0},
            {4.0, 5.0, 6.0}
        });
        Matrix m2 = new Matrix(new double[][] {
            {7.0, 8.0},
            {9.0, 10.0},
            {11.0, 12.0}
        });
        Matrix result = null;
        try {
            result = m1.mult(m2);
        } catch(Exception e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }
}