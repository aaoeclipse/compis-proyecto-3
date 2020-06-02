package dataStructures;

import java.util.ArrayList;

public class NodeSyntax<E> {
    private E value;
    private ArrayList<Integer> firstpos;
    private NodeSyntax<E> parentNode;
    private ArrayList<Integer> lastpos;
    private boolean nullable;

    private NodeSyntax<E> c1;
    private NodeSyntax<E> c2;

    public NodeSyntax(E value, int position) {
        this.value = value;
        this.firstpos = new ArrayList<>();
        this.lastpos = new ArrayList<>();
        this.firstpos.add(position);
    }
    public NodeSyntax(E value, int position, int lastpos) {
        this.value = value;
        this.firstpos = new ArrayList<>();
        this.lastpos = new ArrayList<>();
        this.firstpos.add(position);
        this.lastpos.add(lastpos);
    }
    public NodeSyntax(E value) {
        this.value = value;
        this.firstpos = new ArrayList<>();
        this.lastpos = new ArrayList<>();
    }
    public NodeSyntax() {}

    public NodeSyntax<E> getLeftChild() {
        return c1;
    }

    public void setLeftChild(NodeSyntax<E> leftChild) {
        this.c1 = leftChild;
    }

    public NodeSyntax<E> getRightChild() {
        return c2;
    }

    public void setRightChild(NodeSyntax<E> rightChild) {
        this.c2 = rightChild;
    }

    public E getValue() {
        return value;
    }

    public ArrayList<Integer> getLastpos() {
        return lastpos;
    }

    public void setLastpos(ArrayList<Integer> lastpos) {
        this.lastpos = lastpos;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public ArrayList<Integer> getPosition() {
        return firstpos;
    }

    public void setPosition(ArrayList<Integer> position) {
        this.firstpos = position;
    }

    public void addFirstPos(int firstpos){
        if (!this.firstpos.contains(firstpos)){
            this.firstpos.add(firstpos);
        }
    }

    public void addFirstPosArray(ArrayList<Integer> firstpos){
        for (int i:firstpos) {
            addFirstPos(i);
        }
    }

    public ArrayList<Integer> getFirstpos() {
        return firstpos;
    }

    public void setFirstpos(ArrayList<Integer> firstpos) {
        this.firstpos = firstpos;
    }

    public void addLastPos(int lastpos){
        if (!this.lastpos.contains(lastpos)){
            this.lastpos.add(lastpos);
        }
    }

    public void addLastPosrray(ArrayList<Integer> lastpos){
        for (int i:lastpos) {
            addLastPos(i);
        }
    }

    public NodeSyntax<E> getParentNode() {
        return parentNode;
    }

    public void setParentNode(NodeSyntax<E> parentNode) {
        this.parentNode = parentNode;
    }

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    @Override
    public String toString() {
        if (c1 == null && c2 == null)
            return "Node ("+value+") {"+firstpos+"}{"+lastpos+"} -> ()";
        if (c1 != null && c2 == null)
            return "Node ("+value+") {"+firstpos+"}{"+lastpos+"} -> ("+c1.getValue()+")";
        return "Node ("+value+") {"+firstpos+"}{"+lastpos+"} -> ("+ c1.getValue()+", "+c2.getValue()+")";
    }
}
