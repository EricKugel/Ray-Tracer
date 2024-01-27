package scene.components;

import ray.*;
import scene.*;

public class TriangleComponent implements SceneComponent {
    private Vector3 v0;
    private Vector3 v1;
    private Vector3 v2;
    private Vector3 edge0;
    private Vector3 edge1;
    private Vector3 edge2;
    private Vector3 normal;
    private double d;
    public Texture texture;

    public TriangleComponent(Vector3 v0, Vector3 v1, Vector3 v2, Texture texture) {
        this.v0 = v0;
        this.v1 = v1;
        this.v2 = v2;
        this.edge0 = v1.sub(v0);
        this.edge1 = v2.sub(v1);
        this.edge2 = v0.sub(v2);
        this.normal = edge0.cross(edge1);
        this.d = -v0.dot(normal);
        this.texture = texture;
    }

    public TriangleComponent(Vector3 v0, Vector3 v1, Vector3 v2, Vector3 normal, Texture texture) {
        this.v0 = v0;
        this.v1 = v1;
        this.v2 = v2;
        this.edge0 = v1.sub(v0);
        this.edge1 = v2.sub(v1);
        this.edge2 = v0.sub(v2);
        this.normal = edge0.cross(edge1);
        this.normal = this.normal.align(normal);
        this.d = -v0.dot(normal);
        this.texture = texture;
    }

    @Override
    public void translate(Vector3 vector) {
        v0 = v0.add(vector);
        v1 = v1.add(vector);
        v2 = v2.add(vector);
    }

    @Override
    public Intersection intersect(Ray ray) {
        double dotValue = ray.dir.dot(normal);
        if (dotValue == 0) {
            return null;
        } else if (dotValue < 0) {
            // normal = normal.flip();
        }
        double time = -(normal.dot(ray.origin) + d) / normal.dot(ray.dir);
        if (time <= 0) {
            return null;
        }
        Vector3 point = ray.origin.add(ray.dir.mult(time));

        if (normal.dot(edge0.cross(point.sub(v0))) > 0 &&
            normal.dot(edge1.cross(point.sub(v1))) > 0 &&
            normal.dot(edge2.cross(point.sub(v2))) > 0) return new Intersection(time, point, normal, this.texture);
        return null;
        // return new Intersection(time, point, normal, this.texture);
    }
}