package scene;

import ray.*;

public interface SceneComponent {
    public void translate(Vector3 vector);
    public Intersection intersect(Ray ray);
}
