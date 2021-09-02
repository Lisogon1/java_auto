package ru.stqa.pft.addressbook.test;


import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ModificationContactCase extends TestBase {
    @Test
    public void modificationContactTest() {
    app.getContactHelper().choiceContactModification();
    app.getContactHelper().fillContactField(new ContactData("testFirstNameEdit", "testMiddleNameEdit", "testLastNameEdit", "+7919999-9999", "test@gmail.com", "2", "February", "2000"));
    app.getContactHelper().updateContactData();
    }



}
