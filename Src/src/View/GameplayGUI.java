package View;

import Model.Hero;
import Model.Priestess;
import Model.Thief;
import Model.Warrior;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Class where all the actual gameplay happens.
 *
 * @author Regan Lai
 * @version 1.0
 */
public class GameplayGUI extends JPanel {

    /** Screen size, screen is not resizable*/
    private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 500;

    /** Name that user entered in SettingsGUI*/
    private String myName;

    /** Hero class that user picked in SettingsGUI, 0 is thief, 1 is warrior, 2 is priestess*/
    private int myClass;

    /** Difficulty level that user picked in SettingsGUI, 0 is easy, 1 is normal, 2 is difficult*/
    private int myDifficulty;

    private JPanel myPanel;

    /** Main label that is responsible for showing the Gameplay as an image*/
    private JLabel myGameplay;

    /** Arrow JLabels that are being displayed on the screen*/
    private JLabel myUpArrow;
    private JLabel myDownArrow;
    private JLabel myLeftArrow;
    private JLabel myRightArrow;

    /** The hero that the user chose*/
    private Hero myHero;

    /** Initial message that are shown to user, myMessage will be modified according to
     *  user actions.
     *  mySecond message will show same message at the beginning of the gameplay and hides
     *  after first user move.
     */
    private JLabel myMessage;
    private JLabel mySecondMessage;

    private JFrame myMainFrame;
    private JMenuBar myMenubar;
    private JMenu myGameplayMenu;
    private JMenuItem myMap;
    private JMenuItem myHelp;
    private JMenuItem myInstructions;
    private JMenu myInventory;
    private JMenuItem myControls;

    /**
     * Constructs a GameplayGUI object.
     *
     * @param theFrame parent JFrame that holds his JPanel
     * @param thePanel the panel that holds the items in this class
     * @param theCard the CardLayout that holds this JPanel
     * @param theName user's name entered as a String
     * @param theClass an int that represents the hero class
     * @param theDifficulty an int that represents the difficulty of the game
     */
    public GameplayGUI(final JFrame theFrame,
                       final JPanel thePanel,
                       final CardLayout theCard,
                       final String theName,
                       final int theClass,
                       final int theDifficulty) {
        setLayout(null);
        myMainFrame = theFrame;
        myName = theName;
        myClass = theClass;
        myDifficulty = theDifficulty;
        myPanel = thePanel;
        myGameplay = new JLabel();
        myUpArrow = new JLabel();
        myDownArrow = new JLabel();
        myLeftArrow = new JLabel();
        myRightArrow = new JLabel();
        myMessage = new JLabel();
        mySecondMessage = new JLabel();
        myMenubar = new JMenuBar();
        myGameplayMenu = new JMenu("Gameplay");
        myMap = new JMenuItem("Map");
        myHelp = new JMenuItem("Help");
        myInstructions = new JMenuItem("Instructions");
        myInventory = new JMenu("Inventory");
        myControls = new JMenuItem("Controls");
        setArrows();
        setMyMessage();
        setGameplay();
        keyboardArrowClicked();
        setMenuBar();
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }

    /**
     * Sets the icon of myGameplay JLabel initially to the right image.
     */
    private void setGameplay() {
        myPanel.setVisible(true);
        myGameplay.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        if (myClass == 0) {
            myGameplay.setIcon(new ImageIcon("images/thief_in_dungeon.png"));
            myHero = new Thief(myName);
        } else if (myClass == 1) {
            myGameplay.setIcon(new ImageIcon("images/warrior_in_dungeon.png"));
            myHero = new Warrior(myName);
        } else {
            myGameplay.setIcon(new ImageIcon("images/priestess_in_dungeon.png"));
            myHero = new Priestess(myName);
        }
        myGameplay.setOpaque(true);
        add(myGameplay);
    }

    /**
     * Sets and displays the arrows on the screen.
     */
    private void setArrows() {
        myUpArrow.setIcon(new ImageIcon("images/up.png"));
        myUpArrow.setBounds(820, 260, 60, 60);
        myRightArrow.setIcon(new ImageIcon("images/right.png"));
        myRightArrow.setBounds(880, 320, 60, 60);
        myDownArrow.setIcon(new ImageIcon("images/down.png"));
        myDownArrow.setBounds(820, 380, 60, 60);
        myLeftArrow.setIcon(new ImageIcon("images/left.png"));
        myLeftArrow.setBounds(760, 320, 60, 60);
        myUpArrow.setOpaque(false);
        myRightArrow.setOpaque(false);
        myDownArrow.setOpaque(false);
        myLeftArrow.setOpaque(false);
        add(myUpArrow);
        add(myRightArrow);
        add(myDownArrow);
        add(myLeftArrow);
    }

    /**
     * Sets and displays two messages as JLabels on the screen.
     * myMessage welcomes the user to the dungeon.
     * mySecond message briefly instructs the user how to move around the dungeon.
     */
    private void setMyMessage() {
        myMessage.setText("Welcome to the dungeon, " + myName);
        mySecondMessage.setText("Start moving by clicking the arrows on the screen or on your keyboard");
        myMessage.setBounds(370, 370, 500, 30);
        mySecondMessage.setBounds(290, 385, 500, 30);
        myMessage.setForeground(Color.WHITE);
        mySecondMessage.setForeground(Color.WHITE);
        myMessage.setVisible(true);
        mySecondMessage.setVisible(true);
        add(myMessage);
        add(mySecondMessage);
    }

    /**
     * Adds action listener to keyboard arrows.
     */
    private void keyboardArrowClicked() {
        myMainFrame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                //System.out.println("Key Pressed: " + KeyEvent.getKeyText(e.getKeyCode()));
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    System.out.println("You pressed up!");
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    System.out.println("You pressed down!");
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    System.out.println("You pressed right!");
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    System.out.println("You pressed left!");
                }
            }
        });
        myMainFrame.setFocusable(true);
        myMainFrame.requestFocusInWindow();
    }

    private void upArrowClicked() {

    }

    private void downArrowClicked() {

    }

    private void rightArrowClicked() {

    }

    private void leftArrowClicked() {

    }

    private void setMenuBar() {
        myGameplayMenu.add(myMap);
        myGameplayMenu.addSeparator();
        myGameplayMenu.add(myInventory);
        myHelp.add(myInstructions);
        myHelp.add(myControls);
        myMenubar.add(myGameplayMenu);
        myMenubar.add(myHelp);
        myMainFrame.setJMenuBar(myMenubar);
    }

}
