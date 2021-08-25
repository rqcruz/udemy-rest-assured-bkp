package br.com.rcruz.rest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserJsonTest {

    @Test
    public void deveVerificarPrimeiroNivel() {
        given()
                .when().get("https://restapi.wcaquino.me/users/1")
                .then()
                .statusCode(200)
                .body("id", is(1))
                .body("name", containsString("Silva"))
                .body("age", greaterThan(18));
    }

    @Test
    public void deveVerificarPrimeiroNivelOutrasFormas() {
        Response response = RestAssured.request(Method.GET, "https://restapi.wcaquino.me/users/1");

        //path
        Assertions.assertEquals(Integer.valueOf(1), response.path("id"));
        Assertions.assertEquals(Integer.valueOf(1), response.path("%s", "id"));

        //Json Path
        JsonPath jpath = new JsonPath(response.asString());
        Assertions.assertEquals(1, jpath.getInt("id"));

        //from by Jsonpath
        int id = JsonPath.from(response.asString()).getInt("id");
        Assertions.assertEquals(1, id);
    }

    @Test
    public void deveVerificarSegundoNivel() {
        given()
                .when().get("https://restapi.wcaquino.me/users/2")
                .then()
                .statusCode(200)
                .body("name", containsString("Joaquina"))
                .body("endereco.rua", is("Rua dos bobos"));
    }

    @Test
    public void deveVerificarLista() {
        given()
                .when().get("https://restapi.wcaquino.me/users/3")
                .then()
                .statusCode(200)
                .body("name", containsString("Ana"))
                .body("filhos", hasSize(2))
                .body("filhos[0].name", is("Zezinho"))
                .body("filhos[1].name", is("Luizinho"))
                .body("filhos.name", hasItem("Zezinho"))
                .body("filhos.name", hasItems("Zezinho", "Luizinho"))
        ;
    }
}
