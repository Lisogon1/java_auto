package ru.stqa.pft.mantis.model;

import org.hamcrest.core.Is;

public class Issue {
    private int id;
    private String summary;
    private String description;
    private Status status;
    private Project project;




    public int getId() {
        return id;
    }

    public Issue withId(int id) {
        this.id = id;
        return this;
    }

    public String getSummary() {
        return summary;
    }

    public Issue  withSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Issue  withDescription(String description) {
        this.description = description;
        return this;
    }

    public Project getProject() {
        return project;
    }

    public Issue withProject(Project project) {
        this.project = project;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public Issue withStatus (Status status) {
        this.status = status;
        return this;
    }
}
