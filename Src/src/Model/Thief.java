package Model;

import javax.swing.*;
import java.io.Serial;
import java.io.Serializable;

public class Thief extends Hero implements Serializable {
    @Serial
    private static final long serialVersionUID = -8405930066486030078L;

    private final static ImageIcon STANDING_THIEF = new ImageIcon("images/standingthief.png");
    private final static ImageIcon THIEF_ATTACK = new ImageIcon("images/thiefattack.png");
    private final static ImageIcon THIEF_BLOCK = new ImageIcon("images/thiefblock.png");
    private final static ImageIcon THIEF_WON = new ImageIcon("images/thiefwon.png");
    private final static ImageIcon THIEF_LOST = new ImageIcon("images/thieflost.png");


    private final double mySpecialChance = .4;
    private final double myCaughtChance = .2;

    public Thief (final String theName) {
//        super(theName, 150, 20, 40, 6, .8,
//                .4, 150,0 ,0);
        super(theName, 150, 20, 40, 6,
                .8, 0.4, 150);

    }
    public ImageIcon getImageIcon(final Action theAction) {
        ImageIcon imageIcon;
        switch (theAction) {
            case ATTACK:
                imageIcon = THIEF_ATTACK;
                break;
            case BLOCK:
                imageIcon = THIEF_BLOCK;
                break;
            default:
                imageIcon = STANDING_THIEF;
        }
        return imageIcon;
    }

    public ImageIcon getHeroWonImage() {
        return THIEF_WON;
    }
    public ImageIcon getHeroLostImage() { return THIEF_LOST; }

    @Override
    public boolean specialAbility(final Monster theOp, final Action theMonsterAction) {

        boolean success = false;
        System.out.println(getMyName() + "attempts a Surprise Attack!");
        final double chance = Math.random();

        if (chance < 0.4) { //40%
            // 2 attacks
            attack(theOp, theMonsterAction);
            attack(theOp, theMonsterAction);
            success = true;
        } else if (chance < 0.6) { //20%
            success = false;

        } else { //40% remaining
            attack(theOp, theMonsterAction);
            success = true;
        }

//        if(Math.random() < myCaughtChance) {
//            System.out.println(getMyName() + " was caught!");
//        } else if (Math.random() < mySpecialChance) {
//            System.out.println("Surprise Attack successful! " + getMyName() + " lands an extra attack!");
//            this.attack(theOp);
//            this.attack(theOp);
//        } else {
//            System.out.println(getMyName() + " performs a regular attack.");
//            attack(theOp);
//        }
        return success;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nClass: Model.Thief" +
                "\nSpecial Skill: Surprise Attack (Chance: " + (mySpecialChance * 100) + "% success, " +
                (myCaughtChance * 100) + "% chance of getting caught)";
    }
}
