package Laborka_4;

/**
 * Created by Kuba on 2016-04-02.
 */
interface Fabryka {
    Figura zwrocFigure(String typ, int a, int b, int c ) throws Wyjatek;
}
