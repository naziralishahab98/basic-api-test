package com.koligrum.test.api;

import com.koligrum.test.models.request.update_users.UpdateUserRequest;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UpdateUserEndpoint extends BaseRequest {


    public Response UpdateUser(UpdateUserRequest request){
        Response response = baseRequest()
                .body(request)
                .log().all()
                .when().put("/users");

        return response;
    }

}
