package Model;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;
import java.io.Serializable;

/**
 * Represents a Warrior that extends Hero.
 *
 * @author Evan Tran
 */
public class Warrior extends Hero implements Serializable {

    /** A generated serialization ID. */
    @Serial
    private static final long serialVersionUID = 3733480867287879417L;

    /** String path for the image of a standing warrior. */
    private final static ImageIcon STANDING_WARRIOR = new ImageIcon("images/standingwarrior.png");

    /** String path for the image of an attacking warrior. */
    private final static ImageIcon WARRIOR_ATTACK = new ImageIcon("images/warriorattack.png");

    /** String path for the image of a blocking warrior. */
    private final static ImageIcon WARRIOR_BLOCK = new ImageIcon("images/warriorblock.png");

    /** String path for the image of warrior winning the game. */
    private final static ImageIcon WARRIOR_WON = new ImageIcon("images/warriorwon.png");

    /** String path for the image of warrior losing the game. */
    private final static ImageIcon WARRIOR_LOST = new ImageIcon("images/warriorlost.png");

    /** String path for the image of warrior standing in the dungeon. */
    private final static ImageIcon WARRIOR_IN_DUNGEON = new ImageIcon("images/warrior_in_dungeon.png");

    /** Warrior's chance of hitting using its special. */
    private final static double SPECIAL_CHANCE = 0.4;

    /** Minimum damage special is going to deal. */
    private final  int minCrushingBlowDmg = 75;

    /** Maximum damage special is going to deal. */
    private final int maxCrushingBlowDmg = 175;

    /**
     * Creates a Warrior with its respective fields.
     */
    public Warrior(final String theName) {
        super(theName, 125, 35, 60, 4, .8, .2, 125);
    }

    /**
     * @return image that shows this hero standing in the dungeon.
     */
    @Override
    public ImageIcon getHeroInDungeon() {
        return WARRIOR_IN_DUNGEON;
    }

    /**
     * @return image of the hero according to the action chosen by the user.
     */
    @Override
    public ImageIcon getImageIcon(Action theAction) {
        ImageIcon imageIcon;
        switch (theAction) {
            case ATTACK:
                imageIcon = WARRIOR_ATTACK;
                break;
            case BLOCK:
                imageIcon = WARRIOR_BLOCK;
                break;
            default:
                imageIcon = STANDING_WARRIOR;
        }
        return imageIcon;
    }

    @Override
    public ImageIcon getImageIcon(javax.swing.Action theAction) {
        return null;
    }

    /**
     * @return image that this hero has won.
     */
    public ImageIcon getHeroWonImage() {
        return WARRIOR_WON;
    }

    /**
     * @return image that this hero has lost.
     */
    public ImageIcon getHeroLostImage() {
        return WARRIOR_LOST;
    }

    /**
     * Warrior uses their special ability.
     * @param theOp the monster this hero is fighting
     * @param theMonsterAction the monster's action
     */
    @Override
    public boolean specialAbility(final Monster theOp, final Action theMonsterAction) {
        boolean success = false;
        System.out.println(getMyName() + " tries to use Crushing Blow!");
        if (Math.random() <= SPECIAL_CHANCE) {
            int dmg = (int) (Math.random() * (maxCrushingBlowDmg - minCrushingBlowDmg + 1)) + minCrushingBlowDmg;
            theOp.takeDamage(dmg);
            success = true;
            System.out.println("Crushing Blow landed!");
        } else {
            success = false;
            System.out.println("Crushing Blow missed!");
        }

        return success;
    }

    /**
     * @return maximum damage special can do.
     */
    public int getMaxCrushingBlowDmg() {
        return maxCrushingBlowDmg;
    }

    /**
     * @return minimum damage special can do.
     */
    public int getMinCrushingBlowDmg() {
        return minCrushingBlowDmg;
    }

    /**
     * toString used for debugging.
     */
    @Override
    public String toString() {
        return super.toString() +
                "\nClass: Model.Warrior" +
                "\nSpecial Skill: Crushing Blow (Chance: " + (SPECIAL_CHANCE * 100) + "%, Damage: " +
                minCrushingBlowDmg + " - " + maxCrushingBlowDmg + ")";
    }

}
