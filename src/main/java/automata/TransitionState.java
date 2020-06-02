package automata;

import dataStructures.Token;

public class TransitionState<T> {
    private State firstState;
    private T transition;
    private State nextState;

    public TransitionState(State firstState, T transition, State nextState) {
        this.firstState = firstState;
        this.transition = transition;
        this.nextState = nextState;
    }

    public State getFirstState() {
        return firstState;
    }

    public void setFirstState(State firstState) {
        this.firstState = firstState;
    }

    public T getTransition() {
        return transition;
    }

    public void setTransition(T transition) {
        this.transition = transition;
    }

    public State getNextState() {
        return nextState;
    }

    public void setNextState(State nextState) {
        this.nextState = nextState;
    }

    @Override
    public String toString() {
        return "TransitionState{" +
                "firstState=" + firstState +
                ", transition=" + transition +
                ", nextState=" + nextState +
                '}';
    }
}
