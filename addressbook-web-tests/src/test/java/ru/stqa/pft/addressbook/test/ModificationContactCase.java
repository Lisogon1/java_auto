package ru.stqa.pft.addressbook.test;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ModificationContactCase extends TestBase {
    @Test
    public void modificationContactTest() {
    if (! app.getContactHelper().isThereAContact()) {
          app.getContactHelper().createContact(new ContactData("testFirstName", "testMiddleName", "testLastName", "+7912999-9999", "test@gmail.com", "2", "February", "2000"));
        }
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData(before.get(before.size() - 1).getId(),"testFirstName", "testMiddleName", "testLastName", "+7912999-9999", "test@gmail.com", "2", "February", "2000");
    int modId = app.getContactHelper().GetModificationContactId(before.size() - 1);
    app.getContactHelper().choiceContactModification(modId);
    app.getContactHelper().fillContactField(contact);
    app.getContactHelper().updateContactData();
    app.getHelperBase().returnToMainPage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());
    before.remove(before.size()-1);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);
    }



}
