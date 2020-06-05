import automata.DFA;
import automata.DFA2;
import automata.State;
import automata.TransitionState;
import cfg.ContextFreeGrammar;
import cfg.Productions;
import dataStructures.Token;
import junit.framework.TestCase;
import reader.DefaultValues;

import java.util.ArrayList;

public class TestCFG extends TestCase {
    Productions prod;
    ContextFreeGrammar CFG;
    DFA dfaAlpha;
    DFA dfaNum;

    public void testSimplePrudction(){
        CFG = new ContextFreeGrammar();
        prod = new Productions<Token<DFA2>>();

        ArrayList<Token<DFA2>> Numbers = new ArrayList<Token<DFA2>>();
        DFA<Integer> digit = new DFA();

        digit.constructDFASimple(DefaultValues.digits, true);

        Numbers.add(new Token("digit", digit));
        Token<DFA2> firstProduction = new Token("Number", Numbers);



//        prod = new Productions(firstProduction, );
//        TODO aqui me quede
        System.out.println("prod = " + prod);
    }

    public void testProduction(){
        String definitionOfProduction = "ab|c";
        ArrayList<Integer> definition = new ArrayList<>();
        for (char c: definitionOfProduction.toCharArray()) {
            definition.add((int) c);
        }
        prod = new Productions((int) 'A', definition);
        System.out.println("Expected: A -> ab|c  || Actual: "+ prod.toString());
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
        boolean test = false;
        for (Object i: CFG.getDeterminant()) {
            if (!expected.contains(i)) {
                test = false;
                break;
            }
        }

        fail();
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

    public void testProdcutionWithTokens(){
        String testProduction = "Expr = Stat \";\" | number.\n";
        CFG = new ContextFreeGrammar();

    }
}
