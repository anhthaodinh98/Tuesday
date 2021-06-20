package com.testarchitect.pages;

import com.logigear.control.base.imp.Clickable;
import com.logigear.control.common.imp.*;
import com.testarchitect.object.models.AddPageInfo;
import com.testarchitect.utils.Constants;
import io.cucumber.datatable.DataTable;
import java.util.List;
import java.util.Map;

public class DashboardPage extends GeneralPage {
    private Link lnkUser = new Link("//a[@href='#Welcome']");
    private Link lnkLogout = new Link("//a[text()='Logout']");
    private Label lblRepository = new Label("//a[@href='#Repository']/span");
    private Link lnkChoosePanels = new Link("//a[@id='btnChoosepanel']");
    private Button btnCreateNewPanel = new Button("//span[text()='Create new panel']");
    private Link lnkGlobalIcon = new Link("//div[@id='main-menu']//li[@class='mn-setting']");
    private Link lnkDynamicItemSetting = new Link("//li[@class = 'mn-setting']//a[contains(text(),'%s')]");
    private Button btnDynamicButton = new Button("//input[@id='%s']");
    private Link lnkDynamicDialog = new Link("//div[@id='div_popup']//h2[text()='%s']");
    private TextBox txtPageName = new TextBox("//input[@id='name']");
    private Link lnkDynamicPositionAfter = new Link("//a[text()='%s']/ancestor::li/following-sibling::li/a[text()='%s']");
    private ComboBox cbxParentPage = new ComboBox("id=parent");
    private ComboBox cbxNumberOfColumn = new ComboBox("id=columnnumber");
    private ComboBox cbxDisplayAfter = new ComboBox("id=afterpage");
    private CheckBox ckbPublic = new CheckBox("id=ispublic");
    private Link lnkDynamicPage = new Link("//div[@id='main-menu']//a[text()='%s']");
    private Link lnkDynamicChildOrParentPage = new Link("//div[@id='main-menu']//a[(text()='%s')]/ancestor::li//a[(text()='%s')]");
    private Link lnkTitle = new Link("//title[contains(.,'TestArchitect')]");

    // Section: Choose Panels drop-down
    private Link lnkDynamicChartItems = new Link("//div[@class='ptit pchart']//following-sibling::table//a[text()='%s']");
    private ComboBox cbbSelectPage = new ComboBox("id=cbbPages");
    private TextBox txtHeight = new TextBox("id=txtHeight");
    private TextBox txtFolder = new TextBox("id=txtFolder");
    private Clickable divPanelConfiguration = new Clickable("//div[@class='ui-dialog editpanelDlg']//div[@id='div_panelConfigurationDlg']");
    private Button btnDynamicPCPopup = new Button("//div[@class='ui-dialog editpanelDlg']//div[@id='div_panelConfigurationDlg']//input[@id='%s']");


    public void deleteAddedPage(String path) {
        goToPage(path);
        selectItemSetting(SettingsButton.DELETE_PAGE);
        while (alert.isAlertPresent()) {
            acceptAlert();
        }
    }

    public void clickDeletePage(String path) {
        goToPage(path);
        selectItemSetting(SettingsButton.DELETE_PAGE);
    }


    public boolean isDialogDisplayed(String page) {
        lnkDynamicDialog.setDynamicValue(page);
        return lnkDynamicDialog.isVisible();
    }

    public List<String> updateDeletePath(List<String> currentPath, DataTable dataTable) {
        Map<String, String> columns = dataTable.asMaps().get(0);
        for (int i = 0; i < currentPath.size(); i++) {
            // Overview>PageA>PageB
            String pathValue = currentPath.get(i);
            if (columns.get("Field Name").equals("Page Name") || columns.get("Field Name").equals("Paren Page")) {
                pathValue = pathValue.replace(columns.get("Old Value"), columns.get("New Value"));
                currentPath.set(i, pathValue);
            }
        }
        return currentPath;
    }

    public void editPageDataInfo(DataTable dataTable) {
        Map<String, String> columns = dataTable.asMaps().get(0);
        AddPageInfo data = new AddPageInfo();
        if (columns.get("Field Name").equals("Page Name")) {
            data.setPageName(columns.get("New Value"));
        } else if (columns.get("Field Name").equals("Number Of Column")) {
            data.setNumberOfColumn(columns.get("New Value"));
        } else if (columns.get("Field Name").equals("Display After")) {
            data.setDisplayAfter(columns.get("New Value"));
        }
        fillPageDataInfo(data);
    }

    /**
     * Delete all child pages and parent page
     *
     * @param path: page1>page2>page3
     */
    public void deleteAllPagesWithPath(String path) {
        String[] pages = path.split(">");
        for (int i = 0; i < pages.length; i++) {
            if (isPageDisplayed(path)) {
                deleteAddedPage(path);
                if (pages.length <= i + 1) {
                    break;
                } else {
                    path = path.substring(0, path.lastIndexOf(">"));
                    if (path.equals(Constants.OVERVIEW_PAGE_NAME)) {
                        break;
                    }
                }
            }
        }
    }

    /**
     * Check if these added pages are displayed or not
     *
     * @param path: page1>page2>page3 or pageA
     */
    public boolean isPageDisplayed(String path) {
        waitForPageLoad();
        String[] pages = path.split(">");
        boolean result = false;
        int numberOfPages = pages.length;
        //Check if the last page of this path (path = page1>page2>page3) displays or not
        for (int i = 0; i < numberOfPages; i++) {
            lnkDynamicPage.setDynamicValue(pages[i].trim());
            result = lnkDynamicPage.isVisible(Constants.WAIT_TIME);
            if (result) {
                lnkDynamicPage.moveTo();
            } else {
                break;
            }
        }
        return result;
    }
}
