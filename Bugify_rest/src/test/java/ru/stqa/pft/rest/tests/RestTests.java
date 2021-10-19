package ru.stqa.pft.rest.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.rest.model.Issue;


import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestTests extends TestBase{

        @Test
    public void testCreateIssue() throws IOException {
     skipIfNotFixed(2);
     Set<Issue> oldIssues = app.rest().getIssue();
     Issue newIssue = new Issue().withSubject("test issue").withDescription("test description");
     int issueId = app.rest().createIssue(newIssue);
     Set<Issue> newIssues = app.rest().getIssue();
     oldIssues.add(newIssue.withId(issueId));
    assertEquals(newIssues,oldIssues);
}



}
