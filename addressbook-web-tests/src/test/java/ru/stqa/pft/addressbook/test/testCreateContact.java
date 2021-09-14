package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class testCreateContact extends TestBase{

  @Test
  public void CreateContactTest() throws Exception {
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData(before.get(before.size() - 1).getId(),"testFirstName", "testMiddleName", "testLastName", "+7912999-9999", "test@gmail.com", "2", "February", "2000");
    app.getContactHelper().createNewContactButton();
    app.getContactHelper().fillContactField(contact);
    app.getContactHelper().submitContactCreation();
    app.getHelperBase().returnToMainPage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size()+1);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);
  }

}
