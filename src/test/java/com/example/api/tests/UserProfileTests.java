package com.example.api.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UserProfileTests {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost:3000"; // Ensure this matches your server
        RestAssured.requestSpecification = ApiSpecifications.requestSpec(); // Ensure the spec is correct
    }

    @Test
    public void testUpdateUserProfile() {
        String requestBody = "{ \"username\": \"updatedUser\", \"email\": \"updateduser@example.com\" }";

        // Log the request details before sending
        System.out.println("Sending PUT request to /userProfiles/1");

        Response response = given()
                .header("Authorization", "Bearer your-auth-token") // Ensure the token is correct
                .body(requestBody)
                .when()
                .put("/userProfiles/1") // Corrected path based on the router configuration
                .then()
                .log().all() // Log request and response details
                .extract().response();

        // Print response details
        System.out.println("Update User Profile Response Status Code: " + response.getStatusCode());
        System.out.println("Update User Profile Response Body: " + response.getBody().asString());

        // Assertions
        response.then().statusCode(200); // Ensure this matches the expected status code
        response.then().body("message", equalTo("User profile updated successfully")); // Adjust based on actual API response
    }

    @Test
    public void testDeleteUserAccount() {

        // Log the request details before sending
        System.out.println("Sending DELETE request to /userProfiles/1");

        Response response = given()
                .header("Authorization", "Bearer your-auth-token") // Ensure the token is correct
                .when()
                .delete("/userProfiles/1") // Corrected path based on the router configuration
                .then()
                .log().all() // Log request and response details
                .extract().response();

        // Print response details
        System.out.println("Delete User Account Response Status Code: " + response.getStatusCode());
        System.out.println("Delete User Account Response Body: " + response.getBody().asString());

        // Assertions
        response.then().statusCode(200); // Adjust based on actual expected status code
        response.then().body("message", equalTo("User account deleted successfully")); // Adjust based on actual API response
    }
}
