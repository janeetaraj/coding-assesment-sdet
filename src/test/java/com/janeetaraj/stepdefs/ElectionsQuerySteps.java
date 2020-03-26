package com.janeetaraj.stepdefs;

import com.janeetaraj.models.Elections;
import com.janeetaraj.models.Election;

import com.janeetaraj.rest.RestUtlity;
import com.janeetaraj.utils.constants;
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

    private String apiKey;
    RestUtlity restUtlity = new RestUtlity();
    boolean isKeyPass = true;

    @Given("API Key is {string}")
    public void setAPIKey(String apiKeyValue) {
        switch (apiKeyValue) {
            case "valid":
                apiKey = constants.API_KEY;
                break;

            case "invalid":
                apiKey = "invalidkey";
                break;

            case "null":
                apiKey = null;
                break;

            case "empty":
                apiKey = "";
                break;

            case "no":
                isKeyPass = false;
                break;

            default:
                apiKey = apiKeyValue;

        }
    }

    @Given("A valid API Key")
    public void setValidAPIKey() {
        apiKey = constants.API_KEY;
    }

    @Given("An invalid API Key")
    public void setInvalidAPIKey() {
        apiKey = "invalidkey";
    }

    @When("I call Elections Query")
    public void callElectionsQuery() {
        restUtlity.doGetRequest(apiKey, isKeyPass);
    }

    @When("I call Elections Query without api key")
    public void callElectionsQueryWithoutApiKey() {


    }

    @Then("I get a response status code {int}")
    public void validateElectionsResponseStatusCode(Integer expectedCode) {
        Integer statusCode = RestUtlity.httpResponse.getStatusLine().getStatusCode();
        assertEquals(expectedCode, statusCode);
    }

    @And("I get a valid list of elections")
    public void validateElections() {

        try {

            String responseBody = EntityUtils.toString(RestUtlity.httpResponse.getEntity());
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


}
