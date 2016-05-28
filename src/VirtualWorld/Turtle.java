package VirtualWorld;
import VirtualWorld.Randoms;
/**
 * Created by Kuba on 2016-05-10.
 */
public class Turtle extends Animal
{
   Turtle(World organism_world, Container world_organisms, Organism organisms_array[][], boolean born, int parent_pos_x, int parent_pos_y) {
        Randoms randomNumber = new Randoms();
        myException notBorn = new myException();
        int posX, posY;
        this.setWorld(organism_world);
        // Losowanie pozycji i sprawdzanie czy nie jest juz zajeta dla nie urodzonego organizmu
        if (born == false) {
            while (true) {
                posX = randomNumber.Rand_pos_x(this.getWeight());
                posY = randomNumber.Rand_pos_y(this.getHeight());
                if (organisms_array[posX][posY] == null) {
                    setOrganism(2, 1, 1, "Turtle", posX, posY, organism_world);
                    world_organisms.insert_element(this);
                    organisms_array[posX][posY] = this;
                    break;
                }
            }
        }
        // Dla urodzonego organizmu
        else {
            posX = parent_pos_x + randomNumber.Rand_move_x();
            posY = parent_pos_y + randomNumber.Rand_move_y();
           setOrganism(2, 1, 1, "Turtle", posX, posY, organism_world);
            if ((posX != parent_pos_x || posY != parent_pos_y) && posX >= 0 && posX < this.getWeight() && posY >= 0 && posY < this.getHeight()) {
                try {
                    if (organisms_array[posX][posY] == null) {
                        organisms_array[posX][posY] = this;
                        world_organisms.insert_element(this);
                        this.animalBirthMessage();
                    }
                    else throw notBorn;
                }
                catch (myException notBornAnimal) {
                    notBornAnimal.animalNotBorn(this.getOrganismWorld(), this.getName());
                }
            }
        }
    }
    Turtle(World organism_world,Container world_organisms, Organism organisms_array[][]){
        boolean born = false;
        int parent_pos_x=0 , parent_pos_y=0;
        new Turtle(organism_world,world_organisms,organisms_array,born,parent_pos_x,parent_pos_y);
    }

   Turtle(World organism_world, Container world_organisms, Organism organisms_array[][], int strength, int initiative, int age, String name, int posX, int posY) {
       setOrganism(strength, initiative, age, name, posX, posY, organism_world);
        world_organisms.insert_element(this);
        organisms_array[posX][posY] = this;
    }


    void action(Container world_organisms, Organism organisms_array[][]) {
        Randoms randomNumber = new Randoms();
        int moveX = 0, moveY = 0;
        int posX = this.getPosX();
        int posY = this.getPosY();

        this.increaseAge();
        if (randomNumber.turtleMove() == true) {
            while (true) {
                moveX = randomNumber.Rand_move_x();
                moveY = randomNumber.Rand_move_y();
                if (moveX != 0 || moveY != 0) {
                    if ((posX + moveX) >= 0 && (posX + moveX) < this.getWeight() && (posY + moveY) >= 0 && (posY + moveY) <this.getHeight()) // Sprawdzanie czy nie wyjdzie poza mape
                    break;
                }
            }
            if (organisms_array[posX + moveX][posY + moveY] == null) {
                organisms_array[posX + moveX][posY + moveY] = this;
                organisms_array[posX][posY] = null;
                this.changePosition(posX + moveX, posY + moveY);
            }
            else
                collision(world_organisms, organisms_array, moveX, moveY);
        }
    }


    int reproduce(Container world_organisms, Organism organisms_array[][], int parentPosX, int parentPosY) {
        new Turtle(this.getOrganismWorld(), world_organisms, organisms_array, true, parentPosX, parentPosY);
        return 0;
    }
    @Override
    int defence(Container world_organisms, Organism organisms_array[][], int moveX, int moveY) {
        int posX = this.getPosX();
        int posY = this.getPosY();
        if (organisms_array[posX - moveX][posY - moveY].getStrength() < 5) {
            return 0;
        }
        else super.defence(world_organisms,organisms_array, moveX, moveY);
        return 0;
    }


}
