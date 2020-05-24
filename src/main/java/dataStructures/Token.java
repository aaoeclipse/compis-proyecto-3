package dataStructures;

import java.util.Objects;

public class Token<T> {
    private String name;
    private T value;

    public Token(){}

    public Token(String name){
        this.name = name;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token<?> token = (Token<?>) o;
        return Objects.equals(name, token.name) &&
                Objects.equals(value, token.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }
}
