import javax.swing.*;
import java.awt.*;

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

    private static final String HERO_CLASSES[] =
            { "Thief", "Warrior", "Priestess" };

    private static final String DIFFICULTY_CHOICES[] =
            { "Easy", "Normal", "Hard" };

    private static final int X_COORDINATE = 150;

    private static final int LABEL_WIDTH = 150;

    private static final int LABEL_HEIGHT = 30;

    private ImageIcon myWarriorImage;

    private ImageIcon myPriestessImage;

    private ImageIcon myThiefImage;

    private JLabel myHeroLabel;

    private JLabel myBorderLabel;

    private JLabel myNameLabel;

    private JTextField myNameField;

    private JLabel myHeroClassLabel;

    private JComboBox myHeroClassBox;

    private JLabel myDifficultyLabel;

    private JComboBox myDifficultyBox;

    private JButton myReadyButton;

    public SettingsGUI() {
        super(WINDOW_TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        start();
    }

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
        myNameLabel.setVisible(true);
        add(myNameLabel);

        myNameField.setBounds(X_COORDINATE, 105, LABEL_WIDTH, LABEL_HEIGHT);
        myNameField.setText("BravePotato6000");
        myNameField.setVisible(true);
        add(myNameField);

        myHeroClassLabel.setBounds(X_COORDINATE, 180, LABEL_WIDTH, LABEL_HEIGHT);
        myHeroClassLabel.setText("Choose your hero:");
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
        add(myReadyButton);

        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);
    }

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