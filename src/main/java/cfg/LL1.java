package cfg;

public class LL1 {
    ContextFreeGrammar cfg;

    public LL1(ContextFreeGrammar cfg) {
        this.cfg = cfg;
        constructTable();
    }

    public void constructTable(){
        for (int i:cfg.getNonDeterminant()) {

        }
    }

    public void First(){

    }

    public void Follow(){

    }
}
