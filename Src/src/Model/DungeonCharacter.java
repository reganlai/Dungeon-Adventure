package Model;

import java.util.Random;

/**
 * Represents an abstract character within the dungeon, with attributes and methods
 * for combat and health management. Characters have customizable stats such as
 * health, attack range, and hit chance.
 *
 * @author Evan Tran
 */
public abstract class DungeonCharacter {
    /** The name of the character */
    private String myName;

    /** The character's current health points */
    private int myHp;

    /** The minimum attack damage the character can deal */
    private final int myMinAttack;

    /** The maximum attack damage the character can deal */
    private final int myMaxAttack;

    /** The character's attack speed */
    private final int myAttackSpd;

    /** The probability (0-1) of successfully hitting a target */
    private final double myHitChance;

    /** The Max Hp this dungeon character can have */
    private final int myMaxHp;
    /** The current X position of the character. */
    private int myX;
    /** The current Y position of the character. */
    private int myY;

    /**
     * Constructs a Model.DungeonCharacter with the specified attributes.
     *
     * @param theName the name of the character
     * @param theHp the initial health points
     * @param theMinAttack the minimum attack damage
     * @param theMaxAttack the maximum attack damage
     * @param theAttackSpd the attack speed of the character
     * @param theHitChance the probability (0-1) that an attack hits
     */
    DungeonCharacter(final String theName, final int theHp, final int theMinAttack,
                     final int theMaxAttack, final int theAttackSpd, final double theHitChance,
                     final int theMaxHp) {
        super();
        myName = theName;
        myHp = theHp;
        myMinAttack = theMinAttack;
        myMaxAttack = theMaxAttack;
        myAttackSpd = theAttackSpd;
        myHitChance = theHitChance;
        myMaxHp = theMaxHp;
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
        myY += theY;
    }
    public void setMyX(final int theX) {
        myX += theX;
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
    public void attack(DungeonCharacter theOp) {
        Random rand = new Random();
        System.out.println(this.myName + " attacks " + theOp.getMyName() + "!");

        if (rand.nextDouble() <= this.myHitChance) {
            int dmg = rand.nextInt(this.myMaxAttack - this.myMinAttack + 1) + this.myMinAttack;

            if (theOp instanceof Hero) {
                Hero heroOp = (Hero) theOp;
                if (rand.nextDouble() <= heroOp.getMyChanceToBlock()) {
                    System.out.println(heroOp.getMyName() + " blocked the attack!");
                    return;
                }
            }

            theOp.takeDamage(dmg);
            System.out.println(this.myName + " deals " + dmg + " damage to " + theOp.getMyName() + "!");
        } else {
            System.out.println(this.myName + " missed the attack!");
        }
    }

    /**
     * Helper method to apply damage.
     * Reduces the character's health points by the specified damage amount.
     *
     * @param theDmg the damage to be taken
     */
    public void takeDamage(int theDmg) {
        this.setMyHp(this.myHp - theDmg);
        if (this.myHp < 0) setMyHp(0);
    }
}
