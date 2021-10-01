package com.koligrum.test;

import com.koligrum.test.api.GetUserEndpoint;
import com.koligrum.test.models.response.get_user_by_name.GetUserByNameResponse;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;


public class GetUserByNameTest {
    GetUserEndpoint getUserByNameEndpoint = new GetUserEndpoint();

    @Test
    @Tag("Statuscode200")
    public void getUsers(){

        Response response = getUserByNameEndpoint.getUser200("Nazir");

        GetUserByNameResponse getUserByNameResponse = response.as(GetUserByNameResponse.class);

        assertEquals("Nazir",getUserByNameResponse.getData().get(0).getFirstName());
        assertEquals("Ali",getUserByNameResponse.getData().get(0).getLastName());
        assertEquals(Integer.valueOf(24),getUserByNameResponse.getData().get(0).getAge());
        assertEquals("QA Engineer",getUserByNameResponse.getData().get(0).getOccupation());
        assertEquals("INDONESIA",getUserByNameResponse.getData().get(0).getNationality());

        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

        MatcherAssert.assertThat(response.getBody().asString(),
                JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get-user-by-name-schema.json"));

    }
}
