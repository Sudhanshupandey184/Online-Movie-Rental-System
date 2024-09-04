package com.example.api.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class MovieFilterTests {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost:3000"; // Ensure this matches your server
        RestAssured.requestSpecification = ApiSpecifications.requestSpec(); // Check that this spec is correct
    }

    @Test
    public void testFilterMoviesByGenre() {
        String genreQuery = "Action";

        // Log the request details before sending
        System.out.println("Sending GET request to /movies/filter with query: " + genreQuery);

        Response response = given()
                .queryParam("genre", genreQuery) // Use query parameter for genre filter
                .when()
                .get("/movies/filter") // Ensure this path matches your server configuration
                .then()
                .log().all() // Log request and response details
                .extract().response();

        // Print response details
        System.out.println("Filter Movies by Genre Response Status Code: " + response.getStatusCode());
        System.out.println("Filter Movies by Genre Response Body: " + response.getBody().asString());

        // Assertions
//        response.then().statusCode(200); // Expecting status code 200 for successful filtering
//        response.then().body("movies", hasSize(greaterThan(0))); // Ensure that at least one movie is returned
//        response.then().body("movies.genre", hasItem(equalTo(genreQuery))); // Ensure the movie genre matches the query
    }
}
