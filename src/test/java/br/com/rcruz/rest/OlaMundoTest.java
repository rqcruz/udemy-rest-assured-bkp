package br.com.rcruz.rest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

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

    @Test
    public void devoConhecerMatchersHamcrest() {
        assertThat("Maria", is("Maria"));
        assertThat(128, is(128));
        assertThat(128, isA(Integer.class));
        assertThat(128d, isA(Double.class));
        assertThat(128d, greaterThan(125d));
        assertThat(128d, lessThan(130d));

        List<Integer> impares = Arrays.asList(1, 3, 5, 7, 9);
        assertThat(impares, hasSize(5));
        assertThat(impares, contains(1, 3, 5, 7, 9));
        assertThat(impares, containsInAnyOrder(9, 5, 3, 7, 1));
        assertThat(impares, hasItem(1));
        assertThat(impares, hasItems(1, 9));

        assertThat("Maria", is(not("Joao")));
        assertThat("Maria", not("Joao"));
        assertThat("Joaquina", anyOf(is("Maria"), is("Joaquina")));
        assertThat("Joaquina", allOf(startsWith("Joa"), containsString("qui"), endsWith("ina")));
    }

    @Test
    public void devoValidarBody() {
        given()
                .when().get("https://restapi.wcaquino.me/ola")
                .then().statusCode(200)
        .body(is("Ola Mundo!"))
        .body(containsString("Mundo"))
        .body(is(not(nullValue())));
    }
}
