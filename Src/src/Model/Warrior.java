package Model;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;
import java.io.Serializable;

public class Warrior extends Hero implements Serializable {
    @Serial
    private static final long serialVersionUID = 3733480867287879417L;

    private final static ImageIcon STANDING_WARRIOR = new ImageIcon("images/standingwarrior.png");
    private final static ImageIcon WARRIOR_ATTACK = new ImageIcon("images/warriorattack.png");
    private final static ImageIcon WARRIOR_BLOCK = new ImageIcon("images/warriorblock.png");
    private final static ImageIcon WARRIOR_WON = new ImageIcon("images/warriorwon.png");
    private final static ImageIcon WARRIOR_LOST = new ImageIcon("images/warriorlost.png");
    private final static double SPECIAL_CHANCE = 0.4;

    private final  int minCrushingBlowDmg = 75;
    private final int maxCrushingBlowDmg = 175;



    public Warrior(final String theName) {
        super(theName, 125, 35, 60, 4, .8, .2, 125);
        //super(theName, 250, 35, 60, 4, .2, 250, 0,0);
    }

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

    public ImageIcon getHeroWonImage() {
        return WARRIOR_WON;
    }
    public ImageIcon getHeroLostImage() { return WARRIOR_LOST; }

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
                "\nSpecial Skill: Crushing Blow (Chance: " + (SPECIAL_CHANCE * 100) + "%, Damage: " +
                minCrushingBlowDmg + " - " + maxCrushingBlowDmg + ")";
    }

}
