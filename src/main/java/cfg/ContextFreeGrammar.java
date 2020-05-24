package cfg;

import dataStructures.Token;
import reader.DefaultValues;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ContextFreeGrammar {
    ArrayList<Integer> nonDeterminant;
    ArrayList<Integer> determinant;

    Set<Productions> P;

    public ContextFreeGrammar(){
        nonDeterminant = new ArrayList<>();
        determinant = new ArrayList<>();
        P = new HashSet<Productions>();
    }

    public void findDeterminant(){
        determinant = new ArrayList<>();
        for (Productions p: P) {
            for (int ch:p.getDefinition()) {
                if (DefaultValues.availableChars.contains(ch) && !(nonDeterminant.contains(ch))){
                    determinant.add(ch);
                }
            }
        }
    }

    public void addProduction(Productions P){
        nonDeterminant.add(P.getNonDeterminant());
        this.P.add(P);
    }

    public ArrayList<Integer> getNonDeterminant() {
        return nonDeterminant;
    }

    public void setNonDeterminant(ArrayList<Integer> nonDeterminant) {
        this.nonDeterminant = nonDeterminant;
    }

    public ArrayList<Integer> getDeterminant() {
        return determinant;
    }

    public void setDeterminant(ArrayList<Integer> determinant) {
        this.determinant = determinant;
    }

    public Set<Productions> getP() {
        return P;
    }

    public void setP(Set<Productions> p) {
        P = p;
    }

    public void LL1(){

    }
}
