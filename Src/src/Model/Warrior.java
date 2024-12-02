package Model;

import javax.swing.*;
import java.awt.*;

public class Warrior extends Hero {

    private final static ImageIcon STANDING_WARRIOR = new ImageIcon("images/standingwarrior.png");
    private final static ImageIcon WARRIOR_ATTACK = new ImageIcon("images/warriorattack.png");
    private final static ImageIcon WARRIOR_BLOCK = new ImageIcon("images/warriorblock.png");
    private final static ImageIcon WARRIOR_WON = new ImageIcon("images/warriorwon.png");
    private final double mySpecialChance;
    private final  int minCrushingBlowDmg = 75;
    private final int maxCrushingBlowDmg = 175;



    public Warrior(final String theName) {
        super(theName, 250, 35, 60, 4, .8, .2, 250, 0,0);
        mySpecialChance = .4;
    }
    public ImageIcon getHeroWonImage() {
        return WARRIOR_WON;
    }
    public ImageIcon getImageIcon() {
        return STANDING_WARRIOR;
    }
    public ImageIcon getAttackImage() { return WARRIOR_ATTACK; }
    public ImageIcon getBlockImage() { return WARRIOR_BLOCK; }

    public void specialAbility(final DungeonCharacter theOp) {
        System.out.println(getMyName() + " tries to use Crushing Blow!");
        if (Math.random() <= mySpecialChance) {
            int dmg = (int) (Math.random() * (maxCrushingBlowDmg - minCrushingBlowDmg + 1)) + minCrushingBlowDmg;
            theOp.takeDamage(dmg);
            System.out.println("Crushing Blow landed!");
        } else {
            System.out.println("Crushing Blow missed!");
        }
    }
    public int getMaxCrushingBlowDmg() {
        return maxCrushingBlowDmg;
    }

    public int getMinCrushingBlowDmg() {
        return minCrushingBlowDmg;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nClass: Model.Warrior" +
                "\nSpecial Skill: Crushing Blow (Chance: " + (mySpecialChance * 100) + "%, Damage: " +
                minCrushingBlowDmg + " - " + maxCrushingBlowDmg + ")";
    }

}
