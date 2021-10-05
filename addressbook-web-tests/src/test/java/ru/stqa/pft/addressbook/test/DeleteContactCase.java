package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteContactCase extends TestBase{
        @Test
        public void deleteContactTest() {
            if (app.db().contacts().size() == 0) {
                app.contact().createContact(new ContactData().withFirstName("testModFirstName").withLastName("testModLastName").withPhoneHome("8999999999")
                        .withPhoneMobile("").withPhoneWork("").withUserEmail(""));
            }
            Contacts before = app.db().contacts();
            ContactData deletedContact = before.iterator().next();
            app.contact().deleteContact(deletedContact);
            assertThat(app.contact().count(), equalTo(before.size() - 1));
            Contacts after = app.db().contacts();
            assertThat(after, equalTo(before.withOut(deletedContact)));
    }

}
