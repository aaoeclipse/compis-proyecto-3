package controler;

import automata.DFA;
import dataStructures.SyntaxTree;
import reader.ReaderCustom;


/**
 * This class works by using the comunication between the two classes
 * DFA and Reader
 */
public class DFACrator {
    private ReaderCustom reader;
    private DFA dfa;
    private SyntaxTree stree;

    public DFACrator(String input){
        stree = new SyntaxTree();
        stree.withConcat(input);
        stree.constructTree();

        this.reader = new ReaderCustom(input);
        this.dfa = new DFA();
    }

    public DFA getDfa(){
        return this.dfa;
    }


}
