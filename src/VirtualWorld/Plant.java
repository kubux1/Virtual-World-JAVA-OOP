package VirtualWorld;
import VirtualWorld.Organism;
import VirtualWorld.Randoms;
/**
 * Created by Kuba on 2016-05-09.
 */
abstract public class Plant extends Organism {
    abstract void action(Container world_organisms, Organism organisms_array[][]); // Proba rozmnozenia
    abstract void collision(Container world_organisms, Organism organisms_array[][], int moveX, int moveY);
}
