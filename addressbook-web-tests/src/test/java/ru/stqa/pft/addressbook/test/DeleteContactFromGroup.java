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
            ContactData deletedContact = null;
            GroupData deletedGroup = null;
            for (ContactData contact : contacts) {
                if (contact.getGroups().size() > 1) {
                    deletedContact = contact;
                    break;
                }
            }
            if (deletedContact == null) {
                deletedContact = contacts.iterator().next();
                deletedGroup = app.db().groups().iterator().next();
                app.contact().addContactToGroup(deletedContact, deletedGroup);
                app.goTo().gotoHomePage();
            }
            Groups deletedContactGroupList = app.db().getContactGroupList(deletedContact);
            deletedGroup = deletedContactGroupList.iterator().next();
            app.goTo().gotoHomePage();
            app.contact().removeContactFromGroup(deletedContact,deletedGroup);
            assertThat(deletedContact.getGroups(),equalTo(app.db().getContactGroupList(deletedContact).withAdded(deletedGroup)));
        }

    }




