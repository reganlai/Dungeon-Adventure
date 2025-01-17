package Model;

import javax.swing.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Random;

/**
 * Represents an abstract character within the dungeon, with attributes and methods
 * for combat and health management. Characters have customizable stats such as
 * health, attack range, and hit chance.
 *
 * @author Evan Tran
 */
public abstract class DungeonCharacter implements Serializable {

    /** A generated serialization ID. */
    @Serial
    private static final long serialVersionUID = -6818388129184736745L;

    /** The name of the character */
    private String myName;

    /** The character's current health points */
    private int myHp;

    /** The minimum attack damage the character can deal */
    private int myMinAttack;

    /** The maximum attack damage the character can deal */
    private int myMaxAttack;

    /** The character's attack speed */
    private int myAttackSpd;

    /** The probability (0-1) of successfully hitting a target */
    private double myHitChance;

    /** The Max Hp this dungeon character can have */
    private int myMaxHp;

    /** The current X position of the character. */
    private int myX;

    /** The current Y position of the character. */
    private int myY;

    /** Amount of shield a dungeon character can have. */
    private int myShield;

    /**
     * Constructs a DungeonCharacter with the specified attributes.
     *
     * @param theName the name of the character
     * @param theHp the initial health points
     * @param theMinAttack the minimum attack damage
     * @param theMaxAttack the maximum attack damage
     * @param theAttackSpd the attack speed of the character
     * @param theHitChance the probability (0-1) that an attack hits
     */
    //@param theHitChance the probability (0-1) that an attack hits
    protected DungeonCharacter(final String theName, final int theHp, final int theMinAttack,
                               final int theMaxAttack, final int theAttackSpd, final double theHitChance,
                               final int theMaxHp) {
        setName(theName);
        setHp(theHp);
        setMinAttack(theMinAttack);
        setMaxAttack(theMaxAttack, theMinAttack);
        setAttackSpd(theAttackSpd);
        setHitChance(theHitChance);
        setMaxHp(theMaxHp, theHp);

        myShield = 100;
    }

    /**
     * @return the default image of the dungeon character(standby).
     */
    public abstract ImageIcon getImageIcon(final Action theAction);


    /**
     * Sets the maximum hp the dungeon character can have.
     * @param theMaxHp the maximum hp the dungeon character can have
     * @param theHp hp that the dungeon character currently has
     */
    private void setMaxHp(final int theMaxHp, final int theHp) {
        if (theMaxHp <= 0 || theMaxHp < theHp) {
            throw new IllegalArgumentException("Max HP must be greater than zero and not less than current HP.");
        } else {
            myMaxHp = theMaxHp;
        }
    }

    /**
     * Sets the chance of the dungeon character hitting the opponent.
     * @param theHitChance the chance of the dungeon character hitting the opponent
     */
    private void setHitChance(final double theHitChance) {
        if (theHitChance <= 0 || theHitChance >= 1) {
            throw new IllegalArgumentException("Hit chance must be between 0 and 1.");
        } else {
            myHitChance = theHitChance;
        }
    }

    /**
     * Sets thow many attacks the dungeon character can do in one turn.
     * @param theAttackSpd how many attacks the dungeon character can do in one turn
     */
    private void setAttackSpd(final int theAttackSpd) {
        if (theAttackSpd <= 0) {
            throw new IllegalArgumentException("Attack speed must be greater than zero.");
        } else {
            myAttackSpd = theAttackSpd;
        }
    }

    /**
     * Sets the max damage the dungeon character can do.
     * @param theMaxAttack the maximum damage the dungeon character can do to the opponent
     * @param theMinAttack the minimum damage the dungeon character can do to the opponent
     */
    private void setMaxAttack(final int theMaxAttack, final int theMinAttack) {
        if (theMaxAttack <= 0 || theMaxAttack < theMinAttack) {
            throw new IllegalArgumentException("Maximum attack must be greater than zero " +
                    "and not less than minimum attack.");
        } else {
            myMaxAttack = theMaxAttack;
        }
    }

    /**
     * Sets the min damage the dungeon character can do.
     * @param theMinAttack the minimum damage the dungeon character can do to the opponent
     */
    private void setMinAttack(final int theMinAttack) {
        if (theMinAttack <= 0) {
            throw new IllegalArgumentException("Minimum attack must be greater than zero.");
        } else {
            myMinAttack = theMinAttack;
        }
    }

    /**
     * Sets name of the dungeon character.
     * @param theName name of the dungeon character
     */
    private void setName(final String theName) {
        if (theName == null || theName.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        } else {
            myName = theName;
        }
    }

    /**
     * Sets the current hp that the dungeon character has.
     * @param theHp current hp that the dungeon character has
     */
    private void setHp(final int theHp) {
        if (theHp <= 0) {
            throw new IllegalArgumentException("HP must be greater than zero.");
        } else {
            myHp = theHp;
        }
    }


    /**
     * Return the current X position on the maze.
     *
     * @return the character's X position.
     */
    public int getMyX() {
        return myX;
    }

    /**
     * Return the current Y position on the maze.
     *
     * @return the character's Y position.
     */
    public int getMyY() {
        return myY;
    }
    public void setMyY(final int theY) {
        myY = theY;
    }
    public void setMyX(final int theX) {
        myX = theX;
    }


    /**
     * Returns the name of the character.
     *
     * @return the character's name
     */
    public String getMyName() {
        return myName;
    }

    /**
     * Sets the name of the character.
     *
     * @param theName the new name for the character
     */
    public void setMyName(String theName) {
        myName = theName;
    }

    /**
     * Returns the current health points of the character.
     *
     * @return the character's health points
     */
    public int getMyHp() {
        return myHp;
    }

    /**
     * Sets the health points of the character.
     *
     * @param theHp the new health points
     */
    public void setMyHp(int theHp) {
        myHp = theHp;
    }

    /**
     * Returns the minimum attack damage the character can deal.
     *
     * @return the minimum attack damage
     */
    public int getMyMinAttack() {
        return myMinAttack;
    }

    /**
     * Returns the maximum attack damage the character can deal.
     *
     * @return the maximum attack damage
     */
    public int getMyMaxAttack() {
        return myMaxAttack;
    }

    /**
     * Returns the attack speed of the character.
     *
     * @return the attack speed
     */
    public int getMyAttackSpd() {
        return myAttackSpd;
    }

    /**
     * Returns the hit chance of the character.
     *
     * @return the hit chance as a double (0-1)
     */
    public double getMyHitChance() {
        return myHitChance;
    }


    /**
     * Returns the max hp of the character.
     *
     * @return the max hp of the character.
     */
    public int getMyMaxHp() {
        return myMaxHp;
    }

    /**
     * Executes an attack on the specified opponent. If the opponent is a Hero,
     * the method checks if the opponent successfully blocks the attack.
     *
     * @param theOp the opponent being attacked
     */
    public void attack(Monster theOp, final Action theOpAction) {
        Random rand = new Random();
        System.out.println(myName + " attacks " + theOp.getMyName() + "!");

        if (Math.random() < myHitChance) {
            theOp.getmyAdaptiveCounterAttack().recordPlayerAction(Action.ATTACK);
            int dmg;
            if (theOpAction == Action.ATTACK) {
                //Both take damage
                dmg = rand.nextInt(theOp.getMyMaxAttack() - theOp.getMyMinAttack() + 1)
                        + theOp.getMyMinAttack();
                theOp.takeDamage(dmg);
                if (Math.random() < theOp.getMyHitChance()) {
                    dmg = rand.nextInt(myMinAttack - myMinAttack + 1)
                            + myMinAttack;
                    System.out.println(theOp.getMyName() + " deals " + dmg + " damage to " + myName + "!");

                    takeDamage(dmg);
                } else {
                    System.out.println(theOp.getMyName() + " missed the attack!");
                }
            } else if (theOpAction == Action.SPECIAL) {
                //Player takes more damage
                if (Math.random() < theOp.getMyHitChance()) {
                    System.out.println(theOp.getMyName() + " deals " + myMaxAttack + " damage to " + myName + "!");
                    takeDamage(myMaxAttack / 2);
                } else {
                    System.out.println(theOp.getMyName() + " missed the attack!");
                }

                dmg = rand.nextInt(theOp.getMyMaxAttack() - theOp.getMyMinAttack() + 1)
                        + theOp.getMyMinAttack();
                theOp.takeDamage(dmg);
            } else { //If theOp blocks
                theOp.shieldDamage(Action.ATTACK);
            }

        } else {
            System.out.println(myName + " missed the attack!");
        }
    }

    /**
     * @return true if dungeon character is alive, false otherwise.
     */
    public boolean isAlive() {
        return myHp > 0;
    }

    /**
     * Dungeon character shields damage.
     * @param theOpAction the action chosen by the opposite party
     */
    public void shieldDamage(final Action theOpAction) {
        Random rand = new Random();
        System.out.println(this.getMyName() + " blocks.");
        if (theOpAction == Action.ATTACK) {
            int dmg = rand.nextInt(myMaxAttack - myMinAttack + 1)
                    + myMinAttack;
            //Both take damage
            if (dmg > myShield) {
                takeDamage(Math.abs(myShield - dmg));
                myShield = 0;
            } else {
                myShield -= dmg;
            }

        } else if (theOpAction == Action.SPECIAL) {
            //Player takes more damage
            takeDamage(myMaxAttack);
        }
        // else both blocked therefore no damage.

    }

    /**
     * Helper method to apply damage.
     * Reduces the character's health points by the specified damage amount.
     *
     * @param theDmg the damage to be taken
     */
    public void takeDamage(int theDmg) {
        setMyHp(theDmg >= myHp ? 0 : myHp - theDmg);
        System.out.println(this.getMyName() + " took " + theDmg + " damage!!!!!");
    }

    public abstract ImageIcon getImageIcon(javax.swing.Action theAction);
}