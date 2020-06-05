package dataStructures;

import automata.DFA;

import java.util.Objects;

public class Token<T> {
    private String name;
    private T value;
    private DFA dfa;
    private boolean keyword;

    public Token(){
        keyword = false;
    }

    public Token(String name){
        keyword = false;
        this.name = name;
    }

    public Token(String name, T value) {
        keyword = false;
        this.name = name;
        this.value = value;
    }

    public Token(String name, T value, boolean keyword) {
        this.keyword = keyword;
        this.name = name;
        this.value = value;
    }

    public boolean isKeyword() {
        return keyword;
    }

    public void setKeyword(boolean keyword) {
        this.keyword = keyword;
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

    public void addDFA(String character) {

    }

    public boolean printDFA() {
        return false;
    }
}
