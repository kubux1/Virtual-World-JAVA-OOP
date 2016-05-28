package VirtualWorld;
import VirtualWorld.Randoms;
/**
 * Created by Kuba on 2016-05-10.
 */
public class Antelope extends Animal {
    Antelope(World organism_world, Container world_organisms, Organism organisms_array[][], boolean born, int parent_pos_x, int parent_pos_y) {
        Randoms randomNumber = new Randoms();
        myException notBorn = new myException();
        int posX, posY;
        this.setWorld(organism_world);
        if (born == false) { // Losowanie pozycji i sprawdzanie czy nie jest juz zajeta dla nie urodzonego organizmu
            while (true) {
                posX = randomNumber.Rand_pos_x(this.getWeight());
                posY = randomNumber.Rand_pos_y(this.getHeight());
                if (organisms_array[posX][posY] == null) {
                    setOrganism(4, 4, 1, "Antelope", posX, posY, organism_world);
                    world_organisms.insert_element(this);
                    organisms_array[posX][posY] = this;
                    break;
                }
            }
        }

        else { // -||- dla urodzonego organizmu
            posX = parent_pos_x + randomNumber.Rand_move_x();
            posY = parent_pos_y + randomNumber.Rand_move_y();
            setOrganism(4, 4, 1, "Antelope", posX, posY, organism_world);
            if ((posX != parent_pos_x || posY != parent_pos_y) && posX >= 0 && posX <this.getWeight() && posY >= 0 && posY < this.getHeight()) {
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
    Antelope(World organism_world,Container world_organisms, Organism organisms_array[][]){
        boolean born = false;
        int parent_pos_x=0 , parent_pos_y=0;
        new Antelope(organism_world,world_organisms,organisms_array,born,parent_pos_x,parent_pos_y);
    }

   Antelope(World organism_world, Container world_organisms, Organism organisms_array[][], int strength, int initiative, int age, String name, int posX, int posY) {
        setOrganism(strength, initiative, age, name, posX, posY, organism_world);
        world_organisms.insert_element(this);
        organisms_array[posX][posY] = this;
    }

    @Override
    void action(Container world_organisms, Organism organisms_array[][]) {
        Randoms randomNumber = new Randoms();
        int moveX = 0, moveY = 0;

        this.increaseAge();
        for (int i = 0; i < 2 && this!=null; i++){
            int posX = this.getPosX();
            int posY = this.getPosY();
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
            }
            else
                collision(world_organisms, organisms_array, moveX, moveY);
        }
    }

    int reproduce(Container world_organisms, Organism organisms_array[][], int parentPosX, int parentPosY) {
        new Antelope(this.getOrganismWorld(), world_organisms, organisms_array, true, parentPosX, parentPosY);
        return 0;
    }

@Override
    int defence(Container world_organisms, Organism organisms_array[][], int moveX, int moveY) {
        Randoms randomNumber = new Randoms();
        int posX = this.getPosX();
        int posY = this.getPosY();
        if (randomNumber.antelopeEscape() == true) {
            for (int x = 0; x < this.getWeight(); x++) {
                for (int y = 0; y < this.getHeight(); y++) {
                    if (organisms_array[x][y] == null) {
                        organisms_array[x][y] = this;
                        organisms_array[posX][posY] = null;
                        this.changePosition(x, y);
                        return 0;
                    }
                }
            }
            super.defence(world_organisms, organisms_array, moveX, moveY); // Jesli cala mapa zajeta to walczy
        }
        else super.defence(world_organisms, organisms_array, moveX, moveY);
        return 0;
    }


}
