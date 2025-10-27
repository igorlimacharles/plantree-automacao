package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        // 1. Onde estão seus arquivos de feature
        features = "src/test/resources/features",

        // 2. Onde estão seus arquivos de steps (a "cola")
        glue = "steps",

        // 3. Formato dos relatórios de teste
        plugin = {
                "pretty", // Deixa o output do console mais legível
                "html:target/cucumber-reports/cucumber-report.html", // Gera um relatório HTML
                "json:target/cucumber-reports/cucumber-report.json"  // Gera um relatório JSON
        },

        // 4. Permite executar apenas cenários com tags específicas (deixe em branco para rodar tudo)
        tags = ""
)
public class CucumberTestRunner {
}