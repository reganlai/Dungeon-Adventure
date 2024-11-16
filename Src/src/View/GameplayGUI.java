package View;

import javax.swing.*;
import java.awt.*;

public class GameplayGUI extends JPanel {

    private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 500;

    private String myName;
    private int myHero;
    private int myDifficulty;
    private JPanel myPanel;
    private JLabel myGameplay;
    private JTextArea myInventory;
    private JLabel myInstructions;
    private int myCurrentHealthPotions;
    private int myCurrentVisionPotions;
    private int myCurrentPillars;
    private JMenuBar myMenubar;

    public GameplayGUI(final String theName,
                       final int theHero,
                       final int theDifficulty,
                       final JPanel thePanel) {
        setLayout(null);
        myName = theName;
        myHero = theHero;
        myDifficulty = theDifficulty;
        myPanel = thePanel;
        myGameplay = new JLabel();
        myInventory = new JTextArea();
        myInstructions = new JLabel();
        myCurrentHealthPotions = 0;
        myCurrentVisionPotions = 0;
        myCurrentPillars = 0;
        myMenubar = new JMenuBar();
        setGameplay(theHero);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }

    private void setGameplay(final int theHero) {
        myPanel.setVisible(true);
        myGameplay.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
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
