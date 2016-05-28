package Laborka_4;

/**
 * Created by Kuba on 2016-04-02.
 */
public class Figury {
    public static void main(String[] args) throws Wyjatek {
        try {
        FabrykaObiektow fabryka = new FabrykaObiektow();
            Figura obiekt = fabryka.zwrocFigure("prostokat", 10, 20, 0);
            System.out.println(obiekt.toString());
            fabryka.zwrocFigure("inny",20,30,1);
        }
        catch (Wyjatek w){
            w.printStackTrace();
        }
    }
}
