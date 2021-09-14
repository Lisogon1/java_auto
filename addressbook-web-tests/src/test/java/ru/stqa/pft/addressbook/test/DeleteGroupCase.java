package ru.stqa.pft.addressbook.test;


import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class DeleteGroupCase extends TestBase {

  @Test
  public void testDeleteGroupCase() throws Exception {
    app.getNavigationHelper().goToGroupPage();

    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("testGr2", "testHeade22r", "testFoote1r"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getGroupHelper().selectGroup(before.size() - 1);
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
    List<ContactData> after = app.getContactHelper().getContactList();
    before.remove(before.size() -1);
      Assert.assertEquals(before, after);
  }


}
