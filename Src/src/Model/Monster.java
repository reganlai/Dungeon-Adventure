package Model;

import javax.swing.*;
import java.util.Random;

/**
 * Represents a monster in the dungeon adventure game.
 * Monster will change its behaviors according to what the user does.
 *
 * @author Evan Tran
 */
public class Monster extends DungeonCharacter {

    /** An AdaptiveCounterAttack object. */
    private final AdaptiveCounterAttack myAdaptiveCounterAttack;

    /**
     * Constructs a Model.DungeonCharacter with the specified attributes.
     *
     * @param theName      the name of the character
     * @param theHp        the initial health points
     * @param theMinAttack the minimum attack damage
     * @param theMaxAttack the maximum attack damage
     * @param theAttackSpd the attack speed of the character
     * @param theHitChance the probability (0-1) that an attack hits
     */
    Monster(final String theName, final int theHp, final int theMinAttack, final int theMaxAttack,
            final int theAttackSpd, final double theHitChance, final int theMaxHp) {
        super(theName, theHp, theMinAttack, theMaxAttack, theAttackSpd, theHitChance, theMaxHp);

        if (theName == null || theName.trim().isEmpty()) {
            throw new IllegalArgumentException("Monster name must not be null or empty.");
        }
        if (theHp < 0 || theHp > theMaxHp) {
            throw new IllegalArgumentException("HP must be non-negative and less than or equal to max HP.");
        }
        if (theMinAttack < 0 || theMinAttack > theMaxAttack) {
            throw new IllegalArgumentException("Minimum attack damage must be non-negative and less than or equal to maximum attack damage.");
        }
        if (theMaxAttack < 0) {
            throw new IllegalArgumentException("Maximum attack damage must be non-negative.");
        }
        if (theAttackSpd <= 0) {
            throw new IllegalArgumentException("Attack speed must be positive.");
        }
        if (theHitChance < 0.0 || theHitChance > 1.0) {
            throw new IllegalArgumentException("Hit chance must be between 0.0 and 1.0.");
        }
        if (theMaxHp <= 0) {
            throw new IllegalArgumentException("Max HP must be positive.");
        }

        myAdaptiveCounterAttack = new AdaptiveCounterAttack();
    }

    /**
     * @return returns AdaptiveCounterAttack.
     */
    public AdaptiveCounterAttack getmyAdaptiveCounterAttack() {
        return myAdaptiveCounterAttack;
    }

    /**
     * toString used for debugging.
     */
    public String toString() {
        return "Model.Monster: " + this.getMyName() +
                "\nHit Points: " + this.getMyHp() +
                "\nAttack Speed: " + this.getMyAttackSpd();
    }

    /**
     * @return image of the monster according to the determined action.
     */
    @Override
    public ImageIcon getImageIcon(final Action theAction) {
        return null;
    }
}
