package ru.stqa.pft.rest.applicationmanager;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    private final Properties properties;
    private RestHelper rest;


    public ApplicationManager() {
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    }

    public String getProperty(String key) {
        return  properties.getProperty(key);
    }

    public RestHelper rest() {
        if (rest == null) {
            rest = new RestHelper(this);
        } return rest;
    }

}

