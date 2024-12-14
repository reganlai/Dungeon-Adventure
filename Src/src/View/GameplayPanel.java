package View;

import Controller.DungeonController;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.Color;
import java.awt.Image;

public class GameplayPanel extends JPanel {
    private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 500;
    private static KeyBindingsManager myKeyBindingManager;
    private final DungeonController myController;
    private final ExitGUI myExitPanel;

    private DungeonGUI myMainFrame;
    private JMenuBar myMenubar;
    private JMenu myGameplayMenu;
    private JMenuItem myMap;
    private JMenu myHelp;
    private JMenuItem myInstructions;
    private JMenuItem myInventory;
    private JMenuItem myControls;
    private JMenuItem mySave;
    private JLabel myHeroAndBackGround;
    private JLabel myMessage;
    private JLabel mySecondMessage;
    private JLabel myItem;

    protected GameplayPanel(final DungeonGUI theMainFrame, final DungeonController theController,
                            final ExitGUI theExitPanel) {
        super();
        setLayout(null);
        myMainFrame = theMainFrame;
        myController = theController;
        myExitPanel = theExitPanel;
        myItem = new JLabel();
        myMessage = new JLabel();
        mySecondMessage = new JLabel();
        myHeroAndBackGround = new JLabel();

        add(myItem);
        myItem.setBounds(540, 265, 100, 100);

    }
    protected void init() {
        myKeyBindingManager = new KeyBindingsManager(this, myController);
        myKeyBindingManager.keyboardArrowClicked();

        myMenubar = new JMenuBar();
        myGameplayMenu = new JMenu("Gameplay");
        myMap = new JMenuItem("Map");
        myHelp = new JMenu("Help");
        myInstructions = new JMenuItem("Instructions");
        myInventory = new JMenuItem("Inventory");
        myControls = new JMenuItem("Controls");
        mySave = new JMenuItem("Save");

        setMenuBar();
        setMyMessage();
        setHeroLabel();
        setInstructions();
        setControls();

        SwingUtilities.invokeLater(() -> myInstructions.doClick());
    }

    private void setHeroLabel() {
        myHeroAndBackGround.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        myHeroAndBackGround.setIcon(myController.getHero().getHeroInDungeon());
        myHeroAndBackGround.setOpaque(true);
        add(myHeroAndBackGround);

    }

    private void setMyMessage() {
        myMessage.setText("Welcome to the dungeon!");
        myMessage.setBounds(400, 370, 500, 30);
        mySecondMessage.setBounds(445, 385, 500, 30);
        myMessage.setForeground(Color.WHITE);
        mySecondMessage.setForeground(Color.WHITE);
        myMessage.setVisible(true);
        mySecondMessage.setVisible(true);
        add(myMessage);
        add(mySecondMessage);
    }

    private void setMenuBar() {
        myGameplayMenu.add(myMap);
        setMap();
        myGameplayMenu.addSeparator();
        myInventory.addActionListener(event-> {
            InventoryGUI inventory = new InventoryGUI(myController);
        });
        myGameplayMenu.add(myInventory);
        myGameplayMenu.addSeparator();
        mySave.addActionListener(event-> {
            myController.saveGame();
        });

        myGameplayMenu.add(mySave);
        myHelp.add(myInstructions);
        myHelp.add(myControls);

        myMenubar.add(myGameplayMenu);
        myMenubar.add(myHelp);
        myMainFrame.setJMenuBar(myMenubar);
    }

    private void setMap() {
        myMap.addActionListener(e -> {
            myMainFrame.showMap();
        });
    }

    public JLabel getMessageLabel() {
        return mySecondMessage;
    }
    private void setInstructions() {
        myInstructions.addActionListener(e -> {
            JOptionPane.showMessageDialog(myMainFrame,
                    "You are lost in a dungeon, you must collect the four sacred pillars of \n" +
                            "object-oriented programming and navigate to the exit. \n" +
                            "Throughout the dungeon, you may need to fight monsters or pick \n" +
                            "up items that aid your journey. Good luck adventurer!",
                    "Instructions",
                    JOptionPane.INFORMATION_MESSAGE);
        });
    }

    private void setControls() {
        myControls.addActionListener(e ->{
            JOptionPane.showMessageDialog(myMainFrame,
                    "You may click the arrows on the screen or the \n" +
                            "arrow keys on your keyboard to move around the dungeon.",
                    "Controls",
                    JOptionPane.INFORMATION_MESSAGE);
        });
    }
    public void updateVisuals(final String theItem) {
        switch (theItem) {
            case "A", "E", "I", "P":
                ImageIcon pillar = new ImageIcon("images/pillar.png");
                Image scaledPillar = pillar.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                myItem.setIcon(new ImageIcon(scaledPillar));
                myItem.setVisible(true);
                mySecondMessage.setText("You moved and found a pillar!");
                mySecondMessage.setBounds(400, 385, 500, 30);
                break;
            case "H":
                ImageIcon potion = new ImageIcon("images/healthpotion.png");
                Image scaledPotion = potion.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                myItem.setIcon(new ImageIcon(scaledPotion));
                myItem.setVisible(true);
                mySecondMessage.setText("You moved and found a health potion!");
                mySecondMessage.setBounds(380, 385, 500, 30);
                break;
            case "O":
                int pillarsCollected = myController.getHero().getMyPillarsCollected();
                if (pillarsCollected < 4) {
                    mySecondMessage.setText("You found the exit but you haven't collected all the pillars yet!");
                    mySecondMessage.setBounds(320, 385, 500, 30);
                } else {
                    myExitPanel.setGameResult("Won");
                    myMainFrame.exit();
                }

                break;
            default:
                myItem.setVisible(false);
                mySecondMessage.setText("Empty room.");
                mySecondMessage.setBounds(445, 385, 500, 30);
        }
        revalidate();
        repaint();
    }
}
