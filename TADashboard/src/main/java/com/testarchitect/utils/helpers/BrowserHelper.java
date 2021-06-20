package com.testarchitect.utils.helpers;

import com.logigear.driver.DriverProperty;
import com.logigear.driver.DriverUtils;
import com.logigear.helper.BrowserSettingHelper;
import com.testarchitect.utils.Constants;
import com.testarchitect.utils.Log;

public class BrowserHelper {

    private static ThreadLocal<String> CURRENT_BROWSER = new InheritableThreadLocal<>();

    public static void startBrowser(String key, DriverProperty property) {
        try {
            DriverUtils.getDriver(key, property);
            DriverUtils.setPageLoadTimeout(Constants.DRIVER_PAGE_LOAD_TIMEOUT);
            DriverUtils.setTimeOut(Constants.DRIVER_TIMEOUT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void startBrowser(String key) {
        try {
            DriverProperty property = BrowserSettingHelper.getDriverProperty(Constants.BROWSER_SETTING_FILE, getCurrentBrowser());
            startBrowser(key, property);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void setCurrentBrowser(String browser) {
        Log.info("Running with " + browser);
        CURRENT_BROWSER.set(browser);
    }

    public static String getCurrentBrowser() {
        return CURRENT_BROWSER.get();
    }
}
