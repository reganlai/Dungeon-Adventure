package Model;

import javax.swing.*;
import java.awt.*;

public abstract class Hero extends DungeonCharacter{

    private int myHealthPotions;
    private int myVisionPotions;
    private final double myChanceToBlock;
    private int myPillarsCollected;


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
    Hero(String theName,
         int theHp,
         int theMinAttack, int theMaxAttack, int theAttackSpd, double theHitChance,
            double theChanceToBlock, int theMaxHp, int theHealthPotions, int theVisionPotions) {
        super(theName, theHp, theMinAttack, theMaxAttack, theAttackSpd, theHitChance, theMaxHp);
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
    public abstract void specialAbility(final DungeonCharacter theOp);

    public double getMyChanceToBlock(){
        return myChanceToBlock;
    }
    public int getMyHealthPotions(){
        return myHealthPotions;
    }
    public int getMyVisionPotions(){
        return myVisionPotions;
    }
    public int getMyPillarsCollected() { return myPillarsCollected; }

    public void addHealthPotion() {
        myHealthPotions++;
    }
    public void addVisionPotion() {
        myVisionPotions++;
    }
    public void addPillarCollected() { myPillarsCollected++; }

    public void usePotion() {
        if(myHealthPotions > 0) {
            myHealthPotions--;
            int hp = getMyHp();
            hp += 25;
            if (hp > getMyMaxHp()) {
                setMyHp(getMyMaxHp());
            } else {
                setMyHp(hp);
            }
            System.out.println(getMyName() + " used a healing postion!");

        } else {
            System.out.println(getMyName() + " has no potions left!");
        }

    }

    public void useVisionPotion(){
        if(myVisionPotions > 0) {
             myVisionPotions--;
             System.out.println(getMyName() + " used a vision potion!");

        } else {
            System.out.println(getMyName() + " has no vision potions left");

        }
    }

    public String toString() {
        return "Model.Hero: " + getMyName() +
                "\nHp: " + getMyHp() +
                "\nHealing Potions: " + getMyHealthPotions() +
                "\nVision Potions: " + getMyVisionPotions();
                // "\nPillars Found: " + pillars;
    }

}
