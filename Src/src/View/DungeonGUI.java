package View;

import Controller.DungeonController;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.Serial;
import java.io.Serializable;

/**
 * Class that shows right when the program is ran.
 *
 * @author George Njane
 * @version 1.0
 */
public class DungeonGUI extends JFrame {

    /** A generated serialization ID. */
    @Serial
    private static final long serialVersionUID = -7503476143259759311L;

    /** JFrame title. */
    private static final String WINDOW_TITLE = "Dungeon Adventure";

    /** String image path for icon image. */
    private static final String ICON_IMAGE_PATH = "images/torch.png";

    /** String iamge path for the background image. */
    private static final String BACKGROUND_IMAGE_PATH = "images/home.jpg";

    /** JFrame width in pixels. */
    private static final int FRAME_WIDTH = 1000;

    /** JFrame height in pixels. */
    private static final int FRAME_HEIGHT = 500;

    /** Holds SettingsPanel. */
    private transient SettingsPanel mySettingsPanel;

    /** Holds GameplayPanel. */
    private GameplayPanel myGameplayPanel;

    /** Holds controller. */
    private DungeonController myController;

    /** Holds ExitPanel. */
    private transient ExitGUI myExitPanel;

    /** Holds FightScene panel. */
    private transient FightScene myFightScene;

    /** The current JPanel that is being shown in the JFrame. */
    private JPanel myCurrentPanel;

    /**
     * The panel for the home screen.
     * This is the screen that is first shows when the game launches.
     */
    private JPanel myHomePanel;

    /** Background music audio clip. */
    private static Clip myBackgroundClip;

    /**
     * Creates a DungeonGUI object with its according window title and does nothing else.
     */
    public DungeonGUI() {
        super(WINDOW_TITLE);
    }

    /**
     * Initializes the GUI.
     * @param theController the controller of the program
     * @param theSettings the class that is going to show next
     */
    public void initGui(final DungeonController theController, final SettingsPanel theSettings) {

        myHomePanel = new BackgroundPanel();
        myController = theController;
        mySettingsPanel = theSettings;
        myExitPanel = new ExitGUI(this, myController);
        myGameplayPanel = new GameplayPanel(this, myController, myExitPanel);
        myFightScene = new FightScene(this, myController);
        myCurrentPanel = myHomePanel;

        add(myCurrentPanel);
        playBackgroundMusic("images/bgmusic.wav");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setIconImage(new ImageIcon(ICON_IMAGE_PATH).getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    /**
     * Plays and loops background music indefinitely.
     * @param theFilePath the String path of the audio clip
     */
    private void playBackgroundMusic(final String theFilePath) {
        try {
            File musicFile = new File(theFilePath);

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
     * Decides whether this is the first game played and starts the game.
     * @param theFirstGame whether user is choosing load(false) or play new game(true)
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
     * Gives new fields to the controller if it is a new game played.
     * @param theNewGame true if it is a new game played
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
     * Initializes fight between user and monster. Changes to fight scene class.
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
     * @return the fight scene class
     */
    public FightScene getMyFightScene() {
        return myFightScene;
    }

    public void showMap() {
        myController.renderMap();
        myController.getMapDialog().setVisible(true);
    }

    /**
     * Updates JLabel message.
     * @param theMessage the message that is going to be shown in JLabel
     */
    public void sendMessage(final String theMessage) {
        myGameplayPanel.getMessageLabel().setBounds(440, 385, 500, 30);
        myGameplayPanel.getMessageLabel().setText(theMessage);
    }

    /**
     * Decides which panel to show after the fight between user and monster is done.
     * @param theHeroWins whether the user won or not
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
     * Updates JLabel when user picks up an item.
     * @param theItem the item that the user picked up
     */
    public void updateVisuals(final String theItem) {
        myGameplayPanel.updateVisuals(theItem);
        reconstruct();
    }

    /**
     * User has won and goes to the exit.
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
     * Sets showing panel to myGameplayPanel.
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