package ray;

public class Vector3 {
    public double x;
    public double y;
    public double z;

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        String output = "<";
        for (double val : new double[] {x, y, z}) {
            output += "" + val + ", ";
        }
        output = output.substring(0, output.length() - 2);
        output += ">";
        return output;
    }

    public static double dot(Vector3 a, Vector3 b) {
        return a.x * b.x + a.y * b.y + a.z * b.z;
    }

    public Vector3 add(Vector3 other) {
        return new Vector3(x + other.x, y + other.y, z + other.z);
    }

    public Vector3 sub(Vector3 other) {
        return add(other.mult(-1.0));
    }

    public Vector3 normalized() {
        return mult(1 / len());
    }

    public double len() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public Vector3 mult(double scalar) {
        Vector3 newVector = new Vector3(this.x, this.y, this.z);
        newVector.x *= scalar;
        newVector.y *= scalar;
        newVector.z *= scalar;
        return newVector;
    }

    public Vector3 rotateZ(double r) {
        Matrix m = new Matrix(new double[][] {
            {Math.cos(r), -Math.sin(r), 0.0},
            {Math.sin(r), Math.cos(r), 0.0},
            {0.0, 0.0, 1.0}
        });
        return rotate(m);
    }

    public Vector3 rotateY(double r) {
        Matrix m = new Matrix(new double[][] {
            {Math.cos(r), 0.0, Math.sin(r)},
            {0.0, 1.0, 0.0},
            {-Math.sin(r), 0.0, Math.cos(0)}
        });
        return rotate(m);
    }

    public Vector3 rotateX(double r) {
        Matrix m = new Matrix(new double[][] {
            {1.0, 0.0, 0.0},
            {0.0, Math.cos(r), -Math.sin(r)},
            {0.0, Math.sin(r), Math.cos(r)}
        });
        return rotate(m);
    }

    private Vector3 rotate(Matrix m) {
        Matrix m1 = new Matrix(new double[][] {
            {x},
            {y},
            {z}
        });
        Matrix newMatrix = null;
        try {
            newMatrix = m.mult(m1);
        } catch(Exception e) {
            e.printStackTrace();
        } 
        return new Vector3(newMatrix.value[0][0], newMatrix.value[1][0], newMatrix.value[2][0]);
    }

    public double rotZ() {
        if (x == 0) {
            return y > 0 ? Math.PI / 2 : 3 * Math.PI / 2;
        }
        return Math.atan(y / x) + (x < 0 ? Math.PI : 0);
    }

    public double rotY() {
        if (z == 0) {
            return x > 0 ? Math.PI / 2 : 3 * Math.PI / 2;
        }
        return Math.atan(x / z) + (z < 0 ? Math.PI : 0);
    }

    public double rotX() {
        if (y == 0) {
            return z > 0 ? Math.PI / 2 : 3 * Math.PI / 2;
        }
        return Math.atan(z / y) + (y < 0 ? Math.PI : 0);
    }

    public Vector3 rotateXYZ(double r1, double r2, double r3) {
        return rotateX(r1).rotateY(r2).rotateZ(r3);
    }

    public Vector3 align(Vector3 other) {
        return rotateXYZ(other.rotX(), other.rotY(), other.rotZ());
    }

    public static void main(String[] arg0) {
        // Vector3 v = new Vector3(0.0, 1.0, 0.0);
        // Vector3 newV = v.rotateZ(Math.PI);
    }
}
