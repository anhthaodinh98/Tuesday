package com.testarchitect.pages;

import com.logigear.control.base.imp.BaseControl;
import com.logigear.control.common.imp.*;
import com.logigear.control.common.imp.Button;
import com.logigear.control.common.imp.CheckBox;
import com.logigear.control.common.imp.Link;
import com.logigear.driver.DriverUtils;
import com.testarchitect.controls.imp.LGAlert;
import com.testarchitect.utils.Constants;
import org.openqa.selenium.WebDriver;
import java.util.List;

public class GeneralPage {
    private Link lnkDynamicMenu = new Link("//ul[@class='head-menu']//li/a[contains(text(),'%s')]");
    private Link lnkDynamicSubMenu = new Link("//ul[@class='head-menu']//li/a[contains(text(),'%s')]/../ul/li/a[contains(text(),'%s')]");
    private Link lnkDynamicTableLink = new Link("//form[@id='form1']//div[contains(@class,'panel')]/a[text()='%s']");
    private Button btnDynamicFlowButton = new Button("//input[@class='button' and @value='%s']");
    private Link lnkDynamicPage = new Link("//div[@id='main-menu']//li/a[contains(text(),'%s')]");
    private CheckBox chkDynamicCheckbox = new CheckBox("//div[@id='ccontent']//tr[td[text()='%s']]//following-sibling::tr/td[normalize-space()='%s']//input");
    private Link lnkCheckAllLink = new Link("//table[@id='profilesettings']//a[normalize-space()='Check All']");
    private Link lnkUncheckAllLink = new Link("//table[@id='profilesettings']//a[normalize-space()='Uncheck All']");
    private Label lblNavigationTree = new Label("//div[@id='ccontent']//ul/li[contains(@onclick, 'Dashboard.doPostBack') and normalize-space()='%s']");
    LGAlert alert = new LGAlert();

    public void selectMenu(String menuPath) {
        int maxMenuPathLength = 2;
        String[] menuItems = menuPath.split(">");
        lnkDynamicMenu.setDynamicValue(menuItems[0].trim());
        lnkDynamicMenu.waitForVisibility();
        lnkDynamicMenu.click();
        if (menuItems.length == maxMenuPathLength) {
            lnkDynamicSubMenu.setDynamicValue(menuItems[0].trim(), menuItems[1].trim());
            lnkDynamicSubMenu.waitForVisibility();
            lnkDynamicSubMenu.click();
        }
    }

    public String getAlertText() {
        alert.waitForAlertPresent();
        return alert.getText();
    }

    public void acceptAlert() {
        alert.waitForAlertPresent();
        alert.acceptAlert();
    }

    public void clickTableLink(String linkText) {
        lnkDynamicTableLink.setDynamicValue(linkText);
        lnkDynamicTableLink.waitForElementClickable(Constants.WAIT_TIME);
        lnkDynamicTableLink.moveTo();
        lnkDynamicTableLink.click();
    }

    public void clickFlowButton(FlowButton buttonText) {
        btnDynamicFlowButton.setDynamicValue(buttonText.getButtonName());
        btnDynamicFlowButton.waitForVisibility(Constants.LONG_TIME);
        btnDynamicFlowButton.moveTo();
        btnDynamicFlowButton.click();
    }

    public void fillTextBox(TextBox textbox, String value) {
        if (value == null) return;
        textbox.waitForVisibility();
        textbox.clear();
        textbox.enter(value);
    }

    public void fillComboBox(ComboBox combobox, String value) {
        if (value == null) return;
        combobox.waitForElementClickable(Constants.DRIVER_PAGE_LOAD_TIMEOUT);
        combobox.selectByPartialText(value);
    }

    public void fillRadioButton(RadioButton radioButton, String value) {
        if (value == null) return;
        radioButton.setDynamicValue(value);
        radioButton.waitForVisibility();
        radioButton.click();
    }

    public void fillData(BaseControl control, String value) {
        if (ComboBox.class.equals(control.getClass())) {
            fillComboBox((ComboBox) control, value);
        } else if (TextBox.class.equals(control.getClass())) {
            fillTextBox((TextBox) control, value);
        } else if (RadioButton.class.equals(control.getClass())) {
            fillRadioButton((RadioButton) control, value);
        } else {
            throw new IllegalArgumentException(String.format("%s type of control is not supported.", control.getClass()));
        }
    }

    public boolean doesFlowButtonVisible(FlowButton button) {
        btnDynamicFlowButton.setDynamicValue(button.getButtonName());
        btnDynamicFlowButton.waitForVisibility();
        return btnDynamicFlowButton.isVisible();
    }

    /**
     * This step is used to go page at end path
     *
     * @param path example: Page1>Page2>Page3
     */
    public void goToPage(String path) {
        String[] pages = path.split(">");
        for (int i = 0; i < pages.length; i++) {
            lnkDynamicPage.setDynamicValue(pages[i].trim());
            lnkDynamicPage.waitForVisibility();
            lnkDynamicPage.moveTo();
        }
        lnkDynamicPage.click();
        waitForPageLoad();
    }

    /**
     * Check that checkbox field is checked or not
     *
     * @param pageName
     * @param fields   contains checkbox field needs to be checked
     * @return boolean
     */
    public boolean isFieldCheckboxChecked(String pageName, List<String> fields) {
        for (int i = 0; i < fields.size(); i++) {
            chkDynamicCheckbox.setDynamicValue(pageName, fields.get(i));
            chkDynamicCheckbox.waitForVisibility();
            if (!chkDynamicCheckbox.isChecked()) {
                return false;
            }
        }
        return fields.size() > 0;
    }

    public void clickCheckAllLink() {
        lnkCheckAllLink.waitForVisibility();
        lnkCheckAllLink.click();
    }

    public void clickUncheckAllLink() {
        lnkUncheckAllLink.waitForVisibility();
        lnkUncheckAllLink.click();
    }

    public void waitForPageLoad() {
        DriverUtils.delay(Constants.WAIT_TIME);
    }

    public void setCheckBoxState(String pageName, String field, boolean state) {
        chkDynamicCheckbox.setDynamicValue(pageName, field);
        if (state) {
            chkDynamicCheckbox.check();
        } else {
            chkDynamicCheckbox.uncheck();
        }
    }

    public void clickNavigationTreeItem(NavigationTree item) {
        lblNavigationTree.setDynamicValue(item.getNode());
        lblNavigationTree.waitForVisibility();
        lblNavigationTree.click();
    }

    public String getTile(){
        WebDriver driver = DriverUtils.getWebDriver();
        return driver.getTitle();
    }
}

