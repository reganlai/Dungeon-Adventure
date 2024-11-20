package View;

import Model.MazeGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsGUI extends JPanel {

    private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 500;
    private static final int X_COORDINATE = 150;
    private static final int LABEL_WIDTH = 150;
    private static final int LABEL_HEIGHT = 30;
    private static final String BACKGROUND_IMAGE_PATH = "images/backgroundimage.png";
    private static final String WARRIOR_IMAGE_PATH = "images/warrior.png";
    private static final String THIEF_IMAGE_PATH = "images/thief.png";
    private static final String PRIESTESS_IMAGE_PATH = "images/priestess.png";
    private static final String HERO_CLASSES[] =
            {"Thief", "Warrior", "Priestess"};
    private static final String DIFFICULTY_CHOICES[] =
            {"Easy", "Normal", "Difficult"};

    private final JLabel mySelectedHero = new JLabel();;
    private final JLabel mySelectedHeroBorder = new JLabel();;
    private final JLabel myNameLabel = new JLabel();
    private JTextField myNameField = new JTextField();;
    private JLabel myHeroClassLabel = new JLabel();;
    private final JComboBox myHeroBox = new JComboBox(HERO_CLASSES);;
    private final JLabel myDifficultyLabel = new JLabel();;
    private final JComboBox myDifficultyBox = new JComboBox(DIFFICULTY_CHOICES);;
    private final JButton myReadyButton = new JButton("I'm ready");;

    private JPanel myCardPanel;

    private CardLayout myCardLayout;
    private JLabel myBackgroundImage = new JLabel();;
    private JFrame myMainFrame;


    public SettingsGUI(final JFrame theMainFrame, final JPanel theCardPanel, final CardLayout theCardLayout) {
        super();
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLayout(null);
        myMainFrame = theMainFrame;
        myCardPanel = theCardPanel;
        myCardLayout = theCardLayout;

        initSettings();
        revalidate();
        repaint();
    }

    private void initSettings() {
        setSelectedHero();
        setSelectedHeroBorder();
        setNameLabel();
        setNameField();
        setHeroClassLabel();
        setHeroBox();
        setDifficultyLabel();
        setDifficultyBox();
        setBackgroundImage();
        setReadyButton();
    }

    private void setSelectedHero() {
        mySelectedHero.setBounds(650, 40, 200, 370);
        mySelectedHero.setOpaque(true);
        mySelectedHero.setIcon(new ImageIcon(WARRIOR_IMAGE_PATH));
        mySelectedHero.setVisible(true);
        add(mySelectedHero);
    }

    private void setSelectedHeroBorder() {
        mySelectedHeroBorder.setBounds(640, 30, 220, 390);
        mySelectedHeroBorder.setOpaque(true);
        mySelectedHeroBorder.setBackground(Color.BLACK);
        mySelectedHeroBorder.setVisible(true);
        add(mySelectedHeroBorder);
    }

    private void setNameLabel() {
        myNameLabel.setBounds(X_COORDINATE, 80, LABEL_WIDTH, LABEL_HEIGHT);
        myNameLabel.setText("Your name:");
        myNameLabel.setForeground(Color.WHITE);
        myNameLabel.setVisible(true);
        add(myNameLabel);
    }

    private void setNameField() {
        myNameField.setBounds(X_COORDINATE, 105, LABEL_WIDTH, LABEL_HEIGHT);
        myNameField.setText("BravePotato6000");
        myNameField.setVisible(true);
        add(myNameField);
    }

    private void setHeroClassLabel() {
        myHeroClassLabel.setBounds(X_COORDINATE, 180, LABEL_WIDTH, LABEL_HEIGHT);
        myHeroClassLabel.setText("Choose your hero:");
        myHeroClassLabel.setForeground(Color.WHITE);
        myHeroClassLabel.setVisible(true);
        add(myHeroClassLabel);
    }

    private void setHeroBox() {
        myHeroBox.setBounds(X_COORDINATE, 205, LABEL_WIDTH, LABEL_HEIGHT);
        myHeroBox.setSelectedIndex(1);
        myHeroBox.addActionListener(e ->{
            int selectedIndex = myHeroBox.getSelectedIndex();
            changeHeroImage(selectedIndex);
        });
        myHeroBox.setVisible(true);
        add(myHeroBox);
    }

    private void changeHeroImage(final int theIndex) {
        if (theIndex == 1) {
            mySelectedHero.setIcon(new ImageIcon(WARRIOR_IMAGE_PATH));
        } else if (theIndex == 2) {
            mySelectedHero.setIcon(new ImageIcon(PRIESTESS_IMAGE_PATH));
        } else {
            mySelectedHero.setIcon(new ImageIcon(THIEF_IMAGE_PATH));
        }
    }

    private void setDifficultyLabel() {
        myDifficultyLabel.setBounds(X_COORDINATE, 280, LABEL_WIDTH, LABEL_HEIGHT);
        myDifficultyLabel.setText("Select game difficulty:");
        myDifficultyLabel.setForeground(Color.WHITE);
        myDifficultyLabel.setVisible(true);
        add(myDifficultyLabel);
    }

    private void setDifficultyBox() {
        myDifficultyBox.setBounds(X_COORDINATE, 305, LABEL_WIDTH, LABEL_HEIGHT);
        myDifficultyBox.setSelectedIndex(1);
        myDifficultyBox.setVisible(true);
        add(myDifficultyBox);
    }

    public void setReadyButton() {
        myReadyButton.setBounds(X_COORDINATE, 360, 300, 50);
        //myReadyButton.setText("I'm ready");
        myReadyButton.setFont(new Font("Arial", Font.BOLD, 20));
        myReadyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final String playerName = myNameField.getText();
                final int heroClass = myHeroBox.getSelectedIndex();
                final int difficultyLevel = myDifficultyBox.getSelectedIndex();
                showAnotherPanel(playerName, heroClass, difficultyLevel);

            }
        });
        myReadyButton.setVisible(true);
        add(myReadyButton);
        revalidate();
        repaint();
    }


    private void showAnotherPanel(final String theName, final int theHero,
                                  final int theDifficulty) {

        GameplayGUI gamePanel = new GameplayGUI(theName, theHero, theDifficulty, myMainFrame, myCardPanel);
        myCardPanel.add(gamePanel, "Game");
        myCardLayout.show(myCardPanel, "Game");

//        myMainFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
//        myMainFrame.remove(this);

//        myMainFrame.add(anotherPanel);
//        myMainFrame.revalidate();
//        myMainFrame.repaint();
    }

    private void setBackgroundImage() {
        myBackgroundImage.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        myBackgroundImage.setIcon(new ImageIcon(BACKGROUND_IMAGE_PATH));
        add(myBackgroundImage);
    }

}
