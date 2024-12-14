package View;

import Controller.DungeonController;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Connects arrows displayed on screen to keyboard arrows and adds action listeners to them.
 *
 * @author George Njane
 * @author Regan Lai
 * @version 1.0
 */
public class KeyBindingsManager extends MouseAdapter {

    /** Int representation of UP. */
    private final static int UP = 0;

    /** Int representation of DOWN. */
    private final static int DOWN = 1;

    /** Int representation of RIGHT. */
    private final static int RIGHT = 2;

    /** Int representation of LEFT. */
    private final static int LEFT = 3;

    /** GameplayPanel. */
    private final GameplayPanel myPanel;

    /** Controller of the program. */
    private final DungeonController myController;

    /** Up arrow that allows user to move up. */
    private JLabel myUpArrow;

    /** Down arrow that allows user to move down. */
    private JLabel myDownArrow;

    /** Right arrow that allows user to move right. */
    private JLabel myRightArrow;

    /** Left arrow that allows user to move left. */
    private JLabel myLeftArrow;

    /**
     * Initializes the class
     * @param thePanel the gameplay panel
     * @param theController the controller of the program
     */
    protected KeyBindingsManager(final GameplayPanel thePanel, final DungeonController theController) {
        myController = theController;
        myPanel = thePanel;
        setArrows();
    }

    /**
     * Initializes the arrows and displays it to gameplay panel.
     */
    private void setArrows() {
        myUpArrow = new JLabel();
        myDownArrow = new JLabel();
        myRightArrow = new JLabel();
        myLeftArrow = new JLabel();

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
        myPanel.add(myUpArrow);
        myPanel.add(myRightArrow);
        myPanel.add(myDownArrow);
        myPanel.add(myLeftArrow);

        addMouseClickListener(myUpArrow, UP);
        addMouseClickListener(myDownArrow, DOWN);
        addMouseClickListener(myLeftArrow, LEFT);
        addMouseClickListener(myRightArrow, RIGHT);
    }

    /**
     * Adds mouse clicked listener to the arrows added to the panel.
     * @param theArrow the JLabel
     * @param theDirection the direction of arrow(the direction user wants to move)
     */
    private void addMouseClickListener(final JLabel theArrow, int theDirection) {
        theArrow.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                myController.move(theDirection);
            }
        });
    }

    /**
     * Adds key pressed listeners to arrow keys on keyboard.
     */
    public void keyboardArrowClicked() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(e -> {
            if (e.getID() == KeyEvent.KEY_PRESSED) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        moving(UP);
                        System.out.println("Moving up");
                        break;
                    case KeyEvent.VK_DOWN:
                        moving(DOWN);
                        System.out.println("Moving down");
                        break;
                    case KeyEvent.VK_LEFT:
                        moving(LEFT);
                        System.out.println("Moving left");
                        break;
                    case KeyEvent.VK_RIGHT:
                        moving(RIGHT);
                        System.out.println("Moving right");
                        break;
                }
            }
            return false; // Ensure other components can still process the key event
        });
    }

    /**
     * User moves in their chosen direction.
     */
    private void moving(final int theDir) {
        myController.move(theDir);
    }
}
