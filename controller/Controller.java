package controller;

import ray.*;
import scene.*;
import scene.components.SphereComponent;

import java.awt.event.*;
import javax.swing.*;

public class Controller {
    private double tick;
    public Controller() {
        Scene scene = new Scene();
        Camera camera = new Camera(new Vector3(0, 0, 0), new Vector3(0, 0, 1));
        
        scene.add(new SphereComponent(new Vector3(0, 0, 5), 1));
        scene.add(new SphereComponent(new Vector3(-4, 0, 5), 1));
        scene.add(new SphereComponent(new Vector3(4, 0, 5), 1));

        // // 😠 Who would use such beautiful code in such a degenerate way
        // scene.add(new SphereComponent(new Vector3(-1, 0, 5), 1));
        // scene.add(new SphereComponent(new Vector3(1, 0, 5), 1));
        // scene.add(new SphereComponent(new Vector3(0, 0, 5), 1));
        // scene.add(new SphereComponent(new Vector3(0, .5, 5), 1));
        // scene.add(new SphereComponent(new Vector3(0, 1, 5), 1));
        // scene.add(new SphereComponent(new Vector3(0, 1.5, 5), 1));
        // scene.add(new SphereComponent(new Vector3(0, 2, 5), 1));
        // scene.add(new SphereComponent(new Vector3(0, 2.5, 5), 1));

        Display display = new Display();

        Timer timer = new Timer(1000 / 24, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                camera.set(camera.origin.add(new Vector3(Math.cos(tick) / 4, 0, 0)), camera.dir);
                display.show(camera.getFrame(scene));
                tick += .1;
            }
        });
        timer.start();
    }

    public static void main(String[] arg0) {
        new Controller();
    }
}
