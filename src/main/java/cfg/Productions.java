package cfg;

import dataStructures.Token;

import java.util.ArrayList;

public class Productions {
    int nonDeterminant;
    ArrayList<Integer> definition;


    public Productions(){}

    public Productions(int nonDeterminant, ArrayList<Integer> definition) {
        this.nonDeterminant = nonDeterminant;
        this.definition = definition;
    }

    public int getNonDeterminant() {
        return nonDeterminant;
    }

    public void setNonDeterminant(int nonDeterminant) {
        this.nonDeterminant = nonDeterminant;
    }

    public ArrayList<Integer> getDefinition() {
        return definition;
    }

    public void setDefinition(ArrayList<Integer> definition) {
        this.definition = definition;
    }

    @Override
    public String toString() {
        String toReturn = "";
        for (int i:definition) {
            toReturn += (char) i;
        }
        return "" + (char) nonDeterminant + " -> " + toReturn;
    }
}
