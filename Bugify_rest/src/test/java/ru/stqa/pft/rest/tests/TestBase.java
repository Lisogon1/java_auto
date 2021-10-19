package ru.stqa.pft.rest.tests;

import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


import ru.stqa.pft.rest.applicationmanager.ApplicationManager;
import ru.stqa.pft.rest.model.Issue;

import java.io.IOException;
import java.util.Set;


public class TestBase {

    protected static final ApplicationManager app
            = new ApplicationManager();


    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite
    public void tearDown() throws Exception {
       }

    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    boolean isIssueOpen(Integer  issueId) throws IOException {
        Issue created = app.rest().getIssueData(issueId);
        if (created.getStateName().equals("Closed")) {
            return false;
        }
        if (created.getStateName().equals("Resolved")) {
            return false;
        }
        return true;
    }


}


