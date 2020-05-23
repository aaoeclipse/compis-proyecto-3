import org.junit.runner.RunWith;
import org.junit.runners.Suite;

//JUnit Suite Test
@RunWith(Suite.class)

@Suite.SuiteClasses({
        TestReader.class ,TestAutomata.class
})
public class TestLexicalAnalyser {
}
