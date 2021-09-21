package ru.stqa.pft.addressbook.applicationmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import java.util.List;

public class ContactHelper extends HelperBase{

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
      click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactField(ContactData contactData) {
      type(By.name("firstname"), contactData.getFirstName());
      type(By.name("middlename"),contactData.getMiddleName());
      type(By.name("lastname"),contactData.getLastName());
      type(By.name("mobile"),contactData.getPhoneMobile());
      type(By.name("email"),contactData.getUserEmail());
      type(By.name("byear"),contactData.getYearOfBirth());
    }


    public void updateContactData() {click(By.name("update"));}

    public void deleteContactButton() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void createNewContactButton() {
     click(By.xpath("//*[@id=\"nav\"]/ul/li[2]/a"));
    }


    public void choiceContactModification(int id) {
        click(By.cssSelector("a[href=\"edit.php?id="+id+"\"]"));}

    public void createContact(ContactData contact) {
        createNewContactButton();
        fillContactField(contact);
        submitContactCreation();
        returnToMainPage();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            String lastName = element.findElement(By.xpath("td[2]")).getText();;
            String firstName = element.findElement(By.xpath("td[3]")).getText();;
             contacts.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName));
        }
        return contacts;
    }



    public  void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='"+id+"']")).click();
    }


    public void modificationContact(ContactData modifiedContact, ContactData contact) {
        choiceContactModification(modifiedContact.getId());
        fillContactField(contact);
        updateContactData();
        returnToMainPage();
    }
}
