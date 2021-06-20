package com.testarchitect.tests;

import io.cucumber.testng.CucumberOptions;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/login_validation.feature",
        glue = {"com.testarchitect.definitions"})

public class LoginTests extends BaseTest {
}
