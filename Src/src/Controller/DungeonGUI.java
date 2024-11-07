package Controller;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class DungeonGUI {

    /* Width pixels for JFrame */
    public static final int FRAME_WIDTH = 1000;

    /* Height pixels for JFrame */
    public static final int FRAME_HEIGHT = 500;

    /* Background image's path */
    public static final String BACKGROUND_IMAGE = "images/home.jpg";

    /* JFrame's iconImage's image path */
    public static final String ICON_IMAGE = "images/torch.png";

    private static final String AUDIO_PATH = "images/bgmusic.wav";

    private static Clip audioClip;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createHomePage();
            playAudio(AUDIO_PATH);
        });
    }

    private static void createHomePage() {
        JFrame home = new JFrame("Dungeon Adventure");
        JLabel backgroundImage = new JLabel();
        JButton enter = new JButton();

        ImageIcon image = new ImageIcon(BACKGROUND_IMAGE);
        backgroundImage.setIcon(image);
        backgroundImage.setBounds(0, -30, FRAME_WIDTH, FRAME_HEIGHT);

        ImageIcon iconImage =
                new ImageIcon(ICON_IMAGE);

        enter.setText("Enter Dungeon");
        enter.setBounds(430, 350, 140,40);
        enter.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theE) {
                home.dispose();
                SettingsGUI settings = new SettingsGUI(audioClip);
            }
        });

        home.add(backgroundImage);

        JLayeredPane layeredPane = home.getLayeredPane();
        layeredPane.add(enter, JLayeredPane.PALETTE_LAYER);

        home.setIconImage(iconImage.getImage());
        home.setLayout(null);
        home.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        home.setLocationRelativeTo(null);
        home.setResizable(false);
        home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        home.setVisible(true);
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