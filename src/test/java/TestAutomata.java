import automata.DFA;
import automata.DFA2;
import automata.State;
import automata.TransitionState;
import dataStructures.SyntaxTree;
import dataStructures.Token;
import junit.framework.TestCase;
import reader.DefaultValues;
import reader.ReaderCustom;

import java.util.ArrayList;

public class TestAutomata extends TestCase {
    DFA dfa;
    ReaderCustom reader;

    public void testTransitionTable(){
        // (a|b)c*d
        dfa = new DFA<Integer>();
        ArrayList<TransitionState<Integer>> transitionTable = new ArrayList<>();
        State s1 = new State("1",true, false);
        int transition = (int) 'a';
        State s2 = new State("2",false, false);
        transitionTable.add(new TransitionState<Integer>(s1, transition, s2));
        transition = (int) 'b';
        transitionTable.add(new TransitionState<Integer>(s1, transition, s2));
        s1 = new State("3",false,true);
        transition = (int) 'c';
        transitionTable.add(new TransitionState<Integer>(s2, transition, s2));
        transition = (int) 'd';
        transitionTable.add(new TransitionState<Integer>(s2, transition, s1));

        dfa.setTransitionTable(transitionTable);

        boolean result = dfa.fullSimulate("bcccd");
        assertTrue(result);
        dfa.restarState();
        result = dfa.fullSimulate("abc");
        assertFalse(result);
        dfa.restarState();
        result = dfa.fullSimulate("cdad");
        assertTrue(result);

    }

    public void testSimpleDFA(){
        dfa = new DFA<Integer>();
        SyntaxTree tree = new SyntaxTree("a");
        tree.followpos();
        dfa.constructDFA2(tree);
        assertTrue(dfa.fullSimulate("a"));
    }

    public void testDFA(){
        dfa = new DFA<Integer>();
        String testString = "ba(a"+(char)DefaultValues.OR+"b)"+(char)DefaultValues.STAR+"ab";
        SyntaxTree tree = new SyntaxTree(testString);
        tree.followpos();
        dfa.constructDFA2(tree);
        assertTrue(dfa.fullSimulate("baab"));
        dfa.restarState();
        assertTrue(dfa.fullSimulate("babbaaab"));
        dfa.restarState();
        assertFalse(dfa.fullSimulate("b"));
        dfa.restarState();
        assertFalse(dfa.fullSimulate("babbaaa"));
        dfa.restarState();
        assertFalse(dfa.fullSimulate("baaba"));

    }

    public void testConcatSimple(){
        dfa = new DFA();
        SyntaxTree tree = new SyntaxTree("ab");
        tree.followpos();
        dfa.constructDFA2(tree);

        assertTrue(dfa.fullSimulate("ab"));
        assertFalse(dfa.fullSimulate("b"));
        assertFalse(dfa.fullSimulate("abc"));
    }

    public void testConcatConcat(){
        dfa = new DFA();
        String testCONCAT = "abca"+(char)DefaultValues.STAR;
        SyntaxTree tree = new SyntaxTree(testCONCAT);
        tree.followpos();
        dfa.constructDFA2(tree);

        assertFalse(dfa.fullSimulate("ab"));
        dfa.restarState();

        assertTrue(dfa.fullSimulate("abc"));
        dfa.restarState();
        assertTrue(dfa.fullSimulate("abcaaaaa"));
    }

    public void testORSimple(){
        dfa = new DFA();
        String testOR = "a"+(char)DefaultValues.OR+"b";
        SyntaxTree tree = new SyntaxTree(testOR);
        tree.followpos();
        dfa.constructDFA2(tree);

        assertTrue(dfa.fullSimulate("a"));
        assertFalse(dfa.fullSimulate("abc"));

    }

    public void testStarSimple(){
        dfa = new DFA();
        String testOR = "a"+(char)DefaultValues.STAR;
        SyntaxTree tree = new SyntaxTree(testOR);
        tree.followpos();
        dfa.constructDFA2(tree);

        assertTrue(dfa.fullSimulate("a"));
        assertTrue(dfa.fullSimulate("aaaaaaa"));
        assertFalse(dfa.fullSimulate("abc"));

    }

    public void testDFA2Simple(){
        DFA<Integer> dfa = new DFA<Integer>("ab");
        Token<DFA<Integer>> abcd = new Token<>("a", dfa);
        DFA2 dfa2 = new DFA2(abcd);
        dfa2.dfa2Add(abcd, 0);
        assertFalse(dfa2.simulate('a'));
        assertFalse(dfa2.simulate('b'));
        assertFalse(dfa2.simulate('a'));
        assertTrue(dfa2.simulate('b'));
    }

    public void testDFA2SimpleStar(){
        DFA<Integer> dfa = new DFA<Integer>("b");
        Token<DFA<Integer>> abcd = new Token<>("b", dfa);

        DFA2 dfa2 = new DFA2(abcd);

        dfa = new DFA<Integer>("a");
        abcd = new Token<>("a", dfa);

        dfa2.dfa2Add(abcd, 2);

        assertTrue(dfa2.simulate('b'));
        assertTrue(dfa2.simulate('a'));
        assertFalse(dfa2.simulate('c'));
        assertTrue(dfa2.simulate('b'));
        assertTrue(dfa2.simulate('a'));
        assertTrue(dfa2.simulate('a'));
        assertTrue(dfa2.simulate('b'));
        assertFalse(dfa2.simulate('c'));
    }
}
