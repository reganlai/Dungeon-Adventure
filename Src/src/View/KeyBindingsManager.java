package View;

import Controller.DungeonController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.Serializable;

public class KeyBindingsManager {

    private final static int UP = 0;
    private final static int DOWN = 1;
    private final static int RIGHT = 2;
    private final static int LEFT = 3;


    private final GameplayPanel myPanel;
    private final DungeonController myController;
    private JLabel myUpArrow;
    private JLabel myDownArrow;
    private JLabel myRightArrow;
    private JLabel myLeftArrow;

    protected KeyBindingsManager(final GameplayPanel thePanel, final DungeonController theController) {
        myController = theController;
        myPanel = thePanel;
        setArrows();
    }
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
    }
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
    private void moving(final int theDir) {
        System.out.println("Now moving");
        myController.move(theDir);
    }
}
