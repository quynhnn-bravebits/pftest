package org.pftest.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;


public class CommonUtils {
    public static String convertRGBtoRGBA(String rgbColor) {
        if (rgbColor.startsWith("rgb(") && rgbColor.endsWith(")")) {
            return rgbColor.replace("rgb(", "rgba(").replace(")", ", 1)");
        }
        return rgbColor;
    }

    private static void processSpecialElements(Document doc) {
        // Handle Form2.Field FormInput elements
        Elements elements = doc.select("[data-pf-type=FormInput]");
        for (Element element : elements) {
            element.removeAttr("id");
        }
        // Handle CountdownNumber elements
        elements = doc.select("[data-pf-type=CountdownNumber]");
        for (Element element : elements) {
            element.text("");
        }
        // Remove all attributes except class from elements with class "pf-slide"
        elements = doc.getElementsByClass("pf-slide");
        for (Element pfSlideElement : elements) {
            Attributes attributes = pfSlideElement.attributes();
            for (Attribute attribute : attributes) {
                if (!attribute.getKey().equals("class")) {
                    pfSlideElement.removeAttr(attribute.getKey());
                }
            }
        }
    }

    public static String htmlSourceSpecialElementProcessing(String htmlSource) {
        Document doc = Jsoup.parse(htmlSource);
        processSpecialElements(doc);
        return Objects.requireNonNull(doc.getElementById("__pf")).html().replaceAll("pf-\\d+_", "").replace("no-outline", "");
    }


    public static String htmlSourceProcessing(String htmlSource) {
        Document doc = Jsoup.parse(htmlSource);
        processSpecialElements(doc);

        for (Element element : doc.getAllElements()) {
            // Remove dynamic pf attributes
            Iterator<Attribute> attrIterator = element.attributes().iterator();
            while (attrIterator.hasNext()) {
                Attribute attribute = attrIterator.next();
                if ((attribute.getKey().startsWith("data-") && attribute.getKey().endsWith("-id")) || attribute.getKey().equals("tabindex") || attribute.getKey().startsWith("pf-countdown__")) {
                    attrIterator.remove();
                }
            }
            // Remove dynamic pf classes
            String classValue = element.attr("class");
            String[] classes = classValue.split("\\s+");
            List<String> newClasses = new ArrayList<>();
            for (String cls : classes) {
                if (!cls.matches("pf-\\d+_") && !cls.matches("__pf_\\w+") && !cls.equals("no-outline")) {
                    newClasses.add(cls);
                }
            }
            element.attr("class", String.join(" ", newClasses));
        }

        Document newDoc = Document.createShell(doc.baseUri());
        // Remove all link elements to head
        Elements linkElements = doc.select("link");
        for (Element link : linkElements) {
            link.remove();
        }
        // Put all style elements to head
        Elements styleElements = doc.select("style[^data-]");
        for (Element style : styleElements) {
            Element clonedStyle = style.clone();
            newDoc.head().appendChild(clonedStyle);
            style.remove();
        }

        // Put element with id="__pf" to body
        Element pfElement = doc.getElementById("__pf");
        if (pfElement != null) {
            Element clonedPfElement = pfElement.clone();
            newDoc.body().appendChild(clonedPfElement);
        }

        return newDoc.html();
    }
}
