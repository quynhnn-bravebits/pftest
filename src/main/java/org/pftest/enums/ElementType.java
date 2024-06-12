package org.pftest.enums;

public enum ElementType {
    ROW("Row"),
    COLUMN("Column"),
    SECTION("Section"),
    HEADING("Heading2"),
    CONTENT_LIST("ContentList2"),;

    private final String type;

    ElementType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
