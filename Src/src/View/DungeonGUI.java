/*
 * TCSS 360 - Dungeon Adventure
 */
package View;

import Controller.DungeonController;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

/**
 * GUI that is being shown right when the program is launched.
 *
 * @author George Njane
 * @author Regan Lai
 * @version 1.0
 */
public class DungeonGUI extends JFrame {

    /** JFrame title. */
    private static final String WINDOW_TITLE = "Dungeon Adventure";

    /** String path of icon image. */
    private static final String ICON_IMAGE_PATH = "images/torch.png";

    /** String path for background image. */
    private static final String BACKGROUND_IMAGE_PATH = "images/home.jpg";

    /** JFrame width in pixels. */
    private static final int FRAME_WIDTH = 1000;

    /** JFrame height in pixels. */
    private static final int FRAME_HEIGHT = 500;

    /** Settings panel. */
    private transient SettingsPanel mySettingsPanel;

    /** Gameplay panel. */
    private GameplayPanel myGameplayPanel;

    /** The controller. */
    private DungeonController myController;

    /** Exit panel. */
    private transient ExitGUI myExitPanel;

    /** Fight scene. */
    private transient FightScene myFightScene;

    /** Current panel that is being shown. */
    private JPanel myCurrentPanel;

    /** The screen that shows when the game launches. */
    private JPanel myHomePanel;

    /** Background music. */
    private static Clip myBackgroundClip;

    /** Creates DungeonGUI object and initializes JFrame window title. */
    public DungeonGUI() {
        super(WINDOW_TITLE);
    }

    /** Initializes fields and starts playing background music. */
    public void initGui(final DungeonController theController, final SettingsPanel theSettings) {
        myHomePanel = new BackgroundPanel();

        myController = theController;
        mySettingsPanel = theSettings;
        myExitPanel = new ExitGUI(this, myController);
        myGameplayPanel = new GameplayPanel(this, myController, myExitPanel);
        myFightScene = new FightScene(myController);

        myCurrentPanel = myHomePanel;
        add(myCurrentPanel);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        playBackgroundMusic("images/bgmusic.wav");
        setIconImage(new ImageIcon(ICON_IMAGE_PATH).getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    /**
     * Plays background music.
     */
    private static void playBackgroundMusic(String filePath) {
        try {
            File musicFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);
            myBackgroundClip = AudioSystem.getClip();
            myBackgroundClip.open(audioStream);
            myBackgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
            myBackgroundClip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Starts the game according to whether using is clicking on new game or load.
     * @param theFirstGame true if user plays new game, false if user chooses load
     */
    public void start(final boolean theFirstGame) {
        if (!theFirstGame) {
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

    /**
     * Updates controller fields if user plays new game.
     * @param theNewGame true if user plays new game, false if user chooses load
     */
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

    /**
     * Starts fight between user and monster.
     */
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

    /**
     * @return returns myFightScene
     */
    public FightScene getMyFightScene() {
        return myFightScene;
    }

    /**
     * Shows map.
     */
    public void showMap() {
        myController.renderMap();
        myController.getMapDialog().setVisible(true);
    }

    /**
     * Updates JLabel message.
     * @param theMessage the message that is going to be shown
     */
    public void sendMessage(final String theMessage) {
        myGameplayPanel.getMessageLabel().setBounds(440, 385, 500, 30);
        myGameplayPanel.getMessageLabel().setText(theMessage);
    }

    /**
     * Shows the right panel after user is done fighting the monster.
     * @param theHeroWins true if hero had won the fight against the monster, false otherwise
     */
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

    /**
     * Updates JLabel to the item that user picked up
     * @param theItem the item that the user picked up
     */
    public void updateVisuals(final String theItem) {
        myGameplayPanel.updateVisuals(theItem);
        reconstruct();
    }

    /**
     * User has won the game and goes to the exit.
     */
    public void exit() {
        myExitPanel.setGameResult("Won");
        remove(myCurrentPanel);
        myCurrentPanel = myExitPanel;
        add(myCurrentPanel);
        reconstruct();
    }

    /**
     * Revalidates and repaints the program.
     */
    private void reconstruct() {
        revalidate();
        repaint();
    }

    /**
     * Shows myGameplayPanel.
     */
    public void setScreen() {
        remove(myCurrentPanel);
        myCurrentPanel = myGameplayPanel;
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