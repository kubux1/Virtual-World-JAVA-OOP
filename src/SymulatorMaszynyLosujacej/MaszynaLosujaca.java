package SymulatorMaszynyLosujacej;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.lang.*;
import SymulatorMaszynyLosujacej.Kula;
/**
 * Created by Kuba on 2016-03-17.
 */
public class MaszynaLosujaca {
    Kula[] numerKuli = new Kula[49];
    Timer timer = new Timer();

    public void Kule() {
        Random losowanieCzyObciazane = new Random();
        int ZliczajOszukanie = 0;

        for (int i = 0; i < 49; i++) {
            boolean obciazane = losowanieCzyObciazane.nextBoolean();
            numerKuli[i] = new Kula();
            numerKuli[i].LiczbaNaKuli = i;
            if (ZliczajOszukanie <= 6) {
                if (numerKuli[i].czy_dociazona = obciazane)
                    ZliczajOszukanie++;
            } else {
                numerKuli[i].czy_dociazona = false;
            }
        }
    }

    public void start() {
        timer = new Timer();
        System.out.println("Losowanie rozpoczęte!");
        timer.schedule(new Losuj(),0, 10);
    }

    private void losowanie() {
        //zamienianie 2 losowych kul miejscami
        int tmp;
        boolean tmp1;
        Random losowanieKuli = new Random();
        int losowaKula1 = losowanieKuli.nextInt(48);
        int losowaKula2 = losowanieKuli.nextInt(48);

        tmp = numerKuli[losowaKula1].LiczbaNaKuli;
        tmp1 = numerKuli[losowaKula1].czy_dociazona;
        numerKuli[losowaKula1].LiczbaNaKuli = numerKuli[losowaKula2].LiczbaNaKuli;
        numerKuli[losowaKula1].czy_dociazona = numerKuli[losowaKula2].czy_dociazona;
        numerKuli[losowaKula2].LiczbaNaKuli = tmp;
        numerKuli[losowaKula2].czy_dociazona = tmp1;

        //przesuwanie oszukanych kul o 1 w gore
        for (int i = 0; i < numerKuli.length; i++) {
            if (numerKuli[i].czy_dociazona == true) {
                if(i-1>=0) {
                    tmp = numerKuli[i - 1].LiczbaNaKuli;
                    tmp1 = numerKuli[i - 1].czy_dociazona;
                    numerKuli[i - 1].LiczbaNaKuli = numerKuli[i].LiczbaNaKuli;
                    numerKuli[i - 1].czy_dociazona = numerKuli[i].czy_dociazona;
                    numerKuli[i].LiczbaNaKuli = tmp;
                    numerKuli[i].czy_dociazona = tmp1;
                }
            }
        }
    }

    public void stop() {
        timer.cancel();
        System.out.println("Losowanie zakończone!");
        for (int i = 0; i < 6; i++) {
            if(!(numerKuli[i].czy_dociazona))
            System.out.println("Liczba na " + (i + 1) + " kuli: " + numerKuli[i].LiczbaNaKuli );
            if(numerKuli[i].czy_dociazona)
                   System.out.println("Liczba na " + (i + 1) + " kuli:* " + numerKuli[i].LiczbaNaKuli );
        }
    }

    class Losuj extends TimerTask {
        public void run() {
            losowanie();
        }
    }

    class Stopuj extends TimerTask {
        public void run() {
            stop();
        }
    }
}


