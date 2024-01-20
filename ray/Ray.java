package ray;

import scene.*;

import java.awt.Color;

public class Ray {
    public Vector3 origin;
    public Vector3 dir;

    public Ray(Vector3 origin, Vector3 dir) {
        this.origin = origin;
        this.dir = dir.normalized();
    }

    public int doYoThang(Scene scene) {
        Color color = Color.WHITE;
        for (SceneComponent comp : scene) {
            Intersection intersection = comp.intersect(this);
            if (intersection != null) {
                color = Color.BLACK;
                if (intersection.normal.x > 0 && intersection.normal.y > 0 && intersection.normal.z < 0) {
                    color = Color.GRAY;
                }
            }
        }
        return color.getRGB();
    }
}