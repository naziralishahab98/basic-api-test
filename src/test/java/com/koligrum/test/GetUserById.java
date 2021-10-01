package com.koligrum.test;

import com.koligrum.test.api.GetUserEndpoint;
import com.koligrum.test.models.response.get_user_by_id.GetUserByIdResponse;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;


public class GetUserById {
    GetUserEndpoint getUserEndpoint = new GetUserEndpoint();

    @Test
    @Tag("Statuscode200")
    public void getUsersById(){

        Response getUserByname = getUserEndpoint.getUser200("Nazir");
        String id = getUserByname.path("data[0].id");
        Response response = getUserEndpoint.getUserById(id);

        int statusCode = response.getStatusCode();
        Assert.assertEquals(200, statusCode);

        GetUserByIdResponse getUserByIdResponse = response.as(GetUserByIdResponse.class);
        assertEquals("Nazir",getUserByIdResponse.getFirstName());
        assertEquals("Ali",getUserByIdResponse.getLastName());
        assertEquals(Integer.valueOf(24),getUserByIdResponse.getAge());
        assertEquals("QA Engineer",getUserByIdResponse.getOccupation());
        assertEquals("INDONESIA",getUserByIdResponse.getNationality());


        MatcherAssert.assertThat(response.getBody().asString(),
                JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get-user-by-id-schema.json"));

    }
}
