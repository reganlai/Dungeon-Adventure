package View;

import Controller.DungeonController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serial;
import java.io.Serializable;

public class DungeonGUI extends JFrame {
    @Serial
    private static final long serialVersionUID = -7503476143259759311L;
    private static final String WINDOW_TITLE = "Dungeon Adventure";
    private static final String ICON_IMAGE_PATH = "images/torch.png";
    private static final String BACKGROUND_IMAGE_PATH = "images/home.jpg";
    private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 500;
    private transient SettingsPanel mySettingsPanel;
    private GameplayPanel myGameplayPanel;
    private DungeonController myController;
    private transient ExitGUI myExitPanel;
    private transient FightScene myFightScene;
    private JPanel myCurrentPanel;
     /**
     * The panel for the home screen.
     * This is the screen that is first shows when the game launches.
     */
    private JPanel myHomePanel;


    public DungeonGUI() {
        super(WINDOW_TITLE);
    }

    public void initGui(final DungeonController theController, final SettingsPanel theSettings) {
        //myMainFrame = new JFrame(WINDOW_TITLE);
        myHomePanel = new BackgroundPanel();

        myController = theController;
        mySettingsPanel = theSettings;
        myExitPanel = new ExitGUI(this, myController);
        myGameplayPanel = new GameplayPanel(this, myController, myExitPanel);
        myFightScene = new FightScene(this, myController);

        myCurrentPanel = myHomePanel;
        add(myCurrentPanel);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setIconImage(new ImageIcon(ICON_IMAGE_PATH).getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
    public void start(final boolean theFirstGame) {
        if (!theFirstGame) {
            //getJMenuBar().setVisible(false);
            //initGui();
            this.dispose();
            DungeonController controller = new DungeonController(new DungeonGUI());
            controller.startGame();
        } else {
            remove(myCurrentPanel);
            myCurrentPanel = mySettingsPanel;
            add(myCurrentPanel);
            reconstruct();
        }
    }

    public void handleSettingsInput(final boolean theNewGame) {
        if (theNewGame) {
            final String thePlayerName = mySettingsPanel.getMyPlayerName();
            myController.setGameSetting(thePlayerName, mySettingsPanel.getHeroSelection(),
                    mySettingsPanel.getDifficultyLevel());
        }
        myGameplayPanel.init();
        remove(myCurrentPanel);
        myCurrentPanel = myGameplayPanel;
        add(myCurrentPanel);
        reconstruct();
    }

    public void initiateFight() {
        System.out.println("Fight2");

        remove(myCurrentPanel);
        if (myFightScene != null) {
            System.out.println("Fight scene not null");
        }
        myCurrentPanel = myFightScene;
        myFightScene.fight();
        add(myCurrentPanel);
        reconstruct();

    }
    public FightScene getMyFightScene() {
        return myFightScene;
    }

    public void showMap() {
        myController.renderMap();
        myController.getMapDialog().setVisible(true);
    }
    public void sendMessage(final String theMessage) {
        myGameplayPanel.getMessageLabel().setBounds(440, 385, 500, 30);
        myGameplayPanel.getMessageLabel().setText(theMessage);
    }


    public void doneFight(final boolean theHeroWins) {
        if (theHeroWins) {
            remove(myCurrentPanel);
            myCurrentPanel = myGameplayPanel;

        } else {
            myExitPanel.setGameResult("Lost");
            remove(myCurrentPanel);
            myCurrentPanel = myExitPanel;
        }
        add(myCurrentPanel);
        reconstruct();
    }

    public void updateVisuals(final String theItem) {
        myGameplayPanel.updateVisuals(theItem);
        reconstruct();
    }

    public void exit() {
        myExitPanel.setGameResult("Won");
        remove(myCurrentPanel);
        myCurrentPanel = myExitPanel;
        add(myCurrentPanel);
        reconstruct();
    }

//    public void setController(final DungeonController theLoadedController) {
//        JLabel testLabel = new JLabel("Test Label - Component Added");
//        testLabel.setBounds(10, 10, 200, 30);  // Position the label at the top left of the panel
//        testLabel.setVisible(true);
//        myController = theLoadedController;
//        myFightScene.setController(myController);
//        myGameplayPanel = new GameplayPanel(this, myController, myExitPanel);
//        myGameplayPanel.init();
//        removeAll();
//        add(testLabel);
//        revalidate();
//        repaint();
//    }

    private void reconstruct() {

//        myGameplayPanel.init();
//        remove(myCurrentPanel);
//        myCurrentPanel = myGameplayPanel;
//        System.out.println(myController.getHero().getMyName());
//        add(myCurrentPanel);
        revalidate();
        repaint();
    }

    public void setScreen() {
        remove(myCurrentPanel);
        myCurrentPanel = myGameplayPanel;
        //add(myCurrentPanel);
        reconstruct();
    }

    /**
     * This class creates the background image of the game.
     */
    private class BackgroundPanel extends JPanel {

        private transient final Image myBackgroundImage;

        private BackgroundPanel() {
            myBackgroundImage = new ImageIcon(BACKGROUND_IMAGE_PATH).getImage();
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    start(true);
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