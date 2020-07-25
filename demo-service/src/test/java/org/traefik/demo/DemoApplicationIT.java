package org.traefik.demo;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.restassured.RestAssured;

public class DemoApplicationIT {
    private String url = "http://traefik/demo";

    @Test
    public void helloV1() throws JsonProcessingException {
        RestAssured.get(url + "/v1/hello").then().statusCode(200);
    }

    @Test
    public void helloV2() throws JsonProcessingException {
        RestAssured.get(url + "/v2/hello").then().statusCode(200);
    }
}
