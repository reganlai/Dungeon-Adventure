package Model;

import javax.swing.*;
import java.io.Serial;
import java.io.Serializable;

/**
 * Represents a Thief that extends Hero.
 *
 * @author Evan Tran
 */
public class Thief extends Hero implements Serializable {

    /** A generated serialization ID. */
    @Serial
    private static final long serialVersionUID = -8405930066486030078L;

    /** String path for the image of a standing thief. */
    private final static ImageIcon STANDING_THIEF = new ImageIcon("images/standingthief.png");

    /** String path for the image of an attacking thief. */
    private final static ImageIcon THIEF_ATTACK = new ImageIcon("images/thiefattack.png");

    /** String path for the image of a blocking thief. */
    private final static ImageIcon THIEF_BLOCK = new ImageIcon("images/thiefblock.png");

    /** String path for the image of thief winning the game. */
    private final static ImageIcon THIEF_WON = new ImageIcon("images/thiefwon.png");

    /** String path for the image of thief losing the game. */
    private final static ImageIcon THIEF_LOST = new ImageIcon("images/thieflost.png");

    /** String path for the image of thief standing in the dungeon. */
    private final static ImageIcon THIEF_IN_DUNGEON = new ImageIcon("images/thief_in_dungeon.png");

    /** Thief's chance of hitting using its special. */
    private final double mySpecialChance = .4;

    /** Thief's chance of hitting using its special. */
    private final double myCaughtChance = .2;

    /**
     * Creates a Thief with its respective fields.
     */
    public Thief (final String theName) {
        super(theName, 150, 20, 40, 6,
                .8, 0.4, 150);

    }

    /**
     * @return image that shows this hero standing in the dungeon.
     */
    public ImageIcon getHeroInDungeon() {
        return THIEF_IN_DUNGEON;
    }

    /**
     * @return image of the hero according to the action chosen by the user.
     */
    public ImageIcon getImageIcon(final Action theAction) {
        ImageIcon imageIcon;
        switch (theAction) {
            case ATTACK:
                imageIcon = THIEF_ATTACK;
                break;
            case BLOCK:
                imageIcon = THIEF_BLOCK;
                break;
            default:
                imageIcon = STANDING_THIEF;
        }
        return imageIcon;
    }

    /**
     * @return image that this hero has won.
     */
    public ImageIcon getHeroWonImage() {
        return THIEF_WON;
    }

    /**
     * @return image that this hero has lost.
     */
    public ImageIcon getHeroLostImage() {
        return THIEF_LOST;
    }

    /**
     * Thief uses their special ability.
     * @param theOp the monster this hero is fighting
     * @param theMonsterAction the monster's action
     */
    @Override
    public boolean specialAbility(final Monster theOp, final Action theMonsterAction) {

        boolean success = false;
        System.out.println(getMyName() + "attempts a Surprise Attack!");
        final double chance = Math.random();

        if (chance < 0.4) { //40%
            // 2 attacks
            attack(theOp, theMonsterAction);
            attack(theOp, theMonsterAction);
            success = true;
        } else if (chance < 0.6) { //20%
            success = false;

        } else { //40% remaining
            attack(theOp, theMonsterAction);
            success = true;
        }

        return success;
    }

    /**
     * toString used for debugging.
     */
    @Override
    public String toString() {
        return super.toString() +
                "\nClass: Model.Thief" +
                "\nSpecial Skill: Surprise Attack (Chance: " + (mySpecialChance * 100) + "% success, " +
                (myCaughtChance * 100) + "% chance of getting caught)";
    }
}
