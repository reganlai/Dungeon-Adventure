package View;

import Model.Hero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class YouLostGUI extends JPanel {
    private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 500;
    private final JFrame myMainFrame;
    private final CardLayout myCardLayout;
    private final JPanel myCardPanel;
    private final Hero myHero;

    private Image myBackgroundImage;
    private JLabel myBackground;
    private JLabel myYesLabel;
    private JLabel myNoLabel;

    public YouLostGUI(final JFrame theMainFrame,
                   final CardLayout theCardLayout,
                   final JPanel theCardPanel,
                   final Hero theHero) {
        setLayout(null);
        myMainFrame = theMainFrame;
        myCardLayout = theCardLayout;
        myCardPanel = theCardPanel;
        myHero = theHero;

        myBackgroundImage = myHero.getHeroLostImage().getImage().
                getScaledInstance(FRAME_WIDTH, FRAME_HEIGHT, Image.SCALE_SMOOTH);
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
        myBackground.setIcon(new ImageIcon(myBackgroundImage));

        myBackground.setVisible(true);
        add(myBackground);
    }

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
}
