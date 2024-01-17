package controller;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Display extends JFrame {
    private JPanel mainPanel;
    public Display() {
        setTitle("Ray Tracer");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        initGUI();
        pack();
    }

    private void initGUI() {
        mainPanel = new JPanel();
        add(mainPanel);
    }

    public void show(BufferedImage image) {
        mainPanel.removeAll();
        mainPanel.add(new JLabel(new ImageIcon(image)));
        pack();
    }
}
