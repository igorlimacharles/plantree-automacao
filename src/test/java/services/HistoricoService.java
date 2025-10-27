package services;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.HistoricoModel;

import static io.restassured.RestAssured.given;

public class HistoricoService {

    public Response getHistoryById(String token, String userId) {
        return given()
                .log().all()
                .header("Authorization", "Bearer " + token)
                .pathParam("id", userId)
                .when()
                .get("/history/{id}")
                .then().log().all()
                .extract().response();
    }

    public Response createHistory(String token, HistoricoModel historico) {
        return given()
                .log().all()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(historico)
                .when()
                .post("/history/add")
                .then().log().all()
                .extract().response();
    }

    public Response deleteHistory(String token, long historyId) {
        return given()
                .log().all()
                .header("Authorization", "Bearer " + token)
                .pathParam("id", historyId)
                .when()
                .delete("/history/delete/{id}")
                .then().log().all()
                .extract().response();
    }
}