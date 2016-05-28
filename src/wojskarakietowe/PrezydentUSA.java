/**
 * Created by Kuba on 2016-03-17.
 */
package wojskarakietowe;
import wojskarakietowe.TeczkaAtomowa;

public class PrezydentUSA {

    private TeczkaAtomowa teczka;
    public PrezydentUSA(TeczkaAtomowa teczka){
this.teczka=teczka;
    }
    public void mialemZlyDzien(){
        teczka.czerwonyGuzik();
    }
}
