package ray;

import scene.*;

import java.awt.Color;
import java.util.random.RandomGenerator;

public class Ray {
    public static final int MAX_BOUNCES = 3;
    public static final Vector3 UP = new Vector3(0, 1, 0);
    public Vector3 origin;
    public Vector3 dir;

    private static RandomGenerator random = RandomGenerator.of("Xoroshiro128PlusPlus");

    public Ray(Vector3 origin, Vector3 dir) {
        this.origin = origin;
        this.dir = dir.normalized();
    }

    public static double randNormal() { 
        double value = random.nextGaussian();
        return Math.max(-1, Math.min(1, value * .3));
    }

    public Vector3 randomAngle(Intersection i) {
        Vector3 angle = new Vector3(randNormal(), randNormal(), randNormal());
        while (angle.dot(i.normal) < 0) {
            angle = new Vector3(randNormal(), randNormal(), randNormal());
        }
        return angle;
    }

    public Vector3 shinyAngle(Intersection i) {
        return i.normal.rotate(this.dir.flip().getRotationMatrix(i.normal));
    }

    public Vector3 getEnvironment(Vector3 color, Vector3 light) {
        Vector3 envColor = new Vector3(dir.dot(UP) <= 0 ? Color.GREEN : Color.WHITE);
        return light.add(envColor.mash(color).mult(.4));
    }

    public Vector3 doYoThang(Scene scene) {
        Vector3 color = new Vector3(Color.WHITE);
        Vector3 light = new Vector3(Color.BLACK);
        
        Intersection mintersection = null;
        for (int i = 0; i < MAX_BOUNCES; i++) {
            mintersection = null;
            for (SceneComponent comp : scene) {
                Intersection intersection = comp.intersect(this);
                if (intersection != null) {
                    if (mintersection == null || intersection.t < mintersection.t) {
                        mintersection = intersection;
                    }
                }
            }
            if (mintersection == null) { 
                // return getEnvironment(color, light);
                break;
            }
            
            
            Vector3 emittedLight = new Vector3(mintersection.texture.emissionColor).mult(mintersection.texture.luminosity);
            light = light.add(emittedLight.mash(color));
            color = color.mash(new Vector3(mintersection.texture.color));


            this.origin = mintersection.origin;
            this.dir = random.nextDouble() > mintersection.texture.shininess ? randomAngle(mintersection).normalized() : shinyAngle(mintersection).normalized();
        }
        return light;
    }
}