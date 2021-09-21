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
            if (! app.contact().isThereAContact()) {
                app.contact().createContact(new ContactData().withFirstName("testFirstName").withLastName("testLastName"));
            }
            Contacts before = app.contact().all();
            ContactData deletedContact = before.iterator().next();
            app.contact().selectContactById(deletedContact.getId());
            app.contact().deleteContactButton();
            app.getHelperBase().acceptAlert();
            Contacts after = app.contact().all();
            Assert.assertEquals(after.size(), before.size() -1);
            assertThat(after, equalTo(before.withOut(deletedContact)));
    }
}
