package Controller;

import View.SettingsGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DungeonGUI extends JFrame {

    private static final String WINDOW_TITLE = "Dungeon Adventure";
    private static final String ICON_IMAGE_PATH = "images/torch.png";
    private static final String BACKGROUND_IMAGE_PATH = "images/home.jpg";
    private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 500;
    private JPanel myHomePanel;
    private JLabel myBackgroundLabel;

    public static void main(String[] args) {
        new DungeonGUI();
    }

    private DungeonGUI() {
        super(WINDOW_TITLE);
        myHomePanel = new JPanel();
        myBackgroundLabel = new JLabel();
        setHomePage();
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setIconImage(new ImageIcon(ICON_IMAGE_PATH).getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void setHomePage() {
        myHomePanel.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        ImageIcon originalIcon = new ImageIcon(BACKGROUND_IMAGE_PATH);
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(FRAME_WIDTH, FRAME_HEIGHT, Image.SCALE_SMOOTH);
        myBackgroundLabel.setIcon(new ImageIcon(scaledImage));
        myBackgroundLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showSettingsPanel();
            }
        });
        myHomePanel.add(myBackgroundLabel);
        add(myHomePanel);
    }

    private void showSettingsPanel() {
        remove(myHomePanel);
        SettingsGUI settingsPanel = new SettingsGUI();
        add(settingsPanel);
        revalidate();
        repaint();
    }

}
