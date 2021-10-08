package ru.stqa.pft.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class DeleteContactFromGroup extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        Contacts PreconditionsContact = app.db().contacts();
        Groups PreconditionsGroups = app.db().groups();
        GroupData newGroup = new GroupData().withName("NameGroupForTestAddContact16")
                .withFooter("FooterGroupForTestAddContact16").withHeader("HeaderGroupForTestAddContact16");
        if (PreconditionsGroups.size() == 0) {
            app.goTo().GroupPage();
            app.group().create(newGroup);
            app.goTo().gotoHomePage();
        }
        if (PreconditionsContact.size() == 0) {
            Groups predictionGroupsAfterCreate = app.db().groups();
            GroupData group = predictionGroupsAfterCreate.iterator().next();
            app.contact().createContact(new ContactData().withFirstName("testAddGroup").withLastName("testAddGroup").inGroup(group));
            app.goTo().gotoHomePage();
        }
    }

        @Test
        public void deleteContactFromGroup() {
            Contacts contacts = app.db().contacts();
            Groups groupList = app.db().groups();
            ContactData deletedContact = contacts.iterator().next();
            GroupData deletedGroup = groupList.iterator().next();
            app.goTo().gotoHomePage();
        if (deletedContact.getGroups().size() == 0) {
            app.contact().addContactToGroup(deletedContact, deletedGroup);
            app.goTo().gotoHomePage();
        }
            Contacts beforeContact = app.db().contacts();
            app.contact().removeContactFromGroup(deletedContact,deletedGroup);
            Contacts afterContact = app.db().contacts();
            assertThat(beforeContact, equalTo(afterContact.withOut(deletedContact).withAdded(deletedContact.inGroup(deletedGroup))));

        }

    }




