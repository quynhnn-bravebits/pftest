package org.pftest.utils;

public class CommonUtils {
    public static String convertRGBtoRGBA(String rgbColor) {
        if (rgbColor.startsWith("rgb(") && rgbColor.endsWith(")")) {
            return rgbColor.replace("rgb(", "rgba(").replace(")", ", 1)");
        }
        return rgbColor;
    }
}
