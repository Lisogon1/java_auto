package ru.stqa.pft.addressbook.test;



import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;


import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;


public class testCreateGroup extends TestBase{

  @DataProvider
  public Iterator<Object[]> validGroupsFromCsv() throws IOException {
      List<Object[]> list = new ArrayList<Object[]>();
      try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.csv")))) {
          String line = reader.readLine();
          while (line != null) {
              String[] split = line.split(";");
              list.add(new Object[]{new GroupData().withName(split[0]).withFooter(split[1]).withHeader(split[2])});
              line = reader.readLine();
          }
          return list.iterator();
      }
  }

    @DataProvider
    public Iterator<Object[]> validGroupsFromJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.json")))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>() {
            }.getType());
            return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

  @Test(dataProvider = "validGroupsFromJson")
  public void testGroupCreation(GroupData group) throws Exception {
    app.goTo().GroupPage();
    Groups before = app.db().groups();
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size() + 1));
    Groups after = app.db().groups();
      assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
      verifyGroupListInUI();
  }



}
