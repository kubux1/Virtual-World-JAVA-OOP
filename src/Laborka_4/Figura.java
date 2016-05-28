package Laborka_4;

/**
 * Created by Kuba on 2016-04-02.
 */
abstract class Figura{
    String name;
    abstract double pole ();
    abstract double obwod();
    @Override
    public String toString(){
        String info = new String(name + ", obwod: "+obwod()+", pole:"+ pole());
        return info;
    }
}
