package cfg;

import dataStructures.Token;

import java.util.ArrayList;

public class Productions <T>{
    T nonDeterminant;
    ArrayList<T> definition;


    public Productions(){}

    public Productions(T nonDeterminant, ArrayList<T> definition) {
        this.nonDeterminant = nonDeterminant;
        this.definition = definition;
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
        for (T i:definition) {
            toReturn += i;
        }
        return "" + nonDeterminant + " -> " + toReturn;
    }
}
