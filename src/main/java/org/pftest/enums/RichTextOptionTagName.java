package org.pftest.enums;

public enum RichTextOptionTagName {
    BOLD("bold", "strong"),
    ITALIC("italic", "em"),
    UNDERLINE("underline", "u"),
    STRIKE("strike", "s"),
    SUPERSCRIPT("superscript", "sup"),
    SUBSCRIPT("subscript", "sub"),
    LINK("link", "a"),
    ;

    private final String tagName;
    private final String htmlTag;

    RichTextOptionTagName (String tagName, String htmlTag) {
        this.tagName = tagName;
        this.htmlTag = htmlTag;
    }

    public String getTagName() {
        return tagName;
    }

    public String getHtmlTag() {
        return htmlTag;
    }

}
