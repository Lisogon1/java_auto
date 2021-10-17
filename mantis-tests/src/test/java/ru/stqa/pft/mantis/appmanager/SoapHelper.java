package ru.stqa.pft.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;
import ru.stqa.pft.mantis.model.Status;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SoapHelper {


    private final ApplicationManager app;

    public SoapHelper(ApplicationManager app) {
        this.app = app;
    }


    public Set<Project> getProjeсts() throws RemoteException, MalformedURLException, ServiceException {
        MantisConnectPortType mc = getMantisConnect();
        ProjectData[] projects = mc.mc_projects_get_user_accessible(app.getProperty("web.login"), app.getProperty("web.password"));
        return  Arrays.asList(projects).stream().map((p) -> new Project().withId(p.getId().intValue()).withName(p.getName()))
                .collect(Collectors.toSet());
    }

    public MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
        return new MantisConnectLocator()
                    .getMantisConnectPort(new URL(app.getProperty("mantisApi")));
    }

    public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
       String[] categories =  mc.mc_project_get_categories(app.getProperty("web.login"), app.getProperty("web.password"), BigInteger.valueOf(issue.getProject().getId()));
        IssueData issueData = new IssueData();
        issueData.setSummary(issue.getSummary());
        issueData.setDescription(issue.getDescription());
        issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()),issue.getProject().getName()));
        issueData.setCategory(categories[0]);
        BigInteger issueId = mc.mc_issue_add(app.getProperty("web.login"), app.getProperty("web.password"), issueData);
        IssueData newIssueData = mc.mc_issue_get(app.getProperty("web.login"), app.getProperty("web.password"), issueId);
        return new Issue().withId(newIssueData.getId().intValue()).withSummary(newIssueData.getSummary())
                .withDescription(newIssueData.getDescription())
                .withProject(new Project().withId(newIssueData.getProject().getId().intValue())
                        .withName(newIssueData.getProject().getName()));
    }


    public Issue getIssueData(BigInteger issueId) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        IssueData newIssueData = mc.mc_issue_get(app.getProperty("web.login"), app.getProperty("web.password"), issueId);
        return new Issue().withId(newIssueData.getId().intValue()).withSummary(newIssueData.getSummary())
                .withDescription(newIssueData.getDescription())
                .withProject(new Project().withId(newIssueData.getProject().getId().intValue())
                        .withName(newIssueData.getProject().getName()))
                .withStatus(new Status().withId(newIssueData.getStatus().getId().intValue())
                        .withName(newIssueData.getStatus().getName()));
    }
}

