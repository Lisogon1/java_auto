package ru.stqa.pft.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactEmailTest extends TestBase{
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().gotoHomePage();
        Contacts contact = app.contact().all();
        if (app.contact().all().size() == 0) {
            app.contact().createContact(new ContactData().withFirstName("test1").withLastName("test2")
                    .withAddress("test AAA+-[]\n" + "test st 345 kv 1++\n" + "test.St 23++"));
        }

    }


    @Test
    public void testContactPhones() {
        app.goTo().gotoHomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditPage = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllEmail(), equalTo(mergeEmail(contactInfoFromEditPage)));
    }

    private String  mergeEmail(ContactData contact) {
        return Arrays.asList(contact.getUserEmail(), contact.getUserEmail2(), contact.getUserEmail3())
                .stream().filter((s) -> ! s.equals(""))
                .collect(Collectors.joining("\n"));
    }
}
