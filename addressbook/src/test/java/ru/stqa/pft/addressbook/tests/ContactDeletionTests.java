package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.TestBase;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {

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
              .withBday("15")
              .withEmail("qqqq@gmail.com")
              .withEmail1("dftrg@gmail.com")
              .withEmail2("frgf@gmail.com")
              .withBmonth("October")
              .withByear("1992"),
          true);
    }
  }

  @Test
  public void testContactDeletion() throws Exception {
    Contacts before = applicationManager.db().contacts();
    ContactData deletedContact = before.iterator().next();
    applicationManager.contact().delete(deletedContact);
    Thread.sleep(3000);
    assertEquals(applicationManager.contact().count(), before.size() - 1);
    Contacts after = applicationManager.db().contacts();
    assertThat(after, equalTo(before.without(deletedContact)));
    verifyContactListInUI();
  }


}
