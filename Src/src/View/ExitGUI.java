package View;

import Controller.DungeonController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ExitGUI extends JPanel {
    private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 500;
    private final DungeonGUI myMainFrame;

    private final DungeonController myController;

    private Image myBackgroundImage;
    private JLabel myBackground;
    private JLabel myYesLabel;
    private JLabel myNoLabel;
//    private String myGameResult;

//    public ExitGUI(final JFrame theMainFrame,
//                   final CardLayout theCardLayout,
//                   final JPanel theCardPanel,
//                   final Hero theHero,
//                   final String theGameResult) {
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

    private void setBackgroundImage() {
        myBackground.setBounds(0, -300, FRAME_WIDTH, FRAME_WIDTH);
        myBackground.setVisible(true);
        add(myBackground);
    }

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

    public void setGameResult(final String theResults) {
        myBackgroundImage = myController.getHeroImageBasedOnResults(theResults).getImage().
                getScaledInstance(FRAME_WIDTH, FRAME_HEIGHT, Image.SCALE_SMOOTH);

        myBackground.setIcon(new ImageIcon(myBackgroundImage));
    }
}
