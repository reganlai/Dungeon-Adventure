package View;

import Model.*;

import javax.swing.*;
import java.awt.*;

public class GameplayGUI extends JPanel {

    private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 500;

    private String myName;
    private int myClass;
    private int myDifficulty;
    private JPanel myPanel;
    private JLabel myGameplay;
    private JTextArea myInventory;
    private JLabel myMessage;
    private JLabel mySecondMessage;
    private JLabel myUpArrow;
    private JLabel myDownArrow;
    private JLabel myRightArrow;
    private JLabel myLeftArrow;
    private JMenuItem myInstructions;
    private JMenuItem myControls;
    private JMenuBar myMenubar;
    private Hero myHero;

    public GameplayGUI(final String theName,
                       final int theClass,
                       final int theDifficulty,
                       final JPanel thePanel,
                       JMenuItem theInstructions,
                       JMenuItem theControls) {
        setLayout(null);
        myName = theName;
        myClass = theClass;
        myDifficulty = theDifficulty;
        myPanel = thePanel;
        myGameplay = new JLabel();
        myInventory = new JTextArea();
        myMessage = new JLabel();
        mySecondMessage = new JLabel();
        myUpArrow = new JLabel();
        myDownArrow = new JLabel();
        myRightArrow = new JLabel();
        myLeftArrow = new JLabel();
        myMenubar = new JMenuBar();
        myInstructions = theInstructions;
        myControls = theControls;
        setMyMessage();
        setArrows();
        setHero(theClass);
        setInstructions();
        setControls();
        MazeGenerator maze = new MazeGenerator(6,6);
        maze.toString();
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }

    private void setHero(final int theHero) {
        myPanel.setVisible(true);
        myGameplay.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        if (theHero == 0) {
            myGameplay.setIcon(new ImageIcon("images/thief_in_dungeon.png"));
            //myHero = new Thief(myDifficulty);
        } else if (theHero == 1) {
            myGameplay.setIcon(new ImageIcon("images/warrior_in_dungeon.png"));
            //myHero = new Warrior(myDifficulty);
        } else {
            myGameplay.setIcon(new ImageIcon("images/priestess_in_dungeon.png"));
            //myHero = new Priestess(myDifficulty);
        }
        myGameplay.setOpaque(true);
        add(myGameplay);
    }


    private void setMyMessage() {
        myMessage.setText("Welcome to the dungeon, " + myName);
        mySecondMessage.setText("You may use the buttons on the right or arrow keys to move");
        myMessage.setBounds(370, 370, 500, 30);
        mySecondMessage.setBounds(320, 385, 500, 30);
        myMessage.setForeground(Color.WHITE);
        mySecondMessage.setForeground(Color.WHITE);
        myMessage.setVisible(true);
        mySecondMessage.setVisible(true);
        add(myMessage);
        add(mySecondMessage);
    }

    private void setArrows() {
        myUpArrow.setIcon(new ImageIcon("images/up.png"));
        myUpArrow.setBounds(820, 230, 60, 60);
        myRightArrow.setIcon(new ImageIcon("images/right.png"));
        myRightArrow.setBounds(880, 290, 60, 60);
        myDownArrow.setIcon(new ImageIcon("images/down.png"));
        myDownArrow.setBounds(820, 350, 60, 60);
        myLeftArrow.setIcon(new ImageIcon("images/left.png"));
        myLeftArrow.setBounds(760, 290, 60, 60);
        myUpArrow.setOpaque(false);
        myRightArrow.setOpaque(false);
        myDownArrow.setOpaque(false);
        myLeftArrow.setOpaque(false);
        add(myUpArrow);
        add(myRightArrow);
        add(myDownArrow);
        add(myLeftArrow);
    }

    private void setInstructions() {
        myInstructions.addActionListener(e ->{
            JOptionPane.showMessageDialog(null,
                    "You are lost in a dungeon, you must collect the four sacred pillars of \n" +
                    "object-oriented programming and navigate to the exit. \n" +
                            "Throughout the dungeon, you may need to fight monsters or pick \n" +
                            "up items that aid your journey. Good luck adventurer!",
                    "Instructions",
                    JOptionPane.INFORMATION_MESSAGE);
        });
    }

    private void setControls() {
        myControls.addActionListener(e ->{
            JOptionPane.showMessageDialog(null,
                    "You may click the arrows on the screen or the \n" +
                            "arrow keys on your keyboard to move around the dungeon.",
                    "Controls",
                    JOptionPane.INFORMATION_MESSAGE);
        });
    }


}
