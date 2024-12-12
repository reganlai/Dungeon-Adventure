package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The SettingsGUI class prompts user to chose their hero, hero name, and difficulty level before entering GameplayGUI.
 *
 * @author Regan Lai
 * @author George Njane
 * @version 1.0
 */
public class SettingsGUI extends JPanel {

    /** Width for JPanel*/
    private static final int FRAME_WIDTH = 1000;

    /** Height for JPanel*/
    private static final int FRAME_HEIGHT = 500;

    /** The CardLayout that deals with the screen changing.*/
    private CardLayout myCardLayout;

    /** The parent panel for all the screens. Used by the CardLayout.*/
    private JPanel myCardPanel;

    /** The main frame shared across different classes to update the same frame.*/
    private JFrame myMainFrame;

    /** JLabel that holds the background image.*/
    private JLabel myBackgroundImage;

    /** Common x coordinate for some components.*/
    private static final int X_COORDINATE = 150;

    /** Common label width for some components.*/
    private static final int LABEL_WIDTH = 150;

    /** Common label heights for some components.*/
    private static final int LABEL_HEIGHT = 30;

    /** String path for background image.*/
    private static final String BACKGROUND_IMAGE_PATH = "images/backgroundimage.png";

    /** String path for warrior.*/
    private static final String WARRIOR_IMAGE_PATH = "images/warrior.png";

    /** String path for thief.*/
    private static final String THIEF_IMAGE_PATH = "images/thief.png";

    /** String path for priestess.*/
    private static final String PRIESTESS_IMAGE_PATH = "images/priestess.png";

    /** String array for hero classes.*/
    private static final String HERO_CLASSES[] =
            {"Thief", "Warrior", "Priestess"};

    /** String array for difficulty options.*/
    private static final String DIFFICULTY_CHOICES[] =
            {"Easy", "Normal", "Difficult"};

    /** JLabel that displays image of currently selected hero.*/
    private final JLabel mySelectedHero;

    /** Black border for mySelectedHero.*/
    private final JLabel mySelectedHeroBorder;

    /** JLabel that holds plain text that prompts user to enter their character name.*/
    private final JLabel myNameLabel;

    /** JTextField that allows user to enter their name.*/
    private JTextField myNameField;

    /** JLabel that holds plain text that prompts user to choose their hero.*/
    private JLabel myHeroClassLabel;

    /** JComboBox that allows user to choose between different hero classes.*/
    private final JComboBox myHeroBox;

    /** JLabel that holds plain text that prompts user to choose the difficulty level.*/
    private final JLabel myDifficultyLabel;

    /** JComboBox that allows user to choose between different difficulty levels.*/
    private final JComboBox myDifficultyBox;

    /** JButton that user clicks when they have customized their hero and difficulty.*/
    private final JButton myReadyButton;

    /** Specifies constraints for components.*/
    private final GridBagConstraints myGBC;


    /**
     * Initializes the GUI.
     * @param theMainFrame JFrame shared across different classes
     * @param theCardLayout CardLayout that deals with the screen changing
     * @param theCardPanel parent panel for all the screens
     */
    public SettingsGUI(final JFrame theMainFrame, final JPanel theCardPanel, final CardLayout theCardLayout) {
        super();
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLayout(null);

        myMainFrame = theMainFrame;
        myCardPanel = theCardPanel;
        myCardLayout = theCardLayout;
        myHeroClassLabel = new JLabel();
        myNameField = new JTextField();
        mySelectedHero = new JLabel();
        mySelectedHeroBorder = new JLabel();
        myNameLabel = new JLabel();
        myHeroBox = new JComboBox(HERO_CLASSES);
        myDifficultyLabel = new JLabel();
        myDifficultyBox = new JComboBox(DIFFICULTY_CHOICES);
        myReadyButton = new JButton("I'm ready");
        myBackgroundImage = new JLabel();
        myGBC = new GridBagConstraints();

        initSettings();
        revalidate();
        repaint();
    }

    /**
     * Calls methods that sets up the GUI.
     */
    private void initSettings() {
        setReadyButton();
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
     * Sets the position and size of the selected hero JLabel.
     */
    private void setSelectedHero() {
        mySelectedHero.setBounds(650, 40, 200, 370);
        mySelectedHero.setOpaque(true);
        mySelectedHero.setIcon(new ImageIcon(WARRIOR_IMAGE_PATH));
        mySelectedHero.setVisible(true);
        add(mySelectedHero);
    }

    /**
     * Sets the position and size of the selected hero border JLabel.
     */
    private void setSelectedHeroBorder() {
        mySelectedHeroBorder.setBounds(640, 30, 220, 390);
        mySelectedHeroBorder.setOpaque(true);
        mySelectedHeroBorder.setBackground(Color.BLACK);
        mySelectedHeroBorder.setVisible(true);
        add(mySelectedHeroBorder);
    }

    /**
     * Sets the position and size of the JLabel that prompts user to enter their name.
     */
    private void setNameLabel() {
        myNameLabel.setBounds(X_COORDINATE, 80, LABEL_WIDTH, LABEL_HEIGHT);
        myNameLabel.setText("Your name:");
        myNameLabel.setForeground(Color.WHITE);
        myNameLabel.setVisible(true);
        add(myNameLabel);
    }

    /**
     * Sets the position and size of the JTextField that allows user to enter their name.
     */
    private void setNameField() {
        myNameField.setBounds(X_COORDINATE, 105, LABEL_WIDTH, LABEL_HEIGHT);
        myNameField.setText("BravePotato6000");
        myNameField.setVisible(true);
        add(myNameField);
    }

    /**
     * Sets the position and size of the JLabel that prompts user to choose their hero.
     */
    private void setHeroClassLabel() {
        myHeroClassLabel.setBounds(X_COORDINATE, 180, LABEL_WIDTH, LABEL_HEIGHT);
        myHeroClassLabel.setText("Choose your hero:");
        myHeroClassLabel.setForeground(Color.WHITE);
        myHeroClassLabel.setVisible(true);
        add(myHeroClassLabel);
    }

    /**
     * Sets the position and size of the JComboBox that allows user to choose their hero.
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
     * Changes the image to the currently selected hero.
     * @param theIndex index that is retrieved from the hero JComboBox
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
     * Sets the position and size of the JLabel that prompts user to choose their preferred difficulty.
     */
    private void setDifficultyLabel() {
        myDifficultyLabel.setBounds(X_COORDINATE, 280, LABEL_WIDTH, LABEL_HEIGHT);
        myDifficultyLabel.setText("Select game difficulty:");
        myDifficultyLabel.setForeground(Color.WHITE);
        myDifficultyLabel.setVisible(true);
        add(myDifficultyLabel);
    }

    /**
     * Sets the position and size of the JComboBox that allows user to choose their preferred difficulty.
     */
    private void setDifficultyBox() {
        myDifficultyBox.setBounds(X_COORDINATE, 305, LABEL_WIDTH, LABEL_HEIGHT);
        myDifficultyBox.setSelectedIndex(1);
        myDifficultyBox.setVisible(true);
        add(myDifficultyBox);
    }

    /**
     * Sets the position and size of the JButton and adds action listener to the button.
     */
    public void setReadyButton() {
        myGBC.gridx = 150;
        myGBC.gridy = getHeight();
        myGBC.anchor = GridBagConstraints.SOUTH;

        myReadyButton.setBounds(X_COORDINATE, 360, 300, 50);
        myReadyButton.setText("I'm ready");
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

    /**
     * Displays GameplayGUI
     * @param theName name that user entered
     * @param theHero int representation of the hero chosen
     * @param theDifficulty int representation of the difficulty chosen
     */
    private void showAnotherPanel(final String theName, final int theHero,
                                  final int theDifficulty) {

        GameplayGUI gamePanel = new GameplayGUI(theName, theHero, theDifficulty,
                                                    myMainFrame, myCardPanel, myCardLayout);
        myCardPanel.add(gamePanel, "Game");
        myCardLayout.show(myCardPanel, "Game");

    }

    /**
     * Updates visuals of the JPanel.
     */
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D graphics = (Graphics2D) theGraphics;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        Image backgroundImage = new ImageIcon(BACKGROUND_IMAGE_PATH).getImage();
        theGraphics.drawImage(backgroundImage, 0, 0, FRAME_WIDTH, FRAME_HEIGHT, this);
    }


}
