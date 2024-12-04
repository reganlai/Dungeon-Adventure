package Model;

import javax.swing.*;
import java.util.Random;

/**
 * Represents a hero in the dungeon adventure game.
 * This class extends DungeonCharacter and includes additional attributes
 * and behaviors unique to a hero, such as health potions, vision potions, and the ability
 * to block attacks. The class is abstract, as specific types of heroes (e.g., Warrior, Thief)
 * will extend it.
 *
 * @author Evan Tran
 */
public abstract class Hero extends DungeonCharacter {

    private int myHealthPotions;
    private int myVisionPotions;
    private final double myChanceToBlock;
    private int myPillarsCollected;


    /**
     * Constructs a Hero with the specified attributes.
     *
     * @param theName          the name of the hero
     * @param theHp            the initial health points of the hero
     * @param theMinAttack     the minimum attack damage
     * @param theMaxAttack     the maximum attack damage
     * @param theAttackSpd     the attack speed of the hero
     * @param theHitChance     the probability (0-1) that an attack hits
     * @param theChanceToBlock the probability (0-1) that the hero blocks an attack
     * @param theMaxHp         the maximum health points of the hero
     * @param theHealthPotions the number of health potions the hero starts with
     * @param theVisionPotions the number of vision potions the hero starts with
     */
    Hero(String theName,
         int theHp,
         int theMinAttack, int theMaxAttack, int theAttackSpd, double theHitChance,
         double theChanceToBlock, int theMaxHp, int theHealthPotions, int theVisionPotions) {
        super(theName, theHp, theMinAttack, theMaxAttack, theAttackSpd, theHitChance, theMaxHp);

        if (theChanceToBlock <= 0 || theChanceToBlock >= 1) {
            throw new IllegalArgumentException("Chance to block must be between 0 and 1.");
        }
        if (theHealthPotions < 0) {
            throw new IllegalArgumentException("Health potions cannot be negative.");
        }
        if (theVisionPotions < 0) {
            throw new IllegalArgumentException("Vision potions cannot be negative.");
        }

        myChanceToBlock = theChanceToBlock;
        myHealthPotions = theHealthPotions;
        myVisionPotions = theVisionPotions;
        myPillarsCollected = 0;
    }

    public abstract ImageIcon getImageIcon();
    public abstract ImageIcon getHeroWonImage();
    public abstract ImageIcon getHeroLostImage();
    public abstract ImageIcon getAttackImage();
    public abstract ImageIcon getBlockImage();

    /**
     * Represents the Hero's special ability.
     * @param theOp is the opponent that the special ability will target.
     */
    public abstract void specialAbility(final DungeonCharacter theOp);

    /**
     * @return the chance (0-1) of the hero blocking an attack
     */
    public double getMyChanceToBlock() {
        return myChanceToBlock;
    }

    /**
     * @return the number of health potions the hero currently has
     */
    public int getMyHealthPotions() {
        return myHealthPotions;
    }

    /**
     * @return the number of vision potions the hero currently has
     */
    public int getMyVisionPotions() {
        return myVisionPotions;
    }

    /**
     * @return the number of pillars the hero has collected
     */
    public int getMyPillarsCollected() {
        return myPillarsCollected;
    }

    /**
     * Increments the number of health potions the hero has by 1.
     */
    public void addHealthPotion() {
        myHealthPotions++;
    }

    /**
     * Increments the number of vision potions the hero has by 1.
     */
    public void addVisionPotion() {
        myVisionPotions++;
    }

    /**
     * Increments the number of pillars collected by the hero by 1.
     */
    public void addPillarCollected() {
        myPillarsCollected++;
    }

    /**
     * Uses a health potion, restoring 25 health points.
     * If the hero has no health potions, this method will notify the user.
     */
    public void usePotion() {
        if (myHealthPotions > 0) {
            myHealthPotions--;
            int hp = getMyHp();
            hp += 25;
            if (hp > getMyMaxHp()) {
                setMyHp(getMyMaxHp());
            } else {
                setMyHp(hp);
            }
            System.out.println(getMyName() + " used a healing potion!");
        } else {
            System.out.println(getMyName() + " has no potions left!");
        }
    }

    /**
     * Uses a vision potion, which could reveal additional parts of the dungeon.
     * If the hero has no vision potions, this method will notify the user.
     */
    public void useVisionPotion() {
        if (myVisionPotions > 0) {
            myVisionPotions--;
            System.out.println(getMyName() + " used a vision potion!");
        } else {
            System.out.println(getMyName() + " has no vision potions left!");
        }
    }

    /**
     * Helper method to apply damage while rolling chance to block the damage.
     * @param theDmg the damage to be taken
     */
    @Override
    public void takeDamage(int theDmg) {
        Random rand = new Random();

        if (rand.nextDouble() <= this.getMyChanceToBlock()) {
            System.out.println(this.getMyName() + " blocked the attack!");
        } else {
            setMyHp(getMyHp() - theDmg);
            if (getMyHp() < 0) {
                setMyHp(0);
            }
        }
    }

    /**
     * Returns a string representation of the hero, including their name, health points,
     * and the number of potions they currently have.
     *
     * @return a string representation of the hero
     */
    @Override
    public String toString() {
        return "Hero: " + getMyName() +
                "\nHp: " + getMyHp() +
                "\nHealing Potions: " + getMyHealthPotions() +
                "\nVision Potions: " + getMyVisionPotions();
    }
}
