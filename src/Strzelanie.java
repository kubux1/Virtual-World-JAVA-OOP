/**
 * Created by Kuba on 2016-03-17.
 */
public class Strzelanie {
private static int liczbaNaboi=1;
    Strzelanie(int liczbaNaboij){
        liczbaNaboi=liczbaNaboij;
    }
    public void strzel(Kaczka doJakiejKaczki,  int liczbaNaboi){
        if((this.liczbaNaboi)-1>0){
            doJakiejKaczki.czyZywa = false;
            this.liczbaNaboi--;
        }
    }

    public static void main(String[] args){
        Strzelanie polowanie=new Strzelanie(3);
        Kaczka DzikaKaczka=new Kaczka(true);

        Kaczka[] nazwy =new Kaczka[3];
        for(int i=0;i<nazwy.length;i++)
            nazwy[i]=new Kaczka(true);
        nazwy[0].imie="Dzika";
        nazwy[1].imie="Wolna";
        nazwy[2].imie="Tutek";

        for(int i=0;i<3;i++){
            nazwy[i].kwacz();
            polowanie.strzel(nazwy[i],  polowanie.liczbaNaboi );
            nazwy[i].kwacz();
        }
        DzikaKaczka.kwacz();
        System.out.println("Stan magazynka: "+ polowanie.liczbaNaboi);
        polowanie.strzel(DzikaKaczka,  polowanie.liczbaNaboi );
        DzikaKaczka.kwacz();
        System.out.println("Stan magazynka: "+  polowanie.liczbaNaboi);
    }
}


class Kaczka{
   public String imie;
    boolean czyZywa;
    public Kaczka( boolean czyZywa){
        this.czyZywa=czyZywa;
    }

    public void kwacz(){
        if(this.czyZywa){
            System.out.println("kwa kwa: "+this.imie);
        }
        else{
             System.out.println("Kaczka martwa: "+ this.imie);
        }
    }
}
