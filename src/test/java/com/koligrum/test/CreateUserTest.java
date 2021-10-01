package com.koligrum.test;

import com.koligrum.test.api.CreateUserEndpoint;
import com.koligrum.test.models.request.create_users.CreateUserRequest;
import com.koligrum.test.models.response.create_users.CreateUserResponse;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.runner.OrderWith;

import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class CreateUserTest {
    CreateUserEndpoint createUserEndpoint = new CreateUserEndpoint();

    @Test
    @Tag("Statuscode200")
    public void createUsers(){

        //serialization json menggunakan pojo
        CreateUserRequest reqBody = new CreateUserRequest();
        reqBody.setFirstName("Nazir");
        reqBody.setLastName("Ali");
        reqBody.setAge(24);
        reqBody.setOccupation("QA Engineer");
        reqBody.setNationality("INDONESIA");
        reqBody.setHobbies(Arrays.asList("Futsal","Beatbox"));
        reqBody.setGender("MALE");

        Response response = createUserEndpoint.createUser200(reqBody);

        //Deserialization response menggunakan RoboPOJO
        CreateUserResponse responseCreateUsers = response.as(CreateUserResponse.class);
        String name = responseCreateUsers.getFirstName();
        String lastname = responseCreateUsers.getLastName();
        assertEquals("Nazir",name);
        assertEquals("Ali",lastname);
        assertEquals(reqBody.getHobbies(),responseCreateUsers.getHobbies());

        MatcherAssert.assertThat(response.getBody().asString(),
                JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/create-user-schema.json"));

        //assert status code
        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

    }

}
