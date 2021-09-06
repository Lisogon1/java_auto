package ru.stqa.pft.addressbook.test;


import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ModificationContactCase extends TestBase {
    @Test
    public void modificationContactTest() {
    if (! app.getContactHelper().isThereAContact()) {
          app.getContactHelper().createContact(new ContactData("testFirstName", "testMiddleName", "testLastName", "+7912999-9999", "test@gmail.com", "2", "February", "2000"));
        }
    app.getContactHelper().choiceContactModification();
    app.getContactHelper().fillContactField(new ContactData("testModNameEdit", "testMiddleNameEdit", "testLastNameEdit", "+7919999-9999", "test@gmail.com", "2", "February", "2000"));
    app.getContactHelper().updateContactData();
    }



}
