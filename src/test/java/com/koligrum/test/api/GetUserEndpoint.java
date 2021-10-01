package com.koligrum.test.api;

import io.restassured.response.Response;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.given;

public class GetUserEndpoint extends BaseRequest {
    final static String url = "http://localhost:1234";


    public Response getUser200(String value){
        Response response = baseRequest()
                .queryParam("name", value)
                .log().all()
                .when().get("/users");

        return response;
    }

    public Response getUserById(String id){
        Response response = baseRequest()
                .pathParam("id", id)
                .log().all()
                .when().get("/users/{id}");

        return response;
    }

}
