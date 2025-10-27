package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import model.TreeModel;
import services.TreeService;
import steps.context.TestContext;

import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

public class TreeSteps {

    private final TreeService treeService;
    private TreeModel.TreeModelBuilder treeBuilder;
    private final TestContext testContext;

    public TreeSteps(TestContext context) {
        this.testContext = context;
        this.treeService = new TreeService();
    }

    @Dado("que o usuário informa os seguintes dados válidos para uma nova árvore:")
    public void queOUsuarioInformaOsSeguintesDadosValidosParaUmaNovaArvore(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        treeBuilder = TreeModel.builder()
                .nomePopular(data.get("nomePopular"))
                .nomeCientifico(data.get("nomeCientifico"))
                .descricao(data.get("descricao"))
                .formulaCarbono(data.get("formulaCarbono"))
                .tipo(data.get("tipo"));
    }

    @Dado("que o usuário informa dados incompletos para uma nova árvore:")
    public void queOUsuarioInformaDadosIncompletosParaUmaNovaArvore(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        treeBuilder = TreeModel.builder()
                .nomePopular(data.get("nomePopular"))
                .nomeCientifico(data.get("nomeCientifico"))
                .descricao(data.get("descricao"))
                .formulaCarbono(data.get("formulaCarbono"))
                .tipo(data.get("tipo"));
    }

    @Quando("o usuário envia uma requisição POST para {string} com esses dados")
    public void oUsuarioEnviaUmaRequisicaoPOSTParaComEssesDados(String endpoint) {
        testContext.response = treeService.createTree(treeBuilder.build());
    }

    @Então("a resposta deve conter o campo {string} com valor {string}")
    public void aRespostaDeveConterOCampoComValor(String field, String value) {
        testContext.response.then().body(field, equalTo(value));
    }

    @Dado("que existe uma árvore cadastrada no sistema com o ID {int}")
    public void queExisteUmaArvoreCadastradaNoSistemaComOID(int id) {
        // Opcional: Adicionar lógica para garantir que a árvore exista
    }

    @Quando("o usuário envia uma requisição GET para {string}")
    public void oUsuarioEnviaUmaRequisicaoGETPara(String endpoint) {
        int id = Integer.parseInt(endpoint.split("/")[3]);
        testContext.response = treeService.searchTreeById(id);
    }

    @Dado("que não existe uma árvore cadastrada no sistema com o ID {int}")
    public void queNaoExisteUmaArvoreCadastradaNoSistemaComOID(int id) {
        // Opcional: Adicionar lógica para garantir que a árvore NÃO exista
    }

    @Dado("que o usuário informa um ID inválido, como {int}")
    public void queOUsuarioInformaUmIDInvalidoComo(int id) {
        // Não há ação necessária aqui, o ID será usado no próximo passo
    }

    @Quando("o usuário envia uma requisição DELETE para {string}")
    public void oUsuarioEnviaUmaRequisicaoDELETEPara(String endpoint) {
        int id = Integer.parseInt(endpoint.split("/")[3]);
        testContext.response = treeService.deleteTreeById(id);
    }
}
