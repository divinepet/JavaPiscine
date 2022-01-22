package edu.school21.printer.app;

import edu.school21.printer.logic.ConvertImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class Main {
    private InputStream getFileFromResourceAsStream(String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }

    }

    public static void main(String[] args) {
        InputStream is = new Main().getFileFromResourceAsStream("image.bmp");

        try {
            BufferedImage image = ImageIO.read(is);
            char background = args[0].charAt(0);
            char fill = args[1].charAt(0);
            ConvertImage newimage = new ConvertImage(image);
            newimage.printImage(background, fill);
        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            System.out.println("No such file or not enough arguments");
        }
    }
}