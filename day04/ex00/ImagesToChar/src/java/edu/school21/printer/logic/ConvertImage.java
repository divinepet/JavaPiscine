package edu.school21.printer.logic;

import java.awt.image.BufferedImage;

public class ConvertImage {
    private final BufferedImage image;

    public ConvertImage(BufferedImage image) {
        this.image = image;
    }

    public void printImage(char background, char fill) {
        int w = image.getWidth();
        int h = image.getHeight();
        int[][] imageArray = new int[w][h];

        for (int i=0; i<w; i++) {
            for(int j=0; j<h; j++) {
                imageArray[i][j] = image.getRGB(j, i);
                if (imageArray[i][j] != -1)
                    imageArray[i][j] = fill;
                if (imageArray[i][j] == -1)
                    imageArray[i][j] = background;
                System.out.print((char)imageArray[i][j]);
            }
            System.out.println();
        }
    }
}