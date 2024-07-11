package org.pftest.enums.pagefly;

public enum PageStatus {
    PUBLISHED, UNPUBLISHED;

    @Override
    public String toString() {
        return this.name().substring(0, 1).toUpperCase() + this.name().substring(1).toLowerCase();
    }
}
