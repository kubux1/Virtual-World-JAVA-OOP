/**
 * Created by Kuba on 2016-03-17.
 */
package wojskarakietowe;
public class TeczkaAtomowa {

    private WyrzutniaRakietAtomowych wyrzutnia;

    public TeczkaAtomowa() {
        wyrzutnia= new WyrzutniaRakietAtomowych ();
    }


public void czerwonyGuzik(){
    if(potwierdzenie()==true){
        wyrzutnia.ognia();
    }
    else {
        System.out.println("The rocket launcher is temporarily " +
                "unable to service your request due to " +
                "maintenance downtime or " +
                "capacity problems. Please try " +
                "again later.");
    }
    }

    private boolean potwierdzenie() {
    return true;
    }
}

