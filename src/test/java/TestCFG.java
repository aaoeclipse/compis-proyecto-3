import automata.DFA;
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

    protected void setUp(){
        CFG = new ContextFreeGrammar<Integer>();
        String definitionOfProduction = "abB|c";
        ArrayList<Integer> definition = new ArrayList<>();

        for (char c: definitionOfProduction.toCharArray()) {
            definition.add((int) c);
        }

        prod = new Productions<Integer>( (int)'A', definition );

        CFG.addProduction(prod);

        definitionOfProduction = "d";
        definition = new ArrayList<>();
        for (char c: definitionOfProduction.toCharArray()) {
            definition.add((int) c);
        }
        prod = new Productions((int) 'B', definition);
        CFG.addProduction(prod);

        // CREATING DFAS for test
        // letter, contains all alphabet
        dfaAlpha = new DFA();
        int counter = 1;
        State s1 = new State(""+counter,true, false);
        counter++;
        State s2 = new State(""+counter,false, false);
        ArrayList<TransitionState<Integer>> transitionTable = new ArrayList<>();
        for (int i:
                DefaultValues.alphabet) {

        }
         s1 = new State("1",true, false);
        int transition = (int) 'a';
        s2 = new State("2",false, false);
        transitionTable.add(new TransitionState<Integer>(s1, transition, s2));
        transition = (int) 'b';
        transitionTable.add(new TransitionState<Integer>(s1, transition, s2));
        s1 = new State("3",false,true);
        transition = (int) 'c';
        transitionTable.add(new TransitionState<Integer>(s2, transition, s2));
        transition = (int) 'd';
        transitionTable.add(new TransitionState<Integer>(s2, transition, s1));

//        dfa.setTransitionTable(transitionTable);
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
//        CFG.findDeterminant();
//        ArrayList<Integer> expected = new ArrayList<Integer>(){
//            {
//                add((int) 'a');
//                add((int) 'b');
//                add((int) 'c');
//                add((int) 'd');
//            }
//        };
//        boolean test = true;
//        for (int i: CFG.getDeterminant()) {
//            if (! (expected.contains(i)))
//                test = false;
//        }

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

    public void testDFAwithProduction(){
        String test = "santi = number Ltter | Ltter." +
                       "Ltter = letter.";
        String result = "santi -> number Ltter | Ltter\n"+
                "Lettr -> letter\n";

        this.CFG = new ContextFreeGrammar<Token<DFA>>();
        fail();
    }

    public void testLeftRecursionRemoval(){
        // Testing A -> Ax|b
        String definitionOfProduction = "Ax|b";
        String nonDeterminant = "A";
        // Result should be A -> bA'
        //                 A' -> xA'| epsilon
        fail();
    }
}
