package controller;

import ray.*;
import scene.*;
import scene.components.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class Controller {
    private double tick;
    public Controller() {
        Scene scene = new Scene();
        Vector3 origin = new Vector3(0, 10, -5);
        Camera camera = new Camera(origin, new Vector3(0, 0, 30).sub(origin));
        
        SphereComponent s0 = new SphereComponent(new Vector3(-4, 0, 30), 3, new Texture(new Color(194, 148, 227)));
    
        SphereComponent s1 = new SphereComponent(new Vector3(0, 0, 30), 3, new Texture(new Color(59, 83, 84)));
        SphereComponent s2 = new SphereComponent(new Vector3(4, 0, 30), 3, new Texture(Color.PINK));

        BoxMeshComponent b0 = new BoxMeshComponent(new Vector3(-8, -1, 20), new Vector3(8, 1, 30), new Texture(Color.GREEN));

        // TriangleComponent floor = new TriangleComponent(new Vector3(-10, 5, 30),
        //                                                 new Vector3(10, 5, 30), 
        //                                                 new Vector3(-10, 5, 40), new Texture(Color.GREEN, 1));
        // scene.add(floor);
        SphereComponent floor = new SphereComponent(new Vector3(0, -40, 30), 38.5, new Texture(Color.WHITE));
        SphereComponent sun = new SphereComponent(new Vector3(0, 30, 0), 10, new Texture(Color.WHITE, 50));
        scene.add(sun);

        // scene.add(s0);
        // scene.add(s1);
        // scene.add(s2);

        scene.add(b0);

        // scene.add(floor);

        // TriangleComponent tri = new TriangleComponent(new Vector3(0, 4, 30),
        //                                                 new Vector3(-4, 0, 30), 
        //                                                 new Vector3(4, 0, 30), new Texture(Color.GREEN));

        // scene.add(new SphereComponent(new Vector3(-1, 0, 5), 1));
        // scene.add(new SphereComponent(new Vector3(1, 0, 5), 1));
        // scene.add(new SphereComponent(new Vector3(0, 0, 5), 1));
        // scene.add(new SphereComponent(new Vector3(0, .5, 5), 1));
        // scene.add(new SphereComponent(new Vector3(0, 1, 5), 1));
        // scene.add(new SphereComponent(new Vector3(0, 1.5, 5), 1));
        // scene.add(new SphereComponent(new Vector3(0, 2, 5), 1));
        // scene.add(new SphereComponent(new Vector3(0, 2.5, 5), 1));

        // ProgressiveDisplay display = new ProgressiveDisplay();
        // for (int i = 1; i <= Camera.RESOLUTION; i++) {
        //     // , i, Camera.RESOLUTION
        //     display.show(camera.getLayer(scene));
        //     // break;
        //     // System.out.println(i);
        // }
        // System.out.println("Done!");
        Display display = new Display();
        display.show(camera.getFrame(scene));

        // Timer timer = new Timer(1000 / 24, new ActionListener() {
        //     public void actionPerformed(ActionEvent e) {
        //         // camera.set(camera.origin, camera.dir.rotateY(Math.PI / 2));
        //         // camera.set(new Vector3(Math.cos(tick) * 3, 0, 0), camera.dir);
        //         s0.translate(new Vector3(0, Math.sin(tick * 4) / 2, Math.sin(tick)));
        //         s2.translate(new Vector3(0, 0, -Math.sin(tick + Math.PI)));
        //         display.show(camera.getFrame(scene));
        //         tick += .1;
        //     }
        // });
        // timer.start();
    }

    public static void main(String[] arg0) {
        new Controller();
    }
}
