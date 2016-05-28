package VirtualWorld;
/**
 * Created by Kuba on 2016-05-11.
 */
public class myException extends Exception {
    public void animalNotBorn(World organismWorld,String name) {
        organismWorld.putMessage(name + " couldn't be born");
    }
}
