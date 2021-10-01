package com.koligrum.test.api;

import com.koligrum.test.models.request.create_users.CreateUserRequest;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CreateUserEndpoint extends BaseRequest{
    final static String url = "http://localhost:1234";

    public Response createUser200(CreateUserRequest requestBody){

        Response response = baseRequest()
                .body(requestBody)
                .log().all()
                .when().post("/users")
                .then().log().all().extract().response();
        return response;
    }


}
