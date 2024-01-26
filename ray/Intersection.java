package ray;

import scene.*;

public class Intersection {
    public double t;
    public Vector3 origin;
    public Vector3 normal;
    public Texture texture;

    public Intersection(double t, Vector3 origin, Vector3 normal, Texture texture) {
        this.t = t;
        this.origin = origin;
        this.normal = normal;
        this.texture = texture;
    }
}
