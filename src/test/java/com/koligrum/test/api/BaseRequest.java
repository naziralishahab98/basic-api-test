package com.koligrum.test.api;

import io.restassured.specification.RequestSpecification;
import org.junit.runner.Request;

import static io.restassured.RestAssured.given;

public class BaseRequest {
    final static String url = "http://localhost:1234";

    public RequestSpecification baseRequest(){
        RequestSpecification request = given().baseUri(url)
                .basePath("/v1")
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .log().all();
        return request;
    }
}
