import automata.DFA;
import dataStructures.LexicalAnalyzer;
import dataStructures.Token;
import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import reader.DefaultValues;

import java.util.ArrayList;


public class TestLexicalAnalyser extends TestCase {

    public void testDFA(){
        LexicalAnalyzer lexAn = new LexicalAnalyzer();

        lexAn.addSimpleDfa("alphabet", DefaultValues.alphabet, true);

        ArrayList<Token<DFA>> test = lexAn.simulate('a');
        assertEquals(1, test.size());
        assertEquals("alphabet", test.get(0).getName());
    }

    public void testTwoDFAs(){
        LexicalAnalyzer lexAn = new LexicalAnalyzer();

        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add((int) '0');
        numbers.add((int) '1');
        numbers.add((int) '2');
        numbers.add((int) '3');
        numbers.add((int) '4');
        numbers.add((int) '5');
        numbers.add((int) '6');
        numbers.add((int) '7');
        numbers.add((int) '8');
        numbers.add((int) '9');

        lexAn.addSimpleDfa("alphabet", DefaultValues.alphabet, true);
        lexAn.addSimpleDfa("number", numbers, true);

        ArrayList<Token<DFA>> test = lexAn.simulate('a');

        assertEquals(1, test.size());
        assertEquals("alphabet", test.get(0).getName());

        test = lexAn.simulate('2');

        assertEquals(1, test.size());
        assertEquals("number", test.get(0).getName());
    }

    public void testSimultaneousDFAS(){
        LexicalAnalyzer lexAn = new LexicalAnalyzer();
        ArrayList<Integer> test = new ArrayList<>();
        ArrayList<Integer> test2 = new ArrayList<>();

        test.add((int) 'a');
        test2.add((int) 'a');

        lexAn.addSimpleDfa("test1", test,true);
        lexAn.addSimpleDfa("test2", test2,true);

        ArrayList<Token<DFA>> testResult = lexAn.simulate('a');

        assertEquals(2, testResult.size());
        assertEquals("test1", testResult.get(0).getName());
        assertEquals("test2", testResult.get(1).getName());

    }

    public void testOneAfterAnother(){
        LexicalAnalyzer lex = new LexicalAnalyzer();

        ArrayList<Integer> test = new ArrayList<>();
        test.add((int) 'c');
        test.add((int) 'd');

        lex.addSimpleDfa("test", test,false);


        ArrayList<Token<DFA>> testResult = lex.simulate('c');
        testResult = lex.simulate('d');

        assertEquals(1, testResult.size());
        assertEquals("test", testResult.get(0).getName());

        testResult = lex.simulate('c');
        testResult = lex.simulate('d');

        assertEquals(1, testResult.size());
        assertEquals("test", testResult.get(0).getName());

        testResult = lex.simulate('c');
        testResult = lex.simulate('c');
        testResult = lex.simulate('d');

        assertEquals(1, testResult.size());
        assertEquals("test", testResult.get(0).getName());

    }

    public void testCocorDoubleAritmeticaCHARACTERS(){
        LexicalAnalyzer lex = new LexicalAnalyzer();
        lex.addCocolDoubleAritmetica();
        ArrayList<String> s = lex.simulating('1');
        assertEquals("digit",s.get(0));

        s = lex.simulating((char) 10);
        assertEquals("eol",s.get(0));

        s = lex.simulating((char) 9);
        assertEquals("tab",s.get(0));

        s = lex.simulating(' ');
        assertEquals("blanco",s.get(0));

        s = lex.simulating('&');
        assertEquals("<UNKNOWN Token>", s.get(0));
    }

    public void testCocorDoubleAritmeticaKEYWORDS(){
        LexicalAnalyzer lex = new LexicalAnalyzer();
        lex.addCocolDoubleAritmetica();
        ArrayList<String> s = lex.simulating('1');
        for (String ss:s) {
            System.out.println("ss = " + ss);
        }
        s = lex.simulating('0');
        for (String ss:s) {
            System.out.println("ss = " + ss);
        }
        s = lex.simulating('9');
        for (String ss:s) {
            System.out.println("ss = " + ss);
        }
    }

    public void testCocorDoubleAritmeticaCHARACTERSandKEYWORDS(){
        LexicalAnalyzer lex = new LexicalAnalyzer();
        lex.addCocolDoubleAritmetica();
        ArrayList<String> s = lex.simulating('1');
        for (String ss:s) {
            System.out.println("ss = " + ss);
        }
        s = lex.simulating('0');
        for (String ss:s) {
            System.out.println("ss = " + ss);
        }
        s = lex.simulating('9');
        for (String ss:s) {
            System.out.println("ss = " + ss);
        }
    }
}
