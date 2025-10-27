package services;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class AuthService {

    String baseUrl = "http://localhost:8080";

    public Response post(String endpoint, String body) {
        String url = baseUrl + endpoint;
        return given()
                .log().all() // Adiciona o log de toda a requisição
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(body)
                .when()
                .post(url)
                .then()
                .extract()
                .response();
    }
}
