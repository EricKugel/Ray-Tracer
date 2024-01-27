package scene.components;

import java.util.ArrayList;

import ray.Intersection;
import ray.Ray;
import ray.Vector3;
import scene.SceneComponent;
import scene.Texture;

public class BoxMeshComponent implements SceneComponent {
    public ArrayList<TriangleComponent> triangles = new ArrayList<TriangleComponent>();

    public void translate(Vector3 vector) {
        // translate it yourself dangit
        return;
    }

    public BoxMeshComponent(Vector3 p0, Vector3 p1, Texture texture) {
        Vector3 d = p1.sub(p0);
        Vector3[][] triangles = {
            // top, bottom
            {p0, p0.add(d.getX()), p0.add(d.getY()), d.getZ()},
            {p1.sub(d.getZ()), p0.add(d.getX()), p0.add(d.getY()), d.getZ()},
            {p0.add(d.getZ()), p0.add(d.getX()).add(d.getZ()), p0.add(d.getY()).add(d.getZ()), d.getZ().flip()},
            {p1, p0.add(d.getX()).add(d.getZ()), p0.add(d.getY()).add(d.getZ()), d.getZ().flip()},
            // front, back
            {p0, p0.add(d.getX()), p0.add(d.getZ()), d.getY()},
            {p1.sub(d.getY()), p0.add(d.getX()), p0.add(d.getZ()), d.getY()},
            {p0.add(d.getY()), p0.add(d.getX()).add(d.getY()), p0.add(d.getZ()).add(d.getY()), d.getY().flip()},
            {p1, p0.add(d.getX()).add(d.getY()), p0.add(d.getZ()).add(d.getY()), d.getY().flip()},
            // sides
            {p0, p0.add(d.getZ()), p0.add(d.getY()), d.getX()},
            {p1.sub(d.getX()), p0.add(d.getZ()), p0.add(d.getY()), d.getX()},
            {p0.add(d.getX()), p0.add(d.getZ()).add(d.getX()), p0.add(d.getY()).add(d.getX()), d.getX().flip()},
            {p1, p0.add(d.getZ()).add(d.getX()), p0.add(d.getY()).add(d.getX()), d.getX().flip()},
        };
        for (Vector3[] triangle : triangles) {
            this.triangles.add(new TriangleComponent(triangle[0], triangle[1], triangle[2], triangle[3], texture));
        }
    }

    @Override
    public Intersection intersect(Ray ray) {
        Intersection mintersection = null;
        for (TriangleComponent component : this.triangles) {
            Intersection intersection = component.intersect(ray);
            if (intersection != null) {
                if (mintersection == null || intersection.t < mintersection.t) {
                    mintersection = intersection;
                }
            }
        }
        return mintersection;
    }
}
