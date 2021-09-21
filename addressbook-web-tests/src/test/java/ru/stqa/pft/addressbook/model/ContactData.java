package ru.stqa.pft.addressbook.model;

public class ContactData {
    private int id = Integer.MAX_VALUE;
    private String firstName;
    private String middleName;
    private String lastName;
    private String phoneMobile;
    private String group;
    private String phoneHome;
    private String phoneWork;
    private String allPhones;
    private String userEmail;
    private String userEmail2;
    private String userEmail3;
    private String allEmail;
    private String tableAddress;
    private String userAddress;
    private String dayOfBirth;
    private String monthOfBirth;
    private String yearOfBirth;

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withAddress(String userAddress) {
        this.userAddress = userAddress;
        return this;
    }

    public ContactData withAllEmail(String allEmail) {
        this.allEmail = allEmail;
        return this;
    }

    public ContactData withTableAddress(String tableAddress) {
        this.tableAddress = tableAddress;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ContactData withMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public ContactData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }
    public ContactData withPhoneHome(String phoneHome) {
        this.phoneHome = phoneHome;
        return this;
    }
    public ContactData withPhoneWork(String phoneWork) {
        this.phoneWork = phoneWork;
        return this;
    }

    public ContactData withPhoneMobile(String phoneMobile) {
        this.phoneMobile = phoneMobile;
        return this;
    }

    public ContactData withUserEmail(String userEmail) {
        this.userEmail = userEmail;
        return this;
    }

    public ContactData withDayOfBirth(String dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
        return this;
    }

    public ContactData withMonthOfBirth(String monthOfBirth) {
        this.monthOfBirth = monthOfBirth;
        return this;
    }

    public ContactData withYearOfBirth(String yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
        return this;
    }

    public ContactData withUserEmail2(String userEmail2) {
        this.userEmail2 = userEmail2;
        return this;
    }

    public ContactData withUserEmail3(String userEmail3) {
        this.userEmail3 = userEmail3;
        return this;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public String getUserEmail2() {
        return userEmail2;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public String getUserEmail3() {
        return userEmail3;
    }

    public String getAllEmail() {
        return allEmail;
    }

    public String getTableAddress() {
        return tableAddress;
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

    public String getGroup() {
        return group;
    }

    public String getPhoneHome() {
        return phoneHome;
    }

    public String getPhoneWork() {
        return phoneWork;
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

        if (id != that.id) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        return middleName != null ? middleName.equals(that.middleName) : that.middleName == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

}
