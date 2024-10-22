package org.pftest.enums.pagefly;

public enum ModalType {
    BEFORE_SAVE_PAGE("Please name your page before saving", "Save", "Cancel"),
    SAVE_PAGE("Save Page", "Save", "Cancel"),
    BEFORE_PUBLISH_PAGE("Please name your page before publishing", "Publish", "Cancel"),
    PUBLISH_PAGE("Your page is ready to publish!", "Publish", "Cancel"),
    PUBLISHING_HOMEPAGE("Publishing homepage", "Publish", "Cancel"),
    SELECT_PRODUCTS("Select products", "Select", "Cancel"),
    SELECT_COLLECTIONS("Select collections", "Select", "Cancel"),
    SELECT_EDITOR_TYPE("Create new page", null, null),
    DELETE("Delete %02d %s?", "Delete", "Cancel"),
    EXPORT("Export %s", "Export", "Cancel"),
    ;

    private String title;
    private String primaryButton;
    private String secondaryButton;

    ModalType(String title, String primaryButton, String secondaryButton) {
        this.title = title;
        this.primaryButton = primaryButton;
        this.secondaryButton = secondaryButton;
    }

    public String getTitle() {
        return title;
    }

    public String getPrimaryButton() {
        return primaryButton;
    }

    public String getSecondaryButton() {
        return secondaryButton;
    }
}
