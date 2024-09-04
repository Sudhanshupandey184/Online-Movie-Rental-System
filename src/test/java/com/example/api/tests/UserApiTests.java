package com.example.api.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UserApiTests {

    @BeforeAll
    public static void setup() {
    	 RestAssured.baseURI = "http://localhost:3000/";
        RestAssured.requestSpecification = ApiSpecifications.requestSpec();
    }

    @Test
    public void testUserRegistration() {
        String requestBody = "{ \"username\": \"testuser\", \"password\": \"password123\", \"email\": \"testuser@example.com\" }";

        Response response = given()
                .body(requestBody)
                .when()
                .post("/users/register")
                .then()
                .spec(ApiSpecifications.responseSpec(201))
                .log().all()
                .extract().response();

        // Assertions
        response.then().body("message", equalTo("User created successfully"));
    }

    @Test
    public void testUserLogin() {
        String requestBody = "{ \"username\": \"testuser\", \"password\": \"password123\" }";

        Response response = given()
                .body(requestBody)
                .when()
                .post("/users/login")
                .then()
                .spec(ApiSpecifications.responseSpec(200))
                .extract().response();

        // Corrected Assertion: Check that the token is not null and not empty
        response.then().body("token", org.hamcrest.Matchers.notNullValue());
    }

    @Test
    public void testGetAllMovies() {
        given()
                .when()
                .get("/movies")
                .then()
                .spec(ApiSpecifications.responseSpec(200))
                .log().all()
                .assertThat()
                .body("[0].title", equalTo("Inception"));
    }
}
