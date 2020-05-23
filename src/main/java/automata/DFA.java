package automata;

import dataStructures.SyntaxTree;
import reader.ReaderCustom;

import java.util.ArrayList;

public class DFA {
    private ArrayList<TransitionState> transitionTable;
    private String constructor;

    public DFA(){}

    public DFA(String constructor){ this.constructor = constructor; }

    public void constructDFA(SyntaxTree tree){

    }

    public boolean simulate(String ab) {
        return false;
    }


}
