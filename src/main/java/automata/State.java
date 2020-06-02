package automata;

import java.util.Objects;
import java.util.UUID;

public class State {
    private String id;
    private String name;
    private boolean i;
    private boolean f;

    public State(){
        this.id = UUID.randomUUID().toString();
    }

    public State(String id, boolean i, boolean f) {
        this.id = id;
        this.i = i;
        this.f = f;
    }

    public State(boolean i) {
        this.id = UUID.randomUUID().toString();
        this.i = i;
        this.f = false;
    }

    public State(boolean i, boolean f) {
        this.id = UUID.randomUUID().toString();
        this.i = i;
        this.f = f;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isI() {
        return i;
    }

    public void setI(boolean i) {
        this.i = i;
    }

    public boolean isF() {
        return f;
    }

    public void setF(boolean f) {
        this.f = f;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return Objects.equals(id, state.id);
    }

    @Override
    public String toString() {
        return "State{" +
                "id='" + id + '\'' +
                ", i=" + i +
                ", f=" + f +
                '}';
    }
}
