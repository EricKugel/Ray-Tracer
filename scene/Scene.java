package scene;

import ray.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Scene {
    public ArrayList<SceneComponent> components = new ArrayList<SceneComponent>();

    public Scene() {}

    public void add(SceneComponent component) {
        components.add(component);
    }
}
