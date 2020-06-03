package dataStructures;

import automata.DFA;
import automata.DFA2;
import reader.DefaultValues;
import reader.ReaderCustom;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class LexicalAnalyzer {
    private ReaderCustom reader;
    private SyntaxTree stree;
    private ArrayList<Token<DFA>> dfas;

    private ArrayList<Token<DFA>> CHARACTERS;
    private ArrayList<Token<DFA>> KEYWORDS;
    private ArrayList<Token<DFA2>> TOKENS;


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
        CHARACTERS = new ArrayList<>();
        KEYWORDS = new ArrayList<>();
        TOKENS = new ArrayList<>();
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

    public void addCocolDoubleAritmetica(){
        DFA dfa = new DFA<Integer>();
        ArrayList<Integer> eol = new ArrayList<>();
        dfa.constructDFASimple(DefaultValues.digits, true);
        CHARACTERS.add(new Token<DFA>( "digit",  dfa));

        dfa = new DFA<Integer>();
        eol.add(9);
        dfa.constructDFASimple(eol, true);
        CHARACTERS.add(new Token<DFA>( "tab",  dfa));

        dfa = new DFA<Integer>();
        eol = new ArrayList<>();
        eol.add(10);
        dfa.constructDFASimple(eol, true);
        CHARACTERS.add(new Token<DFA>( "eol",  dfa));

        dfa = new DFA<Integer>();
        int blanco =10+13+9;
        eol = new ArrayList<>();
        eol.add(blanco);
        dfa.constructDFASimple(eol, false);
        CHARACTERS.add(new Token<DFA>( "blanco",  dfa));
    }

    public ArrayList<String> simulating(char c){
        ArrayList<String> toReturn = new ArrayList<>();

        for (Token<DFA> t:this.CHARACTERS) {
            if (t.getValue().simulate((int) c))
                toReturn.add(t.getName());
        }

        for (Token<DFA> t:this.KEYWORDS) {
            if (t.getValue().simulate((int) c))
                toReturn.add(t.getName());
        }

        if (toReturn.isEmpty())
            toReturn.add("<UNKNOWN Token>");

        return toReturn;
    }



}
