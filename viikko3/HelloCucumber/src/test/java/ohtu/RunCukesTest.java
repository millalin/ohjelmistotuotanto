package ohtu;

//import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import cucumber.api.SnippetType;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = "pretty",
        features = "src/test/resources/ohtu",
        snippets = SnippetType.CAMELCASE
)
public class RunCukesTest {}