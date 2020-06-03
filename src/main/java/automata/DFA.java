package automata;

import dataStructures.NodeSyntax;
import dataStructures.SyntaxTree;
import reader.DefaultValues;
import reader.ReaderCustom;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;
import java.util.Stack;

public class DFA <T>{
    private ArrayList<TransitionState<T>> transitionTable;
    private State currentState;
    private String constructor;
    private ArrayList<String> visited;

    public DFA(){
        this.constructor = "";
        this.transitionTable = new ArrayList<>();
    }

    public DFA(String constructor){
        this.constructor = constructor;
        this.transitionTable = new ArrayList<>();
    }

    public void constructDFASimple(ArrayList<Integer> arInt, boolean OR) {
        State curr;
        State foll;

//        if (arInt.size() == 1){
//            curr = new State("0", true, false);
//            foll = new State("1", false, true);
//            this.transitionTable.add(new TransitionState(curr, arInt.get(0), foll));
//            return;
//        }
        if (OR) {
            curr = new State("0", true, false);
            foll = new State("1", false, true);
            for (int i = 0; i < arInt.size(); i++)
                this.transitionTable.add(new TransitionState(curr, arInt.get(i), foll));
        } else {
            for (int i = 0; i < arInt.size(); i++) {
                curr = new State("" + i, false, false);
                foll = new State("" + (i + 1), false, false);
                if (i == 0)
                    curr.setI(true);
                if ((i+1) == arInt.size() )
                    foll.setF(true);

                this.transitionTable.add(new TransitionState(curr, arInt.get(i), foll));
            }
        }
    }

    public void constructDFA2(SyntaxTree tree){
        visited = new ArrayList<>();
        Map<NodeSyntax<Integer>, ArrayList<Integer>> table = tree.followTable;
        boolean running = true;
        this.transitionTable = new ArrayList<>();
        State curr;
        State follow;

        Map.Entry<NodeSyntax<Integer>, ArrayList<Integer>> row = getRowByPosition(1,table);
        Map<Integer, ArrayList<Integer>> newRow;

        ArrayList<Integer> leftSide = new ArrayList<>();

        curr = new State("" + row.getKey().getPosition(), true, false);
        follow = new State("" + row.getValue(), false, false);
        addTransition(curr, row.getKey().getValue(), follow);

        visited.add(curr.getId());

        while (running) {
            curr = nextVisitor(visited);
            visited.add(curr.getId());

            // muchas variables
            if (curr.getId().length() > 3){ //[3,4,5]
                newRow =  new HashMap();

                // tiene mas que 1
                for (int i:getArrayFromCurr(curr)) {
                    row = getRowByPosition(i, table);
                    newRow = addToHash(newRow, row);
                }
                for (Map.Entry<Integer, ArrayList<Integer>> hashrow: newRow.entrySet()) {
                    if (hashrow.getKey().equals(DefaultValues.EOF)){
                        curr.setF(true);
                        running = false;
                    }else {
                        addTransition(curr, hashrow.getKey(), getState("" + hashrow.getValue()));
                    }
                }

            } else {
                // buscar en la tabla
                row = getRowByPosition(row.getValue().get(0), table);
                follow = new State("" + row.getValue(), false, false);
                if (row.getKey().getValue() == DefaultValues.EOF){
                    curr.setF(true);
                    running = false;
                }else {
                    addTransition(curr, row.getKey().getValue(), follow);
                }
            }
        }
    }

    private ArrayList<Integer> getArrayFromCurr(State curr) {
        String toParse = curr.getId();
        ArrayList<Integer> toReturn = new ArrayList();
        toParse = toParse.replace("[", "");
        toParse = toParse.replace("]", "");
        ArrayList<String> arl = new ArrayList<String>    (Arrays.asList(toParse.split(",")));

        for(String fav:arl){
            toReturn.add(Integer.parseInt(fav.trim()));
        }

        return toReturn;
    }

    private State nextVisitor(ArrayList<String> visited) {
        for (TransitionState s:this.transitionTable) {
            if (!visited.contains(s.getNextState().getId()))
                return s.getNextState();
        }
        return new State("-1", false, false);
    }

    private Map<Integer, ArrayList<Integer>> addToHash(
            Map<Integer, ArrayList<Integer>> newRow,
            Map.Entry<NodeSyntax<Integer>, ArrayList<Integer>> row) {


        if (!newRow.containsKey(row.getKey().getValue())) {
            newRow.put(row.getKey().getValue(), row.getValue());
        }
        else {
            for (int i : row.getValue()) {
                if (!newRow.get(row.getKey().getValue()).contains(i))
                    newRow.get(row.getKey().getValue()).add(i);
            }
        }
        return newRow;
    }

    private void addTransition(State s1, int t, State s2) {
        TransitionState<T> transi = new TransitionState(s1,t,s2);
        if (!this.transitionTable.contains(transi)){
            this.transitionTable.add(transi);
        }
    }

    private State findStateContains(int i) {
        for (TransitionState t: this.transitionTable) {
            for (char c: t.getFirstState().getId().toCharArray()) {
                if (i == (int) c){
                    return t.getFirstState();
                }
            }
        }
        return null;
    }

    private State getState(String s) {
        for (TransitionState<T> t:
                this.transitionTable) {
            if (t.getFirstState().getId().equals(s))
                return t.getFirstState();
            if (t.getNextState().getId().equals(s))
                return t.getNextState();
        }
        return new State(s, false, false);
    }

    private boolean checkState(String s) {

        for (TransitionState<T> t:
                this.transitionTable) {
            if (t.getFirstState().getId().equals(s))
                return true;
            if (t.getNextState().getId().equals(s))
                return true;

        }
        return false;
    }

    private Map.Entry<NodeSyntax<Integer>, ArrayList<Integer>> getRowByPosition(int id,
            Map<NodeSyntax<Integer>, ArrayList<Integer>> followTable){
        int counter = 1;
        while (counter <= followTable.size()) {
            for (Map.Entry<NodeSyntax<Integer>, ArrayList<Integer>> row : followTable.entrySet()) {
                if (id == row.getKey().getPosition().get(0)) {
                    return row;
                }
            }
            counter++;
        }
        return null;
    }

    public State searchState(String id){
        for (TransitionState<T> st: this.transitionTable) {
            if (st.getFirstState().getId().equals(id))
                return st.getFirstState();
            if (st.getNextState().getId().equals(id))
                return st.getNextState();
        }
        return null;
    }



    public boolean fullSimulate(String ab) {
        boolean lastState = false;
        for (char c: ab.toCharArray())
            lastState = simulate((int)c);
        return lastState;
    }

    public boolean simulate(int c){
        boolean check = false;
        boolean isFinal = false;
        boolean dead=false;
        if (currentState == null) {
            this.currentState = getInitialState();
            dead = true;
        }
        for (TransitionState<T> s: this.transitionTable) {
            if (s.getFirstState().equals(currentState))
                if (s.getTransition().equals(c)) {
                    check = true;
                    currentState = s.getNextState();
                    break;
                }
        }
        if (!check) {
            currentState = null;
            if (!dead) {
                return simulate(c);
            }
        }
        else{
            isFinal = (currentState.isF());
        }

        return isFinal;
    }

    private State getInitialState() {
        for (TransitionState<T> s: this.transitionTable) {
            if (s.getFirstState().isI())
                return s.getFirstState();
        }
        return null;
    }

    public void setTransitionTable(ArrayList<TransitionState<T>> transitionTable) {
        this.transitionTable = transitionTable;
    }

    public void restarState(){
        this.currentState = getInitialState();
    }
}
