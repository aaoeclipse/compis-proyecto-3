package reader;

import dataStructures.Operations;

import java.util.ArrayList;

public final class DefaultValues {
    public static ArrayList<Integer> alphabet = new ArrayList<Integer>(){
        {
            for (int i = 0; i < 26; i++) {
                // UPPER CASE
                add(65 + i);
                // LOWER CASE
                add(97 + i);
            }
        }
    };
    public static ArrayList<Integer> digits = new ArrayList<Integer>(){
        {
            add((int) '0');
            add((int) '1');
            add((int) '2');
            add((int) '3');
            add((int) '4');
            add((int) '5');
            add((int) '6');
            add((int) '7');
            add((int) '8');
            add((int) '9');
        }
    };
    public static ArrayList<Integer> parentesis = new ArrayList<Integer>(){
        {
            add((int) '(');
        }
    };

    public static int EOF = -1;
    public static int BLANK = (int) ' ';
    public static int NL = (int) '\n';

    public static ArrayList<Integer> availableChars = new ArrayList<>(){
        {
            addAll(alphabet);
            addAll(digits);
        }
    };

    // Operators
    public static int CONCAT = 2500;
    public static int OR = 2501;
    public static int STAR = 2502;
    public static int ATLEASTONE = 2503;
    public static int EPSILON = 2504;
    public static int ONEORLESS = 2505;

    public static ArrayList<Integer> ignore = new ArrayList<>(){
        {
            add((int) ')');
        }
    };

    public static ArrayList<Integer> BINARYOPERATIONS = new ArrayList<>(){
        {
            add(STAR);
            add(ATLEASTONE);
            add(ONEORLESS);
        }
    };

    public static Operations opertaions = new Operations();

}
