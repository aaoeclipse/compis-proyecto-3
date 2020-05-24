package dataStructures;

public class NodeSyntax<E> {
    private E value;
    private int position;
    private NodeSyntax<E> parentNode;

    private NodeSyntax<E> leftChild;
    private NodeSyntax<E> rightChild;

    public NodeSyntax(E value, int position) {
        this.value = value;
        this.position = position;
    }
    public NodeSyntax(E value) {this.value = value;}
    public NodeSyntax() {}

    public NodeSyntax<E> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(NodeSyntax<E> leftChild) {
        this.leftChild = leftChild;
    }

    public NodeSyntax<E> getRightChild() {
        return rightChild;
    }

    public void setRightChild(NodeSyntax<E> rightChild) {
        this.rightChild = rightChild;
    }

    private boolean nullable;



    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
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
        return "NodeSyntax{" +
                "value=" + value +
                ", position=" + position +
                ", parentNode=" + parentNode +
                ", leftChild=" + leftChild +
                ", rightChild=" + rightChild +
                ", nullable=" + nullable +
                '}';
    }
}
