package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.swing.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="addressbook")
public class ContactData {

    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;
    @Expose
    @Column(name = "firstName")
    private String firstName;
    @Transient
    private String middleName;
    @Expose
    @Column(name = "lastname")
    private String lastName;
    @Type(type = "text")
    @Column(name = "mobile")
    @Expose
    private String phoneMobile;
    @Column(name = "home")
    @Type(type = "text")
    @Expose
    private String phoneHome;
    @Expose
    @Column(name = "work")
    @Type(type = "text")
    private String phoneWork;
    @Transient
    private String allPhones;
    @Expose
    @Column(name = "email")
    @Type(type = "text")
    private String userEmail;
    @Transient
    private String userEmail2;
    @Transient
    private String userEmail3;
    @Transient
    private String allEmail;
    @Expose
    @Transient
    private String tableAddress;
    @Transient
    private String userAddress;
    @Transient
    private String dayOfBirth;
    @Transient
    private String monthOfBirth;
    @Transient
    private String yearOfBirth;
    @Transient
    private String  photo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "address_in_groups", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupData> groups = new HashSet<GroupData>();

    public Groups getGroups() {
        return new Groups(groups);
    }

    public File getPhoto() {
        return new File(photo);
    }

    public ContactData inGroup(GroupData group) {
        groups.add(group);
        return this;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

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
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (phoneMobile != null ? !phoneMobile.equals(that.phoneMobile) : that.phoneMobile != null) return false;
        if (phoneHome != null ? !phoneHome.equals(that.phoneHome) : that.phoneHome != null) return false;
        if (phoneWork != null ? !phoneWork.equals(that.phoneWork) : that.phoneWork != null) return false;
        if (userEmail != null ? !userEmail.equals(that.userEmail) : that.userEmail != null) return false;
        return groups != null ? groups.equals(that.groups) : that.groups == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (phoneMobile != null ? phoneMobile.hashCode() : 0);
        result = 31 * result + (phoneHome != null ? phoneHome.hashCode() : 0);
        result = 31 * result + (phoneWork != null ? phoneWork.hashCode() : 0);
        result = 31 * result + (userEmail != null ? userEmail.hashCode() : 0);
        result = 31 * result + (groups != null ? groups.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneMobile='" + phoneMobile + '\'' +
                ", phoneHome='" + phoneHome + '\'' +
                ", phoneWork='" + phoneWork + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", groups='" + groups + '\'' +
                '}';
    }

}
