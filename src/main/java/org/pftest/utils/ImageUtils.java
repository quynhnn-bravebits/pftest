package org.pftest.utils;

import io.qameta.allure.Allure;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;

public class ImageUtils {
    private static final double acceptedDifference = 0.05;

    public static boolean bufferedImagesEqual(BufferedImage imgA, BufferedImage imgB) throws IOException {
        int width = imgA.getWidth();
        int height = imgA.getHeight();
        int totalPixels = width * height;
        int diffPixels = 0;

        if (width == imgB.getWidth() && height == imgB.getHeight()) {
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    if (imgA.getRGB(x, y) != imgB.getRGB(x, y)) {
                        diffPixels++;
                    }
                }
            }
        } else {
            subtractImages(imgA, imgB);
            return false;
        }

        double diffPercent = (double) diffPixels / totalPixels;
        if (diffPercent > acceptedDifference) {
            subtractImages(imgA, imgB);
            return false;
        }
        subtractImages(imgA, imgB);
        return true;
    }

    public static void subtractImages(BufferedImage img1, BufferedImage img2) throws IOException {
        final int w = img1.getWidth(),
                h = img1.getHeight(),
                highlight = Color.MAGENTA.getRGB();
        final int[] p1 = img1.getRGB(0, 0, w, h, null, 0, w);
        final int[] p2 = img2.getRGB(0, 0, w, h, null, 0, w);
        // compare img1 to img2, pixel by pixel. If different, highlight img1's pixel...
        for (int i = 0; i < p1.length; i++) {
            if (p1[i] != p2[i]) {
                p1[i] = highlight;
            }
        }
        // save img1's pixels to a new BufferedImage, and return it...
        final BufferedImage out = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        out.setRGB(0, 0, w, h, p1, 0, w);

        // Convert BufferedImage to byte array
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(out, "png", baos);
        byte[] imageInByte = baos.toByteArray();

        // Attach to Allure report
        Allure.addAttachment("Subtracted Image", "image/png", new ByteArrayInputStream(imageInByte), ".png");
    }
}
