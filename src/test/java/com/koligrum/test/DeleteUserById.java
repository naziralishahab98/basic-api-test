package com.koligrum.test;

import com.koligrum.test.api.DeleteUserEndpoint;
import com.koligrum.test.api.GetUserEndpoint;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;


public class DeleteUserById {

    DeleteUserEndpoint deleteUserEndpoint = new DeleteUserEndpoint();
    GetUserEndpoint getUserEndpoint = new GetUserEndpoint();

    @Test
    @Tag("Statuscode200")
    public void deleteUserById() {
        Response getUserById = getUserEndpoint.getUser200("Nazir");
        String id = getUserById.path("data[0].id");

        Response response = deleteUserEndpoint.deleteUser(id);

        int statusCode = response.getStatusCode();
        Assert.assertEquals(200, statusCode);

    }
}