package ru.stqa.pft.mantis.tests;


import org.openqa.selenium.remote.BrowserType;

import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;
import ru.stqa.pft.mantis.model.Issue;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;


public class TestBase {


    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
        app.ftp().upload(new File("src/test/resources/config_defaults_inc.php"), "config_defaults_inc.php", "config_defaults_inc.php.back");
    }

    @AfterSuite
    public void tearDown() throws Exception {
        app.ftp().restore("config_defaults_inc.php.back","config_defaults_inc.php");
        app.stop();
    }

    public void skipIfNotFixed(Integer issueId) throws RemoteException, ServiceException, MalformedURLException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    boolean isIssueOpen(Integer  issueId) throws RemoteException, ServiceException, MalformedURLException {
        Issue created = app.soap().getIssueData(BigInteger.valueOf(issueId));
        String test = created.getStatus().getName();
        if (created.getStatus().getName().equals("closed")) {
            return false;
        }
        if (created.getStatus().getName().equals("resolved")) {
            return false;
        }
        return true;

    }
}
