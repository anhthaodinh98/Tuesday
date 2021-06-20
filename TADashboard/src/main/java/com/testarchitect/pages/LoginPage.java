package com.testarchitect.pages;

import com.logigear.control.common.imp.Button;
import com.logigear.control.common.imp.ComboBox;
import com.logigear.control.common.imp.TextBox;
import com.logigear.driver.DriverUtils;
import com.testarchitect.utils.Constants;
import com.testarchitect.utils.Log;
import org.apache.commons.lang3.StringUtils;

public class LoginPage extends GeneralPage {
    private ComboBox cbbRepository = new ComboBox("id=repository");
    private TextBox txtUserName = new TextBox("id=username");
    private TextBox txtPassword = new TextBox("id=password");
    private Button btnLogin = new Button("class=btn-login");

    public void openTADashboard() {
        String envUrl = System.getenv("ENV_URL");
        String dashboardUrl = StringUtils.isEmpty(envUrl) ? Constants.TA_URL : envUrl;
        Log.info("Current env: " + dashboardUrl);
        DriverUtils.navigate(dashboardUrl);
        DriverUtils.waitForAjax();
        waitForPageLoad();
    }

    public String getRepository() {
        return cbbRepository.getValue();
    }

    public void login(String user, String password) {
        txtUserName.enter(user);
        txtPassword.enter(password);
        btnLogin.click();
        waitForPageLoad();
    }

    public void selectRepository(String repositoryName) {
        if (repositoryName != null) {
            cbbRepository.waitForDisplay();
            cbbRepository.select(repositoryName);
        }
    }
}
