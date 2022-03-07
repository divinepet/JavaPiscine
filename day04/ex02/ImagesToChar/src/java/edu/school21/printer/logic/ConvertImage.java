package edu.school21.printer.logic;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;

import java.awt.image.BufferedImage;

public class ConvertImage {
    private final BufferedImage image;

    public ConvertImage(BufferedImage image) {
        this.image = image;
    }

    public void printImage(String bgStr, String fgStr) {

        ColoredPrinter bg = new ColoredPrinter.Builder(1, false)
                .background(Ansi.BColor.valueOf(bgStr)).build();
        ColoredPrinter fg = new ColoredPrinter.Builder(1, false)
                .background(Ansi.BColor.valueOf(fgStr)).build();

        int w = image.getWidth();
        int h = image.getHeight();

        for (int i = 0; i < w; i++) {
            bg.setBackgroundColor(Ansi.BColor.valueOf(bgStr));
            for (int j = 0; j < h; j++) {
                int currentColor = image.getRGB(j, i);
                if (currentColor != -1)
                    fg.print(" ");
                else
                    bg.print(" ");
            }
            bg.clear();
            System.out.println();
        }
    }
}