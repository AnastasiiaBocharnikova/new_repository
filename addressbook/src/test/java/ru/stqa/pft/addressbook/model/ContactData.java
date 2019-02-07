package ru.stqa.pft.addressbook.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "addressbook")
@XStreamAlias("contact")
public class ContactData {

    @XStreamOmitField
    @Id
    private int id = Integer.MAX_VALUE;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "middlename")
    private String middlename;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "title")
    private String title;

    @Column(name = "company")
    private String company;

    @Column(name = "address")
    @Type(type = "text")
    private String address;

    @Column(name = "home")
    @Type(type = "text")
    private String homephone;

    @Column(name = "mobile")
    @Type(type = "text")
    private String mobile;

    @Column(name = "work")
    @Type(type = "text")
    private String workphone;

    @Column(name = "email")
    @Type(type = "text")
    private String email;

    @Column(name = "email2")
    @Type(type = "text")
    private String email1;

    @Column(name = "email3")
    @Type(type = "text")
    private String email2;

    @Transient
    private String bday;

    @Transient
    private String bmonth;

    @Transient
    private String byear;

    @Transient
    private String allPhones;

    @Transient
    private String allEmails;

    @Column(name = "photo")
    @Type(type = "text")
    private String photo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "address_in_groups",
               joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupData> groups = new HashSet<>();


    public int getId() {

        return id;
    }

    public String getFirstname() {

        return firstname;
    }

    public String getMiddlename() {

        return middlename;
    }

    public String getLastname() {

        return lastname;
    }

    public String getNickname() {

        return nickname;
    }

    public String getTitle() {

        return title;
    }

    public String getCompany() {

        return company;
    }

    public String getAddress() {

        return address;
    }

    public String getHomephone() {

        return homephone;
    }

    public String getMobile() {

        return mobile;
    }

    public String getWorkphone() {

        return workphone;
    }

    public String getEmail() {

        return email;
    }

    public String getEmail1() {

        return email1;
    }

    public String getEmail2() {

        return email2;
    }

    public String getAllEmails() {

        return allEmails;
    }


    public String getBday() {

        return bday;
    }

    public String getBmonth() {

        return bmonth;
    }

    public String getByear() {

        return byear;
    }


    public String getAllPhones() {

        return allPhones;
    }

    public File getPhoto() {

        return new File(photo);
    }

    @Override
    public String toString() {

        return "ContactData{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public ContactData withId(int id) {
        this.id = id;

        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;

        return this;
    }

    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;

        return this;
    }

    public ContactData withMiddlename(String middlename) {
        this.middlename = middlename;

        return this;
    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactData withNickname(String nickname) {
        this.nickname = nickname;

        return this;
    }

    public ContactData withTitle(String title) {
        this.title = title;

        return this;
    }

    public ContactData withCompany(String company) {
        this.company = company;

        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;

        return this;
    }

    public ContactData withHomephone(String homephone) {
        this.homephone = homephone;

        return this;
    }

    public ContactData withMobile(String mobile) {
        this.mobile = mobile;

        return this;
    }

    public ContactData withWorkphone(String workphone) {
        this.workphone = workphone;

        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;

        return this;
    }

    public ContactData withEmail1(String email1) {
        this.email1 = email1;

        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;

        return this;
    }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;

        return this;
    }

    public ContactData withBday(String bday) {
        this.bday = bday;

        return this;
    }

    public ContactData withBmonth(String bmonth) {
        this.bmonth = bmonth;

        return this;
    }

    public ContactData withByear(String byear) {
        this.byear = byear;

        return this;
    }

    public Groups getGroups() {
        return new Groups(groups);
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();

        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id &&
                Objects.equals(firstname, that.firstname) &&
                Objects.equals(middlename, that.middlename) &&
                Objects.equals(lastname, that.lastname) &&
                Objects.equals(nickname, that.nickname) &&
                Objects.equals(title, that.title) &&
                Objects.equals(company, that.company) &&
                Objects.equals(address, that.address) &&
                Objects.equals(homephone, that.homephone) &&
                Objects.equals(mobile, that.mobile) &&
                Objects.equals(workphone, that.workphone) &&
                Objects.equals(email, that.email) &&
                Objects.equals(email1, that.email1) &&
                Objects.equals(email2, that.email2) &&
                Objects.equals(allPhones, that.allPhones) &&
                Objects.equals(allEmails, that.allEmails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, middlename,
                lastname, nickname, title, company, address, homephone, mobile,
                workphone, email, email1, email2, allPhones, allEmails);
    }

    public ContactData inGroup(GroupData group) {
        groups.add(group);

        return this;
    }
}
