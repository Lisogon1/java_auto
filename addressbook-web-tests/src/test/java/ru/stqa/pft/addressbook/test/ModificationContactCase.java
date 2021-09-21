package ru.stqa.pft.addressbook.test;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ModificationContactCase extends TestBase {
    @Test
    public void modificationContactTest() {
        if (! app.contact().isThereAContact()) {
            app.contact().createContact(new ContactData().withFirstName("testFirstName").withLastName("testLastName"));
        }
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId())
                .withFirstName("testFirstName").withLastName("testLastName");
        app.contact().modificationContact(modifiedContact, contact);
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));
        Assert.assertEquals(after.size(), before.size());
    }
}

