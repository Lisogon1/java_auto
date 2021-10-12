package ru.stqa.pft.addressbook.test;

import com.google.common.collect.Sets;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class AddContactToGroup extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        Contacts PreconditionsContact = app.db().contacts();
        Groups PreconditionsGroups = app.db().groups();
    if (PreconditionsContact.size() == 0) {
        app.contact().createContact(new ContactData().withFirstName("testAddGroup").withLastName("testAddGroup"));
    }
    if (PreconditionsGroups.size() == 0) {
        GroupData group = new GroupData().withName("NameGroupForTestAddContact16")
                .withFooter("FooterGroupForTestAddContact16").withHeader("HeaderGroupForTestAddContact16");
        app.goTo().GroupPage();
        app.group().create(group);
    }
    }


    @Test
    public void AddContactToGroup() {
        Contacts beforeContact = app.db().contacts();
        Groups groupList = app.db().groups();
        ContactData addedContact = null;
        for (ContactData contact : beforeContact) {
            if (contact.getGroups().size() == groupList.size()) {
            } else {
            addedContact = contact;
            break;
            }
        }
        if (addedContact == null) {
            GroupData group = new GroupData().withName("NameGroupForTestAddContact20")
            .withFooter("FooterGroupForTestAddContact20").withHeader("HeaderGroupForTestAddContact20");
            app.goTo().GroupPage();
            app.group().create(group);
            addedContact = beforeContact.iterator().next();
            groupList = app.db().groups();
        }
        Sets.SetView<GroupData> groupsDiff = Sets.difference(groupList,addedContact.getGroups());
        GroupData addedGroup = groupsDiff.iterator().next();
        app.goTo().gotoHomePage();
        app.contact().addContactToGroup(addedContact, addedGroup);
        assertThat(addedContact.getGroups().withAdded(addedGroup),equalTo(app.db().getContactGroupList(addedContact)));
        }

    }

