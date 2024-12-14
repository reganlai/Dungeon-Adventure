package Model;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;
import java.io.Serializable;

/**
 * Represents a Priestess that extends Hero.
 *
 * @author Evan Tran
 */
public class Priestess extends Hero implements Serializable {

    /** A generated serialization ID. */
    @Serial
    private static final long serialVersionUID = 2956966238326498914L;

    /** String path for the image of a standing priestess. */
    private final static ImageIcon STANDING_PRIESTESS = new ImageIcon("images/standingpriestess.png");

    /** String path for the image of an attacking priestess. */
    private final static ImageIcon PRIESTESS_ATTACK = new ImageIcon("images/priestessattack.png");

    /** String path for the image of a blocking priestess. */
    private final static ImageIcon PRIESTESS_BLOCK = new ImageIcon("images/priestessblock.png");

    /** String path for the image of priestess winning the game. */
    private final static ImageIcon PRIESTESS_WON = new ImageIcon("images/priestesswon.png");

    /** String path for the image of priestess losing the game. */
    private final static ImageIcon PRIESTESS_LOST = new ImageIcon("images/priestesslost.png");

    /** String path for the image of priestess standing in the dungeon. */
    private final static ImageIcon PRIESTESS_IN_DUNGEON = new ImageIcon("images/priestess_in_dungeon.png");

    /** Minimum amount priestess heals herself when using special. */
    private final int myMinHeal = 20;

    /** Maximum amount priestess heals herself when using special. */
    private final int myMaxHeal = 50;

    /**
     * Creates a Priestess with its respective fields.
     */
    public Priestess(final String theName) {
        super(theName, 75, 25, 45, 5, .7, .3, 75);

    }

    /**
     * @return image that shows this hero standing in the dungeon.
     */
    @Override
    public ImageIcon getHeroInDungeon() {
        return PRIESTESS_IN_DUNGEON;
    }

    /**
     * @return image of the hero according to the action chosen by the user.
     */
    @Override
    public ImageIcon getImageIcon(final Action theAction) {
        ImageIcon imageIcon;
        switch (theAction) {
            case ATTACK:
                imageIcon = PRIESTESS_ATTACK;
                break;
            case BLOCK:
                imageIcon = PRIESTESS_BLOCK;
                break;
            default:
                imageIcon = STANDING_PRIESTESS;
        }
        return imageIcon;
    }

    /**
     * @return image that this hero has won.
     */
    public ImageIcon getHeroWonImage() {
        return PRIESTESS_WON;
    }

    /**
     * @return image that this hero has lost.
     */
    public ImageIcon getHeroLostImage() {
        return PRIESTESS_LOST;
    }


    /**
     * Priestess uses their special ability.
     * @param theOp the monster this hero is fighting
     * @param theMonsterAction the monster's action
     */
    @Override
    public boolean specialAbility(final Monster theOp, final Action theMonsterAction) {
        int healAmount = (int) (Math.random() * (myMaxHeal - myMinHeal + 1)) + myMinHeal;
        System.out.println(getMyName() + "uses healing ability, restoring" + healAmount + " hp.");
        setMyHp(getMyHp() + healAmount);

        return true;
    }

    /**
     * toString used for debugging.
     */
    @Override
    public String toString() {
        return super.toString() +
                "\nClass: Model.Priestess" +
                "\nSpecial Skill: Heal (Restores " + myMinHeal + " - " + myMaxHeal + " HP)";
    }

}
