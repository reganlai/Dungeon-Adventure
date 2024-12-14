package View;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * User chooses to either play a new game and its settings or loads previous game.
 *
 * @author George Njane
 * @autho Regan Lai
 * @version 1.0
 */
public class SettingsPanel extends JPanel {

    /** JPanel width in pixels. */
    private static final int FRAME_WIDTH = 1000;

    /** JPanel height in pixels. */
    private static final int FRAME_HEIGHT = 500;

    /** Common X coordinate for some components. */
    private static final int X_COORDINATE = 150;

    /** Common width in pixels for some components. */
    private static final int LABEL_WIDTH = 150;

    /** Common height in pixels for some components. */
    private static final int LABEL_HEIGHT = 30;

    /** String path for background image. */
    private static final String BACKGROUND_IMAGE_PATH = "images/backgroundimage.png";

    /** String path for warrior image. */
    private static final String WARRIOR_IMAGE_PATH = "images/warrior.png";

    /** String path for thief image. */
    private static final String THIEF_IMAGE_PATH = "images/thief.png";

    /** String path for priestess image. */
    private static final String PRIESTESS_IMAGE_PATH = "images/priestess.png";

    /** String array for user to choose their preferred hero class. */
    private static final String HERO_CLASSES[] =
            {"Thief", "Warrior", "Priestess"};

    /** String array for user to choose their preferred difficulty setting. */
    private static final String DIFFICULTY_CHOICES[] =
            {"Easy", "Normal", "Difficult"};

    /** The JFrame that holds the JPanels. */
    private final DungeonGUI myMainFrame;

    /** Int representation of the hero. */
    private int myHeroSelection;

    /** Int representation of the difficulty level. */
    private int myDifficultyLevel;

    /** JLabel that shows the image of the currently selected hero. */
    private JLabel mySelectedHero;

    /** JLabel that provides a border for mySelectedHero. */
    private JLabel mySelectedHeroBorder;

    /** JLabel that prompts user to enter their name. */
    private JLabel myNameLabel;

    /** JTextField that allows user to enter their name. */
    private JTextField myNameField;

    /** JLabel that prompts user to enter their preferred hero class. */
    private JLabel myHeroClassLabel;

    /** JComboBox that allows user to choose their preferred hero. */
    private JComboBox myHeroBox;

    /** JLabel that prompts user to enter their preferred difficulty label. */
    private JLabel myDifficultyLabel;

    /** JComboBox that allows user to choose their preferred difficulty. */
    private JComboBox myDifficultyBox;

    /** JButton that allows user to start a new game. */
    private JButton myReadyButton;

    /** String representation of user's name. */
    private String myPlayerName;

    /** JButton that allows user to load previously saved game. */
    private JButton myLoadButton;

    /** PropertyChangeSupport used for this class. */
    private PropertyChangeSupport myPcs;


    /**
     * Initializes the GUI.
     * @param theMainFrame the JFrame that holds the JPanels together
     */
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

    /**
     * Initializes the fields.
     */
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

    /**
     * Displays all the components to the JPanel.
     */
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

    /**
     * Displays JLabel that shows currently selected hero(image is warrior initially).
     */
    private void setSelectedHero() {
        mySelectedHero.setBounds(650, 40, 200, 370);
        mySelectedHero.setOpaque(true);
        mySelectedHero.setIcon(new ImageIcon(WARRIOR_IMAGE_PATH));
        mySelectedHero.setVisible(true);
        add(mySelectedHero);
    }

    /**
     * Displays the selected hero border.
     */
    private void setSelectedHeroBorder() {
        mySelectedHeroBorder.setBounds(640, 30, 220, 390);
        mySelectedHeroBorder.setOpaque(true);
        mySelectedHeroBorder.setBackground(Color.BLACK);
        mySelectedHeroBorder.setVisible(true);
        add(mySelectedHeroBorder);
    }

    /**
     * Displays the JLabel that prompts user to enter their name.
     */
    private void setNameLabel() {
        myNameLabel.setBounds(X_COORDINATE, 80, LABEL_WIDTH, LABEL_HEIGHT);
        myNameLabel.setText("Your name:");
        myNameLabel.setForeground(Color.WHITE);
        myNameLabel.setVisible(true);
        add(myNameLabel);
    }

    /**
     * Displays the JTextField that allows user to enter their name.
     */
    private void setNameField() {
        myNameField.setBounds(X_COORDINATE, 105, LABEL_WIDTH, LABEL_HEIGHT);
        myNameField.setText("BravePotato6000");
        myNameField.setVisible(true);
        add(myNameField);
    }

    /**
     * Displays the JLabel that prompts user to choose their hero.
     */
    private void setHeroClassLabel() {
        myHeroClassLabel.setBounds(X_COORDINATE, 180, LABEL_WIDTH, LABEL_HEIGHT);
        myHeroClassLabel.setText("Choose your hero:");
        myHeroClassLabel.setForeground(Color.WHITE);
        myHeroClassLabel.setVisible(true);
        add(myHeroClassLabel);
    }

    /**
     * Displays the JComboBox that allows user to choose their hero.
     */
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

    /**
     * Updates selected hero image.
     */
    private void changeHeroImage(final int theIndex) {
        if (theIndex == 1) {
            mySelectedHero.setIcon(new ImageIcon(WARRIOR_IMAGE_PATH));
        } else if (theIndex == 2) {
            mySelectedHero.setIcon(new ImageIcon(PRIESTESS_IMAGE_PATH));
        } else {
            mySelectedHero.setIcon(new ImageIcon(THIEF_IMAGE_PATH));
        }
    }

    /**
     * Displays the JLabel that prompts user to choose their difficulty level.
     */
    private void setDifficultyLabel() {
        myDifficultyLabel.setBounds(X_COORDINATE, 280, LABEL_WIDTH, LABEL_HEIGHT);
        myDifficultyLabel.setText("Select game difficulty:");
        myDifficultyLabel.setForeground(Color.WHITE);
        myDifficultyLabel.setVisible(true);
        add(myDifficultyLabel);
    }

    /**
     * Displays JComboBox that allows user to choose their preferred difficulty.
     */
    private void setDifficultyBox() {
        myDifficultyBox.setBounds(X_COORDINATE, 305, LABEL_WIDTH, LABEL_HEIGHT);
        myDifficultyBox.setSelectedIndex(1);
        myDifficultyBox.setVisible(true);
        add(myDifficultyBox);
    }

    /**
     * Adds I'm ready and Load button and their respective action listeners.
     */
    public void setSettingsButton() {
        myLoadButton.setBounds(350, 305, 100, LABEL_HEIGHT);
        myLoadButton.setFont(new Font("Arial", Font.BOLD, 20));
        myLoadButton.addActionListener(event -> {
            myPcs.firePropertyChange("Load", null, null);
        });

        myReadyButton.setBounds(X_COORDINATE, 360, 300, 50);
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
        myLoadButton.setVisible(true);
        add(myLoadButton);
        add(myReadyButton);
        revalidate();
        repaint();
    }

    /**
     * @return returns the player's name.
     */
    protected String getMyPlayerName() {
        return myPlayerName;
    }

    /**
     * @return returns the int value of the chosen hero.
     */
    protected int getHeroSelection() {
        return myHeroSelection;
    }

    /**
     * @return returns the int value of the chosen difficulty.
     */
    protected int getDifficultyLevel() {
        return myDifficultyLevel;
    }

    /**
     * Adds property change listener to this class.
     */
    public void addPropertyChangeListener(final PropertyChangeListener theListener) {
        myPcs.addPropertyChangeListener(theListener);
    }

    /**
     * Removes property change listener.
     */
    public void removePropertyChangeListener(final PropertyChangeListener theListener) {
        myPcs.removePropertyChangeListener(theListener);
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
