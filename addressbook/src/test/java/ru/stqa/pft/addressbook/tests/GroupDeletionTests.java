package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.TestBase;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    applicationManager.goTo().groupPage();

    if (applicationManager.group().all().size() == 0) {
      applicationManager.group().create(new GroupData().withName("test11"));
    }
  }

  @Test
  public void testGroupDeletion() {
    Groups before = applicationManager.db().groups();
    GroupData deletedGroup = before.iterator().next();
    applicationManager.group().delete(deletedGroup);
    assertEquals(applicationManager.group().count(), before.size() - 1);
    Groups after = applicationManager.db().groups();
    assertThat(after, equalTo(before.without(deletedGroup)));
    verifyGroupListInUI();
  }



}
