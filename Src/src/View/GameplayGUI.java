package View;

import javax.swing.*;
import java.awt.*;

public class GameplayGUI extends JPanel {

    private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 500;

    private String myName;
    private int myHero;
    private int myDifficulty;
    private JLabel myGameplay;
    private JTextArea myInventory;
    private JLabel myInstructions;
    private int myCurrentHealthPotions;
    private int myCurrentVisionPotions;
    private int myCurrentPillars;

    public GameplayGUI(final String theName, final int theHero, final int theDifficulty) {
        setLayout(null);
        myName = theName;
        myHero = theHero;
        myDifficulty = theDifficulty;
        myGameplay = new JLabel();
        myInventory = new JTextArea();
        myInstructions = new JLabel();
        myCurrentHealthPotions = 0;
        myCurrentVisionPotions = 0;
        myCurrentPillars = 0;
        System.out.println(myName);
        System.out.println(myHero);
        System.out.println(myDifficulty);
        setGameplay(theHero);
        setInventory();
        setRobotImage();
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }

    private void setGameplay(final int theHero) {
        myGameplay.setBounds(0, 0, 750, 325);
        if (theHero == 0) {
            myGameplay.setIcon(new ImageIcon("images/thief_in_dungeon.png"));
        } else if (theHero == 1) {
            myGameplay.setIcon(new ImageIcon("images/warrior_in_dungeon.png"));
        } else {
            myGameplay.setIcon(new ImageIcon("images/priestess_in_dungeon.png"));
        }
        myGameplay.setOpaque(true);
        add(myGameplay);
    }

    private void setInventory() {
        myInventory.setBounds(750, 0, 300, 325);
        myInventory.append("Health potions x " + myCurrentHealthPotions + "\n");
        myInventory.append("Vision potions x " + myCurrentVisionPotions + "\n");
        myInventory.append("Pillars Collected: " + myCurrentHealthPotions + "/4\n");
        myInventory.setOpaque(true);
        add(myInventory);
    }

    private void setRobotImage() {
        myInstructions.setBounds(0, 325, FRAME_WIDTH, FRAME_HEIGHT);
        myInstructions.setIcon(new ImageIcon("images/robot.png"));
        myInstructions.setOpaque(true);
        add(myInstructions);
    }

    public int getNumberOfHealthPotions() {
        return myCurrentHealthPotions;
    }

    public int getNumberOfVisionPotions() {
        return myCurrentVisionPotions;
    }

    public int getNumberOfPillars() {
        return myCurrentPillars;
    }

    public void addCollectedHealthPotion() {
        myCurrentHealthPotions++;
    }

    public void addCollectedVisionPotion() {
        myCurrentVisionPotions++;
    }

    public void addCollectedPillar() {
        myCurrentPillars++;
    }
}
