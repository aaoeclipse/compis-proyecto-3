package automata;

import java.util.ArrayList;

public class DFA2 {
    private ArrayList<TransitionState<DFA>> transitionTable;
    private State currState;

    public DFA2(){
        transitionTable = new ArrayList<>();
    }

    public void addDFA(DFA dfa, int transition){
        switch (transition){
            case 1:
                break;
            case 2:
                break;
            default:
                System.out.println("Default");
        }
//        transitionTable.add(new TransitionState<>())
    }

    public void simulate(char c){
        simulate((int) c);
    }

    public void simulate(int c){

    }


}
