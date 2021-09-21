package ru.stqa.pft.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactAddressTest extends TestBase{
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().gotoHomePage();
        if (app.contact().all().size() == 0) {
            app.contact().createContact(new ContactData().withFirstName("test1").withLastName("test2")
                    .withUserEmail("t[e]st@gmail.com213").withUserEmail2("t-e=+st@mail.ru").withUserEmail3("test@post.ru"));
        }
    }


    @Test
    public void testContactPhones() {
        app.goTo().gotoHomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditPage = app.contact().infoFromEditForm(contact);

        assertThat(contact.getTableAddress(), equalTo(mergeAddress(contactInfoFromEditPage)));
    }

    private String  mergeAddress(ContactData contact) {
        return Arrays.asList(contact.getUserAddress())
                .stream().filter((s) -> ! s.equals(""))
                .collect(Collectors.joining("\n"));
    }
}
