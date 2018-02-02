package com.whgtf.sportsbook.pom.utils;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by javierg on 28/07/2016.
 */

@Component
@Lazy
public class JsonReader {


    public <T> T readJSON(String url, Class<T> valueType) throws IOException, JAXBException, UnirestException {


        InputStream json = new ByteArrayInputStream(
                        Unirest.get(url).asString().getBody().toString().getBytes());

        T result = null ;
        result = unmarshal(json, valueType);

        return result;

    }

    public JSONObject readJSON(String url) throws IOException{
                JSONObject  result = null;
        try {
            result = new JSONObject(Unirest.get(url).asString().getBody());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        System.out.println("");
        return result;
    }


    private <T> T unmarshal(final InputStream inputStream, final Class<T> myClass)
            throws IOException {
        final ObjectMapper result = new ObjectMapper();

        final AnnotationIntrospector jaxbIntrospector = new JaxbAnnotationIntrospector(result.getTypeFactory());

        result.setAnnotationIntrospector(jaxbIntrospector);

        return result.readValue(inputStream, myClass);
    }



}
