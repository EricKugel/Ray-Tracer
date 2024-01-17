package scene.components;

import java.awt.image.BufferedImage;

import ray.*;
import scene.SceneComponent;

public class ImageComponent implements SceneComponent {
    private Vector3 point;
    private Vector3 normal;
    private BufferedImage image;
    
    public ImageComponent(Vector3 point, Vector3 normal, BufferedImage image) {
        this.point = point;
        this.normal = normal;
        this.image = image;

    }

    public void rotateX(Vector3 vector) {
        this.normal = this.normal.add(vector);
    }

    @Override
    public void translate(Vector3 vector) {
        this.point = this.point.add(vector);
    }

    @Override
    public Intersection intersect(Ray ray) {
        return null;
    }
}
