package Controller;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;

public class SettingsGUI extends JPanel {

    private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 500;

    public static final String BACKGROUND_IMAGE = "images/backgroundimage.png";

    /** String file location for torch. */
    private static final String ICON_IMAGE = "images/torch.png";

    /** String file location for warrior image. */
    private static final String WARRIOR_PATH = "images/warrior.png";

    /** String file location for thief image. */
    private static final String THIEF_PATH = "images/thief.png";

    /** String file location for priestess image. */
    private static final String PRIESTESS_PATH = "images/priestess.png";

    /** ImageIcon that represents warrior. */
    private ImageIcon myWarriorImage;

    /** ImageIcon that represents priestess. */
    private ImageIcon myPriestessImage;

    /** ImageIcon that represents thief. */
    private ImageIcon myThiefImage;

    private JLabel myNameLabel;
    private JTextField myNameField;

    private JLabel myHeroLabel;
    private JComboBox myHeroClassBox;

    private JLabel myDifficultyLabel;
    private JComboBox myDifficultyBox;
    private JButton myReadyButton;
    private Clip myAudioClip;

    private JLabel mySelectedHero;
    private JLabel myBorderLabel;

    public SettingsGUI(Clip theClip) {
        this.myAudioClip = theClip;
        if (myAudioClip == null) {
            System.out.println("Audio clip is null");
        } else {
            System.out.println("Audio clip is ready");
        }
        myWarriorImage = new ImageIcon(WARRIOR_PATH);
        myPriestessImage = new ImageIcon(PRIESTESS_PATH);
        myThiefImage = new ImageIcon(THIEF_PATH);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));

        ImageIcon backgroundImageIcon = new ImageIcon(BACKGROUND_IMAGE);
        Image backgroundImage =
                backgroundImageIcon.getImage().getScaledInstance(FRAME_WIDTH, FRAME_HEIGHT, Image.SCALE_SMOOTH);
        JLabel background = new JLabel(new ImageIcon(backgroundImage));
        background.setBounds(0, -40, FRAME_WIDTH, FRAME_HEIGHT + 40);
        layeredPane.add(background, Integer.valueOf(0));

        mySelectedHero = new JLabel();
        mySelectedHero.setIcon(myWarriorImage);
        mySelectedHero.setBounds(650, 40, 200, 370);
        layeredPane.add(mySelectedHero, Integer.valueOf(1));

        myBorderLabel = new JLabel();
        myBorderLabel.setBounds(640, 30, 220, 390);
        myBorderLabel.setOpaque(true);
        myBorderLabel.setBackground(Color.black);
        layeredPane.add(myBorderLabel, Integer.valueOf(1));

        myNameLabel = new JLabel("Your name:");
        myNameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        myNameLabel.setForeground(Color.WHITE);
        myNameLabel.setBounds(150, 80, 150, 30);
        layeredPane.add(myNameLabel, Integer.valueOf(1));

        myNameField = new JTextField("BravePotato6000");
        myNameField.setBounds(150, 105, 150, 30);
        layeredPane.add(myNameField, Integer.valueOf(1));

        myHeroLabel = new JLabel("Choose your hero:");
        myHeroLabel.setFont(new Font("Arial", Font.BOLD, 16));
        myHeroLabel.setForeground(Color.WHITE);
        myHeroLabel.setBounds(150, 180, 150, 30);
        layeredPane.add(myHeroLabel, Integer.valueOf(1));

        String[] heroClasses = {"Thief", "Warrior", "Priestess"};
        myHeroClassBox = new JComboBox(heroClasses);
        myHeroClassBox.setSelectedIndex(1);
        myHeroClassBox.setBounds(150, 205, 150, 30);
        myHeroClassBox.addActionListener(e -> {
            int selectedIndex = myHeroClassBox.getSelectedIndex();
            changeHeroImage(selectedIndex);
        });
        layeredPane.add(myHeroClassBox, Integer.valueOf(1));

        myDifficultyLabel = new JLabel("Choose game difficulty:");
        myDifficultyLabel.setFont(new Font("Arial", Font.BOLD, 13));
        myDifficultyLabel.setForeground(Color.WHITE);
        myDifficultyLabel.setBounds(150, 280, 200, 30);
        layeredPane.add(myDifficultyLabel, Integer.valueOf(1));

        String[] difficulties = {"Easy", "Normal", "Hard"};
        myDifficultyBox = new JComboBox(difficulties);
        myDifficultyBox.setSelectedIndex(1);
        myDifficultyBox.setBounds(150, 305, 150, 30);
        layeredPane.add(myDifficultyBox, Integer.valueOf(1));

        myReadyButton = new JButton("I'm Ready");
        myReadyButton.setBounds(150, 370, 240, 40);
        myReadyButton.addActionListener(e -> {
            if (myAudioClip != null && myAudioClip.isRunning()) {
                myAudioClip.stop();
            }
            String playerName = myNameField.getText();
            int selectedHero = myHeroClassBox.getSelectedIndex();
            int selectedDifficulty = myDifficultyBox.getSelectedIndex();

            DungeonGUI.showGameplayPanel(playerName, selectedHero, selectedDifficulty);
        });
        layeredPane.add(myReadyButton, Integer.valueOf(1));

        add(layeredPane);
    }


    private void changeHeroImage(final int theIndex) {
        if (theIndex == 1) {
            mySelectedHero.setIcon(myWarriorImage);
        } else if (theIndex == 2) {
            mySelectedHero.setIcon(myPriestessImage);
        } else {
            mySelectedHero.setIcon(myThiefImage);
        }
    }
}
