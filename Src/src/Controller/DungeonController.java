/*
 * TCSS 360 - Dungeon Adventure
 */
package Controller;
import Model.Action;
import Model.Action;
import Model.Direction;
import Model.Hero;
import Model.MazeGenerator;
import Model.Monster;
import Model.MonsterFactory;
import Model.MoveHandler;
import Model.Priestess;
import Model.Room;
import Model.Thief;
import Model.WallType;
import Model.Warrior;
import View.DungeonGUI;
import View.SettingsPanel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.io.*;
import java.util.Random;

/**
 * The controller of this program.
 *
 * @author George Njane
 * @version 1.0
 */
public class DungeonController implements java.io.Serializable {

    /** A generated serialization ID. */
    @Serial
    private static final long serialVersionUID = 2912734733664585654L;

    /** The maze(dungeon) generated and used. */
    private MazeGenerator myMaze;

    /** DungeonGUI. */
    private DungeonGUI myMainView;

    /** The SettingsPanel. */
    private SettingsPanel mySettingsPanel;

    /** The Hero used by the user. */
    private Hero myHero;

    /** JDialog holding the map. */
    private JDialog myMapDialog;

    /** The JPanel representation of the map. */
    private JPanel myMapPanel;

    /** The monster that the user is fighting. */
    private Monster myMonster;

    /**
     * Initializes the controller.
     * @param theMainView DungeonGUI
     */
    public DungeonController(final DungeonGUI theMainView) {
        myMainView = theMainView;
        mySettingsPanel = new SettingsPanel(theMainView);

        mySettingsPanel.addPropertyChangeListener(evt -> {
            if ("Load".equals(evt.getPropertyName())) {
                loadGame();
            }
        });
    }

    /**
     * User moves in that certain direction.
     */
    public void move(final int theDir) {
        Direction dir = Direction.WEST;
        MoveHandler move;
        switch (theDir) {
            case 0 -> dir = Direction.NORTH;
            case 1 -> dir = Direction.SOUTH;
            case 2 -> dir = Direction.EAST;
        }
        move = myMaze.move(dir, myHero);
        if (move.getSuccess()) {
            doSomethingWithItem(move);
        } else {
            String message = "You can't move " + theDir + " direction.";
            myMainView.sendMessage(message);
        }
    }

    /**
     * Updates the GUI according to what's picked up in the dungeon.
     */
    private void doSomethingWithItem(final MoveHandler theMove) {
        final Room newRoom = theMove.getMyNewRoom();
        final String item = newRoom.getRoomOccupant();
        switch (item) {
            case "A", "E", "I", "P":
                myHero.addPillarCollected();
                newRoom.setMyEmptyRoom(true);
                break;
            case "H":
                if (myHero.getMyHealthPotions() > 4) {
                    newRoom.setMyEmptyRoom(false);
                } else {
                    newRoom.setMyEmptyRoom(true);
                    myHero.addHealthPotion();
                    myMainView.sendMessage("You collected a health potion!");
                }
                break;
            case "M":
                myMainView.initiateFight();
                theMove.getMyNewRoom().setMyEmptyRoom(true);
                break;
        }
        myMainView.updateVisuals(item);
        renderMap();
    }

    /**
     * Randomly generates a maze.
     */
    private void generateMaze(final int theDifficultyLevel) {
        switch (theDifficultyLevel) {
            case 0:
                myMaze = new MazeGenerator(6, 6);
                break;
            case 1:
                myMaze = new MazeGenerator(10, 10);
                break;
            default:
                myMaze = new MazeGenerator(12, 12);
        }
        myHero.setMyY(myMaze.getMySpawnInRow());
        myHero.setMyX(myMaze.getMySpawnInCol());
        setMap();
    }

    /**
     * Creates layout for the map.
     */
    private void setMap() {
        //myMapDialog = new JDialog();
        GridLayout mazeGrid = new GridLayout(myMaze.getRows(), myMaze.getCol());
        myMapDialog = new JDialog(myMainView, "Map");
        myMapPanel = new JPanel(mazeGrid);

        // Puts a panel (room) in each grid dimensions
        for (int grid = 0; grid < mazeGrid.getRows() * mazeGrid.getColumns(); grid++) {
            JPanel room = new JPanel();
            myMapPanel.add(room);
        }

        myMapDialog.setSize(400, 400);
        myMapDialog.add(myMapPanel);
        myMapDialog.setLocationRelativeTo(myMainView);
    }

    /**
     * Creates layout for the map.
     */
    public void renderMap() {
        Component[] gridTo1DArray = myMapPanel.getComponents();

        for (int row = 0; row < myMaze.getRows(); row++) {
            for (int col = 0; col < myMaze.getCol(); col++) {
                int componentIndex = row * myMaze.getCol() + col;
                JPanel currRoom = (JPanel) gridTo1DArray[componentIndex];
                //Clear old data
                currRoom.removeAll();
                currRoom.add(new JLabel(myMaze.getMaze()[row][col].toRoomString()));
                Border currRoomWallsStatus = createRoomWalls(row, col, currRoom);
                currRoom.setBorder(currRoomWallsStatus);
            }
        }
        myMapPanel.revalidate();
        myMapPanel.repaint();

    }

    /**
     * Creates walls for each room in the map.
     */
    private Border createRoomWalls(final int theRow, final int theCol, final JPanel thePanel) {
        int top, left, bottom, right;
        if (myMaze.getMaze()[theRow][theCol].getNorthWall() == WallType.HORIZONTAL_WALL) {
            top = 3;
        } else {
            top = 1;
        }
        if (myMaze.getMaze()[theRow][theCol].getSouthWall() == WallType.HORIZONTAL_WALL) {
            bottom = 3;
        } else {
            bottom = 1;
        }
        if (myMaze.getMaze()[theRow][theCol].getWestWall() == WallType.VERTICAL_WALL) {
            left = 3;
        } else {
            left = 1;
        }
        if (myMaze.getMaze()[theRow][theCol].getEastWall() == WallType.VERTICAL_WALL) {
            right = 3;
        } else {
            right = 1;
        }
        return BorderFactory.createMatteBorder(top, left, bottom, right, Color.BLACK);
    }

    /**
     * Creates a new hero according to the user's selection.
     */
    public void setGameSetting(final String thePlayerName, final int theHeroSelection, final int theDifficultyLevel) {
        if (theHeroSelection == 0) {
            myHero = new Thief(thePlayerName);
        } else if (theHeroSelection == 1) {
            myHero = new Warrior(thePlayerName);
        } else {
            myHero = new Priestess(thePlayerName);
        }
        generateMaze(theDifficultyLevel);
    }

    /**
     * @return returns image icon depending on whether user had won or not.
     */
    public ImageIcon getHeroImageBasedOnResults(String theResults) {
        return theResults == "Won" ? myHero.getHeroWonImage() : myHero.getHeroLostImage();
    }

    /**
     * @return the map dialog.
     */
    public JDialog getMapDialog() {
        return myMapDialog;
    }
     /**
     * @return the hero.
     */
    public Hero getHero() {
        return myHero;
    }

    /**
     * @return the monster.
     */
    public Monster getMyMonster() {
        return myMonster;
    }

    /**
     * Generates monster.
     */
    public void setMonster() {
        final Random random = new Random();
        int randomInt = random.nextInt(3);
        switch(randomInt) {
            case 0:
                myMonster = MonsterFactory.createMonster("Gremlin");
                //setMonsterHp();
                //setMonsterDmg();
                break;
            case 1:
                myMonster = MonsterFactory.createMonster("Ogre");
                //setMonsterHp();
                //setMonsterDmg();
                break;
            case 2:
                myMonster = MonsterFactory.createMonster("Skeleton");
//                setMonsterHp();
//                setMonsterDmg();
                break;
        }
    }

    /**
     * @return hero's image according to its action.
     */
    public ImageIcon getMyHeroImage(final int theAction) {
        ImageIcon image = myHero.getImageIcon(Action.STANDBY);
        switch (theAction) {
            case 1:
                image = myHero.getImageIcon(Action.ATTACK);
                break;
            case 2:
                image = myHero.getImageIcon(Action.BLOCK);
                break;
            case 3:
                image = myHero.getImageIcon(Action.SPECIAL);
        }
        return image;
    }

    /**
     * @return monster image according to its current action.
     */
    public ImageIcon getMyMonsterImage(final int theAction) {
        ImageIcon image = myMonster.getImageIcon(Action.STANDBY);
        switch (theAction) {
            case 1:
                image = myMonster.getImageIcon(Action.ATTACK);
                break;
            case 2:
                image = myMonster.getImageIcon(Action.BLOCK);
                break;
            case 3:
                image = myMonster.getImageIcon(Action.SPECIAL);
        }
        return image;
    }

    /**
     * Saves game.
     */
    public void saveGame() {
        try {
            FileOutputStream fos = new FileOutputStream("game_adv.sav");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.flush();
            oos.close();

            System.out.println("Game saved!");

        } catch (Exception e) {
            System.out.println("Failed to save the game:(");
            System.out.println(e.getClass() + ": " + e.getMessage());
        }
    }

    /**
     * @return DungeonGUI.
     */
    private DungeonGUI getMyMainView() {
        return myMainView;
    }

    /**
     * @return SettingsPanel.
     */
    private SettingsPanel getMySettingsPanel() {
        return mySettingsPanel;
    }

    /**
     * Loads previously saved game.
     */
    private void loadGame() {
        try (FileInputStream fis = new FileInputStream("game_adv.sav");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            DungeonController controller = (DungeonController) ois.readObject();

            if (myMainView != null) {
                myMainView.dispose(); // Close and release resources of the old frame
                myMainView = null;
            }
            if (controller.getMySettingsPanel() == null) {
                System.out.println("Settings is null");
            }
            myMainView = controller.getMyMainView();   // Update the reference to the new frame
            myMainView.setVisible(true);
            myMainView.setScreen();

            myMainView.initGui(controller, controller.getMySettingsPanel());
            myMainView.handleSettingsInput(false);
            //myMainView.reconstruct();

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * User attacks monster.
     */
    public void attack() {
        Action theOpAction = myMonster.getmyAdaptiveCounterAttack().generateAttack();
        if (myHero.isAlive() && myMonster.isAlive()) {
            myMainView.getMyFightScene().setHeroImage(myHero.getImageIcon(Action.ATTACK));
            myMainView.getMyFightScene().setMyMonsterImage(myMonster.getImageIcon(theOpAction));

            myHero.attack(myMonster, theOpAction);

            myMainView.getMyFightScene().paintScreen();
        }
        if (!myMonster.isAlive() && myHero.isAlive()) {
            myMainView.doneFight(true);
        } else if (myMonster.isAlive() && !myHero.isAlive() || !myMonster.isAlive() && !myHero.isAlive()){
            myMainView.doneFight(false);
        }
        myMainView.getMyFightScene().paintScreen();
        // continue the fight otherwise.
    }

    /**
     * User blocks.
     */
    public void block() {
        Action theOpAction = myMonster.getmyAdaptiveCounterAttack().generateAttack();
        myMonster.getmyAdaptiveCounterAttack().recordPlayerAction(Action.BLOCK);
        if (myHero.isAlive() && myMonster.isAlive()) {
            myMainView.getMyFightScene().setHeroImage(myHero.getImageIcon(Action.BLOCK));
            myMainView.getMyFightScene().setMyMonsterImage(myMonster.getImageIcon(theOpAction));

            myHero.shieldDamage(theOpAction);

            myMainView.getMyFightScene().paintScreen();
        }
        if (!myMonster.isAlive() && myHero.isAlive()) {
            myMainView.doneFight(true);
        } else if (myMonster.isAlive() && !myHero.isAlive() || !myMonster.isAlive() && !myHero.isAlive()){
            myMainView.doneFight(false);
        }
        myMainView.getMyFightScene().paintScreen();
        // continue the fight otherwise.
    }

    /**
     * User uses special.
     */
    public void special() {
        Action theOpAction = myMonster.getmyAdaptiveCounterAttack().generateAttack();
        if (myHero.isAlive() && myMonster.isAlive()) {
            myMainView.getMyFightScene().setHeroImage(myHero.getImageIcon(Action.ATTACK));
            myMainView.getMyFightScene().setMyMonsterImage(myMonster.getImageIcon(theOpAction));

            myHero.specialAbility(myMonster, theOpAction);

            myMainView.getMyFightScene().paintScreen();
        }
        if (!myMonster.isAlive() && myHero.isAlive()) {
            myMainView.doneFight(true);
        } else if (myMonster.isAlive() && !myHero.isAlive() || !myMonster.isAlive() && !myHero.isAlive()){
            myMainView.doneFight(false);
        }
        myMainView.getMyFightScene().paintScreen();
        // continue the fight otherwise.
    }

    /**
     * Starts the game.
     */
    public void startGame() {
        myMainView.initGui(this, mySettingsPanel);
    }

}
