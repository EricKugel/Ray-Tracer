package controller;

import javax.swing.*;

import ray.Vector3;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class ProgressiveDisplay extends JFrame {
    private JPanel mainPanel = new JPanel();
    private JPanel imagePanel = new JPanel();
    private JLabel progress = new JLabel("0/0 layers done");

    private BufferedImage image = null;
    
    public ProgressiveDisplay() {
        setTitle("Ray Tracer");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        initGUI();
        pack();
    }

    private void initGUI() {
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        add(mainPanel);
        mainPanel.add(imagePanel);
        mainPanel.add(progress);
    }

    public void show(BufferedImage image) {
        mainPanel.removeAll();
        mainPanel.add(new JLabel(new ImageIcon(image)));
        pack();
    }

    public void update(BufferedImage layer, int done, int total) {
        imagePanel.removeAll();
        if (this.image == null) {
            this.image = layer;
        } else {
            BufferedImage newImage = new BufferedImage(this.image.getWidth(), this.image.getHeight(), BufferedImage.TYPE_INT_RGB);
            double oldWeight = (done - 1) / (double) done;
            double layerWeight = 1 - oldWeight;
            for (int x = 0; x < this.image.getWidth(); x++) {
                for (int y = 0; y < this.image.getHeight(); y++) {
                    Vector3 oldColor = new Vector3(new Color(this.image.getRGB(x, y)));
                    Vector3 layerColor = new Vector3(new Color(layer.getRGB(x, y)));
                    Color newColor = oldColor.mult(oldWeight).add(layerColor.mult(layerWeight)).toColor();
                    newImage.setRGB(x, y, newColor.getRGB());
                }
            }
            this.image = newImage;
        }
        imagePanel.add(new JLabel(new ImageIcon(layer)));
        progress.setText("" + done + "/" + total + " layers complete");
        pack();
    }
}
