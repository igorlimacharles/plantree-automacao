package steps.context;

import io.restassured.response.Response;
import lombok.Data;
import model.HistoricoModel;

@Data
public class TestContext {
    public Response response;
    public String token;
    public String userId;
    public HistoricoModel historicoModel;
    public long historyId;
}