package ru.stqa.pft.addressbook.model;

public class ContactData {
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    private String phoneMobile;
    private String userEmail;
    private String dayOfBirth;
    private String monthOfBirth;
    private String yearOfBirth;

    public ContactData(String firstName, String middleName, String lastName, String phoneMobile, String userEmail, String dayOfBirth, String monthOfBirth, String yearOfBirth) {
        this.id = Integer.MAX_VALUE;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.phoneMobile = phoneMobile;
        this.userEmail = userEmail;
        this.dayOfBirth = dayOfBirth;
        this.monthOfBirth = monthOfBirth;
        this.yearOfBirth = yearOfBirth;
    }

    public ContactData(int id, String firstName, String middleName, String lastName, String phoneMobile, String userEmail, String dayOfBirth, String monthOfBirth, String yearOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.phoneMobile = phoneMobile;
        this.userEmail = userEmail;
        this.dayOfBirth = dayOfBirth;
        this.monthOfBirth = monthOfBirth;
        this.yearOfBirth = yearOfBirth;
    }

    public int getId() {
        return id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }



}
