package scene;

import java.awt.*;

public class Texture {
    public Color color;
    public Color emissionColor = Color.WHITE;
    public double luminosity = 0;
    public double shininess = 0;

    public Texture(Color color) {
        this.color = color;
    }

    public Texture(Color color, double luminosity) {
        this.color = color;
        this.luminosity = luminosity;
    }

    public Texture(Color color, double luminosity, Color emissionColor) {
        this.color = color;
        this.luminosity = luminosity;
        this.emissionColor = emissionColor;
    }

    public static Texture reflectorFactory(Color color, double shininess) {
        Texture texture = new Texture(color);
        texture.shininess = shininess;
        return texture;
    }
}
