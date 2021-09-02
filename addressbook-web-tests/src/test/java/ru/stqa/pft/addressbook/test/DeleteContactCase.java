package ru.stqa.pft.addressbook.test;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class DeleteContactCase extends TestBase{
        @Test
        public void deleteContactTest() {
            app.getContactHelper().choiceContactOnId();
            app.getContactHelper().deleteContactButton();
            app.getHelperBase().acceptAlert();
    }
}
