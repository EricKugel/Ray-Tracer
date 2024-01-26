package scene;

import java.awt.Color;
import java.awt.image.BufferedImage;

import ray.*;

public class Camera {
    public static final double ASPECT_RATIO = 16.0/9;
    public static final int SCREEN_WIDTH = 640;
    public static final int SCREEN_HEIGHT = (int) (SCREEN_WIDTH / ASPECT_RATIO);

    public static final double VIEWPORT_HEIGHT = 2;
    public static final double VIEWPORT_WIDTH = VIEWPORT_HEIGHT * SCREEN_WIDTH / SCREEN_HEIGHT;
    public static final double FOCAL_LENGTH = 5;

    public static final Vector3 ORIGINAL_DIR = new Vector3(0, 0, 1);

    // rays/pixel
    public static final int RESOLUTION = 300;

    public Vector3 origin;
    public Vector3 dir;
    
    private Vector3 firstPixelLocation;
    private Vector3 pixelDeltaU;
    private Vector3 pixelDeltaV;

    public Camera(Vector3 origin, Vector3 dir) {
        set(origin, dir);
    }

    public void set(Vector3 origin, Vector3 dir) {
        this.origin = origin;
        this.dir = dir;

        Vector3 viewportU = new Vector3(VIEWPORT_WIDTH, 0, 0);
        Vector3 viewportV = new Vector3(0, -VIEWPORT_HEIGHT, 0);
        
        Matrix rotationMatrix = ORIGINAL_DIR.getRotationMatrix(dir);
        viewportU = viewportU.rotate(rotationMatrix);
        viewportV = viewportV.rotate(rotationMatrix);
        
        pixelDeltaU = viewportU.mult(1.0 / SCREEN_WIDTH);
        pixelDeltaV = viewportV.mult(1.0 / SCREEN_HEIGHT);

        Vector3 viewportUpperLeft = origin.add(new Vector3(0, 0, FOCAL_LENGTH).align(dir));
        // viewportUpperLeft = viewportUpperLeft.align(dir);
        viewportUpperLeft = viewportUpperLeft.sub(viewportU.mult(.5));
        viewportUpperLeft = viewportUpperLeft.sub(viewportV.mult(.5));
        
        firstPixelLocation = viewportUpperLeft.add(pixelDeltaU.add(pixelDeltaV).mult(.5));
    }

    public BufferedImage getFrame(Scene scene) {
        BufferedImage image = new BufferedImage(SCREEN_WIDTH, SCREEN_HEIGHT, BufferedImage.TYPE_INT_RGB);
        for (int j = 0; j < SCREEN_HEIGHT; j++) {
            for (int i = 0; i < SCREEN_WIDTH; i++) {
                Vector3 pixelCenter = firstPixelLocation.add(pixelDeltaU.mult(i)).add(pixelDeltaV.mult(j));
                Vector3 rayDirection = pixelCenter.sub(origin);

                Vector3[] colors = new Vector3[RESOLUTION];
                for (int k = 0; k < RESOLUTION; k++) {
                    Ray ray = new Ray(origin, rayDirection);
                    colors[k] = ray.doYoThang(scene);
                }
                Color newColor = Vector3.average(colors).toColor();
                image.setRGB(i, j, newColor.getRGB());
            }
            System.out.println("Row " + j + "/" + SCREEN_HEIGHT + " done");
        }
        return image;
    }

    public BufferedImage getLayer(Scene scene) {
        BufferedImage image = new BufferedImage(SCREEN_WIDTH, SCREEN_HEIGHT, BufferedImage.TYPE_INT_RGB);
        for (int j = SCREEN_HEIGHT - 1; j <= 0; j--) {
            for (int i = 0; i < SCREEN_WIDTH; i++) {
                Vector3 pixelCenter = firstPixelLocation.add(pixelDeltaU.mult(i)).add(pixelDeltaV.mult(j));
                Vector3 rayDirection = pixelCenter.sub(origin);
                Ray ray = new Ray(origin, rayDirection);
                image.setRGB(i, j, ray.doYoThang(scene).toColor().getRGB());
            }
        }
        return image;
    }

    public static void main (String[] arg0) {
    }
}
