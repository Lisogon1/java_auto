package ru.stqa.pft.addressbook.applicationmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class NavigationHelper extends HelperBase{

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void GroupPage() {
        click(By.linkText("groups"));
    }

    public void gotoHomePage() {
        click(By.linkText("home"));
    }
}
