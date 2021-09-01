package ru.stqa.pft.addressbook.model;

public class ContactData {
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String phoneMobile;
    private final String userEmail;
    private final String dayOfBirth;
    private final String monthOfBirth;
    private final String yearOfBirth;

    public ContactData(String firstName, String middleName, String lastName, String phoneMobile, String userEmail, String dayOfBirth, String monthOfBirth, String yearOfBirth) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.phoneMobile = phoneMobile;
        this.userEmail = userEmail;
        this.dayOfBirth = dayOfBirth;
        this.monthOfBirth = monthOfBirth;
        this.yearOfBirth = yearOfBirth;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneMobile() {
        return phoneMobile;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getDayOfBirth() {
        return dayOfBirth;
    }

    public String getMonthOfBirth() {
        return monthOfBirth;
    }

    public String getYearOfBirth() {
        return yearOfBirth;
    }
}
