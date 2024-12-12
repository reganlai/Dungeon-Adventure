package View;

import Model.Hero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The ExitGUI class is a GUI that prompts the user whether they would like to continue
 * playing the game or not after either winning or losing.
 *
 * @author Regan Lai
 * @version 1.0
 */
public class ExitGUI extends JPanel {

    /** Width for JPanel*/
    private static final int FRAME_WIDTH = 1000;

    /** Height for JPanel*/
    private static final int FRAME_HEIGHT = 500;

    /** The main frame shared across different classes to update the same frame.*/
    private final JFrame myMainFrame;

    /** The CardLayout that deals with the screen changing.*/
    private final CardLayout myCardLayout;

    /** The parent panel for all the screens. Used by the CardLayout.*/
    private final JPanel myCardPanel;

    /** The hero that was chosen by the user to play this game.*/
    private final Hero myHero;

    /** The background image determined by whether the user had won.*/
    private Image myBackgroundImage;

    /** The JLabel that holds the background image(whether user had won or lost).*/
    private JLabel myBackground;

    /** Goes back to SettingsGUI and restarts the game when user clicks on this JLabel.*/
    private JLabel myYesLabel;

    /** Closes the program when user clicks on this JLabel.*/
    private JLabel myNoLabel;

    /** String that checks whether user had won or lost the game.*/
    private String myGameResult;

    /**
     * Initializes the GUI.
     * @param theMainFrame JFrame shared across different classes
     * @param theCardLayout CardLayout that deals with the screen changing
     * @param theCardPanel parent panel for all the screens
     * @param theHero the Hero chosen by the user
     * @param theGameResult whether user had won or lost
     */
    public ExitGUI(final JFrame theMainFrame,
                   final CardLayout theCardLayout,
                   final JPanel theCardPanel,
                   final Hero theHero,
                   final String theGameResult) {
        setLayout(null);
        myMainFrame = theMainFrame;
        myCardLayout = theCardLayout;
        myCardPanel = theCardPanel;
        myHero = theHero;
        myBackground = new JLabel();
        myYesLabel = new JLabel();
        myNoLabel = new JLabel();

        myGameResult = theGameResult;

        setMyBackgroundImage();
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setYesLabel();
        setNoLabel();
        setBackgroundImage();
        setVisible(true);
    }

    /**
     * Sets the position and size of the background image JLabel.
     */
    private void setBackgroundImage() {
        myBackground.setBounds(0, -300, FRAME_WIDTH, FRAME_WIDTH);
        myBackground.setVisible(true);
        add(myBackground);
    }

    /**
     * Sets the position and size of the "yes" JLabel.
     * Adds MouseListener to this JLabel.
     */
    private void setYesLabel() {
        myYesLabel.setBounds(460, 380, 45, 30);
        myYesLabel.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                myCardLayout.show(myCardPanel, "Settings");
            }
        });
        myYesLabel.setVisible(true);
        add(myYesLabel);
    }

    /**
     * Sets the position and size of the "no" JLabel.
     * Adds MouseListener to this JLabel.
     */
    private void setNoLabel() {
        myNoLabel.setBounds(520, 380, 40, 30);
        myNoLabel.setVisible(true);
        myNoLabel.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });
        add(myNoLabel);
    }

    /**
     * Sets the local field myGameResult
     * @param theResult whether user had won or lost
     */
    public void setGameResult(final String theResult) {
        myGameResult = theResult;
        setMyBackgroundImage();
    }

    /**
     * Sets the background image according to whether user had won
     */
    private void setMyBackgroundImage() {
        if (myGameResult.equals("Won")) {
            myBackgroundImage = myHero.getHeroWonImage().getImage().
                    getScaledInstance(FRAME_WIDTH, FRAME_HEIGHT, Image.SCALE_SMOOTH);
            myBackground.setIcon(new ImageIcon(myBackgroundImage));
        } else {
            myBackgroundImage = myHero.getHeroLostImage().getImage().
                    getScaledInstance(FRAME_WIDTH, FRAME_HEIGHT, Image.SCALE_SMOOTH);
            myBackground.setIcon(new ImageIcon(myBackgroundImage));
        }
    }
}
