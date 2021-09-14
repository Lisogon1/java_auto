package ru.stqa.pft.addressbook.applicationmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
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
      selectByList(By.name("bday"),contactData.getDayOfBirth());
      selectByList(By.name("bmonth"),contactData.getMonthOfBirth());
      type(By.name("byear"),contactData.getYearOfBirth());
    }

    public void choiceContactOnId() {click(By.name("selected[]"));}

    public void updateContactData() {click(By.name("update"));}

    public void deleteContactButton() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void createNewContactButton() {
     click(By.xpath("//*[@id=\"nav\"]/ul/li[2]/a"));
    }


    public void choiceContactModification(int index) {
        click(By.cssSelector("a[href=\"edit.php?id="+index+"\"]"));}

    public void createContact(ContactData contact) {
        createNewContactButton();
        fillContactField(contact);
        submitContactCreation();
        returnToMainPage();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            String lastName = element.findElement(By.xpath("td[2]")).getText();;
            String firstName = element.findElement(By.xpath("td[3]")).getText();;
            ContactData contact = new ContactData(id,firstName,null,lastName, null,null,null,null,null);
            contacts.add(contact);
        }
        return contacts;
    }

    public  void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public int GetModificationContactId(int index) {
        int idModContact = Integer.parseInt(wd.findElements(By.name("selected[]")).get(index).getAttribute("id"));
    return idModContact;
    }

    }
