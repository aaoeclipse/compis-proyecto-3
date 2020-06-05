package controler;

import automata.DFA;
import automata.DFA2;
import dataStructures.SyntaxTree;
import dataStructures.Token;
import reader.DefaultValues;
import reader.ReaderCustom;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class LexicalAnalyzer {
    private ReaderCustom reader;
    private SyntaxTree stree;
    private ArrayList<Token<DFA>> dfas;

    private ArrayList<Token<DFA<Integer>>> CHARACTERS;
    private ArrayList<Token<DFA<Integer>>> KEYWORDS;
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

    public void addKeyword(String name, String input){
        DFA<Integer> dfa = new DFA<Integer>();
        stree = new SyntaxTree(input);
        stree.followpos();
        dfa.constructDFA2(stree);
        this.KEYWORDS.add(new Token<DFA<Integer>>(name, dfa, true));
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
        addingCharacters();
        addingKeywords();
        addingTokens();
    }

    private void addingKeywords() {
        addKeyword("do", "do");
        addKeyword("while", "while");
    }

    private void addingTokens() {
        /* number = digit{digit}.
        decnumber = digit{digit}"."digit{digit}.
        white = blanco{blanco}.*/
        DFA2 number = new DFA2(getCHARACTER("digit"));
        number.dfa2Add(getCHARACTER("digit"), 2);

        Token<DFA2> t = new Token("number", number);
        this.TOKENS.add(t);

        DFA2 dec = new DFA2(getCHARACTER("digit"));

        dec.dfa2Add(getCHARACTER("digit"), 0);
        dec.dfa2Add(new Token<DFA<Integer>>(".", new DFA(".")), 2);
        dec.dfa2Add(getCHARACTER("digit"), 0);

        t = new Token("decnumber", number);
        this.TOKENS.add(t);

    }

    private Token<DFA<Integer>> getCHARACTER(String name) {
        for (Token<DFA<Integer>> t: this.CHARACTERS) {
            if (t.getName().equals(name))
                return t;
        }
        return null;
    }

    private void addingCharacters() {
        DFA<Integer> dfa = new DFA<Integer>();
        ArrayList<Integer> eol = new ArrayList<>();

        // digits = "0123456789"
        dfa.constructDFASimple(DefaultValues.digits, true);
        CHARACTERS.add(new Token<DFA<Integer>>( "digit",  dfa));

        dfa = new DFA<Integer>();
        eol.add(9);
        dfa.constructDFASimple(eol, true);
        CHARACTERS.add(new Token<DFA<Integer>>( "tab",  dfa));

        dfa = new DFA<Integer>();
        eol = new ArrayList<>();
        eol.add(10);
        dfa.constructDFASimple(eol, true);
        CHARACTERS.add(new Token<DFA<Integer>>( "eol",  dfa));

        dfa = new DFA<Integer>();
        int blanco =10+13+9;
        eol = new ArrayList<>();
        eol.add(blanco);
        dfa.constructDFASimple(eol, false);
        CHARACTERS.add(new Token<DFA<Integer>>( "blanco",  dfa));
    }

    public ArrayList<String> simulating(char c){
        ArrayList<String> toReturn = new ArrayList<>();
        ArrayList<String> tokenReturn = new ArrayList<>();
        ArrayList<Token<DFA<Integer>>> characters = new ArrayList<>();

        for (Token<DFA<Integer>> t:this.CHARACTERS) {
            if (t.getValue().simulate((int) c)) {
                characters.add(t);
                toReturn.add(t.getName());
            }
        }

        for (Token<DFA<Integer>> t:this.KEYWORDS) {
            if (t.getValue().simulate((int) c)) {
                toReturn.add(t.getName());
            }
        }

        for (Token<DFA<Integer>> t: characters) {
            tokenReturn = simulateToken(t);
        }

        if (toReturn.isEmpty())
            toReturn.add("<UNKNOWN Token>");

        return toReturn;
    }


    public Token<DFA2> simulatingToken(char c){
        ArrayList<Token<DFA<Integer>>> toReturn = new ArrayList<>();
        Token<DFA2> tokenReturn = null;

        ArrayList<Token<DFA<Integer>>> characters = new ArrayList<>();

        for (Token<DFA<Integer>> t:this.CHARACTERS) {
            if (t.getValue().simulate((int) c)) {
                characters.add(t);
                toReturn.add(t);
            }
        }

        for (Token<DFA<Integer>> t:this.KEYWORDS) {
            if (t.getValue().simulate((int) c)) {
                toReturn.add(t);
            }
        }

        for (Token<DFA<Integer>> t: characters) {
            tokenReturn = simulateTokenSmall(t);
        }

        if (toReturn.isEmpty())
            return null;

        return tokenReturn;
    }

    private ArrayList<String> simulateToken(Token<DFA<Integer>> nextToken) {
        for (Token<DFA2> t:this.TOKENS) {
//            System.out.println("t = " + t);
        }
        return null;
    }

    private Token<DFA2> simulateTokenSmall(Token<DFA<Integer>> nextToken) {
        for (Token<DFA2> t:this.TOKENS) {
                if (t.getValue().simulate(nextToken.getValue()))
                    return t;
        }
        return null;
    }


}
