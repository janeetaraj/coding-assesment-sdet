package com.janeetaraj.rest;

import com.janeetaraj.utils.constants;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.fail;

public class RestUtlity {
    private CloseableHttpClient httpClient;
    public static HttpResponse httpResponse;

    public void doGetRequest(String apiKey, boolean isKeyPass) {
        try {
            //Construct the elections query url and add api key as parameter
            URIBuilder builder = new URIBuilder(constants.ELECTION_URL);
            if (isKeyPass) {
                builder.setParameter("key", apiKey);
            }

            //Create a http get request
            HttpGet getRequest = new HttpGet(builder.build());
            //Send http get request
            sendHttpGetRequest(getRequest);

        } catch (URISyntaxException ex) {
            System.err.println(ex);
            fail("Invalid Google Civic API URL");
        }
    }

    // A simple method to send the http get request
    public void sendHttpGetRequest(HttpGet getRequest) {
        try {
            //Create a http client
            httpClient = HttpClients.createDefault();

            getRequest.addHeader(HttpHeaders.ACCEPT, "application/json");
            httpResponse = httpClient.execute(getRequest);

        } catch (ClientProtocolException ex) {
            System.err.println(ex);
            fail("Error sending http get request");
        } catch (IOException ex) {
            System.err.println(ex);
            fail("Error sending http get request");
        }

    }
}
