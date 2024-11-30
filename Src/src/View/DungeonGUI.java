package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
    /** The panel for the home screen.
     /**
     * The panel for the home screen.
     * This is the screen that is first shows when the game launches.
     */
    private final JPanel myHomePanel;

    public DungeonGUI() {
        myMainFrame = new JFrame(WINDOW_TITLE);
        myHomePanel = new BackgroundPanel();

        myCardLayout = new CardLayout();
        myCardPanel = new JPanel(myCardLayout);

        initGui();

    }

    private void initGui() {
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
    }

    private void showSettingsPanel() {
        myCardLayout.show(myCardPanel, "Settings");
    }

    /*
     * This class that creates the background of the game.
     */
    private class BackgroundPanel extends JPanel {
        private final Image myBackgroundImage;

        private BackgroundPanel() {
            myBackgroundImage = new ImageIcon(BACKGROUND_IMAGE_PATH).getImage();
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    showSettingsPanel();
                }
            });
        }

        public void paintComponent(final Graphics theGraphics) {
            super.paintComponent(theGraphics);
            final Graphics2D graphics = (Graphics2D) theGraphics;
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            if (myBackgroundImage != null) {
                theGraphics.drawImage(myBackgroundImage, 0, 0, FRAME_WIDTH, FRAME_HEIGHT, this);
            }
        }
    }
}