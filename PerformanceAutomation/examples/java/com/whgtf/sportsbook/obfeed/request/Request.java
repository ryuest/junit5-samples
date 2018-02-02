package com.whgtf.sportsbook.obfeed.request;

/**
 * Created by Juri on 21/10/2016.
 */

import com.whgtf.sportsbook.common.PropArgs;
import com.whgtf.sportsbook.model.ResponseEvent;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Request {

    org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Request.class.getName());

    public ResponseEvent sendRequest(String request, String env) throws IOException, ParserConfigurationException, SAXException {

        PropArgs props = new PropArgs(env);

    Parser parser = new Parser();
    CloseableHttpClient httpclient = HttpClients.createDefault();
    HttpPost postRequest = new HttpPost(props.getOBfeedUrl());
    StringEntity myEntity = new StringEntity(request, ContentType.create("text/xml", "UTF-8"));
    postRequest.setEntity(myEntity);

    //	System.out.println(myEntity);

    CloseableHttpResponse response = httpclient.execute(postRequest);
    HttpEntity responseEntity = response.getEntity();
    String responseBody =  EntityUtils.toString(responseEntity);

        logger.debug(responseBody);

        BufferedWriter bufferedWriterRequest = Files.newBufferedWriter(Paths.get("EventData" + "/" + env + "/LastRequest.txt"),
                StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        BufferedWriter bufferedWriterResponse = Files.newBufferedWriter(Paths.get("EventData" + "/" + env + "/LastResponse.txt"),
                StandardOpenOption.CREATE, StandardOpenOption.APPEND);

    try {
        bufferedWriterRequest.write(request);
        bufferedWriterResponse.write(responseBody);
        Thread.sleep(100);
        bufferedWriterRequest.close();
        bufferedWriterResponse.close();
    } catch (IOException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
    } catch (InterruptedException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
    }


    return parser.getOpenbetID(responseBody, env);
}
}
