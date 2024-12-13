package Controller;

import Model.Action;
import Model.Direction;
import Model.Gremlin;
import Model.Hero;
import Model.MazeGenerator;
import Model.Monster;
import Model.MoveHandler;
import Model.Ogre;
import Model.Priestess;
import Model.Room;
import Model.Skeleton;
import Model.Thief;
import Model.WallType;
import Model.Warrior;
import View.DungeonGUI;
import View.SettingsPanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.*;
import java.util.Random;

public class DungeonController implements Serializable {
    private MazeGenerator myMaze;
    private DungeonGUI myMainView;
    private SettingsPanel mySettingsPanel;
    private Hero myHero;
    private JDialog myMapDialog;
    private JPanel myMapPanel;
    private Monster myMonster;

    public DungeonController(final DungeonGUI theMainView) {
        myMainView = theMainView;
        mySettingsPanel = new SettingsPanel(theMainView);

        mySettingsPanel.addPropertyChangeListener(evt -> {
            if ("Load".equals(evt.getPropertyName())) {
                loadGame();
            }
        });
    }

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

    public void setGameSetting(final String thePlayerName, final int theHeroSelection, final int theDifficultyLevel) {
        if (theHeroSelection == 0) {
            //myGameplay.setIcon(new ImageIcon("images/thief_in_dungeon.png"));
            myHero = new Thief(thePlayerName);
        } else if (theHeroSelection == 1) {
            //myGameplay.setIcon(new ImageIcon("images/warrior_in_dungeon.png"));
            myHero = new Warrior(thePlayerName);
        } else {
            //myGameplay.setIcon(new ImageIcon("images/priestess_in_dungeon.png"));
            myHero = new Priestess(thePlayerName);
        }
        generateMaze(theDifficultyLevel);
    }

    public ImageIcon getHeroImageBasedOnResults(String theResults) {
        return theResults == "Won" ? myHero.getHeroWonImage() : myHero.getHeroLostImage();
    }

    public JDialog getMapDialog() {
        return myMapDialog;
    }

    public Hero getHero() {
        return myHero;
    }
    public Monster getMyMonster() {
        return myMonster;
    }

    public void setMonster() {
        final Random random = new Random();
        int randomInt = random.nextInt(3);
        switch(randomInt) {
            case 0:
                myMonster = new Gremlin();
                //setMonsterHp();
                //setMonsterDmg();
                break;
            case 1:
                myMonster = new Ogre();
                //setMonsterHp();
                //setMonsterDmg();
                break;
            case 2:
                myMonster = new Skeleton();
//                setMonsterHp();
//                setMonsterDmg();
                break;
        }
    }
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
    private DungeonGUI getMyMainView() {
        return myMainView;
    }
    private SettingsPanel getMySettingsPanel() {
        return mySettingsPanel;
    }
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

    public void startGame() {
        myMainView.initGui(this, mySettingsPanel);
    }

}
