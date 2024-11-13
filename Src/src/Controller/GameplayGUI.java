package Controller;

import javax.swing.*;
import java.awt.*;

public class GameplayGUI extends JPanel {

    private String myName;
    private int myHero;

    public GameplayGUI(final String theName, final int theHero, final int theDifficulty) {
        myName = theName;
        myHero = theHero;
        setLayout(null);
        System.out.println(theName);
        System.out.println(theHero);
        System.out.println(theDifficulty);

        setSize(1000, 500);

        JLabel welcomeMessage = new JLabel("Welcome to the dungeon, " + theName + "!");
        welcomeMessage.setBounds(300, 200, 400, 50);
        add(welcomeMessage);

        // Additional setup for gameplay...
    }
}
