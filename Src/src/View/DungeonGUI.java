package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DungeonGUI {

    private static final String WINDOW_TITLE = "Dungeon Adventure";
    private static final String ICON_IMAGE_PATH = "images/torch.png";
    private static final String BACKGROUND_IMAGE_PATH = "images/home.jpg";
    private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 500;
    /** The main frame shared across different classes to update the same frame.*/
    private final JFrame myMainFrame;
    /** The CardLayout that deals with the screen changing.*/
    private final CardLayout myCardLayout;
    /** The parent panel for all the screens. Used by the CardLayout.*/
    private final JPanel myCardPanel;

    /**
     * The panel for the home screen.
     * This is the screen that is first shows when the game launches.
     */
    private final JPanel myHomePanel;
    private JLabel myBackgroundLabel;

    public DungeonGUI() {
        myMainFrame = new JFrame(WINDOW_TITLE);
        myHomePanel = new JPanel();
        myBackgroundLabel = new JLabel();

        myCardLayout = new CardLayout();
        myCardPanel = new JPanel(myCardLayout);

        initGui();

    }

    private void initGui() {
        setHomePage();
        myCardPanel.add(myHomePanel, "Home");

        SettingsGUI settingPanel = new SettingsGUI(myMainFrame, myCardPanel, myCardLayout);
        myCardPanel.add(settingPanel, "Settings");

        myMainFrame.add(myCardPanel);
        myMainFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        myMainFrame.setIconImage(new ImageIcon(ICON_IMAGE_PATH).getImage());
        myMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myMainFrame.setLocationRelativeTo(null);
        myMainFrame.setResizable(false);
        myMainFrame.setVisible(true);

        myMainFrame.revalidate();
        myMainFrame.repaint();
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

        myMainFrame.add(myHomePanel);
    }

    private void showSettingsPanel() {
        myCardLayout.show(myCardPanel, "Settings");
    }
}