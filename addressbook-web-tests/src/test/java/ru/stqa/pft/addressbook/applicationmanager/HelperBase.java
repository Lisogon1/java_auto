package ru.stqa.pft.addressbook.applicationmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;


public class HelperBase {
    public WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    protected void click(By locator) {
        wd.findElement(locator).click();
    }

    protected void type(By locator, String text) {
        click(locator);
        if (text != null) {
            String existingText = wd.findElement(locator).getAttribute("value");
            if (!text.equals(existingText)) {
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(text);
            }
        }
    }

    protected void attach (By locator, File file) {
        if (file != null) {
                wd.findElement(locator).sendKeys(file.getAbsolutePath());
            }
        }

    public void returnToMainPage() {
        wd.findElement(By.linkText("home page")).click();
    }

    public void returnToMainPageAfterContactDeleted() {
        wd.findElement(By.linkText("home")).click();
    }

    public void logoutUser() {
        wd.findElement(By.linkText("Logout")).click();
    }


    public void acceptAlert() {wd.switchTo().alert().accept();}

    public void selectByList(By locator, String text) {
        click(locator);
        new Select(wd.findElement(locator)).selectByVisibleText(text);
    }

    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    protected boolean isElementPresent(By locator) {
        try {
            wd.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
}
