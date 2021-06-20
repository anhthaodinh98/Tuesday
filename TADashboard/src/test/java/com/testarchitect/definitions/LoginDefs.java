package com.testarchitect.definitions;

import com.testarchitect.pages.LoginPage;
import com.testarchitect.utils.Constants;
import com.testarchitect.utils.helpers.CacheHelper;
import io.cucumber.java8.En;

public class LoginDefs implements En {
    LoginPage loginPage = new LoginPage();

    public LoginDefs() {
        Given("I'm on Dashboard login page", () -> {
            loginPage.openTADashboard();
        });

        When("I get repository on login form and save it {string}", (String variable) -> {
            CacheHelper.setValue(variable, loginPage.getRepository());
        });

        When("I get logged in with ADMIN", () -> {
            loginPage.login(Constants.TA_ADMIN_USERNAME, Constants.TA_ADMIN_PASSWORD);
        });

        When("I login with {string} and {string}", (String userName, String password) -> {
            loginPage.login(userName, password);
        });

        When("I choose the repository with {string} name", (String repositoryName) -> {
            loginPage.selectRepository(repositoryName.replaceAll("\\s+", ""));
        });
    }
}
