package controler;

import automata.DFA;
import automata.DFA2;
import cfg.ContextFreeGrammar;
import dataStructures.Token;

import java.util.ArrayList;

public class SyntaxAnalyzer {
    private ContextFreeGrammar CFG;
    private LexicalAnalyzer lex;

    public SyntaxAnalyzer(LexicalAnalyzer lex){
        this.lex = lex;
    }

    public void agregarProduccionesDoubleAritmetica(){
        String P = "";
    }

    public void readChar(String input){
        Token<DFA2> tokensRead = null;
        Token<DFA2> tmp = null;

        for (char c: input.toCharArray()) {
            tokensRead = lex.simulatingToken(c);
            if (tokensRead != null) {
                if (tmp == null){
                    tmp = tokensRead;
                }
            } else if (tmp !=  null){
                System.out.println("c = " + c);
                System.out.println("tokensRead = " + tokensRead);
                System.out.println("tmp = " + tmp);
                tmp = null;
                simulate(tokensRead);
            }
        }
        if (tmp != null){
            System.out.println("tokensRead = " + tokensRead);
            System.out.println("tmp = " + tmp);
            tmp = null;
            simulate(tokensRead);
        }
    }

    public void simulate(Token<DFA2> tokensRead){
        CFG.parse(tokensRead);
    }


}
