package com.whgtf.sportsbook.main.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({@PropertySource("classpath:/${env:pp1}.environment.properties"),@PropertySource("classpath:/properties/${env:pp1}.pom.environment.properties")})
@ComponentScan(basePackages = {"com.whgtf.sportsbook.main.spring",
        "com.whgtf.sportsbook.pom.common.components.impl","com.whgtf.sportsbook.pom.common.pages.impl","com.whgtf.sportsbook.pom.utils","stepsConfig"})
public class Config {
}
