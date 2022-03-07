package edu.school21.printer.app;

import edu.school21.printer.logic.ConvertImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Main {

    private InputStream getFileFromResourceAsStream() {
        return getClass().getClassLoader().getResourceAsStream("image.bmp");
    }

    public static void main(String[] args) {
        try {
            InputStream is = new Main().getFileFromResourceAsStream();
            BufferedImage image = ImageIO.read(is);
            ConvertImage newimage = new ConvertImage(image);
            newimage.printImage(
                    args[0].substring(args[0].indexOf('=') + 1),
                    args[1].substring(args[1].indexOf('=') + 1)
            );
        } catch (IOException | ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            System.out.println("No such file or bad arguments");
        }
    }
}