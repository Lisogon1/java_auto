package ru.stqa.pft.addressbook.test;


import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

public class DeleteGroupCase extends TestBase {

  @Test
  public void testDeleteGroupCase() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("testGr2", "testHeade22r", "testFoote1r"));
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
  }


}
