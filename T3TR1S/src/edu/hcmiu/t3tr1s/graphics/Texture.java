package edu.hcmiu.t3tr1s.graphics;

import edu.hcmiu.t3tr1s.utils.BufferUtils;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;

import java.io.FileInputStream;
import java.io.IOException;

import static org.lwjgl.opengl.GL11.*;

public class Texture {

    private int WIDTH;
    private int HEIGHT;

    private int texture;

    public Texture(String path) {
        texture = load(path);
    }

    private int load(String path) {
        int pixels[] = null;

        try {
            BufferedImage image = ImageIO.read(new FileInputStream(path));
            WIDTH = image.getWidth();
            HEIGHT = image.getHeight();
            pixels = new int[WIDTH * HEIGHT];
            image.getRGB(0, 0, WIDTH, HEIGHT, pixels, 0, WIDTH);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        int pixelData[] = new int[WIDTH * HEIGHT];
        for (int i = 0; i < WIDTH * HEIGHT; i++) {
            int a = (pixels[i] & 0xff000000) >> 24;
            int r = (pixels[i] & 0xff0000) >> 16;
            int g = (pixels[i] & 0xff00) >> 8;
            int b = (pixels[i] & 0xff) >> 0;

            pixelData[i] = a << 24 | b << 16 | g << 8 | r << 0;
        }

        int result = glGenTextures();

        glBindTexture(GL_TEXTURE_2D, result);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, WIDTH, HEIGHT, 0, GL_RGBA, GL_UNSIGNED_BYTE, BufferUtils.createIntBuffer(pixelData));

        glBindTexture(GL_TEXTURE_2D, 0);

        return result;
    }

    public void bind() {
        glBindTexture(GL_TEXTURE_2D, texture);
    }

    public void unbind() {
        glBindTexture(GL_TEXTURE_2D, 0);
    }
}
