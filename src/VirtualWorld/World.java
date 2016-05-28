package VirtualWorld;
import VirtualWorld.Container;
import VirtualWorld.Organism;
import VirtualWorld.Messages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.util.*;
import java.util.List;
import java.nio.file.*;
import java.nio.charset.Charset;
import java.io.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.EventQueue;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;
import java.lang.reflect.Constructor;

/**
 * Created by Kuba on 2016-05-09.
 */
public class World implements java.io.Serializable {
    transient Container worldOrganisms = new Container();
    transient List_element tmp = new List_element();
    private transient Organism organismsArray[][];
    transient Organism org;
    Human_Control humanControl = new Human_Control();
    Messages messages = new Messages();
    private World worlds = this;
    private transient boolean gameStatus_;
    private int height_;
    private int weight_;
    private int countOrganisms;
    private int clickedPosX;
    private int clickedPosY;
World(){}
    World(int height, int weight) {
        Randoms randomOrganism = new Randoms();
        worlds = this;
        this.gameStatus_ = true;
        this.height_ = height;
        this.weight_ = weight;

        organismsArray = new Organism[weight][];
        for (int i = 0; i < weight; i++) {
            organismsArray[i] = new Organism[height];
        }
        this.fillArray();

        new Human(this, worldOrganisms, organismsArray);

        for (int i = 0; i < 15; i++) {
            int organismNumber = randomOrganism.randOrganism();
            switch (organismNumber) {
                case 0: {
                    new Wolf(this, worldOrganisms, organismsArray);
                    break;
                }
                case 1: {
                    new Sheep(this, worldOrganisms, organismsArray);
                    break;
                }
                case 2: {
                    new Fox(this, worldOrganisms, organismsArray);
                    break;
                }
                case 3: {
                    new Turtle(this, worldOrganisms, organismsArray);
                    break;
                }
                case 4: {
                    new Antelope(this, worldOrganisms, organismsArray);
                    break;
                }
                case 5: {
                    new Grass(this, worldOrganisms, organismsArray);
                    break;
                }
                case 6: {
                    new Dandelion(this, worldOrganisms, organismsArray);
                    break;
                }
                case 7: {
                    new Guarana(this, worldOrganisms, organismsArray);
                    break;
                }
                case 8: {
                    new Berries(this, worldOrganisms, organismsArray);
                    break;
                }
                default:
                    break;
            }
        }
        //this->scribeWorld();
    }

    void makeTour() {
        List_element tmp_dead = new List_element();
        this.tmp = worldOrganisms.head;
        while (this.tmp != null) {
            tmp_dead = this.tmp.next;
            this.tmp.organism.action(worldOrganisms, organismsArray);
            if (this.tmp == null)
                this.tmp = tmp_dead;
            else
                this.tmp = this.tmp.next;
        }
        // this->scribeWorld();
    }

    void fillArray() {

        for (int i = 0; i < this.weight_; i++) {
            for (int j = 0; j < this.height_; j++) {
                this.organismsArray[i][j] = null;
            }
        }
    }

    class Interface extends JFrame{
        JPanel mainPanel;
        JFrame mainFrame;
        Image image;
        String[] data = { "Antelope", "Fox", "Sheep", "Turtle", "Wolf", "Berries", "Dandelion", "Grass" , "Guarana"};
        JList list = new JList(data);

        Interface(int weight, int height) { // podawac rozmary int weight, int height
            mainFrame = new JFrame("Virtual World"); // tytul okna
            mainFrame.setSize((weight + 10) * 35, (height + 5) * 35);// rozmiary okna
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setResizable(false);
            mainPanel = new JPanel();
            mainPanel.setLayout(null); // ustawiac na wys i szer
          //  mainPanel.setBackground(Color.GRAY);
            mainFrame.add(mainPanel);
            repaintWorld();
            mainFrame.add(list,BorderLayout.EAST);
            new ButtonPanel();
            mainFrame.setVisible(true);
            JPanel obrazPanel = new drawImage();
            mainFrame.add(obrazPanel);
            MouseControl mouse = new MouseControl();
            mainFrame.add(mouse);
            animalsList();
            keyControl();

        }

        public class ButtonPanel extends JPanel{
            public ButtonPanel() {
                JButton buttonTour = new JButton("Make Tour");
                buttonTour.setBounds(0, height_ * 35, weight_ * 35 + 1, 20);
                JButton buttonSave = new JButton("Save Game");
                buttonSave.setBounds(0, (height_ + 1) * 35, weight_ * 35 / 2, 20);
                JButton buttonLoad = new JButton("Load Game");
                buttonLoad.setBounds(weight_ * 35 / 2, (height_ + 1) * 35, weight_ * 35 / 2, 20);
                buttonTour.addActionListener(new makeTourButton());
                buttonSave.addActionListener(new makeSaveButton());
                buttonLoad.addActionListener(new makeLoadButton());
                mainPanel.add(buttonTour); // dodanie przycisku
                mainPanel.add(buttonSave);
                mainPanel.add(buttonLoad);
            }
            class makeTourButton extends JButton implements ActionListener {
                makeTourButton() {
                    addActionListener(this);
                }

                @Override
                public void actionPerformed(ActionEvent e) {
                    makeTour();
                    repaintWorld();
                }
            }

            class makeSaveButton extends JButton implements ActionListener {
                makeSaveButton() {
                    addActionListener(this);
                }

                @Override
                public void actionPerformed(ActionEvent e) {
                    SaveGame();
                    messages.push("Game saved");
                    repaintWorld();
                }
            }

            class makeLoadButton extends JButton implements ActionListener {
                makeLoadButton() {
                    addActionListener(this);
                }

                @Override
                public void actionPerformed(ActionEvent e) {
                    LoadGame();
                    messages.push("Game loaded");
                    mainFrame.setSize((weight_ + 10) * 35, (height_ + 5) * 35);
                    repaintWorld();
                }
            }
        }

        void keyControl() {
            mainPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), "forward");
            mainPanel.getActionMap().put("forward", new AbstractAction(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    humanControl.setMoveY(-1);
                    makeTour();
                    repaintWorld();
                    humanControl.deleteCoordinates();
                }
            });
            mainPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), "back");
            mainPanel.getActionMap().put("back", new AbstractAction(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    humanControl.setMoveY(1);
                    makeTour();
                    repaintWorld();
                    humanControl.deleteCoordinates();
                }
            });
            mainPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), "left");
            mainPanel.getActionMap().put("left", new AbstractAction(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    humanControl.setMoveX(-1);
                    makeTour();
                    repaintWorld();
                    humanControl.deleteCoordinates();
                }
            });
            mainPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), "right");
            mainPanel.getActionMap().put("right", new AbstractAction(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    humanControl.setMoveX(1);
                    makeTour();
                    repaintWorld();
                    humanControl.deleteCoordinates();
                }
            });
            mainPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_E, 0), "power");
            mainPanel.getActionMap().put("power", new AbstractAction(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (humanControl . setHumanSuperPower()) {
                        messages.push("Super power used");
                    }
                    else {
                        messages.push("Your super power is not ready yet");
                    }
                }
            });
        }

           private void animalsList() {
               list.addListSelectionListener(new ListSelectionListener(){
                   @Override
                   public void valueChanged(ListSelectionEvent e)
                   {
                       if(list.getSelectedIndex() == 0){
                           if(organismsArray[clickedPosX][clickedPosY] == null)
                       new Antelope(worlds,  worldOrganisms, organismsArray, 4, 4, 1, data[0], clickedPosX, clickedPosY);
                   }
                       if(list.getSelectedIndex() == 1){
                           if(organismsArray[clickedPosX][clickedPosY] == null)
                           new Fox(worlds,  worldOrganisms, organismsArray, 3, 7, 1, data[1], clickedPosX, clickedPosY);
                       }
                       if(list.getSelectedIndex() == 2){
                           if(organismsArray[clickedPosX][clickedPosY] == null)
                           new Sheep(worlds,  worldOrganisms, organismsArray, 4, 4, 1, data[2], clickedPosX, clickedPosY);
                       }
                       if(list.getSelectedIndex() == 3){
                           if(organismsArray[clickedPosX][clickedPosY] == null)
                           new Turtle(worlds,  worldOrganisms, organismsArray, 2, 1, 1, data[3], clickedPosX, clickedPosY);
                       }
                       if(list.getSelectedIndex() == 4){
                           if(organismsArray[clickedPosX][clickedPosY] == null)
                           new Wolf(worlds,  worldOrganisms, organismsArray, 9, 5, 1, data[4], clickedPosX, clickedPosY);
                       }
                       if(list.getSelectedIndex() == 5){
                           if(organismsArray[clickedPosX][clickedPosY] == null)
                           new Berries(worlds,  worldOrganisms, organismsArray, 99, 0, 1, data[5], clickedPosX, clickedPosY);
                       }
                       if(list.getSelectedIndex() == 6){
                           if(organismsArray[clickedPosX][clickedPosY] == null)
                           new Dandelion(worlds,  worldOrganisms, organismsArray, 0, 0, 1, data[6], clickedPosX, clickedPosY);
                       }
                       if(list.getSelectedIndex() == 7){
                           if(organismsArray[clickedPosX][clickedPosY] == null)
                           new Grass(worlds,  worldOrganisms, organismsArray, 0, 0, 1, data[7], clickedPosX, clickedPosY);
                       }
                       if(list.getSelectedIndex() == 8){
                           if(organismsArray[clickedPosX][clickedPosY] == null)
                           new Guarana(worlds,  worldOrganisms, organismsArray, 0, 0, 1, data[8], clickedPosX, clickedPosY);
                       }
                       list.clearSelection();
                       mainFrame.setVisible(true);
                       mainPanel = new drawImage();
                       mainFrame.add(list,BorderLayout.EAST);
                       mainFrame.add(mainPanel);
                       ReadText();
                       mainPanel.validate();
                       mainPanel.repaint();
                   }
               });
             mainFrame.add(list,BorderLayout.EAST);
           }

        void SaveGame(){
            tmp = worldOrganisms.head;
            while (tmp!=null) {
                countOrganisms++;
                tmp = tmp.next;
            }
            try
            {
                FileOutputStream fileOut = new FileOutputStream("Save.txt");
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(worlds);
                tmp = worldOrganisms.head;
                while (tmp!=null) {
                    Organism org = tmp.organism;
                    out.writeObject(org);
                    tmp = tmp.next;
                }
                out.close();
                fileOut.close();
            }catch(IOException i){}
        }

        void LoadGame(){
            //Container con = new Container();
            worldOrganisms = new Container();
            Organism org = null;
            try {
                FileInputStream fileIn = new FileInputStream("Save.txt");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                worlds = (World) in.readObject();
                weight_ = worlds.weight_;
                height_ = worlds.height_;
                humanControl = worlds.humanControl;
                messages = worlds.messages;
                worlds .worldOrganisms = worldOrganisms;
                worlds. tmp = tmp;
                worlds. organismsArray = organismsArray;

                organismsArray = new Organism[weight_][];
                for (int i = 0; i < weight_; i++) {
                    organismsArray[i] = new Organism[height_];
                }
                fillArray();
                for (int i = 0; i < worlds.countOrganisms; i++){
                   org = (Organism) in.readObject();
                    worldOrganisms.insert_element(org);
                    organismsArray[org.getPosX()][org.getPosY()] = org;
                    organismsArray[org.getPosX()][org.getPosY()].organismWorld_ = worlds;
            }
                in.close();
                fileIn.close();
            }catch(IOException i)
            {
            }catch(ClassNotFoundException c)
            {}
        }

void ReadText(){
    int moveCoulmne = 0,messageNumber = 0;
    mainPanel.setLayout(null);
    while (!(messages.show(messageNumber) .equals(""))) {
        JLabel lbl = new JLabel(messages.show(messageNumber));
        mainPanel.add(lbl);
        lbl.setLocation((weight_ + 2) * 35, (height_ +2)*35 - moveCoulmne);
        lbl.setSize(300, 100);
        moveCoulmne+=10;
        messageNumber++;
    }
}
        public class MouseControl extends JPanel implements MouseListener
        {
            public  MouseControl() {
                mainFrame.addMouseListener(this);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                    clickedPosX = e.getX() / 35;
                    clickedPosY = e.getY() / 35;
            }

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}
        }

    class drawImage extends JPanel {
        private BufferedImage image;
        drawImage() {
            super();
            File imageFile = new File("wolf.jpg");
            try {
                image = ImageIO.read(imageFile);
            } catch (IOException e) {
                System.err.println("Blad odczytu obrazka");
                e.printStackTrace();
            }

            Dimension dimension = new Dimension(image.getWidth(), image.getHeight());
            setPreferredSize(dimension);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;
            tmp = worldOrganisms.head;
            while (tmp != null) {
                File imageFile = new File(tmp.organism.getName() + ".jpg");
                try {
                    image = ImageIO.read(imageFile);
                } catch (IOException e) {
                    System.err.println("Blad odczytu obrazka");
                    e.printStackTrace();
                }
                g2d.drawImage(image, tmp.organism.getPosX() * 35, tmp.organism.getPosY() * 35, this);
                tmp = tmp.next;
            }
            for (int i = 0; i < weight_; i++) {
            g2d.drawLine(i * 35, 0, i * 35, height_ * 35);
        }
        g2d.drawLine(weight_ * 35, 0, weight_ * 35, height_ * 35);
        for (int i = 0; i < height_; i++) {
            g2d.drawLine(0, i * 35, weight_ * 35, i * 35);
        }
        g2d.drawLine(0, height_ * 35, weight_ * 35, height_ * 35);
    }
    }
       void repaintWorld(){
           mainPanel = new drawImage();
           mainFrame.add(mainPanel);
           mainFrame.setVisible(true);
           mainPanel.validate();
           mainPanel.repaint();
           ReadText();
        }
}

    Container getContainer() {
        return this.worldOrganisms;
    }
    List_element getTmp() {
        return this.tmp;
    }
    Human_Control getHumanControl() {
        return this.humanControl;
    }
    boolean getGameStatus() {
        return gameStatus_;
    }
    int getHeight() {
        return this.height_;
    }
    int getWeight() {
        return this.weight_;
    }
    void putMessage(String statement) {
        this.messages.push(statement);
    }
}
