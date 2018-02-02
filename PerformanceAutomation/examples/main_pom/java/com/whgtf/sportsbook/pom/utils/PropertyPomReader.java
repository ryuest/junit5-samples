package com.whgtf.sportsbook.pom.utils;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Component
@Lazy
public class PropertyPomReader {



    private Properties prop;

    InputStream inputStream;

    public PropertyPomReader() {
        prop = new Properties();
        inputStream = getClass().getClassLoader().getResourceAsStream("properties/".concat(System.getProperty("env")).concat(".pom.environment.properties"));
        try {
            prop.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String get(String propertyKey) {
        return prop.getProperty(propertyKey);
    }

    public String getOxifeedUrl() {
        return prop.getProperty("oxifeedUrl");
    }

    public String getOxifeedUser() {
        return prop.getProperty("openbetUsername");
    }

    public String getOxifeedPass() {
        return prop.getProperty("openbetPassword");
    }

    public String getPdsRestEndPoint()  {
        return prop.getProperty("pdsRestEndpoint");
    }

}
