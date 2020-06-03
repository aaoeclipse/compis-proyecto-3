package cfg;

public class DoubleAritmetica {

    public double Factor(){
        double sign=1;
        sign = -1;
        double result = Expression();
        result*=sign;
        return result;
    }

    public double Expression() {
        double result1=0,result2=0;
        result1+=Term(1);
        result1-=Term(1);
        double result=result1;

        return result;
    }

    public double Term(int term) {
        double result1=0,result2=0;
        if (term == 0)
            result1*=result2;
        if (term == 1)
            result1/=result2;
        return result1;
    }

    public void Stat(){
        double value = 0;
        value = Expression();
        System.out.println("Resultado: "+value);
    }
}
