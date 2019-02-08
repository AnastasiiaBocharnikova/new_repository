package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.TestBase;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {

        if (applicationManager.contact().all().size() == 0) {
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
                    .withHomephone("+1223")
                    .withMobile("11-11")
                    .withWorkphone("22 222")
                    .withBday("15")
                    .withBmonth("October")
                    .withByear("1992"),
                true);
        }
    }

    @Test(enabled = false)
    public void testContactPhones() {
        applicationManager.goTo().goToHomePage();
        ContactData contact = applicationManager.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = applicationManager.contact().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    }

    private String mergePhones(ContactData contact) {

        return Arrays.asList(contact.getHomephone(), contact.getMobile(), contact.getWorkphone())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactPhoneTests::cleaned).collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone) {

        return phone.replaceAll("\\s", "").replaceAll("-()", "");
    }

}
