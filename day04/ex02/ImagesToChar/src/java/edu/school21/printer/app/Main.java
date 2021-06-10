package edu.school21.printer.app;

import edu.school21.printer.logic.ConvertImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            File pathToimage = new File(args[0]);
            BufferedImage image = ImageIO.read(pathToimage);
            char background = args[1].charAt(0);
            char fill = args[2].charAt(0);
            ConvertImage newimage = new ConvertImage(image);
            newimage.printImage(background, fill);
        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            System.out.println("No such file or not enough arguments");
        }
    }
}