package Laborka_4;

/**
 * Created by Kuba on 2016-04-02.
 */
public class FabrykaObiektow implements Fabryka {
    public Figura zwrocFigure(String typ, int a, int b, int c) throws Wyjatek {
            if (typ != "Prostokat" && typ != "prostokat" && typ != "Trojkat" && typ != "trojkat") {
                throw new Wyjatek("cos");
            }
        else{
            if (typ == "Prostokat" || typ == "prostokat") {
                return new Prostokat(a, b);
            }
            else {
               return new Trojkat(a, b, c);
            }
        }
    }
}
