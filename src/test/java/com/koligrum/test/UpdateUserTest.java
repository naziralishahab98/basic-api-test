package com.koligrum.test;

import com.koligrum.test.api.GetUserEndpoint;
import com.koligrum.test.api.UpdateUserEndpoint;
import com.koligrum.test.models.request.update_users.UpdateUserRequest;
import com.koligrum.test.models.response.update_users.UpdateUserResponse;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.*;

import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;


public class UpdateUserTest {
    UpdateUserEndpoint updateUserEndpoint = new UpdateUserEndpoint();
    GetUserEndpoint getUserByNameEndpoint = new GetUserEndpoint();

    @Test
    @Tag("Statuscode200")
    public void updateUsers(){

        Response responseGetUser = getUserByNameEndpoint.getUser200("Nazir");
        String id = responseGetUser.path("data[0].id");

        UpdateUserRequest reqbody = new UpdateUserRequest();
        reqbody.setId(id);
        reqbody.setFirstName("Nazir");
        reqbody.setLastName("Ali Shahab");
        reqbody.setAge(24);
        reqbody.setOccupation("QA Engineer");
        reqbody.setNationality("INDONESIA");
        reqbody.setGender("MALE");
        reqbody.setHobbies(Arrays.asList("Futsal","Beatbox"));


        Response response = updateUserEndpoint.UpdateUser(reqbody);

        UpdateUserResponse responseUpdateUser = response.as(UpdateUserResponse.class);
        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);
        assertEquals("Nazir",responseUpdateUser.getFirstName());
        assertEquals("Ali Shahab",responseUpdateUser.getLastName());
        assertEquals(Integer.valueOf(24),responseUpdateUser.getAge());
        assertEquals("QA Engineer",responseUpdateUser.getOccupation());
        assertEquals("INDONESIA",responseUpdateUser.getNationality());
        assertEquals("MALE",responseUpdateUser.getGender());
        assertEquals(reqbody.getHobbies(),responseUpdateUser.getHobbies());

        MatcherAssert.assertThat(response.getBody().asString(),
                JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/update-user-schema.json"));

    }

}
