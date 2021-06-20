package com.testarchitect.tests;

import com.logigear.driver.DriverUtils;
import com.testarchitect.utils.helpers.BrowserHelper;
import com.testarchitect.utils.helpers.CacheHelper;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.*;

public class BaseTest extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @BeforeMethod
    @Parameters("browser")
    public void setUpBrowser(@Optional("chrome.local") String browser) {
        BrowserHelper.setCurrentBrowser(browser);
        BrowserHelper.startBrowser(browser);
    }

    @AfterMethod
    public void closeBrowser() {
        CacheHelper.cleanUp();
        DriverUtils.quitAllBrowsers();
    }
}
