package org.pftest.enums;

public enum DeviceMode {
    ALL_DEVICES("All devices", "all", 1440, 1200, 10000),
    LAPTOP("Laptop", "laptop", 1025, 1025, 1199),
    TABLET("Tablet", "tablet", 768, 768, 1024),
    MOBILE("Mobile", "mobile", 320, 0, 767);

    private final String name;
    private final String id;
    private final int defaultSize;
    private final int minSize;
    private final int maxSize;

    DeviceMode(String name, String id, int size, int min, int max) {
        this.name = name;
        this.id = id;
        this.defaultSize = size;
        this.minSize = min;
        this.maxSize = max;
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
}