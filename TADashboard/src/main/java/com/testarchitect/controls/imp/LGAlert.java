package com.testarchitect.controls.imp;

import com.logigear.driver.DriverUtils;
import com.testarchitect.controls.IAlert;
import com.testarchitect.utils.Constants;
import org.openqa.selenium.Alert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LGAlert implements IAlert {

    public void waitForAlertPresent() {
        WebDriverWait wait = new WebDriverWait(DriverUtils.getWebDriver(), Constants.WAIT_TIME);
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public void acceptAlert() {
        Alert alert = DriverUtils.getWebDriver().switchTo().alert();
        alert.accept();
    }

    public String getText() {
        Alert alert = DriverUtils.getWebDriver().switchTo().alert();
        String text = alert.getText();
        return text;
    }

    public boolean isAlertPresent() {
        try {
            WebDriverWait wait = new WebDriverWait(DriverUtils.getWebDriver(), Constants.WAIT_TIME);
            wait.until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (TimeoutException Ex) {
            return false;
        }
    }
}
