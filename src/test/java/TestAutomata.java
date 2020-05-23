import automata.DFA;
import automata.State;
import automata.TransitionState;
import controler.DFACrator;
import dataStructures.SyntaxTree;
import dataStructures.Token;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.Ignore;
import reader.ReaderCustom;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestAutomata extends TestCase {
    DFA dfa;
    ReaderCustom reader;

    public void testSimpleDFA(){
        dfa = new DFA();
        SyntaxTree tree = new SyntaxTree("a");
        assertTrue(dfa.simulate("a"));
    }

    public void testConcatSimple(){
        DFACrator creator = new DFACrator("ab");
        assertTrue(dfa.simulate("ab"));
        assertFalse(dfa.simulate("b"));
        assertFalse(dfa.simulate("abc"));
    }

    public void testConcatConcat(){
        reader = new ReaderCustom("abc");
    }

    public void testORSimple(){
        reader = new ReaderCustom("a|b");

    }

    public void testStarSimple(){
        reader = new ReaderCustom("a*");

    }

    public void testOneOrMore(){

    }


}
