package com.testarchitect.controls;

public interface IAlert {
    void waitForAlertPresent();

    void acceptAlert();

    String getText();

    boolean isAlertPresent();
}
