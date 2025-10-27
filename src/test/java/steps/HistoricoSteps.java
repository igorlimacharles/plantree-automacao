package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import model.HistoricoModel;
import services.HistoricoService;
import steps.context.TestContext;

public class HistoricoSteps {

    private final HistoricoService historicoService = new HistoricoService();
    private final TestContext testContext;

    public HistoricoSteps(TestContext context) {
        this.testContext = context;
    }

    @Dado("um token de autenticação válido")
    public void umTokenDeAutenticacaoValido() {
        // Lógica para obter um token válido, pode ser de um step anterior ou um valor fixo
        // Ex: this.token = "seu-token-jwt";
        // Por enquanto, vamos assumir que ele será preenchido.
    }

    @Dado("que eu tenha um ID de usuário válido")
    public void queEuTenhaUmIdDeUsuarioValido() {
        testContext.userId = "id-de-usuario-existente"; // Substituir por um ID real
    }

    @Quando("eu enviar uma requisição GET para {string}")
    public void euEnviarUmaRequisicaoGETPara(String endpoint) {
        testContext.response = historicoService.getHistoryById(testContext.token, testContext.userId);
    }

    @Dado("que eu tenha um ID de usuário inválido")
    public void queEuTenhaUmIdDeUsuarioInvalido() {
        testContext.userId = " "; // ID inválido conforme lógica do controller
    }

    @Dado("que eu tenha os dados para um novo registro de histórico")
    public void queEuTenhaOsDadosParaUmNovoRegistroDeHistorico() {
        testContext.historicoModel = new HistoricoModel(-23.5505, -46.6333, 15.0, 10, "ipe-amarelo-01", "user-123");
    }

    @Quando("eu enviar uma requisição POST para {string} com os dados")
    public void euEnviarUmaRequisicaoPOSTParaComOsDados(String endpoint) {
        testContext.response = historicoService.createHistory(testContext.token, testContext.historicoModel);
    }

    @Dado("que eu tenha dados incompletos para um novo registro de histórico")
    public void queEuTenhaDadosIncompletosParaUmNovoRegistroDeHistorico() {
        testContext.historicoModel = new HistoricoModel(-23.5505, -46.6333, 15.0, 10, "ipe-amarelo-01", "");
    }

    @Dado("que eu tenha um ID de histórico válido para exclusão")
    public void queEuTenhaUmIdDeHistoricoValidoParaExclusao() {
        // Idealmente, este ID seria criado e obtido em um passo anterior
        testContext.historyId = 1L; // Substituir por um ID real que possa ser deletado
    }

    @Quando("eu enviar uma requisição DELETE para {string}")
    public void euEnviarUmaRequisicaoDELETEPara(String endpoint) {
        testContext.response = historicoService.deleteHistory(testContext.token, testContext.historyId);
    }
    
    @Dado("que eu tenha um ID de histórico inválido para exclusão")
    public void queEuTenhaUmIdDeHistoricoInvalidoParaExclusao() {
        testContext.historyId = 0L; // ID inválido conforme lógica do controller
    }
}