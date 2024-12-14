package View;

import Controller.DungeonController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

public class SettingsPanel extends JPanel {
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
    private final DungeonGUI myMainFrame;
    private int myHeroSelection;
    private int myDifficultyLevel;

    private JLabel mySelectedHero;
    private JLabel mySelectedHeroBorder;
    private JLabel myNameLabel;
    private JButton myLoadButton;
    private JTextField myNameField;
    private JLabel myHeroClassLabel;
    private JComboBox myHeroBox;
    private JLabel myDifficultyLabel;
    private JComboBox myDifficultyBox;
    private JButton myReadyButton;
    private String myPlayerName;
    private PropertyChangeSupport myPcs;


    public SettingsPanel(final DungeonGUI theMainFrame) {
        super();
        myMainFrame = theMainFrame;
        myPcs = new PropertyChangeSupport(this);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLayout(null);
        init();
        setVisible(true);
        revalidate();
        repaint();
    }

    private void init() {
        myHeroClassLabel = new JLabel();
        myNameField = new JTextField();
        mySelectedHero = new JLabel();
        mySelectedHeroBorder = new JLabel();
        myNameLabel = new JLabel();
        myHeroBox = new JComboBox(HERO_CLASSES);
        myDifficultyLabel = new JLabel();
        myDifficultyBox = new JComboBox(DIFFICULTY_CHOICES);
        myReadyButton = new JButton("I'm ready");
        myLoadButton = new JButton("Load");
        initOptions();
    }

    private void initOptions() {
        setSettingsButton();
        setSelectedHero();
        setSelectedHeroBorder();
        setNameLabel();
        setNameField();
        setHeroClassLabel();
        setHeroBox();
        setDifficultyLabel();
        setDifficultyBox();
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
    public void setSettingsButton() {
        myLoadButton.setBounds(350, 305, 100, LABEL_HEIGHT);
        myLoadButton.setFont(new Font("Arial", Font.BOLD, 20));
        myLoadButton.addActionListener(event -> {
            myPcs.firePropertyChange("Load", null, null);
        });

        myReadyButton.setBounds(X_COORDINATE, 360, 300, 50);
        //myReadyButton.setText("I'm ready");
        myReadyButton.setFont(new Font("Arial", Font.BOLD, 20));
        myReadyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myPlayerName = myNameField.getText();
                myHeroSelection = myHeroBox.getSelectedIndex();
                myDifficultyLevel = myDifficultyBox.getSelectedIndex();
                myMainFrame.handleSettingsInput(true);
            }
        });
        myReadyButton.setVisible(true);
        //myLoadButton.setVisible(true);
        add(myLoadButton);
        add(myReadyButton);
        revalidate();
        repaint();
    }
    protected String getMyPlayerName() {
        return myPlayerName;
    }
    protected int getHeroSelection() {
        return myHeroSelection;
    }
    protected int getDifficultyLevel() {
        return myDifficultyLevel;
    }
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        myPcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        myPcs.removePropertyChangeListener(listener);
    }

    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D graphics = (Graphics2D) theGraphics;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        Image backgroundImage = new ImageIcon(BACKGROUND_IMAGE_PATH).getImage();
        theGraphics.drawImage(backgroundImage, 0, 0, FRAME_WIDTH, FRAME_HEIGHT, this);
    }
}
