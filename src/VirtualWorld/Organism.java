package VirtualWorld;
import VirtualWorld.Container;
import VirtualWorld.World;
import VirtualWorld.Messages;


/**
 * Created by Kuba on 2016-05-09.
 */
abstract public class Organism implements java.io.Serializable
{
    abstract void action(Container world_organisms,Organism organisms_array[][]); // Zachowanie organizmu w trakcie tury
    abstract void collision (Container world_organisms, Organism organisms_array[][], int moveX, int moveY); // Zachowanie w trakcie kontaktu z innym organizmem
    private int strength_;
    private int initiative_;
    private int age_;
    private String name_;
    private int posX_;
    private int posY_;
    transient World organismWorld_;


    void setOrganism(int strength, int initiative, int age, String name,int posX, int posY,World organismWorld) {
        this.strength_ = strength;
        this.initiative_ = initiative;
        this.age_ = age;
        this.name_ = name;
        this.posX_ = posX;
        this.posY_ = posY;
        this.organismWorld_ = organismWorld;
    }

    int getStrength() {
        return this.strength_;
    }

    int getInitiative() {
        return this.initiative_;
    }

    int getAge() {
        return this.age_;
    }

    String getName() {
        return this.name_;
    }

    int getPosX() {
        return this.posX_;
    }

    int getPosY() {
        return this.posY_;
    }

    World getOrganismWorld() {
        return this.organismWorld_;
    }

    void increaseStrength() {
        this.strength_ += 3;
        this.organismWorld_.messages.push(this.name_ + " ate a Guarana!");
    }

    void increaseAge() {
        this.age_+=1;
    }

    void changePosition(int posX, int posY) {
        this.posX_ = posX;
        this.posY_ = posY;
    }

    void killOrganism(Organism deadOrganism) {
        this.organismWorld_.messages.push(deadOrganism.name_ + " is killed by " + this.name_);
        deadOrganism.organismWorld_.tmp = null;
    }

    int getHumanMoveX() {
        return this.organismWorld_.humanControl.getMoveX();
    }

    int getHumanMoveY() {
        return this.organismWorld_.humanControl.getMoveY();
    }
    void setHumanSuperPower() {
        this.organismWorld_.humanControl.setHumanSuperPower();
    }
    void decreaseSuperPowerDuration() {
        this.organismWorld_.humanControl.decreaseSuperPowerDuration();
    }
    void decreaseSuperPowerCooldown() {
        this.organismWorld_.humanControl.decreaseSuperPowerCooldown();
    }
    boolean durationPowerStatus() {
        return this.organismWorld_.humanControl.durationPowerStatus();
    }
    boolean cooldownPowerStatus() {
        return this.organismWorld_.humanControl.cooldownPowerStatus();
    }
    void animalBirthMessage() {
        this.organismWorld_.messages.push("A new " + this.name_ + " is born!");
    }
    void plantGrowMessage() {
        this.organismWorld_.messages.push("A new " + this.name_ + " has grown!");
    }
    void humanPowerkills(String name) {
        this.organismWorld_.messages.push(name + " is killed by human power");
    }
    int defence(Container world_organisms, Organism organisms_array[][], int move_x, int move_y) { // Walka organizmow

        if (organisms_array[this.posX_ - move_x][this.posY_ - move_y].strength_ >= this.strength_) { // Ginie organizm broniacy sie
            this.organismWorld_.messages.push(this.name_ + " is killed by "+ organisms_array[this.posX_ - move_x][this.posY_ - move_y].name_);
            organisms_array[this.posX_ - move_x][this.posY_ - move_y].posX_ = this.posX_;
            organisms_array[this.posX_ - move_x][this.posY_ - move_y].posY_ = this.posY_;
            organisms_array[this.posX_][this.posY_] = organisms_array[this.posX_ - move_x][this.posY_ - move_y];
            organisms_array[this.posX_ - move_x][this.posY_ - move_y] = null;
            organismWorld_.worldOrganisms.delete_element(this);
            return 0;
        }

        if (organisms_array[this.posX_ - move_x][this.posY_ - move_y].strength_ < this.strength_) {		// Ginie organizm atakujacy
            this.organismWorld_.messages.push(organisms_array[this.posX_ - move_x][this.posY_ - move_y].name_ + " is killed by " + this.name_);
            organismWorld_.worldOrganisms.delete_element(organisms_array[this.posX_ - move_x][this.posY_ - move_y]);
            organisms_array[this.posX_ - move_x][this.posY_ - move_y] = null;
            organismWorld_.tmp = null;
            return 0;
        }
        return 0;
    }
    void setWorld(World organismWorld) {
        this.organismWorld_ = organismWorld;
    }
    int getHeight() {
        return organismWorld_.getHeight();
    }
    int getWeight() {
        return this.organismWorld_.getWeight();
    }

/*
    void Organism::operator =(const Organism* org) {
        this->strength_ = org->strength_;
        this->initiative_ = org->initiative_;
        this->age_ = org->age_;
        this->name_ = org->name_;
        this->posX_ = org->posX_;
        this->posY_ = org->posY_;
        this->organismWorld_ = org->organismWorld_;
    }

*/
}
