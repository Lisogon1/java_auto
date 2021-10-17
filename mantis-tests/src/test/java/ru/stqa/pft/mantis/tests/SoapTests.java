package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class SoapTests extends TestBase{


    @Test
    public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
   Set<Project> projects = app.soap().getProjeсts();
        System.out.println(projects.size());
       for (Project project : projects) {
           System.out.println(project.getName());
    }
 }


    @Test
    public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
        Set<Project> projects = app.soap().getProjeсts();
        Issue issue = new Issue().withSummary("Test issue").withDescription("Test issue desc")
                .withProject(projects.iterator().next());
        Issue created = app.soap().addIssue(issue);
        assertEquals(issue.getSummary(), created.getSummary());
    }

    @Test
    public void getIssue() throws RemoteException, ServiceException, MalformedURLException {
        skipIfNotFixed(4);
        Issue issue = app.soap().getIssueData(BigInteger.valueOf(0000002));
        System.out.println(issue.getStatus().getName());

    }
}
