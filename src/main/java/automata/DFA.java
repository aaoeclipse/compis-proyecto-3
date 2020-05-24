package automata;

import dataStructures.NodeSyntax;
import dataStructures.SyntaxTree;
import reader.DefaultValues;
import reader.ReaderCustom;

import java.util.ArrayList;
import java.util.Stack;

public class DFA {
    private ArrayList<TransitionState> transitionTable;
    private String constructor;

    public DFA(){}

    public DFA(String constructor){ this.constructor = constructor; }

    public void constructDFA(SyntaxTree tree){
        // for each operation in tree
        Stack<NodeSyntax> results = new Stack<>();

//        NodeSyntax<Integer> currOperation = tree.nextOperation();
//        int currentValue = currOperation.getValue();
//
//        while (currentValue != DefaultValues.EOF){
//            if (DefaultValues.opertaions.getLeftOperation(currentValue)){
//                // is left operation, meaning it only depends on the left character
//                // check if
//            }
//            currOperation = tree.nextOperation();
//        }

    }

    public boolean simulate(String ab) {
        return false;
    }


}
