package View;

import Model.DungeonCharacter;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.Timer;

public class FightScene extends JPanel {
    private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 500;
    /** The main frame shared across different classes to update the same frame.*/
    private final JFrame myMainFrame;
    /** The CardLayout that deals with the screen changing.*/
    private final CardLayout myCardLayout;
    /** The parent panel for all the screens. Used by the CardLayout.*/
    private final JPanel myCardPanel;
    /** The attack button. */
    private JButton myAttackButton;
    /** The block button. */
    private JButton myBlockButton;
    /** The super attack button that deals more damage. */
    private JButton mySuperAttack;
    /** The hero. */
    private DungeonCharacter myCharacter;
    /** The HP display. */
    private JLabel myHpDisplay;
    /** The label to display the fight comments. */
    private JLabel myActionCommentDisplay;


    protected FightScene(final JFrame theMainFrame, final DungeonCharacter theCharacter,
                         final CardLayout theCardLayout, final JPanel theCardPanel) {
        myMainFrame = theMainFrame;
        myCharacter = theCharacter;
        myCardLayout = theCardLayout;
        myCardPanel = theCardPanel;
        myAttackButton = new JButton("Attack");
        myBlockButton = new JButton("Block");
        mySuperAttack = new JButton("Super Attack");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);

        repaint();
        setVisible(true);


        new javax.swing.Timer(3000, e -> doneFight()).start();
    }
    private void doneFight() {
        myCardLayout.show(myCardPanel, "Game");
    }
    protected void paintComponent(Graphics g) {
        /**Placeholder code. For simulation purposes*/
        super.paintComponent(g); // Call the parent class's method to ensure proper painting
        // Cast to Graphics2D for more options
        Graphics2D g2d = (Graphics2D) g;

        // Set background color (optional)
        setBackground(Color.RED);

        // Draw a rectangle
        g2d.setColor(Color.BLUE);
        g2d.fillRect(50, 50, 100, 100);


        // Draw a string
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        g2d.drawString("Fighttttttt!", 50, 200);

        // Draw a line
        g2d.setColor(Color.GREEN);
        g2d.drawLine(50, 250, 300, 250);
    }

}
