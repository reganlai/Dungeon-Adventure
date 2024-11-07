package Model;/*
 * TCSS 360 - Dungeon Adventure
 */

/**
 * An enumeration for the available wall types in a room.
 *
 * @author George Njane
 * @version 1.0
 */
public enum WallType {
    HORIZONTAL_WALL("***"),
    HORIZONTAL_DOOR("*_*"),
    VERTICAL_WALL("*"),
    VERTICAL_DOOR("|");

    private final String myWallSymbol;

    /**
     * Constructs a new WallTyoe with the specified string.
     *
     * @param theWallSymbol The string.
     */
    WallType(final String theWallSymbol) {
        myWallSymbol = theWallSymbol;
    }

    /**
     * The symbol representing the type of wall.
     *
     * @return a string symbol of the wall.
     */
    protected String getWallSymbol() {
        return myWallSymbol;
    }
}
