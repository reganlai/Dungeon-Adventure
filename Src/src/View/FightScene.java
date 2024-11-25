package View;

import Model.DungeonCharacter;

import javax.swing.*;
import java.awt.*;

public class FightScene extends JPanel {
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



    }

}
