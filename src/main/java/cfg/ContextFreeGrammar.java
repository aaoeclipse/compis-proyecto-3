package cfg;

import automata.DFA2;
import dataStructures.Token;
import reader.DefaultValues;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ContextFreeGrammar {
    ArrayList<Token<DFA2>> nonDeterminant;
    ArrayList<Token<DFA2>> determinant;

    Set<Productions<Token<DFA2>>> P;

    public ContextFreeGrammar(){
        nonDeterminant = new ArrayList<>();
        determinant = new ArrayList<>();
        P = new HashSet<Productions<Token<DFA2>>>();
    }

    public void findDeterminant(){
        determinant = new ArrayList<>();
        for (Productions<Token<DFA2>> p: P) {
//            for (T ch:p.getDefinition()) {
//                if (DefaultValues.availableChars.contains(ch) && !(nonDeterminant.contains(ch))){
//                    determinant.add(ch);
//                }
//            }
        }
    }

    public void addProduction(Productions<Token<DFA2>> P){
        nonDeterminant.add(P.getNonDeterminant());
        this.P.add(P);
    }

    public ArrayList<Token<DFA2>> getNonDeterminant() {
        return nonDeterminant;
    }

    public void setNonDeterminant(ArrayList<Token<DFA2>> nonDeterminant) {
        this.nonDeterminant = nonDeterminant;
    }

    public ArrayList<Token<DFA2>> getDeterminant() {
        return determinant;
    }

    public void setDeterminant(ArrayList<Token<DFA2>> determinant) {
        this.determinant = determinant;
    }

    public Set<Productions<Token<DFA2>>> getP() {
        return P;
    }

    public void setP(Set<Productions<Token<DFA2>>> p) {
        P = p;
    }

    public void LL1(){
        removeRedundancy();
        firstFollow();
        createParseTable();
    }

    private void removeRedundancy() {

        for (Productions<Token<DFA2>> p: this.P) {
            if (p.equals(p.first())){
                Productions<Token<DFA2>> newLine = new Productions<>(

                );
                p.modify();
            }

        }
    }

    private void firstFollow() {
        // TODO: Crear la tabla de first follow
    }


    private void createParseTable() {
        // TODO Crear el parsing table
    }

    public void parse(Token<DFA2> tokensRead) {


    }
}
