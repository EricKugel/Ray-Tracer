package scene.components;

import ray.*;
import scene.*;

public class SphereComponent implements SceneComponent {
    private Vector3 center;
    private double radius;

    public SphereComponent(Vector3 center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public void translate(Vector3 offset) {
        this.center = center.add(offset);
    }


    public Intersection intersect(Ray ray) {
        Vector3 l = this.center.sub(ray.origin);
        double t_center = Vector3.dot(l, ray.dir);
        if (t_center < 0) {
            return null;
        }

        double d = Math.sqrt(Math.pow(l.len(), 2) - t_center * t_center);
        if (d < 0 || d > radius) {
            return null;
        }

        double t_edge = Math.sqrt(radius * radius - d * d);
        double t_0 = t_center - t_edge;
        double t_1 = t_center + t_edge;
        if (t_0 <= 0 || t_1 <= 0) {
            return null;
        }

        double time = Math.min(t_0, t_1);
        Vector3 point = ray.origin.add(ray.dir.mult(time));
        Vector3 normal = point.sub(center).normalized();
        return new Intersection(time, point, normal);
    }

    public static void main(String[] arg0) {
        Ray ray = new Ray(new Vector3(0, 0, 0), new Vector3(1, 0, 0));
        SphereComponent sphere = new SphereComponent(new Vector3(5, 3, 0), 2);
        System.out.println(sphere.intersect(ray));
    }
}