package VirtualWorld;
/**
 * Created by Kuba on 2016-05-11.
 */
public class Grass extends Plant {
    Grass(World organism_world,Container world_organisms, Organism organisms_array[][], boolean born, int parent_pos_x, int parent_pos_y) {
        Randoms randomNumber = new Randoms();
        int posX, posY;
        this.setWorld(organism_world);
        // Losowanie pozycji i sprawdzanie czy nie jest juz zajeta dla nie urodzonego organizmu
        if (born == false) {
            while (true) {
                posX = randomNumber.Rand_pos_x(this.getWeight());
                posY = randomNumber.Rand_pos_y(this.getHeight());
                if (organisms_array[posX][posY] == null) {
                    setOrganism(0, 0, 1, "Grass", posX, posY, organism_world);
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
            if ((posX != parent_pos_x || posY != parent_pos_y) && posX >= 0 && posX < this.getWeight() && posY >= 0 && posY < this.getHeight()) {
                if (organisms_array[posX][posY] == null) {
                    organisms_array[posX][posY] = this;
                    setOrganism(0, 0, 1, "Grass", posX, posY, organism_world);
                    world_organisms.insert_element(this);
                    this.plantGrowMessage();
                }
            }
        }
    }
    Grass(World organism_world,Container world_organisms, Organism organisms_array[][]){
        boolean born = false;
        int parent_pos_x=0 , parent_pos_y=0;
        new Grass(organism_world,world_organisms,organisms_array,born,parent_pos_x,parent_pos_y);
    }

  Grass(World organism_world, Container world_organisms, Organism organisms_array[][], int strength, int initiative, int age, String name, int posX, int posY) {
        setOrganism(strength, initiative, age, name, posX, posY, organism_world);
        world_organisms.insert_element(this);
        organisms_array[posX][posY] = this;
    }

@Override
    void action(Container world_organisms, Organism organisms_array[][]) {
        Randoms random_number = new Randoms();
        if (random_number.Rand_reproduce() == true) { // Jesli szansa na rozmanzanie sie powiodla to sie rozmnaza
            new Grass(this.getOrganismWorld(),world_organisms,organisms_array, true, this.getPosX(), this.getPosY());

        }
    }
    @Override
    void collision(Container world_organisms, Organism organisms_array[][], int move_x, int move_y) {}
}
