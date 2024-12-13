package Model;

import View.GameplayGUI;

import java.io.Serial;
import java.io.Serializable;

public class GameState implements Serializable {
    @Serial
    private static final long serialVersionUID = -4114671064039142465L;
    private final MazeGenerator myMaze;
    private final String myPlayerName;
    private final int myClass;
    private final int myDifficulty;
    private final Hero myHero;

    public GameState(final MazeGenerator theMaze, final String thePlayerName, final int theClass,
                     final int theDifficulty, final Hero theHero) {
        myMaze = theMaze;
        myPlayerName = thePlayerName;
        myClass = theClass;
        myDifficulty = theDifficulty;
        myHero = theHero;

    }

    public MazeGenerator getMyMaze() {
        return myMaze;
    }

    public String getMyPlayerName() {
        return myPlayerName;
    }

    public int getMyClass() {
        return myClass;
    }

    public int getMyDifficulty() {
        return myDifficulty;
    }

    public Hero getMyHero() {
        return myHero;
    }
    public String toString() {
        return myPlayerName + "_save.dat";
    }
}
