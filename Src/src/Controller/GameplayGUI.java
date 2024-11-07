package Controller;

import javax.swing.*;

public class GameplayGUI extends JFrame {

    /** Title for JFrame. */
    private static final String WINDOW_TITLE = "Dungeon Adventure";

    /** Width pixels for JFrame */
    private static final int FRAME_WIDTH = 1000;

    /** Height pixels for JFrame */
    private static final int FRAME_HEIGHT = 500;

    /** String file location for torch. */
    private static final String ICON_IMAGE = "images/torch.png";

    private String myName;

    private int myHero;

    private int myDifficulty;

    private JMenuBar myMenuBar;

    private JMenu myDungeon;

    private JMenuItem myMap;

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
        start();
    }

    private void start() {
        final ImageIcon img = new ImageIcon(ICON_IMAGE);
        setIconImage(img.getImage());

        myDungeon.add(myMap);
        myMenuBar.add(myDungeon);

        setJMenuBar(myMenuBar);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
