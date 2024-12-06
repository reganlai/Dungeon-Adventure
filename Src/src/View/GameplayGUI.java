package View;

import Model.Direction;
import Model.Hero;
import Model.MazeGenerator;
import Model.MoveHandler;
import Model.Priestess;
import Model.Room;
import Model.Thief;
import Model.Warrior;
import Model.WallType;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class GameplayGUI extends JPanel {
    private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 500;
    /** The main frame shared across different classes to update the same frame.*/
    private final JFrame myMainFrame;
    /** The menu bar that holds all the menus.*/
    private final JMenuBar myMenubar;
    /** The Gameplay menu. This menu holds the map.*/
    private final JMenu myGameplayMenu;
    /** The map menu. */
    private final JMenuItem myMap;
    /** The help menu. */
    private final JMenu myHelp;
    /** The menu item that displays the game instructions.*/
    private final JMenuItem myInstructions;
    private final JMenuItem myInventory;
    private JMenuItem mySave;
    private JMenuItem myLoad;

    private FightScene myFightScenePanel;
    private ExitGUI myExitPanel;

    /** The CardLayout that deals with the screen changing.*/
    private final CardLayout myCardLayout;
    /** The parent panel for all the screens. Used by the CardLayout.*/
    private final JPanel myCardPanel;
    private JMenuItem myControls;

    private JLabel myGameplay;
    /** The maze that the player is in. */
    private MazeGenerator myMaze;
    /** The player name. */
    private String myPlayerName;
    private int myClass;
    /** The difficulty level. */
    private int myDifficulty;

    /** The hero that the user chose*/
    private Hero myHero;
    //private DungeonCharacter myHero;

    private JDialog myMapPopup;

    private JPanel myMazeMap;

    private JTextArea myInventoryText;
    private JLabel myMessage;
    private JLabel mySecondMessage;
    private JLabel myItem;
    private JLabel myUpArrow;
    private JLabel myDownArrow;
    private JLabel myRightArrow;
    private JLabel myLeftArrow;




    public GameplayGUI(final String thePlayerName,
                       final int theClass,
                       final int theDifficulty,
                       final JFrame theMainFrame,
                       final JPanel theCardPanel,
                       final CardLayout theCardLayout) {
        super();
        setLayout(null);
        myPlayerName = thePlayerName;
        myClass = theClass;
        myDifficulty = theDifficulty;
        myMainFrame = theMainFrame;
        myCardPanel = theCardPanel;
        myCardLayout = theCardLayout;
        myMenubar = new JMenuBar();
        myGameplayMenu = new JMenu("Gameplay");
        myMap = new JMenuItem("Map");
        myHelp = new JMenu("Help");
        myInstructions = new JMenuItem("Instructions");
        myInventory = new JMenuItem("Inventory");
        myControls = new JMenuItem("Controls");
        mySave = new JMenuItem("Save");
        myLoad = new JMenuItem("Load");
        myGameplay = new JLabel();
        myMessage = new JLabel();
        mySecondMessage = new JLabel();
        myUpArrow = new JLabel();
        myDownArrow = new JLabel();
        myRightArrow = new JLabel();
        myLeftArrow = new JLabel();
        myItem = new JLabel();
        setArrows();
        setMyMessage();
        setGameplay();
        initGameScreen();
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }

    private void initGameScreen() {
        keyboardArrowClicked();
        upArrowClicked();
        downArrowClicked();
        leftArrowClicked();
        rightArrowClicked();
        setInstructions();
        setControls();
        initMaze();
        setMap();
        setMenuBar();
    }

    private void initMaze() {
        switch (myDifficulty) {
            case 0:
                myMaze = new MazeGenerator(6,6);
                break;
            case 1:
                myMaze = new MazeGenerator(10,10);
                break;
            default:
                myMaze = new MazeGenerator(12,12);

        }
        myHero.setMyY(myMaze.getMySpawnInRow());
        myHero.setMyX(myMaze.getMySpawnInCol());
    }

    private void setMenuBar() {
        //
        myGameplayMenu.add(myMap);
        myGameplayMenu.addSeparator();
        myInventory.addActionListener(event-> {
            InventoryGUI inventory = new InventoryGUI(myHero);
        });
        myGameplayMenu.add(myInventory);
        myGameplayMenu.addSeparator();
        myGameplayMenu.add(mySave);
        myGameplayMenu.add(myLoad);
        myHelp.add(myInstructions);
        myHelp.add(myControls);


        myMenubar.add(myGameplayMenu);
        myMenubar.add(myHelp);
        myMainFrame.setJMenuBar(myMenubar);

    }
    public void setMap() {
        myMap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (myMapPopup == null) {
                    initializeMapPopup();
                }
                updateMapDisplay();
                myMapPopup.setVisible(true);
            }
        });
    }
    private void initializeMapPopup() {
        GridLayout mazeGrid = new GridLayout(myMaze.getRows(), myMaze.getCol());
        myMapPopup = new JDialog(myMainFrame,"Map");
        myMazeMap = new JPanel(mazeGrid);

        // Puts a panel (room) in each grid dimensions
        for (int grid = 0; grid < mazeGrid.getRows() * mazeGrid.getColumns(); grid++) {
            JPanel room = new JPanel();
            myMazeMap.add(room);
        }

        myMapPopup.setSize(400, 400);
        myMapPopup.add(myMazeMap);
        myMapPopup.setLocationRelativeTo(myMainFrame);
    }
    private void updateMapDisplay() {
        Component[] gridTo1DArray = myMazeMap.getComponents();

        for (int row = 0; row < myMaze.getRows(); row++) {
            for (int col = 0; col < myMaze.getCol(); col++) {
                int componentIndex = row * myMaze.getCol() + col;
                JPanel currRoom = (JPanel) gridTo1DArray[componentIndex];
                //Clear old data
                currRoom.removeAll();
                currRoom.add(new JLabel(myMaze.getMaze()[row][col].toRoomString()));
                //currRoom.add(new JLabel(myMaze.getMaze()[row][col].getRoomOccupant()));
                Border currRoomWallsStatus = createRoomWalls(row, col, currRoom);
                currRoom.setBorder(currRoomWallsStatus);
            }
        }
        myMazeMap.revalidate();
        myMazeMap.repaint();
    }



    private Border createRoomWalls(final int theRow, final int theCol, final JPanel thePanel) {
        Border north, south, west, east;
        int top, left, bottom, right;
        if (myMaze.getMaze()[theRow][theCol].getNorthWall() == WallType.HORIZONTAL_WALL) {
            //north = BorderFactory.createLineBorder(Color.DARK_GRAY, 5);
            top = 3;
        } else {
            //north = BorderFactory.createDashedBorder(Color.RED, 1, 3);
            top = 1;
        }
        if (myMaze.getMaze()[theRow][theCol].getSouthWall() == WallType.HORIZONTAL_WALL) {
            //south = BorderFactory.createLineBorder(Color.DARK_GRAY, 5);
            bottom = 3;
        } else {
            //south = BorderFactory.createDashedBorder(Color.RED, 2, 2);
            bottom = 1;
        }
        if (myMaze.getMaze()[theRow][theCol].getWestWall() == WallType.VERTICAL_WALL) {
            //west = BorderFactory.createLineBorder(Color.DARK_GRAY, 5);
            left = 3;
        } else {
            //west = BorderFactory.createDashedBorder(Color.RED, 2, 2);
            left = 1;

        }
        if (myMaze.getMaze()[theRow][theCol].getEastWall() == WallType.VERTICAL_WALL) {
            //east = BorderFactory.createLineBorder(Color.DARK_GRAY, 5);
            right = 3;
        } else {
            //east = BorderFactory.createDashedBorder(Color.RED, 2, 2);
            right = 1;
        }
        return BorderFactory.createMatteBorder(top, left, bottom, right, Color.BLACK);
    }



    /**
     * Sets the icon of myGameplay JLabel initially to the right image.
     */
    private void setGameplay() {
        setVisible(true);
        myGameplay.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        if (myClass == 0) {
            myGameplay.setIcon(new ImageIcon("images/thief_in_dungeon.png"));
            myHero = new Thief(myPlayerName);
        } else if (myClass == 1) {
            myGameplay.setIcon(new ImageIcon("images/warrior_in_dungeon.png"));
            myHero = new Warrior(myPlayerName);
        } else {
            myGameplay.setIcon(new ImageIcon("images/priestess_in_dungeon.png"));
            myHero = new Priestess(myPlayerName);
        }

        myExitPanel = new ExitGUI(myMainFrame, myCardLayout, myCardPanel, myHero, "placeholder");
        myCardPanel.add(myExitPanel, "Exit");
        myFightScenePanel = new FightScene(myMainFrame, myHero, myExitPanel, myCardLayout, myCardPanel);
        myCardPanel.add(myFightScenePanel, "Fight");
        myGameplay.setOpaque(true);
        add(myGameplay);
    }
    public JPanel getMyExitPanel() {
        return myExitPanel;
    }



    private void setMyMessage() {
        myMessage.setText("Welcome to the dungeon, " + myPlayerName);
        myMessage.setBounds(370, 370, 500, 30);
        mySecondMessage.setBounds(445, 385, 500, 30);
        myMessage.setForeground(Color.WHITE);
        mySecondMessage.setForeground(Color.WHITE);
        myMessage.setVisible(true);
        mySecondMessage.setVisible(true);
        add(myMessage);
        add(mySecondMessage);
    }

    private void setArrows() {
        myItem.setBounds(540, 265, 100, 100);
        add(myItem);

        myUpArrow.setIcon(new ImageIcon("images/up.png"));
        myUpArrow.setBounds(820, 230, 60, 60);
        myRightArrow.setIcon(new ImageIcon("images/right.png"));
        myRightArrow.setBounds(880, 290, 60, 60);
        myDownArrow.setIcon(new ImageIcon("images/down.png"));
        myDownArrow.setBounds(820, 350, 60, 60);
        myLeftArrow.setIcon(new ImageIcon("images/left.png"));
        myLeftArrow.setBounds(760, 290, 60, 60);
        myUpArrow.setOpaque(false);
        myRightArrow.setOpaque(false);
        myDownArrow.setOpaque(false);
        myLeftArrow.setOpaque(false);
        add(myUpArrow);
        add(myRightArrow);
        add(myDownArrow);
        add(myLeftArrow);
    }

    private void setInstructions() {
        myInstructions.addActionListener(e ->{
            JOptionPane.showMessageDialog(null,
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
            JOptionPane.showMessageDialog(null,
                    "You may click the arrows on the screen or the \n" +
                            "arrow keys on your keyboard to move around the dungeon.",
                    "Controls",
                    JOptionPane.INFORMATION_MESSAGE);
        });
    }

    private void keyboardArrowClicked() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(e -> {

            if (e.getID() == KeyEvent.KEY_PRESSED) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        movingUp();
                        break;
                    case KeyEvent.VK_DOWN:
                        movingDown();
                        break;
                    case KeyEvent.VK_LEFT:
                        movingLeft();
                        break;
                    case KeyEvent.VK_RIGHT:
                        movingRight();
                        break;
                }
            }
            return false; // Ensure other components can still process the key event
        });
    }

    /**
     * Does the necessary action based on what the player encountered.
     * @param theMove
     */
    private synchronized String doSomethingWithItem(final MoveHandler theMove) {
        final Room newRoom = theMove.getMyNewRoom();
        final String item = newRoom.getRoomOccupant();

        if (item == "M") {
            myFightScenePanel.fight();
            myCardLayout.show(myCardPanel, "Fight");
            theMove.getMyNewRoom().setMyEmptyRoom(true);
        } else if (item == "P" || item == "I" || item == "E" || item == "A") {
            myHero.addPillarCollected();
            newRoom.setMyEmptyRoom(true);
        } else if (item == "H") {
            if (myHero.getMyHealthPotions() > 4) {
                newRoom.setMyEmptyRoom(false);
            } else {
                newRoom.setMyEmptyRoom(true);
                myHero.addHealthPotion();
            }
        }



        // Prompt user to pick or leave the item.
        // Do logic for if the picked up the item, say health potion, set the myEmptyCurrentRoom to true.
        // This will let the program know that that room should now be empty after the user moves to another room.
        // If they don't pick up the item, set it to false to keep that item in that room.
        updateMapDisplay();
        return item;
    }

    private void upArrowClicked() {
        myUpArrow.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                movingUp();
            }
        });
    }

    private void downArrowClicked() {
        myDownArrow.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                movingDown();
            }
        });
    }

    private void leftArrowClicked() {
        myLeftArrow.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                movingLeft();
            }
        });
    }

    private void rightArrowClicked() {
        myRightArrow.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                movingRight();
            }
        });
    }

    private void movingUp() {
        System.out.println(myMaze.toString());
        System.out.println(myHero.getMyY() + " " + myHero.getMyX());

        MoveHandler move2 = myMaze.move(Direction.NORTH, myHero);
        if (move2.getSuccess()) {
            final String item = doSomethingWithItem(move2);
            updateVisuals(item, "up");
        } else {
            cantMove("up");
        }
    }

    private void movingDown() {
        System.out.println(myMaze.toString());
        System.out.println(myHero.getMyY() + " " + myHero.getMyX());

        MoveHandler move2 = myMaze.move(Direction.SOUTH, myHero);
        if (move2.getSuccess()) {
            final String item = doSomethingWithItem(move2);
            updateVisuals(item, "down");
        } else {
            cantMove("down");
        }
    }

    private void movingRight() {
        System.out.println(myMaze.toString());
        System.out.println(myHero.getMyY() + " " + myHero.getMyX());
        MoveHandler move2 = myMaze.move(Direction.EAST, myHero);
        if (move2.getSuccess()) {
            final String item = doSomethingWithItem(move2);
            updateVisuals(item, "right");
        } else {
            cantMove("right");
        }

    }

    private void movingLeft() {
        System.out.println(myMaze.toString());
        System.out.println(myHero.getMyY() + " " + myHero.getMyX());

        MoveHandler move2 = myMaze.move(Direction.WEST, myHero);
        if (move2.getSuccess()) {
            final String item = doSomethingWithItem(move2);
            updateVisuals(item, "left");
        } else {
            cantMove("left");
        }

    }

    private void updateVisuals(final String theItem, final String theDirection) {
        switch (theItem) {
            case "A", "E", "I", "P":
                ImageIcon pillar = new ImageIcon("images/pillar.png");
                Image scaledPillar = pillar.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                myItem.setIcon(new ImageIcon(scaledPillar));
                myItem.setVisible(true);
                mySecondMessage.setText("You moved " + theDirection + " and found a pillar!");
                mySecondMessage.setBounds(400, 385, 500, 30);
                break;
            case "H":
                ImageIcon potion = new ImageIcon("images/healthpotion.png");
                Image scaledPotion = potion.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                myItem.setIcon(new ImageIcon(scaledPotion));
                myItem.setVisible(true);
                mySecondMessage.setText("You moved " + theDirection + " and found a health potion!");
                mySecondMessage.setBounds(380, 385, 500, 30);
                break;
            case "O":
                int pillarsCollected = myHero.getMyPillarsCollected();
                if (pillarsCollected < 4) {
                    mySecondMessage.setText("You found the exit but you haven't collected all the pillars yet!");
                    mySecondMessage.setBounds(320, 385, 500, 30);
                } else {
                    myExitPanel.setGameResult("Won");
                    myCardLayout.show(myCardPanel, "Exit");
                }

                break;
            case "":
                myItem.setVisible(false);
                mySecondMessage.setText("You moved " + theDirection);
                mySecondMessage.setBounds(445, 385, 500, 30);
                break;
        }

    }

    private void cantMove(final String theDirection) {
        mySecondMessage.setBounds(440, 385, 500, 30);
        mySecondMessage.setText("You can't move " + theDirection);
    }


}