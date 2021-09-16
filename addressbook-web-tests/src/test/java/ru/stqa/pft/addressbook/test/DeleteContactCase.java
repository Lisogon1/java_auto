package ru.stqa.pft.addressbook.test;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class DeleteContactCase extends TestBase{
        @Test
        public void deleteContactTest() {
            if (! app.getContactHelper().isThereAContact()) {
                app.getContactHelper().createContact(new ContactData("testFirstName", "testMiddleName", "testLastName", "+7912999-9999", "test@gmail.com", "2", "February", "2000"));
            }
            List<ContactData> before = app.getContactHelper().getContactList();
            app.getContactHelper().selectContact(before.size() - 1);
            app.getContactHelper().deleteContactButton();
            app.getHelperBase().acceptAlert();
            List<ContactData> after = app.getContactHelper().getContactList();
            before.remove(before.size() -1);
            Assert.assertEquals(before, after);
    }
}
