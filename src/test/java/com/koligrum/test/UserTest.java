package com.koligrum.test;

import static io.restassured.RestAssured.given;

import com.koligrum.test.api.CreateUserEndpoint;
import com.koligrum.test.models.request.create_users.CreateUserRequest;
import com.koligrum.test.models.response.create_users.CreateUserResponse;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserTest {
  final static String url = "http://localhost:1234";
  private CreateUserEndpoint createUserEndpoint = new CreateUserEndpoint();


  @Test
  @Tag("Status200")
  @Order(1)
  public void getUsers(){

    Response response = given().baseUri(url)
        .basePath("/v1")
        .header("accept", "application/json")
        .header("content-type", "application/json")
        .queryParam("name", "Clarissa")
        .log().all()
        .when().get("/users");

    response.getBody().prettyPrint();

    int statusCode = response.getStatusCode();
    Assert.assertEquals(200, statusCode);

    String name = response.path("data[0].firstName");
    Assert.assertEquals("Clarissa", name);
  }

  @Test
  @Tag("Status200")
  @Order(3)
  public void updateUsers(){

    //ganti data id dari response post terlebih dahulu
    String requestBody = "{\n"
        + "  \"id\": \"201e96ba-d197-4d33-b74b-9f545f1e4cfc\" ,\n"
        + "  \"firstName\": \"Clarissa\",\n"
        + "  \"lastName\": \"Martallin\",\n"
        + "  \"age\": 25,\n"
        + "  \"occupation\": \"Athlete\",\n"
        + "  \"nationality\": \"Indonesia\",\n"
        + "  \"hobbies\": [\n"
        + "    \"Zumba\"\n"
        + "  ],\n"
        + "  \"gender\": \"FEMALE\"\n"
        + "}";

    Response response = given().baseUri(url)
        .basePath("/v1")
        .header("accept", "application/json")
        .header("content-type", "application/json")
        .body(requestBody)
        .log().all()
        .when().put("/users");

    response.getBody().prettyPrint();

    int statusCode = response.getStatusCode();
    Assert.assertEquals(200, statusCode);

    String occupation = response.path("occupation");
    Assert.assertEquals("Athlete", occupation);
  }

  @Test
  @Tag("Status200")
  @Order(2)
  public void getUserById(){

    Response response = given().baseUri(url)
        .basePath("/v1")
        .header("accept", "application/json")
        .header("content-type", "application/json")
        .pathParam("id", "201e96ba-d197-4d33-b74b-9f545f1e4cfc")
        .log().all()
        .when().get("/users/{id}");

    int statusCode = response.getStatusCode();
    Assert.assertEquals(200, statusCode);

    response.getBody().prettyPrint();

  }

  @Test
  @Tag("Status200")
  @Order(4)
  public void deleteUserById(){

    Response response = given().baseUri(url)
        .basePath("/v1")
        .header("accept", "application/json")
        .header("content-type", "application/json")
        .pathParam("id", "201e96ba-d197-4d33-b74b-9f545f1e4cfc")
        .log().all()
        .when().delete("/users/{id}");

    int statusCode = response.getStatusCode();
    Assert.assertEquals(200, statusCode);

    response.getBody().prettyPrint();

  }



}
