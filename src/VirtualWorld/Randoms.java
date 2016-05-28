package VirtualWorld;
import java.util.Random;

/**
 * Created by Kuba on 2016-05-10.
 */
public class Randoms {
        int Rand_pos_x(int weight) {
            Random generator = new Random();
            int pos_x = generator.nextInt(weight);
            return pos_x;
        }

        int Rand_pos_y(int height) {
            Random generator = new Random();
            int pos_y = generator.nextInt(height);
            return pos_y;
        }

        int Rand_move_x() {
            Random generator = new Random();
            int move_x = generator.nextInt(3)- 1;
            return move_x;
        }

        int Rand_move_y() {
            Random generator = new Random();
            int move_y = generator.nextInt(3)- 1;
            return move_y;
        }

        boolean Rand_reproduce() {
            Random generator = new Random();
            int reproduce_chance = generator.nextInt(101);
            if (reproduce_chance <= 3)
                return true;
            else return false;
        }

        int randOrganism() {
            Random generator = new Random();
            int randOrg = generator.nextInt(10);
            return randOrg;
        }

        boolean turtleMove() {
            Random generator = new Random();
            int reproduce_chance = generator.nextInt(101);
            if (reproduce_chance <= 75)
                return false;
            else return true;
        }

        boolean antelopeEscape() {
            Random generator = new Random();
            int reproduce_chance = generator.nextInt(101);
            if (reproduce_chance <= 50)
                return true;
            else return false;
        }
    }
