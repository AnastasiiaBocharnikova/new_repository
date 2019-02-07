package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    private final Properties properties;
    private WebDriver driver;


    private String browser;
    private RegistrationHelper registrationHelper;
    private FtpHelper ftp;
    private MailHelper mailHelper;
    private SoapHelper soapHelper;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    }

    public void stop() {

        if (driver != null) {
            driver.quit();
        }
    }

    public HttpSession newSession() {

        return new HttpSession(this);
    }

    public String getProperty(String key) {

        return properties.getProperty(key);
    }

    public RegistrationHelper registration() {

        if (registrationHelper == null) {
            registrationHelper = new RegistrationHelper(this);
        }

        return registrationHelper;
    }

    public FtpHelper ftp() {

        if (ftp == null) {
            ftp = new FtpHelper(this);
        }

        return ftp;
    }

    public WebDriver getDriver() {

        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            driver.get(properties.getProperty("web.baseUrl"));
        }

        return driver;
    }

    public MailHelper mail() {

        if (mailHelper == null) {
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }

    public SoapHelper soap() {

        if (soapHelper == null) {
            soapHelper = new SoapHelper(this);
        }
        return soapHelper;
    }
}