package dataStructures;

import automata.DFA;
import reader.DefaultValues;
import reader.ReaderCustom;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class LexicalAnalyzer {
    private ReaderCustom reader;
    private SyntaxTree stree;
    private ArrayList<Token<DFA>> dfas;

    public LexicalAnalyzer(String input){
        dfas = new ArrayList<>();
        stree = new SyntaxTree();
        stree.withConcat(input);
        stree.constructTree();

        this.reader = new ReaderCustom(input);
    }

    public LexicalAnalyzer() {
        dfas = new ArrayList<>();
        stree = new SyntaxTree();
    }

    public void addDFA(String name, String input){
        DFA dfa = new DFA<Integer>();
        stree = new SyntaxTree(input);
        stree.followpos();
        dfa.constructDFA2(stree);
        this.dfas.add(new Token<DFA>(name, dfa));
    }

    public ArrayList<Token<DFA>> simulate(char c){
        ArrayList<Token<DFA>> toReturn = new ArrayList<>();
        for (Token<DFA> t:this.dfas) {
            if (t.getValue().simulate(c))
                toReturn.add(t);
        }
        return toReturn;
    }

    public void addSimpleDfa(String name, ArrayList<Integer> test, boolean or){
        DFA dfa = new DFA<Integer>();
        dfa.constructDFASimple(test, or);
        this.dfas.add(new Token<DFA>(name, dfa));
    }



}
