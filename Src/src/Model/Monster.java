package Model;

import javax.swing.*;
import javax.swing.Action;
import java.io.Serial;
import java.io.Serializable;
import java.util.Random;

public abstract class Monster extends DungeonCharacter implements Serializable {

    @Serial
    private static final long serialVersionUID = 6331983131408066245L;
    private final AdaptiveCounterAttack myAdaptiveCounterAttack;
    private final double myHealChance;
    private final int myMinHeal;
    private final int myMaxHeal;
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
            final int theAttackSpd, final double theHitChance, final int theMaxHp,
            final double theHealChance, final int theMinHeal, final int theMaxHeal) {
        super(theName, theHp, theMinAttack, theMaxAttack, theAttackSpd, theHitChance, theMaxHp);
        //super(theName, theHp, theMinAttack, theMaxAttack, theAttackSpd, theMaxHp);

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
        if (theHealChance < 0.0 || theHealChance > 1.0) {
            throw new IllegalArgumentException("Heal chance must be between 0.0 and 1.0.");
        }
        if (theMinHeal < 0 || theMinHeal > theMaxHeal) {
            throw new IllegalArgumentException("Minimum heal must be non-negative and less than or equal to maximum heal.");
        }
        if (theMaxHeal < 0) {
            throw new IllegalArgumentException("Maximum heal must be non-negative.");
        }

        // Assign parameters to instance variables
        myHealChance = theHealChance;
        myMinHeal = theMinHeal;
        myMaxHeal = theMaxHeal;
        myAdaptiveCounterAttack = new AdaptiveCounterAttack();
    }

    public AdaptiveCounterAttack getmyAdaptiveCounterAttack() {
        return myAdaptiveCounterAttack;
    }


    public double getMyHealChance() {
        return myHealChance;
    }

    public int getMyMinHeal(){
        return myMinHeal;
    }

    public int getMyMaxHeal() {
        return myMaxHeal;
    }

    public void heal() {
        Random rand = new Random();
        if (rand.nextDouble() <= this.myHealChance) {
            int healAmount = rand.nextInt(this.myMaxHeal - this.myMinHeal + 1) + this.myMinHeal;
            int hp = getMyHp() + healAmount;
            if(hp > getMyMaxHp()){
                setMyHp(getMyMaxHp());
                System.out.println(getMyName() + "healed for " + (hp - getMyMaxHp()) + " points!" );
            } else {
                setMyHp(hp);
                System.out.println(getMyName() + " healed for " + healAmount + " points!");
            }
        } else {
            System.out.println(getMyName() + " tried to heal but failed!");
        }
    }

    public String toString() {
        return "Monster: " + this.getMyName() +
                "\nHit Points: " + this.getMyHp() +
                "\nAttack Speed: " + this.getMyAttackSpd() +
                "\nChance to Heal: " + this.myHealChance +
                "\nHeal Range: " + this.myMinHeal + " - " + this.myMaxHeal;
    }

    @Override
    public ImageIcon getImageIcon(final Action theAction) {
        return null;
    }
}
