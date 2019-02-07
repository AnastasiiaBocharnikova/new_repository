package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class HelperBase {
    protected ApplicationManager app;
    protected WebDriver driver;

    public HelperBase(ApplicationManager applicationManager) {
        this.app = applicationManager;
        this.driver = app.getDriver();
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public void type(By locator, String text) {
        click(locator);

        if (text != null) {
            String existingText = driver.findElement(locator).getAttribute("value");

            if (!text.equals(existingText)) {
                driver.findElement(locator).clear();
                driver.findElement(locator).sendKeys(text);
            }
        }
    }

    public void attach(By locator, File file) {

        if (file != null) {
            driver.findElement(locator).sendKeys(file.getAbsolutePath());
        }
    }

    private boolean isAlertPresent() {

        try {
            driver.switchTo().alert();

            return true;

        } catch (NoAlertPresentException e) {

            return false;
        }
    }

    public boolean isElementPresent(By locator) {

        try {
            driver.findElement(locator);

            return true;

        } catch (NoSuchElementException e) {

            return false;
        }
    }
}
