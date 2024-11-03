import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;

/**
 * GUI class for user to choose their preferred game difficulty, hero class and name.
 *
 * @author Regan Lai
 * @version 1.0
 * @date 10/30/2024
 */
public final class SettingsGUI extends JFrame {

    /** Title for JFrame. */
    private static final String WINDOW_TITLE = "Dungeon Adventure";

    /** Width pixels for JFrame */
    private static final int FRAME_WIDTH = 1000;

    /** Height pixels for JFrame */
    private static final int FRAME_HEIGHT = 500;

    /** String file location for torch. */
    private static final String ICON_IMAGE = "images/torch.png";

    /** String file location for warrior image. */
    private static final String WARRIOR_PATH = "images/warrior.png";

    /** String file location for thief image. */
    private static final String THIEF_PATH = "images/thief.png";

    /** String file location for priestess image. */
    private static final String PRIESTESS_PATH = "images/priestess.png";

    /** String array for different hero classes. */
    private static final String HERO_CLASSES[] =
            { "Thief", "Warrior", "Priestess" };

    /** String array for game difficulty choices. */
    private static final String DIFFICULTY_CHOICES[] =
            { "Easy", "Normal", "Hard" };

    /** The x coordinate for some label and fields. */
    private static final int X_COORDINATE = 150;

    /** The width for some labels and fields. */
    private static final int LABEL_WIDTH = 150;

    /** The height for some labels and fields. */
    private static final int LABEL_HEIGHT = 30;

    /** ImageIcon that represents warrior. */
    private ImageIcon myWarriorImage;

    /** ImageIcon that represents priestess. */
    private ImageIcon myPriestessImage;

    /** ImageIcon that represents thief. */
    private ImageIcon myThiefImage;

    /** JLabel that shows the currently selected hero class. */
    private JLabel myHeroLabel;

    /** JLabel that acts a border around the myHeroLabel. */
    private JLabel myBorderLabel;

    /** Instructs user to enter their name. */
    private JLabel myNameLabel;

    /** JTextField that allows user to enter their name . */
    private JTextField myNameField;

    /** Instructs user to choose their preferred hero class. */
    private JLabel myHeroClassLabel;

    /** JComboBox that stores the options for hero classes. */
    private JComboBox myHeroClassBox;

    /** Instructs user to choose their preferred game difficulty. */
    private JLabel myDifficultyLabel;

    /** JComboBox that stores the options for game difficulty . */
    private JComboBox myDifficultyBox;

    /** JButton that is clicked when user to ready to move on. */
    private JButton myReadyButton;

    private JLabel myBackgroundImage;

    /** The audio clip that is being played in the background. */
    private Clip myAudioClip;

    /** Constructs a SettingsGUI JFrame.
     *
     * @param theClip the audio clip that is being played in the background
     */
    public SettingsGUI(Clip theClip) {
        super(WINDOW_TITLE);
        this.myAudioClip = theClip;
        myWarriorImage = new ImageIcon(WARRIOR_PATH);
        myPriestessImage = new ImageIcon(PRIESTESS_PATH);
        myThiefImage = new ImageIcon(THIEF_PATH);
        myHeroLabel = new JLabel();
        myBorderLabel = new JLabel();
        myNameLabel = new JLabel();
        myHeroClassLabel = new JLabel();
        myDifficultyLabel = new JLabel();
        myNameField = new JTextField();
        myHeroClassBox = new JComboBox(HERO_CLASSES);
        myDifficultyBox = new JComboBox(DIFFICULTY_CHOICES);
        myReadyButton = new JButton();
        myBackgroundImage = new JLabel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        start();
    }

    /** Methods that sets up the coordinates and basic information
     *  of the initialized fields.
     *
     */
    private void start() {
        final ImageIcon img = new ImageIcon(ICON_IMAGE);
        setIconImage(img.getImage());

        setLayout(null);

        myHeroLabel.setBounds(650, 40, 200, 370);
        myHeroLabel.setOpaque(true);
        myHeroLabel.setIcon(myWarriorImage);
        myHeroLabel.setVisible(true);
        add(myHeroLabel);

        myBorderLabel.setBounds(640, 30, 220, 390);
        myBorderLabel.setOpaque(true);
        myBorderLabel.setBackground(Color.black);
        myBorderLabel.setVisible(true);
        add(myBorderLabel);

        myNameLabel.setBounds(X_COORDINATE, 80, LABEL_WIDTH, LABEL_HEIGHT);
        myNameLabel.setText("Your name:");
        myNameLabel.setForeground(Color.WHITE);
        myNameLabel.setVisible(true);
        add(myNameLabel);

        myNameField.setBounds(X_COORDINATE, 105, LABEL_WIDTH, LABEL_HEIGHT);
        myNameField.setText("BravePotato6000");
        myNameField.setVisible(true);
        add(myNameField);

        myHeroClassLabel.setBounds(X_COORDINATE, 180, LABEL_WIDTH, LABEL_HEIGHT);
        myHeroClassLabel.setText("Choose your hero:");
        myHeroClassLabel.setForeground(Color.WHITE);
        myHeroClassLabel.setVisible(true);
        add(myHeroClassLabel);

        myHeroClassBox.setBounds(X_COORDINATE, 205, LABEL_WIDTH, LABEL_HEIGHT);
        myHeroClassBox.setSelectedIndex(1);
        myHeroClassBox.addActionListener(e -> {
            int selectedIndex = myHeroClassBox.getSelectedIndex();
            changeHeroImage(selectedIndex);
        });
        myHeroClassBox.setVisible(true);
        add(myHeroClassBox);

        myDifficultyLabel.setBounds(X_COORDINATE, 280, LABEL_WIDTH, LABEL_HEIGHT);
        myDifficultyLabel.setText("Select game difficulty:");
        myDifficultyLabel.setForeground(Color.WHITE);
        myDifficultyLabel.setVisible(true);
        add(myDifficultyLabel);

        myDifficultyBox.setBounds(X_COORDINATE, 305, LABEL_WIDTH, LABEL_HEIGHT);
        myDifficultyBox.setSelectedIndex(1);
        myDifficultyBox.setVisible(true);
        add(myDifficultyBox);

        myReadyButton.setBounds(X_COORDINATE, 360, 300, 50);
        myReadyButton.setText("I'm ready");
        myReadyButton.setFont(new Font("Arial", Font.BOLD, 20));
        myReadyButton.setVisible(true);
        myReadyButton.addActionListener(e -> {
            if (myAudioClip != null && myAudioClip.isRunning()) {
                myAudioClip.stop();
            }
        });
        add(myReadyButton);

        final ImageIcon background =
                new ImageIcon("images/backgroundimage.png");
        myBackgroundImage.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        myBackgroundImage.setIcon(background);
        add(myBackgroundImage);

        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /** Methods that handles changing the image for the currently selected
     *  hero class.
     *
     * @param theIndex the index of the currently selected hero class
     */
    private void changeHeroImage(final int theIndex) {
        if (theIndex == 1) {
            myHeroLabel.setIcon(myWarriorImage);
        } else if (theIndex == 2) {
            myHeroLabel.setIcon(myPriestessImage);
        } else {
            myHeroLabel.setIcon(myThiefImage);
        }
    }

}