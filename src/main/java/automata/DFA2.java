package automata;

import dataStructures.Token;

import java.util.ArrayList;

public class DFA2 {
    private ArrayList<TransitionState<Token<DFA<Integer>>>> transitionTable;
    private State currState;
    private int counter;

    public DFA2(){
        counter = 1;
        transitionTable = new ArrayList<>();
    }

    public DFA2(Token<DFA<Integer>> first){
        counter = 1;
        transitionTable = new ArrayList<>();
        State firstState = createState(true, false);
        State finalState = createState(false, true);
        transitionTable.add(new TransitionState<Token<DFA<Integer>>> (firstState, first, finalState));
    }

    public void dfa2Add(Token<DFA<Integer>> dfa, int option){
        switch (option){
            case 0:
                // Concat
                State finalstate = getFinalState();
                finalstate.setF(false);
                State newFinalState = createState(false, true);
                transitionTable.add(new TransitionState(finalstate, dfa, newFinalState));
                break;

            case 1:
                // Or
                State initialState = getInitialState();
                State finalState = getFinalState();
                transitionTable.add(new TransitionState(initialState, dfa, finalState));
                break;

            case 2:
                // *
                State finals = getFinalState();
                this.transitionTable.add(new TransitionState<>(finals, dfa, finals));
                break;

            default:
                break;
        }
    }

    private State getInitialState() {
        for (TransitionState<Token<DFA<Integer>>> t: this.transitionTable) {
            if (t.getFirstState().isI())
                return t.getFirstState();
        }
        return null;
    }

    private State getFinalState() {
        for (TransitionState<Token<DFA<Integer>>> t: this.transitionTable) {
            if (t.getNextState().isF())
                return t.getNextState();
            if (t.getFirstState().isF())
                return t.getFirstState();
        }
        return null;
    }

    private State createState(boolean initial, boolean finalS) {
        State toReturn = new State(""+counter, initial, finalS);
        counter++;
        return toReturn;
    }


    private State getState(int i) {
        String s = ""+i;
        for (TransitionState<Token<DFA<Integer>>> t:
                this.transitionTable) {
            if (t.getFirstState().getId().equals(s))
                return t.getFirstState();
            if (t.getNextState().getId().equals(s))
                return t.getNextState();
        }
        return new State(s, false, false);
    }

    public boolean simulate(char c){
        return simulate((int) c);
    }

    public boolean simulate(int c){
        boolean check = false;
        boolean dead = false;

        if (currState == null) {
            dead = true;
            currState = getInitialState();
        }
        for (TransitionState<Token<DFA<Integer>>> t: this.transitionTable) {
            if (currState.getId().equals(t.getFirstState().getId()))
                if (t.getTransition().getValue().simulate(c)) {
                    currState = t.getNextState();
                    check = true;
                    break;
                }
        }
        if (!check && !dead) {
            currState = null;
            return simulate(c);
        }

        assert currState != null;
        return currState.isF();
    }

    public boolean simulate(DFA dfa){
        boolean check = false;
        boolean dead = false;

        if (currState == null) {
            dead = true;
            currState = getInitialState();
        }
        for (TransitionState<Token<DFA<Integer>>> t: this.transitionTable) {
            if (currState.getId().equals(t.getFirstState().getId()))
                if (t.getTransition().getValue().equals(dfa)) {
                    currState = t.getNextState();
                    check = true;
                    break;
                }
        }
        if (!check && !dead) {
            currState = null;
            return simulate(dfa);
        }

        assert currState != null;
        return currState.isF();
    }

    @Override
    public String toString() {
        return "DFA2{" +
                "transitionTable=" + transitionTable +
                ", currState=" + currState +
                '}';
    }
}
