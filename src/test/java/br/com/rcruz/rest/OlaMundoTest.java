package br.com.rcruz.rest;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OlaMundoTest {

    @Test
    public void testOlaMundo() {
        Response response = RestAssured.request(Method.GET, "https://restapi.wcaquino.me/ola");
        Assertions.assertTrue(response.getBody().asString().equals("Ola Mundo!"));
        Assertions.assertTrue(response.getStatusCode() == 200);
        Assertions.assertTrue(response.getStatusCode() == 200, "O status code deveria ser 201");
        Assertions.assertEquals(201, response.getStatusCode());

        ValidatableResponse validate = response.then();
        validate.statusCode(200);
    }
}
