package com.whgtf.sportsbook.main.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Lazy
public class PropertyReader {

    @Autowired
    @Lazy
    private Environment environment;


    public String get(String propertyKey) {
        return environment.getProperty(propertyKey);
    }

    public String getUsername() {
        return environment.getProperty("sportsbook.user");
    }

    public String getPassword() {
        return environment.getProperty("sportsbook.password");
    }

    public String getSquizHomepageTabs (){
        return  environment.getProperty("squiz.homepage.tabs");
    }

    public String getSquizHomepageCarouselIcons (){
        return  environment.getProperty("squiz.homepage.carousel.icons");
    }

    public List<String> ppNodesApi(){
        return Arrays.asList(environment.getProperty("sportsbook.ppNodesApi").split(","));
    }

    public List<String> ppNodesStaticApi(){
        return Arrays.asList(environment.getProperty("sportsbook.ppNodesStaticApi").split(","));
    }

}
