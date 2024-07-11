package org.pftest.projects.V2.pages.editor;

import org.pftest.projects.V2.components.modal.factory.HomePageModalFactory;
import org.pftest.projects.V2.components.modal.factory.PageModalFactory;

public class HomePageEditor extends BaseEditor{
    @Override
    public void save() {

    }

    @Override
    public void publish() {

    }

    @Override
    public PageModalFactory getModalFactory() {
        return new HomePageModalFactory();
    }
}
