package br.com.rcruz.rest;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OlaMundoTest {

    @Test
    public void testOlaMundo() {
        Response response = request(Method.GET, "https://restapi.wcaquino.me/ola");
        Assertions.assertTrue(response.getBody().asString().equals("Ola Mundo!"));
        Assertions.assertTrue(response.getStatusCode() == 200);
        Assertions.assertTrue(response.getStatusCode() == 200, "O status code deveria ser 201");
        Assertions.assertEquals(200, response.getStatusCode());

        ValidatableResponse validate = response.then();
        validate.statusCode(200);
    }

    @Test
    public void devoConhecerOutrasFormasDeTrabalharComRestAssured() {

        // Executa os mesmos passos do teste testOlaMundo, mas de maneira encadeada e menos verbosa
        get("https://restapi.wcaquino.me/ola").then().statusCode(200);
    }

    @Test
    public void devoConhecerOutrasFormasDeTrabalharComRestAssuredComGivenWhenThen() {

        // Executa os mesmos passos do teste testOlaMundo, mas de maneira encadeada, menos verbosa e usando Given When Then
        given()
                .when().get("https://restapi.wcaquino.me/ola")
                .then().statusCode(200);
    }
}
