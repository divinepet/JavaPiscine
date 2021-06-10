package edu.school21.printer.logic;

import java.awt.image.BufferedImage;

public class ConvertImage {
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_RESET = "\u001B[0m";
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
                    System.out.print(ANSI_GREEN_BACKGROUND + "  " + ANSI_RESET);
                if (imageArray[i][j] == -1)
                    System.out.print(ANSI_RED_BACKGROUND + "  " + ANSI_RESET);
            }
            System.out.println();
        }
    }
}