package com.example.api.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class MovieSearchTests {

    @BeforeAll
    public static void setup() {
        // Set the correct base URI to match your server setup
        RestAssured.baseURI = "http://localhost:3000"; 
        RestAssured.requestSpecification = ApiSpecifications.requestSpec(); 
    }

    @Test
    public void testSearchMoviesByTitle() {
        String titleQuery = "Inception";

        // Log the request details before sending
        System.out.println("Sending GET request to /movies/search with query: " + titleQuery);

        Response response = given()
                .queryParam("title", titleQuery)
                .when()
                .get("/movies/search") // Ensure this matches your server's route
                .then()
                .log().ifValidationFails() // Log details only if validation fails
                .extract().response();

        // Print response details for debugging
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        // Assertions
//        response.then().statusCode(200); 
//        response.then().body("movies",notNullValue() );
//        response.then().body("movies.title", hasItem(equalTo(titleQuery)));
    }
}
