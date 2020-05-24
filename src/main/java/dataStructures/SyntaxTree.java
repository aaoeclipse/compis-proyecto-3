package dataStructures;

import controler.RegExConverter;
import reader.DefaultValues;
import reader.ReaderCustom;

import java.util.Stack;

/**
 * this tree is used to create a DFA from a regular expression
 */
public class SyntaxTree {
    private String regularExpression;
    private NodeSyntax<Integer> tree;
    private RegExConverter regex;

    public SyntaxTree(){
        regularExpression = "";
    }

    public SyntaxTree(String input){
        regularExpression = input;
        withConcat(input);
        constructTree();
    }


    /**
     * returns the string with concat character in them
     * @param input is the given string in which it's going to add the concat between them
     * @return new String with the added concat
     */
    public String withConcat(String input){
        StringBuilder toReturn = new StringBuilder();
        boolean concat = false;
        for (int i = 0; i < input.length(); i++) {
            if (DefaultValues.STAR == (int) input.charAt(i)){
                toReturn.append((char) DefaultValues.STAR);
                continue;
            }
            else if (concat){
                if (DefaultValues.parentesis.contains((int) input.charAt(i))){
                    concat = false;
                    toReturn.append((char) DefaultValues.CONCAT);
                }
                else if (DefaultValues.ignore.contains((int) input.charAt(i)))
                    concat = true;
                else if (DefaultValues.availableChars.contains((int) input.charAt(i))) {
                    toReturn.append((char) DefaultValues.CONCAT);
                }
                else {
                    concat = false;
                }

            } else if (DefaultValues.availableChars.contains((int) input.charAt(i)) ){
                concat = true;
            } else if(DefaultValues.ignore.contains((int) input.charAt(i))){
                concat = true;
            } else{
                // it's a symbol
                concat = false;
            }
            toReturn.append(input.charAt(i));
        }

        regularExpression = toReturn.toString();
        return regularExpression;
    }

    public void toPostFix(){
        regex = new RegExConverter();
        this.regularExpression = regex.infixToPostfix(regularExpression);
        this.regularExpression+=((char)DefaultValues.EOF);

    }

    /**
     * construct the tree with the regularExpression variable
     * in the class
     */
    public void constructTree(){
        if (this.regularExpression.equals("")){
            System.err.println("Regular Expression Empty");
            return;
        }
        Stack<NodeSyntax<Integer>> stackNode = new Stack<>();

        // currNode
        NodeSyntax<Integer> currNode = new NodeSyntax<>();

        NodeSyntax<Integer> leftChild = new NodeSyntax<>();
        NodeSyntax<Integer> rightChild = new NodeSyntax<>();

        int position = 1;
        int c = 0;

        for (char character:this.regularExpression.toCharArray()) {
            c = (int) character;
            if (c == DefaultValues.EOF)
                break;
            if (DefaultValues.availableChars.contains(c)){
                stackNode.push(new NodeSyntax<>(c, position));
                position++;
            }else{
                if (DefaultValues.BINARYOPERATIONS.contains(c)){
                    currNode = new NodeSyntax<>(c);


                }else{

                }
            }
        }

    }

    public String getRegularExpression(){
        return regularExpression;
    }

    @Override
    public String toString() {
        StringBuilder toReturn = new StringBuilder();
        for (char c:this.regularExpression.toCharArray()) {
            if (c == (char) DefaultValues.STAR)
                toReturn.append("*");
            else if (c == (char) DefaultValues.CONCAT)
                toReturn.append(".");
            else if (c == (char) DefaultValues.OR)
                toReturn.append("|");
            else if (c == (char) DefaultValues.ATLEASTONE)
                toReturn.append("+");
            else if (c == (char) DefaultValues.ONEORLESS)
                toReturn.append("?");
            else
                toReturn.append(c);
        }
        return toReturn.toString();
    }

    public String showTree(){
        return tree.toString();
    }
}
