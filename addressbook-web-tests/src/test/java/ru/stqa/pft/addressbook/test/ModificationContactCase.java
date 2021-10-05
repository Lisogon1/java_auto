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
        if (app.db().contacts().size() == 0) {
            app.contact().createContact(new ContactData().withFirstName("testModFirstName").withLastName("testModLastName").withPhoneHome("8999999999")
                    .withPhoneMobile("").withPhoneWork("").withUserEmail(""));
        }
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId())
                .withFirstName("testModFirstName").withLastName("testModLastName").withPhoneHome("8999999999")
                .withPhoneMobile("").withPhoneWork("").withUserEmail("");
        app.contact().modificationContact(modifiedContact, contact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));
    }
}
