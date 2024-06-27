package org.pftest.enums;

public enum DeviceMode {
    ALL_DEVICES("All devices", "all", 1440),
    LAPTOP("Laptop", "laptop", 1025),
    TABLET("Tablet", "tablet", 768),
    MOBILE("Mobile", "mobile", 320);

    private final String name;
    private final String id;
    private final int size;

    DeviceMode(String name, String id, int size) {
        this.name = name;
        this.id = id;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public int getSize() {
        return size;
    }
}