package com.example.api.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RentalApiTests {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost:3000"; // Ensure your server is running at this address
        RestAssured.requestSpecification = ApiSpecifications.requestSpec();
    }

    @Test
    public void testRentMovie() {
        // Example request body with required fields
        String requestBody = "{ \"userId\": 1, \"movieId\": 1, \"rentalDate\": \"2024-09-04\" }";

        // Send the request and log the response to troubleshoot any issues
        Response response = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/rentals")
                .then()
                .log().all() // Log request and response details for debugging
                .extract().response();

        // Print the status code and body to verify the correct response
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        // Adjust assertion to match the actual response message
        response.then().statusCode(201)
                .body("message", equalTo("Rental created successfully")); // Update this line with the correct message
    }
}
