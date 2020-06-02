package dataStructures;

import controler.RegExConverter;
import org.w3c.dom.Node;
import reader.DefaultValues;
import reader.ReaderCustom;

import java.lang.reflect.Array;
import java.util.*;

/**
 * this tree is used to create a DFA from a regular expression
 */
public class SyntaxTree {
    private String regularExpression;
    private ArrayList<NodeSyntax<Integer>> allNodes;
    private NodeSyntax<Integer> tree;
    private RegExConverter regex;
    public Map<NodeSyntax<Integer>, ArrayList<Integer>> followTable;

    public SyntaxTree(){
        regularExpression = "";
    }

    public SyntaxTree(String input){
        followTable = new HashMap<>();
        allNodes = new ArrayList<>();
        regularExpression = input;
        regularExpression = withConcat(input);
        toPostFix();
        constructTree();
    }

    public ArrayList<NodeSyntax<Integer>> getAllNodes() {
        return allNodes;
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
            if (c == DefaultValues.EOF || c == 65535) {
                currNode = new NodeSyntax<>(DefaultValues.EOF, position, position);
                stackNode.push(currNode);
                allNodes.add(currNode);
                currNode = new NodeSyntax<>(DefaultValues.CONCAT);
                rightChild = stackNode.pop();
                leftChild = stackNode.pop();
                currNode.setLeftChild(leftChild);
                currNode.setRightChild(rightChild);
                currNode.setPosition(new ArrayList<>(leftChild.getPosition()));
                currNode.setLastpos(new ArrayList<>(rightChild.getPosition()));
                stackNode.push(currNode);
                allNodes.add(currNode);
                break;
            }
            // leaf | alphanumeric value
            if (DefaultValues.availableChars.contains(c)){
                currNode = new NodeSyntax<Integer>(c, position, position);
                stackNode.push(currNode);
                position++;
            }else{
                // Operation either binary (*?+) or non binary (|&)
                if (DefaultValues.BINARYOPERATIONS.contains(c)){
                    currNode = new NodeSyntax<>(c);
                    leftChild = stackNode.pop();
                    currNode.setLeftChild(leftChild);
                    currNode.setLastpos(new ArrayList(leftChild.getLastpos()));
                    currNode.setPosition(new ArrayList(leftChild.getPosition()));
                    stackNode.push(currNode);
                }else{
                    // es cat or OR
                    if (c == DefaultValues.OR) {
                        currNode = new NodeSyntax<>(c);
                        // Setting childs
                        rightChild = stackNode.pop();
                        leftChild = stackNode.pop();

                        currNode.setLeftChild(leftChild);
                        currNode.setRightChild(rightChild);
                        // adding position
                        // firstpos
                        if (DefaultValues.NULLABLE.contains(leftChild.getValue())) {
                            currNode.setPosition(new ArrayList(leftChild.getPosition()));
                            currNode.addFirstPosArray(new ArrayList(rightChild.getPosition()));
                        } else {
                            currNode.setPosition(new ArrayList(leftChild.getPosition()));
                        }
                        // last position
                        if (DefaultValues.NULLABLE.contains(rightChild.getValue())) {
                            currNode.setLastpos(new ArrayList(leftChild.getLastpos()));
                            currNode.addLastPosrray(new ArrayList(rightChild.getLastpos()));
                        } else {
                            currNode.setLastpos(new ArrayList(rightChild.getLastpos()));
                        }
                        // pushing to stack
                        stackNode.push(currNode);
                    } else {
                        currNode = new NodeSyntax<>(c);
                        // Setting childs
                        rightChild = stackNode.pop();
                        leftChild = stackNode.pop();

                        currNode.setLeftChild(leftChild);
                        currNode.setRightChild(rightChild);
                        // adding position
                        // firstpos
                        if (DefaultValues.BINARYOPERATIONS.contains(leftChild.getValue())) {
                            currNode.setPosition(new ArrayList(leftChild.getPosition()));
                            currNode.addFirstPosArray(new ArrayList(rightChild.getPosition()));
                        } else {
                            currNode.setPosition(new ArrayList(leftChild.getPosition()));
                        }
                        // last position
                        if (DefaultValues.BINARYOPERATIONS.contains(rightChild.getValue())) {
                            currNode.setLastpos(new ArrayList(leftChild.getLastpos()));
                            currNode.addLastPosrray(new ArrayList(rightChild.getLastpos()));
                        } else {
                            currNode.setLastpos(new ArrayList(rightChild.getLastpos()));
                        }
                        // pushing to stack
                        stackNode.push(currNode);
                    }
                }
            }
            allNodes.add(currNode);
        }
        this.tree = stackNode.pop();
    }

    public String getRegularExpression(){
        return regularExpression;
    }

    public void followpos(){
        followTable = new HashMap<>();
        int counter = 1;
        boolean added;
        for (NodeSyntax<Integer> n: allNodes)
            if (DefaultValues.availableChars.contains(n.getValue()) || n.getValue().equals(DefaultValues.EOF))
                followTable.put(n, new ArrayList<>());
        for (NodeSyntax<Integer> n: allNodes){
            // concat
            if (n.getValue().equals(DefaultValues.CONCAT)){
                // lado izquierdo agregar valors del lado derecho
                for (int i: n.getLeftChild().getLastpos()) {
                    for (int j: n.getRightChild().getFirstpos())
                        followTable.get(getSyntaxNode(i)).add(j);
                }
            } else if (n.getValue().equals(DefaultValues.STAR))
                for (int i:n.getLastpos())
                    for (int j: n.getFirstpos())
                        followTable.get(getSyntaxNode(i)).add(j);
            }
        }

        private NodeSyntax<Integer> getSyntaxNode(int idInt){
            ArrayList<Integer> id = new ArrayList<>();
            id.add(idInt);
            for (NodeSyntax<Integer> n:allNodes) {
                if (n.getPosition().equals(id))
                    return n;
            }
            return null;
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

    public String getTreeRoot() {

        return this.tree.toString();
        }

    public String getFullTree() {
        String toReturn = "";
        for (NodeSyntax<Integer> node: allNodes) {
            toReturn += node.toString() + "\n";
        }
        return toReturn;
    }

    public String getToStringFollowPos() {
        int counter = 1;
        StringBuilder toReturn = new StringBuilder("Node | followpos\n");
        int value;
        while (counter <= allNodes.size()) {
            for (Map.Entry<NodeSyntax<Integer>, ArrayList<Integer>> row : this.followTable.entrySet()) {
                value = row.getKey().getValue();
                if (value == DefaultValues.EOF)
                    value = (int) '#';
                if (counter == row.getKey().getPosition().get(0)) {
                    toReturn.append((char) value).append(".").append(row.getKey().getPosition().get(0)).append(" | ").append(row.getValue()).append('\n');
                }
            }
            counter++;
        }

        return toReturn.toString();
    }
}
