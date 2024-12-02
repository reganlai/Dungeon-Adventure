package Model;

import javax.swing.*;

public class Thief extends Hero {

    private final static ImageIcon STANDING_THIEF = new ImageIcon("images/standingthief.png");
    private final static ImageIcon THIEF_ATTACK = new ImageIcon("images/thiefattack.png");
    private final static ImageIcon THIEF_BLOCK = new ImageIcon("images/thiefblock.png");
    private final static ImageIcon THIEF_WON = new ImageIcon("images/thiefwon.png");
    private final static ImageIcon THIEF_LOST = new ImageIcon("images/thieflost.png");

    private final double mySpecialChance = .4;
    private final double myCaughtChance = .2;

    public Thief (final String theName) {
        super(theName, 150, 20, 40, 6, .8,
                .4, 150,0 ,0);

    }
    public ImageIcon getHeroWonImage() {
        return THIEF_WON;
    }
    public ImageIcon getHeroLostImage() { return THIEF_LOST; }
    public ImageIcon getImageIcon() {
        return STANDING_THIEF;
    }
    public ImageIcon getAttackImage() { return THIEF_ATTACK; }
    public ImageIcon getBlockImage() { return THIEF_BLOCK; }

    @Override
    public void specialAbility(final DungeonCharacter theOp) {
        System.out.println(getMyName() + "attempts a Surprise Attack!");

        if(Math.random() < myCaughtChance) {
            System.out.println(getMyName() + " was caught!");
        } else if (Math.random() < mySpecialChance) {
            System.out.println("Surprise Attack successful! " + getMyName() + " lands an extra attack!");
            this.attack(theOp);
            this.attack(theOp);
        } else {
            System.out.println(getMyName() + " performs a regular attack.");
            attack(theOp);
        }

    }

    @Override
    public String toString() {
        return super.toString() +
                "\nClass: Model.Thief" +
                "\nSpecial Skill: Surprise Attack (Chance: " + (mySpecialChance * 100) + "% success, " +
                (myCaughtChance * 100) + "% chance of getting caught)";
    }
}
