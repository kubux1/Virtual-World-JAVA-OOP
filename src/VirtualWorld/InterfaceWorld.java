package VirtualWorld;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.util.Scanner;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.applet.Applet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Created by Kuba on 2016-05-11.
 */
public class InterfaceWorld extends Applet {
    public JPanel mainPanel;
    public JFrame mainFrame;
    public InterfaceWorld() { // podawac rozmary int weight, int height
        mainFrame = new JFrame("Virtual World"); // tytul okba
        mainFrame.setVisible(true);
        mainFrame.setSize(600, 600);// rozmiary okna
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // mainFrame.setResizable(false);
        mainPanel = new JPanel();
        mainPanel.setLayout(null); // ustawiac na wys i szer
        mainPanel.setBackground(Color.GRAY);
        mainFrame.add(mainPanel);
        JButton button = new JButton("Make Tour");
        button.setBounds(0, 540, 600, 20);
        mainPanel.add(button); // dodanie przycisku
        button.addActionListener(new makeTourButton());
        // experimentLayout.addLayoutComponent(null,button);
        // experimentLayout.addLayoutComponent(null,panel);
        //prepareGUI();
    }

   class makeTourButton extends JButton implements ActionListener {
        makeTourButton() {
            super("Make Tour");
            addActionListener(this);
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            mainPanel.setBackground(Color.GREEN);
        }
    }
    public static void main(String[] args) {
        InterfaceWorld cos = new InterfaceWorld();
    }
}
