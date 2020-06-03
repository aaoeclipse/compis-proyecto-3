package cfg;

import dataStructures.Token;
import reader.DefaultValues;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ContextFreeGrammar<T> {
    ArrayList<T> nonDeterminant;
    ArrayList<T> determinant;

    Set<Productions<T>> P;

    public ContextFreeGrammar(){
        nonDeterminant = new ArrayList<>();
        determinant = new ArrayList<>();
        P = new HashSet<Productions<T>>();
    }

    public void findDeterminant(){
        determinant = new ArrayList<>();
        for (Productions<T> p: P) {
//            for (T ch:p.getDefinition()) {
//                if (DefaultValues.availableChars.contains(ch) && !(nonDeterminant.contains(ch))){
//                    determinant.add(ch);
//                }
//            }
        }
    }

    public void addProduction(Productions<T> P){
        nonDeterminant.add(P.getNonDeterminant());
        this.P.add(P);
    }




    public ArrayList<T> getNonDeterminant() {
        return nonDeterminant;
    }

    public void setNonDeterminant(ArrayList<T> nonDeterminant) {
        this.nonDeterminant = nonDeterminant;
    }

    public ArrayList<T> getDeterminant() {
        return determinant;
    }

    public void setDeterminant(ArrayList<T> determinant) {
        this.determinant = determinant;
    }

    public Set<Productions<T>> getP() {
        return P;
    }

    public void setP(Set<Productions<T>> p) {
        P = p;
    }

    public void LL1(){

    }
}
