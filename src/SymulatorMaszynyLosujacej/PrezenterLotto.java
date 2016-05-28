package SymulatorMaszynyLosujacej;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import SymulatorMaszynyLosujacej.Kula;
import SymulatorMaszynyLosujacej.MaszynaLosujaca;
/**
 * Created by Kuba on 2016-03-20.
 */
public class PrezenterLotto {
    /**
     * Scanner scanner =  new Scanner(System.in);
     * int Czaslosowania =  scnner.nextInt();
     */
    public static void main(String[] args) throws IOException {
        MaszynaLosujaca losowanie = new MaszynaLosujaca();
        int czasLosowania = 0;

        while (true) {
            System.out.println("Witaj w symulatorze maszyny losujacej");
            System.out.println("Proszę podać czas trwania losowania: ");
            // Wczytuje z konsoli czas trwania losowania
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            try {
                czasLosowania = Integer.parseInt(br.readLine());

            } catch (NumberFormatException nfe) {
                System.err.println("Invalid Format!");
            }

            losowanie.Kule();
                losowanie.start();
                try {
                    Thread.sleep(czasLosowania);
                }
                catch (InterruptedException ie) {
                }
            losowanie.stop();
        }
    }
}

