package com.testarchitect.definitions;

import com.testarchitect.object.enums.BooleanText;
import com.testarchitect.pages.GeneralPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java8.En;
import org.testng.Assert;

public class CommonDefs implements En {
    GeneralPage generalPage = new GeneralPage();

    public CommonDefs() {
        When("I accept alert message", () -> {
            generalPage.acceptAlert();
        });

        When("I select {string} path", (String menuPath) -> {
            generalPage.selectMenu(menuPath);
        });

        When("^I click on \"(Add New|Delete)\" link above current page table$", (String button) -> {
            generalPage.clickTableLink(button);
        });

        When("I click on Check All link", () -> {
            generalPage.clickCheckAllLink();
        });

        When("I click on Uncheck All link", () -> {
            generalPage.clickUncheckAllLink();
        });

        When("^I click on \"(Next|Finish|Cancel)\" flow button$", (String buttonName) -> {
            generalPage.clickFlowButton(FlowButton.fromName(buttonName));
        });

        When("^I select \"([^\"]*)\" checkbox in the \"(Display Fields|Statistic Fields)\" page$",
                (String field, String pageName) -> {
                    generalPage.setCheckBoxState(pageName, field, true);
                });

        When("I click on {string} in the Navigation left side", (String item) -> {
            generalPage.clickNavigationTreeItem(NavigationTree.fromName(item));
        });

        Then("System should show {string} message", (String message) -> {
            // Replace pipe (|) into (\|) to bypass the pipe character as a part of the content in specflow step table
            Assert.assertEquals(generalPage.getAlertText().replace("|", "\\|"), message,
                    "The displayed message does not match the expected one.");
        });

        Then("^All below checkbox fields are \"(checked|unchecked)\" in the \"(Display Fields|Statistic Fields)\" page$",
                (String state, String pageName, DataTable fields) -> {
                    Assert.assertEquals(generalPage.isFieldCheckboxChecked(pageName, fields.asList()), BooleanText.fromCheckOrNot(state).getValue(),
                            "All checkbox fields display incorrectly");
        });
    }
}
