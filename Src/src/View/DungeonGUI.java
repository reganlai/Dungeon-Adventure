package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * The DungeonGUI class acts as the home screen for the program.
 *
 * @author Regan Lai
 * @version 1.0
 */
public class DungeonGUI {

    /** Window title for JFrame*/
    private static final String WINDOW_TITLE = "Dungeon Adventure";

    /** String path for icon image*/
    private static final String ICON_IMAGE_PATH = "images/torch.png";

    /** String path for backgroun image(used for JLabel)*/
    private static final String BACKGROUND_IMAGE_PATH = "images/home.jpg";

    /** Width for JFrame*/
    private static final int FRAME_WIDTH = 1000;

    /** Height for JFrame*/
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

    /**
     *  Constructor for this class
     *  Initializes fields and calls methods that sets up the program
     */
    public DungeonGUI() {
        myMainFrame = new JFrame(WINDOW_TITLE);
        myHomePanel = new BackgroundPanel();

        myCardLayout = new CardLayout();
        myCardPanel = new JPanel(myCardLayout);

        initGui();

    }

    /**
     *  Sets characteristics of the JFrame
     *  Creates SettingsGUI in order to show it later after the user clicks on the background image
     */
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

    /**
     *  Shows SettingsGUI using cardLayout
     */
    private void showSettingsPanel() {
        myCardLayout.show(myCardPanel, "Settings");
    }

    /**
     *  Creates the background image and adds actionListener to the image
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