package ru.stqa.pft.addressbook.test;





import org.hamcrest.MatcherAssert;
import org.slf4j.Logger;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import ru.stqa.pft.addressbook.applicationmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class TestBase {

    Logger logger = LoggerFactory.getLogger(TestBase.class);

    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite
    public void tearDown() throws Exception {
        app.stop();
    }


    @BeforeMethod(alwaysRun = true)
    public void logTestStart(Method m, Object[] p) {
    logger.info("Start test" + m.getName() + "with parametrs" + Arrays.asList(p));
    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method m){
        logger.info("Stop test" + m.getName());
    }


    public void verifyGroupListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Groups dbGroups = app.db().groups();
            Groups UIGroups = app.group().all();
            assertThat(UIGroups, equalTo(dbGroups.stream().map((g) -> new GroupData()
                    .withId(g.getId()).withName(g.getGroupName())).collect(Collectors.toSet())));
        }
    }
}
