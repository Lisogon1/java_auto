package ru.stqa.pft.addressbook.test;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;


import java.io.File;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class testCreateContact extends TestBase{

  @Test
  public void CreateContactTest() throws Exception {
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/test1.png");
    ContactData contact = new ContactData()
            .withFirstName("testFirstName")
            .withLastName("testLastName")
            .withPhoto(photo);
    app.contact().createContact(contact);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));



  }

}
