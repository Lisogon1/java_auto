package ru.stqa.pft.addressbook.applicationmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase{

    public ContactHelper(ChromeDriver wd) {
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


    public void choiceContactModification() {click(By.cssSelector("#maintable > tbody > tr:nth-child(2) > td:nth-child(8) > a > img"));}
}
