package services;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.TreeModel;

import static io.restassured.RestAssured.given;

public class TreeService {

    private final String BASE_URL = "http://localhost:8080/arvore";
    private final Gson gson = new Gson();

    public Response createTree(TreeModel tree) {
        String endpoint = BASE_URL + "/create";
        String body = gson.toJson(tree);

        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(body)
                .when()
                .post(endpoint)
                .then()
                .extract().response();
    }

    public Response searchTreeById(int id) {
        String endpoint = BASE_URL + "/search/" + id;

        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get(endpoint)
                .then()
                .extract().response();
    }

    public Response deleteTreeById(int id) {
        String endpoint = BASE_URL + "/delete/" + id;

        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .delete(endpoint)
                .then()
                .extract().response();
    }
}
