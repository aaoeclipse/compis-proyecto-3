import controler.LexicalAnalyzer;
import controler.SyntaxAnalyzer;
import junit.framework.TestCase;

public class TestSyntaxAnalyzer extends TestCase {

    public void testReadCharSyntax(){
        LexicalAnalyzer lex = new LexicalAnalyzer();
        lex.addCocolDoubleAritmetica();

        SyntaxAnalyzer syn = new SyntaxAnalyzer(lex);

        syn.readChar("69");
    }


    public void testSyntaxAnalyzer(){
        LexicalAnalyzer lex = new LexicalAnalyzer();
        lex.addCocolDoubleAritmetica();

        SyntaxAnalyzer syn = new SyntaxAnalyzer(lex);

    }
}
