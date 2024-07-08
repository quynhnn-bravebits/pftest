package org.pftest.projects.V2.pages.editor;

public interface IEditor {
    public void save();
    public void publish();
    public void close();
    public void changePageTitle(String title);
    public void openPageContentDrawer();
    public void openElementsDrawer();
    public void openThirdPartyDrawer();
    public void openPageTemplatesDrawer();
    public void openPageSettingsDrawer();
    public void openVersionHistoryDrawer();
    public void openCustomCodeDrawer();
    public void openLiveChat();
}
