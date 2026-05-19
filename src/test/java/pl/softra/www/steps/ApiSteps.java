package pl.softra.www.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiSteps {

    private String endpoint;
    private Response response;
    // Base URL można brać z Twojego ConfigReadera!
    private String baseUrl = "https://jsonplaceholder.typicode.com";

    @Given("Ustawiam endpoint {string}")
    public void ustawiamEndpoint(String path) {
        this.endpoint = baseUrl + path;
    }

    @When("Wysyłam zapytanie GET")
    public void wysylamZapytanieGET() {
        // Magia Rest Assured: given().get()...
        response = RestAssured.given()
                .when()
                .get(endpoint);
    }

    @Then("Otrzymuję kod statusu {int}")
    public void otrzymujeKodStatusu(int expectedCode) {
        // Sprawdzamy czy serwer odpowiedział 200 OK
        assertEquals(expectedCode, response.getStatusCode());
    }

    @Then("W odpowiedzi znajduje się pole {string} o wartości {string}")
    public void sprawdzamPole(String field, String expectedValue) {
        // Wyciągamy wartość z JSON-a
        String actualValue = response.jsonPath().getString(field);
        assertEquals(expectedValue, actualValue);
    }
}
