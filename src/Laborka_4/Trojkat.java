package Laborka_4;
import java.lang.Math;
/**
 * Created by Kuba on 2016-04-02.
 */
public class Trojkat extends Figura{
    double a,b,c;

    public Trojkat(float a,float b,float c){
        name = "Trojkat";
        this.a=a;
        this.b=b;
        this.c=c;
    }
    public double pole(){
        double p = (a+b+c)/2;
        return Math.sqrt(p*(p-a)*(p-b)*(p-c));
    }

    public double obwod(){
        return a+b+c;
    }

}
