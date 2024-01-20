package ray;

public class Vector3 {
    public final double x;
    public final double y;
    public final double z;

    public static final Vector3 i = new Vector3(1, 0, 0);
    public static final Vector3 j = new Vector3(0, 1, 0);
    public static final Vector3 k = new Vector3(0, 0, 1);

    private double len = -1;

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

    public double dot(Vector3 other) {
        return x * other.x + y * other.y + z * other.z;
    }

    public Vector3 cross(Vector3 other) {
        return i.mult(y * other.z - z * other.y).sub(j.mult(x * other.z - z * other.x)).add(k.mult(x * other.y - y * other.x));
    }

    public Vector3 add(Vector3 other) {
        return new Vector3(x + other.x, y + other.y, z + other.z);
    }

    public Vector3 sub(Vector3 other) {
        return add(other.mult(-1));
    }

    public Vector3 normalized() {
        return mult(1 / len());
    }

    public double len() {
        if (this.len == -1) {
            this.len = Math.sqrt(x * x + y * y + z * z);
        }
        return this.len;
    }

    public Vector3 mult(double scalar) {
        return new Vector3(this.x * scalar, this.y * scalar, this.z * scalar);
    }

    public Vector3 rotateZ(double r) {
        Matrix m = new Matrix(new double[][] {
            {Math.cos(r), -Math.sin(r), 0},
            {Math.sin(r), Math.cos(r), 0},
            {0, 0, 1}
        });
        return rotate(m);
    }

    public Vector3 rotateY(double r) {
        Matrix m = new Matrix(new double[][] {
            {Math.cos(r), 0, Math.sin(r)},
            {0, 1, 0},
            {-Math.sin(r), 0, Math.cos(0)}
        });
        return rotate(m);
    }

    public Vector3 rotateX(double r) {
        Matrix m = new Matrix(new double[][] {
            {1, 0, 0},
            {0, Math.cos(r), -Math.sin(r)},
            {0, Math.sin(r), Math.cos(r)}
        });
        return rotate(m);
    }

    public Vector3 rotate(Matrix m) {
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

    public Matrix getRotationMatrix(Vector3 other) {
        Vector3 a = this.normalized();
        Vector3 b = other.normalized();

        double rot = a.dot(b);
        Vector3 normal = a.cross(b);
        return new Quaternion(rot, normal.x, normal.y, normal.z).getRotationMatrix();        
    }

    public Vector3 rotateXYZ(double r1, double r2, double r3) {
        return rotateX(r1).rotateY(r2).rotateZ(r3);
    }

    public Vector3 align(Vector3 other) {
        return other.mult(len() / other.len());
    }

    public static void main(String[] arg0) {
        Vector3 v = new Vector3(0, 1, 0);
        Vector3 newV = v.rotateZ(Math.PI);
        System.out.println(newV);
    }

    class Quaternion {
        public double q0;
        public double q1;
        public double q2;
        public double q3;
    
        public Quaternion(double q0, double q1, double q2, double q3) {
            this.q0 = q0;
            this.q1 = q1;
            this.q2 = q2;
            this.q3 = q3;
        }

        // https://danceswithcode.net/engineeringnotes/quaternions/quaternions.html
        public Matrix getRotationMatrix() {
            return new Matrix(new double[][] {
                {1 - 2 * q2 * q2 - 2 * q3 * q3, 2 * q1 * q2 - 2 * q0 * q3, 2 * q1 * q3 + 2 * q0 * q2},
                {2 * q1 * q2 + 2 * q0 * q3, 1 - 2 * q1 * q1 - 2 * q3 * q3, 2 * q2 * q3 - 2 * q0 * q1},
                {2 * q1 * q3 - 2 * q0 * q2, 2 * q2 * q3 + 2 * q0 * q1, 1 - 2 * q1 * q1 - 2 * q2 * q2},
            });
        }
    }
}
