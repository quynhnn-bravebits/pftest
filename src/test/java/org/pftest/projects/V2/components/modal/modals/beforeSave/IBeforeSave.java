package org.pftest.projects.V2.components.modal.modals.beforeSave;

import org.pftest.projects.V2.components.modal.modals.IBaseModal;

public interface IBeforeSave extends IBaseModal {
    void fillPageTitle(String pageTitle);
    void fillPageTitleAndSave(String pageTitle);
}
