package controller;

import ray.*;
import scene.*;
import scene.components.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

import org.w3c.dom.Text;

public class Controller {
    private double tick;
    public Controller() {
        Scene scene = new Scene();
        Vector3 origin = new Vector3(0, 20, -15);
        Camera camera = new Camera(origin, new Vector3(0, 0, 30).sub(origin));
        
        SphereComponent s0 = new SphereComponent(new Vector3(-4, 0, 30), 3, Texture.reflectorFactory(Color.WHITE, 1));

        // Texture.reflectorFactory(Color.WHITE, 1) new Texture(Color.RED)                 new Texture(Color.GREEN)
        // Texture.reflectorFactory(Color.PINK, .5) new Texture(Color.RED)                 new Texture(Color.GREEN)
        SphereComponent s1 = new SphereComponent(new Vector3(0, 6, 30), 3, Texture.reflectorFactory(new Color(105, 78, 0), 1));
        SphereComponent s2 = new SphereComponent(new Vector3(4, 0, 30), 3, Texture.reflectorFactory(Color.WHITE, 1));

        BoxMeshComponent b0 = new BoxMeshComponent(new Vector3(-8, -1, 20), new Vector3(8, 1, 30), new Texture(Color.GREEN));

        // TriangleComponent floor = new TriangleComponent(new Vector3(-10, 5, 30),
        //                                                 new Vector3(10, 5, 30), 
        //                                                 new Vector3(-10, 5, 40), new Texture(Color.GREEN, 1));
        // scene.add(floor);
        SphereComponent floor = new SphereComponent(new Vector3(0, -43, 30), 38.5, new Texture(Color.WHITE));
        SphereComponent sun = new SphereComponent(new Vector3(0, 0, 30), .5, new Texture(Color.GREEN, 200, Color.GREEN));
        SphereComponent sunA = new SphereComponent(new Vector3(-10, 0, 30), .5, new Texture(Color.RED, 200, Color.RED));
        SphereComponent sunB = new SphereComponent(new Vector3(10, 0, 30), .5, new Texture(Color.BLUE, 200, Color.BLUE));
        SphereComponent sun1 = new SphereComponent(new Vector3(60, 30, 0), 10, new Texture(Color.WHITE, 15));
        SphereComponent sun2 = new SphereComponent(new Vector3(-60, 30, 0), 10, new Texture(Color.WHITE, 15));
        SphereComponent sun3 = new SphereComponent(new Vector3(0, 90, 0), 10, new Texture(Color.WHITE, 15));
        SphereComponent sun4 = new SphereComponent(new Vector3(0, -90, 0), 10, new Texture(Color.WHITE, 15));
        scene.add(sun);
        scene.add(sun1);
        scene.add(sun2);
        scene.add(sun3);
        scene.add(sun4);
        scene.add(sunA);
        scene.add(sunB);

        scene.add(s0);
        scene.add(s1);
        scene.add(s2);

        // scene.add(b0);

        scene.add(floor);

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
