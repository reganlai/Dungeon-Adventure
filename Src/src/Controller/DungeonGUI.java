package Controller;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class DungeonGUI {

    public static final int FRAME_WIDTH = 1000;
    public static final int FRAME_HEIGHT = 500;
    public static final String BACKGROUND_IMAGE = "images/home.jpg";
    public static final String ICON_IMAGE = "images/torch.png";
    private static final String AUDIO_PATH = "images/bgmusic.wav";
    private static Clip audioClip;

    private static JFrame home;
    private static CardLayout cardLayout;
    private static JPanel mainPanel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createHomePage();
            playAudio(AUDIO_PATH);
        });
    }

    private static void createHomePage() {
        home = new JFrame("Dungeon Adventure");
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));

        ImageIcon iconImage = new ImageIcon(ICON_IMAGE);
        ImageIcon backgroundImageIcon = new ImageIcon(BACKGROUND_IMAGE);
        Image backgroundImage =
                backgroundImageIcon.getImage().getScaledInstance(FRAME_WIDTH, FRAME_HEIGHT, Image.SCALE_SMOOTH);
        JLabel background = new JLabel(new ImageIcon(backgroundImage));
        JButton enter = new JButton("Enter Dungeon");

        background.setBounds(0, -20, FRAME_WIDTH, FRAME_HEIGHT);
        enter.setBounds(430, 350, 140, 40);

        layeredPane.add(background, Integer.valueOf(0));
        layeredPane.add(enter, Integer.valueOf(1));

        enter.addActionListener(e -> showSettingsPanel());

        home.setIconImage(iconImage.getImage());
        home.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        home.setLocationRelativeTo(null);
        home.setResizable(false);
        home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.add(layeredPane, "Home");

        SettingsGUI settingsPanel = new SettingsGUI(audioClip);
        mainPanel.add(settingsPanel, "Settings");

        GameplayGUI gameplayPanel = new GameplayGUI("PlayerName", 1, 1);
        mainPanel.add(gameplayPanel, "Gameplay");

        home.add(mainPanel);
        home.setVisible(true);
    }


    public static void showSettingsPanel() {
        cardLayout.show(mainPanel, "Settings");
    }

    public static void showGameplayPanel(String playerName, int selectedHero, int selectedDifficulty) {
        cardLayout.show(mainPanel, "Gameplay");
        GameplayGUI gameplayPanel = new GameplayGUI(playerName, selectedHero, selectedDifficulty);
        mainPanel.add(gameplayPanel, "Gameplay");
    }

    private static void playAudio(final String thePath) {
        new Thread(() -> {
            try {
                File audioFile = new File(thePath);
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
                audioClip = AudioSystem.getClip();
                audioClip.open(audioStream);
                audioClip.start();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
