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
    private JMenuBar myMenubar;
    private JMenu myGameplay;
    private JMenu myHelp;
    private JMenuItem myMap;
    private JMenuItem myInventory;
    private JMenuItem myInstructions;
    private JMenuItem myControls;
    private JPanel myMenuPanel;

    public static void main(String[] args) {
        new DungeonGUI();
    }

    private DungeonGUI() {
        super(WINDOW_TITLE);
        myHomePanel = new JPanel();
        myBackgroundLabel = new JLabel();
        myMenubar = new JMenuBar();
        myGameplay = new JMenu("Gameplay");
        myHelp = new JMenu("Help");
        myMap = new JMenuItem("Map");
        myInventory = new JMenuItem("Inventory");
        myInstructions = new JMenuItem("Instructions");
        myControls = new JMenuItem("Controls");
        myMenuPanel = new JPanel(new BorderLayout());
        setHomePage();
        setMenuBar();
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setIconImage(new ImageIcon(ICON_IMAGE_PATH).getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
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
        SettingsGUI settingsPanel = new SettingsGUI(myMenuPanel);
        add(settingsPanel);
        revalidate();
        repaint();
    }

    private void setMenuBar() {
        myGameplay.add(myMap);
        myGameplay.add(myInventory);
        myHelp.add(myInstructions);
        myHelp.add(myControls);
        myMenubar.add(myGameplay);
        myMenubar.add(myHelp);
        myMenuPanel.add(myMenubar, BorderLayout.NORTH);
        add(myMenuPanel, BorderLayout.NORTH);
        myMenuPanel.setVisible(false);
    }

}
