package dataStructures;

public class Token<T> {
    private String name;
    private T value;

    public Token(){}

    public Token(String name, T value) {
        this.name = name;
        this.value = value;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public T getValue() {
        return value;
    }
    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "<" + name + ", " + value + '>';
    }
}
