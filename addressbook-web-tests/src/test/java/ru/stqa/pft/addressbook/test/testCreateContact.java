package ru.stqa.pft.addressbook.test;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

public class testCreateContact extends TestBase{

  @Test
  public void CreateContactTest() throws Exception {
    app.createNewContactButton();
    app.fillContactField(new ContactData("testFirstName", "testMiddleName", "testLastName", "+7912999-9999", "test@gmail.com", "2", "February", "2000"));
    app.submitContactCreation();
    app.returnToMainPage();
  }

}
