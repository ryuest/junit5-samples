package com.whgtf.sportsbook.pom.utils;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Component
@Lazy
public class TemplateReader {


    private BufferedReader input;
    private String content ;

    public TemplateReader(final String filePath) {
        this.content = "";
        InputStream in = getClass().getResourceAsStream(filePath);
        input = new BufferedReader(new InputStreamReader(in));
        String thisLine = null;
        try {
            while ((thisLine = input.readLine()) != null){
                this.content = this.content.concat(thisLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(content);
    }

    public void updateTemplate(final String attribute, final String newValue) {
        String pattern = "\\$\\{".concat(attribute).concat("}");
        content = content.replaceAll(pattern,newValue);
    }

    public String getTemplateAsString() {
        return content;
    }

}
