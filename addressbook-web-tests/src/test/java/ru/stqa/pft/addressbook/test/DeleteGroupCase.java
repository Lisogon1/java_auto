package ru.stqa.pft.addressbook.test;


import org.testng.annotations.*;

public class DeleteGroupCase extends TestBase {

  @Test
  public void testDeleteGroupCase() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
  }


}
