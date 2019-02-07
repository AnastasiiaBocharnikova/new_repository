package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.TestBase;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {

        if (applicationManager.db().contacts().size() == 0) {
            applicationManager.goTo().сontactPage();
            applicationManager.contact().create(
                    new ContactData()
                    .withFirstname("Testname")
                    .withMiddlename("TN")
                    .withLastname("TestLastName")
                    .withNickname("nick")
                    .withTitle("111")
                    .withCompany("company")
                    .withAddress("address")
                    .withHomephone("1223")
                    .withMobile("1111")
                    .withWorkphone("22222")
                    .withEmail("qqqq@gmail.com")
                    .withEmail1("dftrg@gmail.com")
                    .withEmail2("frgf@gmail.com")
                    .withBday("15")
                    .withBmonth("October"),
            true);
        }
    }

    @Test
    public void testContactModification(){
        Contacts before = applicationManager.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        File photo = new File("src/test/resources/Screenshot_1.png");
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId())
                .withFirstname("Testname")
                .withMiddlename("TN")
                .withLastname("TestLastName")
                .withNickname("nick")
                .withTitle("111")
                .withCompany("company")
                .withAddress("address")
                .withHomephone("1223")
                .withMobile("1111")
                .withWorkphone("22222")
                .withEmail("qqqq@gmail.com")
                .withEmail1("dftrg@gmail.com")
                .withEmail2("frgf@gmail.com")
                .withBday("15")
                .withBmonth("October")
                .withByear("1992")
                .withPhoto(photo)
            ;
        applicationManager.contact().modify(contact);
        assertEquals(applicationManager.contact().count(), before.size() - 1);
        Contacts after = applicationManager.db().contacts();
        assertThat(after, equalTo(before.without(modifiedContact)));
        verifyContactListInUI();
    }

}
