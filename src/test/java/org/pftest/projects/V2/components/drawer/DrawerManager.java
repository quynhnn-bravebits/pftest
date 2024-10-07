package org.pftest.projects.V2.components.drawer;

import org.pftest.enums.pagefly.ListingType;

public class DrawerManager {
    private static PageInspector pageInspector;
    private static TemplatesDrawer templatesDrawer;
    private static ElementsDrawer elementsDrawer;

    public static void reset() {
        pageInspector = null;
        templatesDrawer = null;
    }

    public static PageInspector getPageInspector() {
        if (pageInspector == null) {
            pageInspector = new PageInspector();
        }
        return pageInspector;
    }

    public static TemplatesDrawer getTemplatesDrawer(ListingType type) {
        if (templatesDrawer == null) {
            templatesDrawer = new TemplatesDrawer(type);
        }
        return templatesDrawer;
    }

    public static ElementsDrawer getElementsDrawer() {
        if (elementsDrawer == null) {
            elementsDrawer = new ElementsDrawer();
        }
        return elementsDrawer;
    }

}
