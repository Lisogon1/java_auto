package ru.stqa.pft.addressbook.test;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;


import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class testCreateContact extends TestBase{

  @Test
  public void CreateContactTest() throws Exception {
    Contacts before = app.contact().all();
    ContactData contact = new ContactData()
            .withFirstName("testFirstName")
            .withLastName("testLastName");
    app.contact().createContact(contact);
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));



  }

}
