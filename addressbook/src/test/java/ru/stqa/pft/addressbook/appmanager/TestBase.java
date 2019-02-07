package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class TestBase {

    Logger logger = LoggerFactory.getLogger(TestBase.class);

    protected static final ApplicationManager applicationManager = new ApplicationManager(BrowserType.CHROME);

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        applicationManager.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        applicationManager.stop();
    }

    public ApplicationManager getApplicationManager() {

        return applicationManager;
    }

    @BeforeMethod
    public void logTestStart(Method m, Object[] p) {
        logger.info("Start test" + m.getName() + " with parameters " + Arrays.asList(p));

    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method m) {
        logger.info("Stop test" + m.getName());

    }

    public void verifyGroupListInUI() {

        if (Boolean.getBoolean("verifyUI")) {
            Groups dbGroups = applicationManager.db().groups();
            Groups uiGroups = applicationManager.group().all();
            assertThat(uiGroups, equalTo(
                    dbGroups.stream().map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
                            .collect(Collectors.toSet())
            ));
        }
    }

    public void verifyContactListInUI() {

        if (Boolean.getBoolean("verifyUI")) {
            Contacts dbContacts = applicationManager.db().contacts();
            Contacts uiContacts = applicationManager.contact().all();
            assertThat(uiContacts, equalTo(
                    dbContacts.stream().map((g) -> new ContactData().withId(g.getId()).withLastname(g.getLastname())
                            .withFirstname(g.getFirstname()).withAddress(g.getAddress()).withAllEmails(g.getAllEmails())
                            .withAllPhones(g.getAllPhones())).collect(Collectors.toSet())
            ));
        }
    }
}