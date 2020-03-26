package com.janeetaraj;

import com.janeetaraj.models.Elections;
import com.janeetaraj.models.Election;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URISyntaxException;


public class ElectionsQuerySteps {

    public static final String electionQueryUrl = "https://www.googleapis.com/civicinfo/v2/elections";

    private CloseableHttpClient httpClient;
    private String apiKey;
    private HttpResponse electionsQueryResponse;

    @Given("A valid API Key")
    public void setValidAPIKey() {
        // Not worrying about hard coding the api key for this coding assignment
        // Will invalidate the key later after the assignment is done
        apiKey = "AIzaSyBKmy0NZr9ub2DXbPpWyaorEnFRmuEVD3w";
    }

    @Given("An invalid API Key")
    public void setInvalidAPIKey() {
        apiKey = "invalidkey";
    }

    @When("I call Elections Query")
    public void callElectionsQuery() {

        try {

            //Construct the elections query url and add api key as parameter
            URIBuilder builder = new URIBuilder(electionQueryUrl);
            builder.setParameter("key", apiKey);

            //Create a http get request
            HttpGet getRequest = new HttpGet(builder.build());
            //Send http get request
            sendHttpGetRequest(getRequest);

        } catch (URISyntaxException ex) {
            System.err.println(ex);
            fail("Invalid Google Civic API URL");
        }
    }

    @When("I call Elections Query without api key")
    public void callElectionsQueryWithoutApiKey() {

        try {

            //Construct the elections query url and add api key as parameter
            URIBuilder builder = new URIBuilder(electionQueryUrl);
            //Not setting the api key parameter
            //Create a http get request
            HttpGet getRequest = new HttpGet(builder.build());
            //Send http get request
            sendHttpGetRequest(getRequest);

        } catch (URISyntaxException ex) {
            System.err.println(ex);
            fail("Invalid Google Civic API URL");
        }
    }

    @Then("I get a response status code {int}")
    public void validateElectionsResponseStatusCode(Integer expectedCode) {
        Integer statusCode = electionsQueryResponse.getStatusLine().getStatusCode();
        assertEquals(expectedCode, statusCode);
    }

    @And("I get a valid list of elections")
    public void validateElections() {

        try {

            String responseBody = EntityUtils.toString(electionsQueryResponse.getEntity());
            // Parse Json object to class
            Gson gson = new Gson();
            Elections elections = gson.fromJson(responseBody, Elections.class);

            assertEquals("civicinfo#electionsQueryResponse", elections.getKind());
            assertNotNull(elections.getElections());

            // Get one election object and validate its values
            Election election = elections.getElections()[1];
            assertNotNull(election.getId());
            assertNotNull(election.getName());
            assertNotNull(election.getElectionDay());
            assertNotNull(election.getOcdDivisionId());

        } catch (Exception ex) {
            System.err.println(ex);
            fail("Error parsing elections query response");
        }
    }

    // A simple method to send the http get request
    public void sendHttpGetRequest(HttpGet getRequest) {

        try {

            //Create a http client
            httpClient = HttpClients.createDefault();

            getRequest.addHeader(HttpHeaders.ACCEPT, "application/json");
            electionsQueryResponse = httpClient.execute(getRequest);

        } catch(ClientProtocolException ex) {
            System.err.println(ex);
            fail("Error sending http get request");
        } catch(IOException ex) {
            System.err.println(ex);
            fail("Error sending http get request");
        }

    }

}
