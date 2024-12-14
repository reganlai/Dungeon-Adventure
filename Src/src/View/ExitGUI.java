package View;

import Controller.DungeonController;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * GUI that is being shown when user lost fighting a monster or have won the game.
 *
 * @author Regan Lai
 * @version 1.0
 */
public class ExitGUI extends JPanel {

    /** JPanel width in pixels. */
    private static final int FRAME_WIDTH = 1000;

    /** JPanel height in pixels. */
    private static final int FRAME_HEIGHT = 500;

    /** DungeonGUI object. */
    private final DungeonGUI myMainFrame;

    /** DungeonController object. */
    private final DungeonController myController;

    /** Background image, decided based on whether user had won or not. */
    private Image myBackgroundImage;

    /** JLabel holding myBackgroundImage. */
    private JLabel myBackground;

    /** Starts new game when this JLabel is clicked. */
    private JLabel myYesLabel;

    /** Closes program when this JLabel is clicked. */
    private JLabel myNoLabel;

    /**
     * Initializes the GUI.
     * @param theMainFrame the JFrame that holds the panels
     * @param theController the controller of the program
     */
    public ExitGUI(final DungeonGUI theMainFrame, final DungeonController theController) {
        setLayout(null);
        myMainFrame = theMainFrame;
        myController = theController;
        myBackground = new JLabel();
        myYesLabel = new JLabel();
        myNoLabel = new JLabel();

        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setYesLabel();
        setNoLabel();
        setBackgroundImage();
        setVisible(true);
    }

    /**
     * Sets position of the background image JLabel.
     */
    private void setBackgroundImage() {
        myBackground.setBounds(0, -300, FRAME_WIDTH, FRAME_WIDTH);
        myBackground.setVisible(true);
        add(myBackground);
    }

    /**
     * Sets position of the "Yes" JLabel.
     */
    private void setYesLabel() {
        myYesLabel.setBounds(460, 380, 45, 30);
        myYesLabel.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                myMainFrame.start(false);
            }
        });
        myYesLabel.setVisible(true);
        add(myYesLabel);
    }

    /**
     * Sets position of the "No" JLabel.
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
     * Sets whether user had won or not and update myBackgroundImage accordingly
     * @param theResults whether the user had won or not
     */
    public void setGameResult(final String theResults) {
        myBackgroundImage = myController.getHeroImageBasedOnResults(theResults).getImage().
                getScaledInstance(FRAME_WIDTH, FRAME_HEIGHT, Image.SCALE_SMOOTH);

        myBackground.setIcon(new ImageIcon(myBackgroundImage));
    }
}
