package ray;

public class Intersection {
    public Vector3 origin;
    public Vector3 normal;

    public Intersection(double t, Vector3 origin, Vector3 normal) {
        this.origin = origin;
        this.normal = normal;
    }
}
