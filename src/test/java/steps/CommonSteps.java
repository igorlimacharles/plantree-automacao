package steps;

import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import steps.context.TestContext;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CommonSteps {

    private final TestContext testContext;

    public CommonSteps(TestContext context) {
        this.testContext = context;
    }

    @Então("o status da resposta deve ser {int}")
    public void oStatusDaRespostaDeveSer(int statusCode) {
        testContext.response.then().statusCode(statusCode);
    }

    @E("a resposta deve conter a mensagem {string}")
    public void aRespostaDeveConterAMensagem(String message) {
        testContext.response.then().body("message", equalTo(message));
    }

    @E("o corpo da resposta não deve ser nulo")
    public void oCorpoDaRespostaNaoDeveSerNulo() {
        testContext.response.then().body(notNullValue());
    }

    @E("o corpo da resposta deve corresponder ao schema {string}")
    public void oCorpoDaRespostaDeveCorresponderAoSchema(String schemaPath) {
        testContext.response.then().body(matchesJsonSchemaInClasspath("schemas/" + schemaPath));
    }
}