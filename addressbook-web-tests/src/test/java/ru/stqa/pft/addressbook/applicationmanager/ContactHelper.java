package ru.stqa.pft.addressbook.applicationmanager;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase{

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
      click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactField(ContactData contactData, boolean creation) {
      type(By.name("firstname"), contactData.getFirstName());
      type(By.name("middlename"),contactData.getMiddleName());
      type(By.name("lastname"),contactData.getLastName());
      type(By.name("mobile"),contactData.getPhoneMobile());
      type(By.name("email"),contactData.getUserEmail());
      if (creation) {
          if (contactData.getGroups().size() > 0) {
              Assert.assertTrue(contactData.getGroups().size() == 1);
              new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getGroupName());
          }
      } else {
          Assert.assertFalse(isElementPresent(By.name("new_group")));
      }
      type(By.name("byear"),contactData.getYearOfBirth());
      type(By.name("home"),contactData.getPhoneHome());
      type(By.name("work"),contactData.getPhoneWork());
    }


    public void updateContactData() {click(By.name("update"));}

    public void deleteContactButton() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void createNewContactButton() {
     click(By.xpath("//*[@id=\"nav\"]/ul/li[2]/a"));
    }


    public void choiceContactModification(int id) {
        click(By.cssSelector(String.format("a[href='edit.php?id=%s']", id)));}

    public void choiceContactOnTable(int id) {
        click(By.id(String.valueOf(id)));
    }


    public void createContact(ContactData contact) {
        createNewContactButton();
        fillContactField(contact, true);
        submitContactCreation();
        contactCache = null;
        returnToMainPage();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }

        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            String lastName = element.findElement(By.xpath("td[2]")).getText();;
            String firstName = element.findElement(By.xpath("td[3]")).getText();;
            String allPhones = element.findElement(By.xpath("td[6]")).getText();
            String allEmail = element.findElement(By.xpath("td[5]")).getText();
            String tableAddress = element.findElement(By.xpath("td[4]")).getText();
            contactCache.add(new ContactData().withId(id).withFirstName(firstName)
                    .withLastName(lastName).withAllPhones(allPhones)
                    .withAllEmail(allEmail).withTableAddress(tableAddress));
        }
        return new Contacts(contactCache);
    }


    public  void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='"+id+"']")).click();
    }


    public void modificationContact(ContactData modifiedContact, ContactData contact) {
        choiceContactModification(modifiedContact.getId());
        fillContactField(contact, false);
        updateContactData();
        contactCache = null;
        returnToMainPage();
    }

    public void deleteContact(ContactData deletedContact) {
        selectContactById(deletedContact.getId());
        deleteContactButton();
        acceptAlert();
        contactCache = null;
        returnToMainPageAfterContactDeleted();
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public ContactData infoFromEditForm(ContactData contact) {
        choiceContactModification(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lasttname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String adress = wd.findElement(By.name("address")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstName(firstname).withLastName(lasttname)
                .withPhoneHome(home).withPhoneMobile(mobile).withPhoneWork(work)
                .withUserEmail(email).withUserEmail2(email2).withUserEmail3(email3)
                .withAddress(adress);
    }


    public void addContactToGroup(ContactData addedContact, GroupData group) {
        choiceContactOnTable(addedContact.getId());
        wd.findElement(By.name("to_group")).click();
        new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(group.getGroupName());
        click(By.name("add"));
    }

    public void removeContactFromGroup(ContactData addedContact, GroupData group) {
        wd.findElement(By.name("group")).click();
        new Select(wd.findElement(By.name("group"))).selectByVisibleText(group.getGroupName());
        choiceContactOnTable(addedContact.getId());
        wd.findElement(By.name("remove")).click();
    }



    }
