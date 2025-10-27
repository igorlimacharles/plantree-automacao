package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import services.AuthService;
import utils.SchemaValidator;
import steps.context.TestContext;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.notNullValue;

public class AuthSteps {
    private final AuthService authService;
    private final TestContext testContext;

    public AuthSteps(TestContext context) {
        this.testContext = context;
        this.authService = new AuthService();
    }

    @Dado("que a API de contas está operacional")
    public void queAApiDeContasEstaOperacional() {
        // Opcional: Adicionar uma verificação de status da API
    }

    @Quando("eu envio uma requisição POST para {string} com o corpo:")
    public void euEnvioUmaRequisicaoPOSTParaComOCorpo(String endpoint, String body) {
        if (body.contains("email_dinamico")) {
            String dynamicEmail = "teste" + System.currentTimeMillis() + "@exemplo.com";
            body = body.replace("email_dinamico", dynamicEmail);
        }
        testContext.response = authService.post(endpoint, body);
    }

    @Então("eu devo receber um status code {int}")
    public void euDevoReceberUmStatusCode(int statusCode) {
        testContext.response.then().statusCode(statusCode);
    }

    @Então("o corpo da resposta deve conter a mensagem {string}")
    public void oCorpoDaRespostaDeveConterAMensagem(String message) {
        testContext.response.then().body(containsString(message));
    }

    @Então("o corpo da resposta deve conter um {string}")
    public void oCorpoDaRespostaDeveConterUm(String key) {
        testContext.response.then().body(key, notNullValue());
    }

    @Então("o contrato da resposta deve ser válido para {string}")
    public void oContratoDaRespostaDeveSerValidoPara(String schemaName) {
        if (schemaName != null && !schemaName.isEmpty()) {
            SchemaValidator.validateSchema(testContext.response, schemaName);
        }
    }

    @Dado("que um usuário existe com o username {string} e senha {string}")
    public void queUmUsuarioExisteComOUsernameESenha(String username, String password) {
        // Opcional: Adicionar lógica para garantir que o usuário exista
    }

    @Dado("que um usuário com o ID {int} não existe")
    public void queUmUsuarioComOIDNaoExiste(int id) {
        // Opcional: Adicionar lógica para garantir que o usuário exista
    }
}
