import cfg.ContextFreeGrammar;
import cfg.Productions;
import dataStructures.Token;
import junit.framework.TestCase;

import java.util.ArrayList;

public class TestCFG extends TestCase {
    Productions prod;
    ContextFreeGrammar CFG;

    protected void setUp(){
        CFG = new ContextFreeGrammar();
        String definitionOfProduction = "abB|c";
        ArrayList<Integer> definition = new ArrayList<>();

        for (char c: definitionOfProduction.toCharArray()) {
            definition.add((int) c);
        }

        prod = new Productions( 'A', definition );

        CFG.addProduction(prod);

        definitionOfProduction = "d";
        definition = new ArrayList<>();
        for (char c: definitionOfProduction.toCharArray()) {
            definition.add((int) c);
        }
        prod = new Productions((int) 'B', definition);
        CFG.addProduction(prod);
    }

    public void testProduction(){
        String definitionOfProduction = "ab|c";
        ArrayList<Integer> definition = new ArrayList<>();
        for (char c: definitionOfProduction.toCharArray()) {
            definition.add((int) c);
        }
        prod = new Productions((int) 'A', definition);
        assertEquals("A -> ab|c", prod.toString());
    }

    public void testContextFreeGrammarNonDeterminants(){
        CFG.findDeterminant();
        ArrayList<Integer> expected = new ArrayList<Integer>(){
            {
                add((int) 'a');
                add((int) 'b');
                add((int) 'c');
                add((int) 'd');
            }
        };
        boolean test = true;
        for (int i: CFG.getDeterminant()) {
            if (! (expected.contains(i)))
                test = false;
        }

        assertTrue(test);
    }

    public void testContextFreeGrammarDeterminants(){
        assertEquals(new ArrayList<Integer>(){
                            {
                                add((int) 'A');
                                add((int) 'B');
                            }},
                CFG.getNonDeterminant());
    }

    public void testLL1(){
        fail();
    }
}
