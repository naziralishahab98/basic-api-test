package com.koligrum.test.api;

import io.restassured.response.Response;

public class DeleteUserEndpoint extends BaseRequest {

    public Response deleteUser(String id){

        Response response = baseRequest()
                .pathParam("id", id)
                .log().all()
                .when().delete("/users/{id}");

        return  response;
    }
}
