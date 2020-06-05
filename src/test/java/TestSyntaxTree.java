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
        assertEquals("a" + (char) DefaultValues.CONCAT + "b", test);
        System.out.println("test = " + test + " == a" + (char) DefaultValues.CONCAT + "b") ;

        test = stree.withConcat("abc");
        assertEquals("a" + (char) DefaultValues.CONCAT + "b" + (char) DefaultValues.CONCAT + "c", test);

        test = stree.withConcat("ab(c)");
        assertEquals("a" + (char) DefaultValues.CONCAT + "b" + (char) DefaultValues.CONCAT + "(c)", test);

        test = stree.withConcat("ab(cc)(d)");
        assertEquals("a" + (char) DefaultValues.CONCAT + "b" + (char) DefaultValues.CONCAT + "(c" + (char) DefaultValues.CONCAT + "c)" + (char) DefaultValues.CONCAT + "(d)", test);

        test = stree.withConcat("a|b(c|c)(d)");
        assertEquals("a|b" + (char) DefaultValues.CONCAT + "(c|c)" + (char) DefaultValues.CONCAT + "(d)", test);
        System.out.println("test = " + test + " == a|b" + (char) DefaultValues.CONCAT + "(c|c)" + (char) DefaultValues.CONCAT + "(d)") ;


        test = stree.withConcat("a|b|(c|c)(d)");
        assertEquals("a|b|(c|c)" + (char) DefaultValues.CONCAT + "(d)", test);

        test = stree.withConcat("a|b|(c"+(char)DefaultValues.STAR+")"+(char)DefaultValues.STAR+"(d)");
        assertEquals("a|b|(c"+(char)DefaultValues.STAR+")"+(char)DefaultValues.STAR+ (char) DefaultValues.CONCAT+"(d)", test);
    }

    public void testPostfix(){
        String test = "a"+ (char) DefaultValues.OR +"b(e"+(char)DefaultValues.OR+"c)"+(char)DefaultValues.STAR;
        stree = new SyntaxTree(test);
        System.out.println("test = " + stree.toString() + "== ab|ec|*."+(char)DefaultValues.EOF) ;
        assertEquals("ab|ec|*."+(char)DefaultValues.EOF, stree.toString());
    }

    public void testSyntaxTreeSimple(){
        String testString = "(a"+(char)DefaultValues.OR+"b)";
        stree = new SyntaxTree(testString);
        assertEquals("Node (2500) {[1, 2]}{[3]} -> (2501, -1)", stree.getTreeRoot());
        stree = new SyntaxTree("a"+(char)DefaultValues.STAR);
        assertEquals("Node (2500) {[1]}{[2]} -> (2502, -1)", stree.getTreeRoot());
        System.out.println("testString = " + testString);
        System.out.println("result = " + stree.getTreeRoot());
    }

    public void testBiggerTree(){
        String testString = "(a"+(char)DefaultValues.OR+"b)"+(char)DefaultValues.STAR;
        String result = "Node (97) {[1]}{[1]} -> ()\n" +
                "Node (98) {[2]}{[2]} -> ()\n" +
                "Node (2501) {[1, 2]}{[1, 2]} -> (97, 98)\n" +
                "Node (2502) {[1, 2]}{[1, 2]} -> (2501)\n"+
                "Node (-1) {[3]}{[3]} -> ()\n" +
                "Node (2500) {[1, 2]}{[3]} -> (2502, -1)\n";

        System.out.println("testString = " + testString);

        System.out.println("expected result = " + result);
        stree = new SyntaxTree(testString);
        System.out.println("actual result = " + stree.getFullTree());

        assertEquals(result, stree.getFullTree());

        testString = "a"+(char)DefaultValues.OR+"b(c"+(char)DefaultValues.OR + "d)"+(char)DefaultValues.STAR ;
        result = "Node (97) {[1]}{[1]} -> ()\n" +
                "Node (98) {[2]}{[2]} -> ()\n" +
                "Node (2501) {[1, 2]}{[1, 2]} -> (97, 98)\n" +
                "Node (99) {[3]}{[3]} -> ()\n" +
                "Node (100) {[4]}{[4]} -> ()\n" +
                "Node (2501) {[3, 4]}{[3, 4]} -> (99, 100)\n" +
                "Node (2502) {[3, 4]}{[3, 4]} -> (2501)\n" +
                "Node (2500) {[1, 2]}{[1, 2, 3, 4]} -> (2501, 2502)\n"+
                "Node (-1) {[5]}{[5]} -> ()\n" +
                "Node (2500) {[1, 2]}{[5]} -> (2500, -1)\n";
        stree = new SyntaxTree(testString);
        assertEquals(result, stree.getFullTree());

        testString = "(a"+(char)DefaultValues.OR+"b)"+(char)DefaultValues.STAR + "abb";
        result = "Node (97) {[1]}{[1]} -> ()\n" +
                "Node (98) {[2]}{[2]} -> ()\n" +
                "Node (2501) {[1, 2]}{[1, 2]} -> (97, 98)\n" +
                "Node (2502) {[1, 2]}{[1, 2]} -> (2501)\n" +
                "Node (97) {[3]}{[3]} -> ()\n" +
                "Node (2500) {[1, 2, 3]}{[3]} -> (2502, 97)\n" +
                "Node (98) {[4]}{[4]} -> ()\n" +
                "Node (2500) {[1, 2, 3]}{[4]} -> (2500, 98)\n" +
                "Node (98) {[5]}{[5]} -> ()\n" +
                "Node (2500) {[1, 2, 3]}{[5]} -> (2500, 98)\n" +
                "Node (-1) {[6]}{[6]} -> ()\n" +
                "Node (2500) {[1, 2, 3]}{[6]} -> (2500, -1)\n";
        stree = new SyntaxTree(testString);
        assertEquals(result, stree.getFullTree());

        testString = "ba(a"+(char)DefaultValues.OR+"b)"+(char)DefaultValues.STAR+"ab";
        result = "Node (98) {[1]}{[1]} -> ()\n" +
                "Node (97) {[2]}{[2]} -> ()\n" +
                "Node (2500) {[1]}{[2]} -> (98, 97)\n" +
                "Node (97) {[3]}{[3]} -> ()\n" +
                "Node (98) {[4]}{[4]} -> ()\n" +
                "Node (2501) {[3, 4]}{[3, 4]} -> (97, 98)\n" +
                "Node (2502) {[3, 4]}{[3, 4]} -> (2501)\n" +
                "Node (2500) {[1]}{[2, 3, 4]} -> (2500, 2502)\n"+
                "Node (97) {[5]}{[5]} -> ()\n" +
                "Node (2500) {[1]}{[5]} -> (2500, 97)\n"+
                "Node (98) {[6]}{[6]} -> ()\n" +
                "Node (2500) {[1]}{[6]} -> (2500, 98)\n"+
                "Node (-1) {[7]}{[7]} -> ()\n" +
                "Node (2500) {[1]}{[7]} -> (2500, -1)\n";
        stree = new SyntaxTree(testString);
        assertEquals(result, stree.getFullTree());
    }

    public void testFollowPos(){
        String testString = "ba(a"+(char)DefaultValues.OR+"b)"+(char)DefaultValues.STAR+"ab";
        String result =
                "Node | followpos\n"+
                "b.1 | [2]\n"+
                "a.2 | [3, 4, 5]\n"+
                "a.3 | [3, 4, 5]\n"+
                "b.4 | [3, 4, 5]\n"+
                "a.5 | [6]\n"+
                "b.6 | [7]\n"+
                "#.7 | []\n";
        stree = new SyntaxTree(testString);
        stree.followpos();

        assertEquals(result, stree.getToStringFollowPos());

        System.out.println("testString = " + testString);
        System.out.println("expected result = " + result);
        System.out.println("result = " + stree.getToStringFollowPos());
    }

    public void testFollowPosSimple(){
        String testString = "a";
        String result =
                "Node | followpos\n"+
                        "a.1 | [2]\n" +
                        "#.2 | []\n";
        stree = new SyntaxTree(testString);
        stree.followpos();
        assertEquals(result, stree.getToStringFollowPos());
    }

    public void testAlphabet(){
        String testString = DefaultValues.getalphabet();
        String result =
                "Node | followpos\n"+
                        "A.1 | [53]\n" +
                        "a.2 | [53]\n" +
                        "B.3 | [53]\n" +
                        "b.4 | [53]\n" +
                        "C.5 | [53]\n" +
                        "c.6 | [53]\n" +
                        "D.7 | [53]\n" +
                        "d.8 | [53]\n" +
                        "E.9 | [53]\n" +
                        "e.10 | [53]\n" +
                        "F.11 | [53]\n" +
                        "f.12 | [53]\n" +
                        "G.13 | [53]\n" +
                        "g.14 | [53]\n" +
                        "H.15 | [53]\n" +
                        "h.16 | [53]\n" +
                        "I.17 | [53]\n" +
                        "i.18 | [53]\n" +
                        "J.19 | [53]\n" +
                        "j.20 | [53]\n" +
                        "K.21 | [53]\n" +
                        "k.22 | [53]\n" +
                        "L.23 | [53]\n" +
                        "l.24 | [53]\n" +
                        "M.25 | [53]\n" +
                        "m.26 | [53]\n" +
                        "N.27 | [53]\n" +
                        "n.28 | [53]\n" +
                        "O.29 | [53]\n" +
                        "o.30 | [53]\n" +
                        "P.31 | [53]\n" +
                        "p.32 | [53]\n" +
                        "Q.33 | [53]\n" +
                        "q.34 | [53]\n" +
                        "R.35 | [53]\n" +
                        "r.36 | [53]\n" +
                        "S.37 | [53]\n" +
                        "s.38 | [53]\n" +
                        "T.39 | [53]\n" +
                        "t.40 | [53]\n" +
                        "U.41 | [53]\n" +
                        "u.42 | [53]\n" +
                        "V.43 | [53]\n" +
                        "v.44 | [53]\n" +
                        "W.45 | [53]\n" +
                        "w.46 | [53]\n" +
                        "X.47 | [53]\n" +
                        "x.48 | [53]\n" +
                        "Y.49 | [53]\n" +
                        "y.50 | [53]\n" +
                        "Z.51 | [53]\n" +
                        "z.52 | [53]\n" +
                        "#.53 | []\n";
        stree = new SyntaxTree(testString);
        stree.followpos();
        assertEquals(result, stree.getToStringFollowPos());
        System.out.println("stree.getToStringFollowPos() = " + stree.getToStringFollowPos());
    }

}
