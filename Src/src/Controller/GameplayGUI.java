package Controller;

import javax.swing.*;
import java.awt.*;

public class GameplayGUI extends JFrame {

    /** Title for JFrame. */
    private static final String WINDOW_TITLE = "Dungeon Adventure";

    /** Width pixels for JFrame */
    private static final int FRAME_WIDTH = 1000;

    /** Height pixels for JFrame */
    private static final int FRAME_HEIGHT = 500;

    /** String file location for torch. */
    private static final String ICON_IMAGE = "images/torch.png";

    /** String file location for warrior in dungeon image. */
    private static final String WARRIOR_IN_DUNGEON =
            "images/warrior_in_dungeon.png";

    /** String file location for priestess in dungeon image. */
    private static final String PRIESTESS_IN_DUNGEON =
            "images/priestess_in_dungeon.png";

    /** String file location for thief in dungeon image. */
    private static final String THIEF_IN_DUNGEON =
            "images/thief_in_dungeon.png";

    /** ImageIcon that represents warrior in dungeon. */
    private ImageIcon myWarriorInDungeon;

    /** ImageIcon that represents priestess in dungeon. */
    private ImageIcon myPriestessInDungeon;

    /** ImageIcon that represents thief in dungeon. */
    private ImageIcon myThiefInDungeon;

    private String myName;

    private int myHero;

    private int myDifficulty;

    private JMenuBar myMenuBar;

    private JMenu myDungeon;

    private JMenuItem myMap;

    private JLabel myGameplay;

    private JTextArea myInventory;

    private JLabel myMessage;

    private JLabel myControls;

    public GameplayGUI(final String theName,
                       final int theHero,
                       final int theDifficulty) {
        super(WINDOW_TITLE);
        myName = theName;
        myHero = theHero;
        myDifficulty = theDifficulty;
        myMenuBar = new JMenuBar();
        myDungeon = new JMenu("Dungeon");
        myMap = new JMenuItem("Map");
        myGameplay = new JLabel();
        myWarriorInDungeon = new ImageIcon(WARRIOR_IN_DUNGEON);
        myPriestessInDungeon = new ImageIcon(PRIESTESS_IN_DUNGEON);
        myThiefInDungeon = new ImageIcon(THIEF_IN_DUNGEON);
        myInventory = new JTextArea();
        myMessage = new JLabel();
        myControls = new JLabel();
        setGameplay(myGameplay, myHero);
        setMessage(myName);
        start();
    }

    private void setGameplay(JLabel theGameplay, final int theHero) {
        myGameplay.setBounds(0, 0, 750, 325);

        switch(theHero) {
            case 0:
                myGameplay.setIcon(myThiefInDungeon);
                break;
            case 1:
                myGameplay.setIcon(myWarriorInDungeon);
                break;
            case 2:
                myGameplay.setIcon(myPriestessInDungeon);
                break;
        }

        myGameplay.setOpaque(true);
        add(myGameplay);
    }

    private void setMessage(final String theName) {
//        myMessage.setBounds(0,325, 500, 175);
//        myMessage.setText("Welcome to the Dungeon, " + theName + "!");
//        myMessage.setHorizontalAlignment(SwingConstants.CENTER);
//        myMessage.setVerticalAlignment(SwingConstants.CENTER);
//        myMessage.setBackground(Color.RED);
//        myMessage.setOpaque(true);
//        add(myMessage);

        myMessage.setText("Welcome to the Dungeon, " + theName + "!");

        myMessage.setBackground(Color.RED);
        myMessage.setOpaque(true);

        // Set the layout manager for the container to BorderLayout
        JPanel messagePanel = new JPanel(new BorderLayout());
        messagePanel.setBounds(0, 325, 500, 175);
        messagePanel.add(myMessage, BorderLayout.CENTER);
        myMessage.setHorizontalAlignment(SwingConstants.CENTER);
        myMessage.setVerticalAlignment(SwingConstants.CENTER);
        add(messagePanel);
    }

    private void start() {
        final ImageIcon img = new ImageIcon(ICON_IMAGE);
        setIconImage(img.getImage());
        setLayout(null);

        myDungeon.add(myMap);
        myMenuBar.add(myDungeon);



        myInventory.setBounds(750,0,300,325);
        myInventory.setBackground(Color.BLUE);
        myInventory.setOpaque(true);
        add(myInventory);

        myControls.setBounds(500, 325, 500, 175);
        myControls.setBackground(Color.YELLOW);
        myControls.setOpaque(true);
        add(myControls);

        setJMenuBar(myMenuBar);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
