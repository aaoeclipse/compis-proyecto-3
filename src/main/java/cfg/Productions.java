package cfg;

import automata.DFA;
import automata.DFA2;
import dataStructures.Token;

import java.util.ArrayList;

public class Productions <T>{
    T nonDeterminant;
    ArrayList<T> definition;
    private ArrayList<Integer> positionFirst;
    private Token<DFA> orToken;


    public Productions(){
        positionFirst = new ArrayList<>();
        orToken = new Token<>("OR", new DFA("|"));
        this.nonDeterminant = null;
        this.definition = new ArrayList<>();
    }

    public Productions(T nonDeterminant, ArrayList<T> definition) {
        this.nonDeterminant = nonDeterminant;
        this.definition = definition;
        positionFirst = new ArrayList<>();
    }

    public T getNonDeterminant() {
        return nonDeterminant;
    }

    public void setNonDeterminant(T nonDeterminant) {
        this.nonDeterminant = nonDeterminant;
    }

    public ArrayList<T> getDefinition() {
        return definition;
    }

    public void setDefinition(ArrayList<T> definition) {
        this.definition = definition;
    }

    @Override
    public String toString() {
        String toReturn = "";
        for (Object i:definition) {
            toReturn += (char) ((int) i);
        }
        int t = (int) nonDeterminant;
        return "" + (char) t + " -> " + toReturn;
    }

    public Object first() {
        getFirstPositions();
        return positionFirst.get(0);
    }

    private void getFirstPositions() {
        boolean first = true;
        this.positionFirst = new ArrayList<>();
        for (int i = 0; i < definition.size(); i++)
         {
            if (first){
                this.positionFirst.add(i);
            }
            if (definition.get(i).equals(orToken))
                first = true;
        }
    }

    public void modify() {

    }
}
