package automata;

import dataStructures.Token;

public class TransitionState {
    private State firstState;
    private Token transition;
    private State nextState;

    public TransitionState(State firstState, Token transition, State nextState) {
        this.firstState = firstState;
        this.transition = transition;
        this.nextState = nextState;
    }

}
