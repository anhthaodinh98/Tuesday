package com.testarchitect.definitions;

import com.testarchitect.object.enums.BooleanText;
import com.testarchitect.object.models.AddPageInfo;
import com.testarchitect.pages.DashboardPage;
import com.testarchitect.utils.Constants;
import com.testarchitect.utils.helpers.CacheHelper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java8.En;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.List;

public class DashboardDefs implements En {
    DashboardPage dashboardPage = new DashboardPage();
    public ThreadLocal<List<String>> newPagesPath = new ThreadLocal<>();
    public List<String> path = new ArrayList<>();
    PanelConfigurationInfo panelConfigurationInfo = new PanelConfigurationInfo();

    public DashboardDefs() {

        Then("^Page should \"(display|not display)\" in the following \"([^\"]*)\" path$", (String state, String path) -> {
            Assert.assertEquals(dashboardPage.isPageDisplayed(path), BooleanText.fromDisplayOrNot(state).getValue(),
                    String.format("All page %s incorrectly with path %s", state, path));
        });

        Then("The popup should show the message: {string}", (String message) -> {
            Assert.assertEquals(dashboardPage.getAlertText().replaceAll("\n", ""), message,
                    String.format("The popup is not shown with message: %s", message));
        });

        When("I enter fields in Page dialog", (DataTable data) -> {
            AddPageInfo dataInput = new AddPageInfo(data);
            dashboardPage.fillPageDataInfo(dataInput);
            path.add(dataInput.getCreatedPages());
            newPagesPath.set(path);
        });

        When("I edit fields in Page dialog", (DataTable dataTable) -> {
            dashboardPage.editPageDataInfo(dataTable);
            path = dashboardPage.updateDeletePath(path, dataTable);
            newPagesPath.set(path);
        });

        When("I click OK button on popup", () -> {
            dashboardPage.acceptAlert();

        });

        When("I go to page with {string} path", (String path) -> {
            dashboardPage.goToPage(path);
        });

        After("@ClosePopup", () -> {
            dashboardPage.clickButton("Cancel");
        });

        After("@DeletePage", () -> {
            List<String> pathList = newPagesPath.get();
            for (int i = pathList.size() - 1; i >= 0; i--) {
                dashboardPage.deleteAllPagesWithPath(pathList.get(i));
            }
        });
    }
}
