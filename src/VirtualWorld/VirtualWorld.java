package VirtualWorld;
import VirtualWorld.World;
import VirtualWorld.InterfaceWorld;
import java.util.Scanner;


import javax.swing.JButton;
import javax.swing.JPanel;
/**
 * Created by Kuba on 2016-05-09.
 */
public class VirtualWorld {

    public static void main(String args[]) {
        Scanner keyboard = new Scanner(System.in);
        int height = 0, weight = 0;

        while (weight + height < 15) {
            System.out.println("Map height: ");
            height = keyboard.nextInt();
            System.out.println("Map width: ");
            weight = keyboard.nextInt();
        }
        World letsPlay = new World(height, weight);
        World.Interface cos = letsPlay.new Interface( weight,  height);
    }
}
