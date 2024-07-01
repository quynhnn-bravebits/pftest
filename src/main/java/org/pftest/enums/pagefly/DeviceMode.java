package org.pftest.enums.pagefly;

public enum DeviceMode {
    ALL_DEVICES("All devices", "all", 1440, 1200, 10000, 16 * 1.0 / 9),
    LAPTOP("Laptop", "laptop", 1025, 1025, 1199, 16 * 1.0 / 9),
    TABLET("Tablet", "tablet", 768, 768, 1024, 3 * 1.0 / 4),
    MOBILE("Mobile", "mobile", 320, 0, 767, 9 * 1.0 / 16),;

    private final String name;
    private final String id;
    private final int defaultSize;
    private final int minSize;
    private final int maxSize;
    private final double ratio;

    DeviceMode(String name, String id, int size, int min, int max, double ratio) {
        this.name = name;
        this.id = id;
        this.defaultSize = size;
        this.minSize = min;
        this.maxSize = max;
        this.ratio = ratio;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public int getDefaultSize() {
        return defaultSize;
    }

    public int getMinSize() {
        return minSize;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public double getRatio() {
        return ratio;
    }

    public int getRatioHeight(int width) {
        System.out.println(width / ratio);
        return (int) Math.round (width / ratio);
    }

    public int getRatioWidth(int height) {
        return (int) Math.round (height * ratio);
    }
}