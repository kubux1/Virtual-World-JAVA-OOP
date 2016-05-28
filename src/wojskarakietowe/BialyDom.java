/**
 * Created by Kuba on 2016-03-17.
 */
package wojskarakietowe;
import wojskarakietowe.TeczkaAtomowa;
public class BialyDom {

    public static void main(String[] args) {
        PrezydentUSA obama = new PrezydentUSA(new TeczkaAtomowa());
        obama.mialemZlyDzien();
    }
}
