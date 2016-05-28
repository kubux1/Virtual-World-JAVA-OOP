package VirtualWorld;
import VirtualWorld.Organism;
import VirtualWorld.Randoms;
/**
 * Created by Kuba on 2016-05-09.
 */
abstract public class Animal extends  Organism implements java.io.Serializable
{
    abstract int reproduce(Container world_organisms, Organism organisms_array[][], int pos_x, int pos_y);

    @Override
     void action(Container world_organisms, Organism organisms_array[][]) { // Przesuwa organizm na losowe pole
        Randoms randomNumber = new Randoms();
        int moveX = 0, moveY = 0;
        int posX = this.getPosX();
        int posY = this.getPosY();
        this.increaseAge();
        while (true) {
            moveX = randomNumber.Rand_move_x();
            moveY = randomNumber.Rand_move_y();
            if (moveX != 0 || moveY != 0) {
                if ((posX + moveX) >= 0 && (posX + moveX) < this.getWeight() && (posY + moveY) >= 0 && (posY + moveY) < this.getHeight()) // Sprawdzanie czy nie wyjdzie poza mape
                    break;
            }
        }
        if (organisms_array[posX + moveX][posY + moveY] == null) {
            organisms_array[posX + moveX][posY + moveY] = this;
            organisms_array[posX][posY] = null;
            this.changePosition(posX + moveX, posY + moveY);
        } else
            collision(world_organisms, organisms_array, moveX, moveY);
    }

    @Override
    void collision (Container world_organisms, Organism organisms_array[][], int moveX, int moveY){// Walka lub kolizja
        int posX = this.getPosX();
        int posY = this.getPosY();

        if (organisms_array[posX + moveX][posY + moveY].getName() . equals( this.getName())) { // Jesli te same organizmy to rodzi sie nowy organizm
            reproduce(world_organisms, organisms_array, posX, posY);
        }

        else { // Jesli inne to nastepuje walka
            fight(world_organisms, organisms_array, moveX, moveY);
        }
    }

    int fight(Container world_organisms, Organism  organisms_array[][], int moveX, int moveY){
        int posX = this.getPosX();
        int posY = this.getPosY();
        organisms_array[posX + moveX][posY + moveY].defence(world_organisms, organisms_array, moveX, moveY);
        return 0;
    }
}
