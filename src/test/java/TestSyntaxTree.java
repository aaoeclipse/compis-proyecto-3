import automata.DFA;
import dataStructures.SyntaxTree;
import dataStructures.Token;
import junit.framework.TestCase;
import reader.DefaultValues;

public class TestSyntaxTree extends TestCase {
    protected SyntaxTree stree;

    protected void setUp() {
        stree = new SyntaxTree();
    }


    public void testSyntaxTreeTokens(){
        DFA one = new DFA("hi");
        Token<DFA> t1 = new Token<>();
        DFA two = new DFA("hi");
        Token<DFA> t2 = new Token<>();
        DFA three = new DFA("hi");
        Token<DFA> t3 = new Token<>();
    }

    public void testConcat() {
        String test = stree.withConcat("ab");
        assertEquals("a" + (char) DefaultValues.CONCAT + "b"+(char)DefaultValues.EOF, test);
        test = stree.withConcat("abc");
        assertEquals("a" + (char) DefaultValues.CONCAT + "b" + (char) DefaultValues.CONCAT + "c"+(char)DefaultValues.EOF, test);

        test = stree.withConcat("ab(c)");
        assertEquals("a" + (char) DefaultValues.CONCAT + "b" + (char) DefaultValues.CONCAT + "(c)"+(char)DefaultValues.EOF, test);

        test = stree.withConcat("ab(cc)(d)");
        assertEquals("a" + (char) DefaultValues.CONCAT + "b" + (char) DefaultValues.CONCAT + "(c" + (char) DefaultValues.CONCAT + "c)" + (char) DefaultValues.CONCAT + "(d)"+(char)DefaultValues.EOF, test);

        test = stree.withConcat("a|b(c|c)(d)");
        assertEquals("a|b" + (char) DefaultValues.CONCAT + "(c|c)" + (char) DefaultValues.CONCAT + "(d)"+(char)DefaultValues.EOF, test);

        test = stree.withConcat("a|b|(c|c)(d)");
        assertEquals("a|b|(c|c)" + (char) DefaultValues.CONCAT + "(d)"+(char)DefaultValues.EOF, test);

        test = stree.withConcat("a|b|(c"+(char)DefaultValues.STAR+")"+(char)DefaultValues.STAR+"(d)");
        assertEquals("a|b|(c"+(char)DefaultValues.STAR+")"+(char)DefaultValues.STAR+ (char) DefaultValues.CONCAT+"(d)"+(char)DefaultValues.EOF, test);
    }

    public void testPostfix(){
        String test = "a|b&(e|c)*";
    }
}
