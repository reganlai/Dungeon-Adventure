package View;

import javax.swing.*;
import java.awt.*;

public class ExitGUI extends JPanel {
    private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 500;
    private final JFrame myMainFrame;
    private final CardLayout myCardLayout;
    private final JPanel myCardPanel;
    private final int myClass;

    private JLabel myBackgroundImage;
    private JLabel myYesLabel;
    private JLabel myNoLabel;

    public ExitGUI(final JFrame theMainFrame,
                   final CardLayout theCardLayout,
                   final JPanel theCardPanel,
                   final int theClass) {
        setLayout(null);
        myMainFrame = theMainFrame;
        myCardLayout = theCardLayout;
        myCardPanel = theCardPanel;
        myClass = theClass;

        myBackgroundImage = new JLabel();
        myYesLabel = new JLabel();
        myNoLabel = new JLabel();


        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setBackgroundImage();
        setVisible(true);
    }

    private void setBackgroundImage() {
        myBackgroundImage.setBounds(0, 0, FRAME_WIDTH, FRAME_WIDTH);
        if (myClass == 0) {
            ImageIcon thief = new ImageIcon("images/thiefwon.png");
            Image scaledThief = thief.getImage().getScaledInstance(FRAME_WIDTH, FRAME_HEIGHT, Image.SCALE_SMOOTH);
            myBackgroundImage.setIcon(new ImageIcon(scaledThief));
        } else if (myClass == 1) {
            ImageIcon warrior = new ImageIcon("images/warriorwon.png");
            Image scaledWarrior = warrior.getImage().getScaledInstance(FRAME_WIDTH, FRAME_HEIGHT, Image.SCALE_SMOOTH);
            myBackgroundImage.setIcon(new ImageIcon(scaledWarrior));
        } else {
            ImageIcon priestess = new ImageIcon("images/priestesswon.png");
            Image scaledPriestess = priestess.getImage().getScaledInstance(FRAME_WIDTH, FRAME_HEIGHT, Image.SCALE_SMOOTH);
            myBackgroundImage.setIcon(new ImageIcon(scaledPriestess));
        }
        myBackgroundImage.setVisible(true);
        add(myBackgroundImage);
    }

    private void setYesLabel() {

    }

    private void setNoLabel() {

    }

}
