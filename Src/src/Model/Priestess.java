package Model;

import javax.swing.*;
import java.awt.*;

public class Priestess extends Hero {

    private final static ImageIcon STANDING_PRIESTESS = new ImageIcon("images/standingpriestess.png");
    private final static ImageIcon PRIESTESS_ATTACK = new ImageIcon("images/priestessattack.png");
    private final static ImageIcon PRIESTESS_BLOCK = new ImageIcon("images/priestessblock.png");
    private final static ImageIcon PRIESTESS_WON = new ImageIcon("images/priestesswon.png");
    private final static ImageIcon PRIESTESS_LOST = new ImageIcon("images/priestesslost.png");
    private final int myMinHeal = 20;
    private final int myMaxHeal = 50;

    public Priestess(final String theName) {
        super(theName, 150, 25, 45, 5, .7, .3, 150, 0,0);

    }
    public ImageIcon getHeroWonImage() {
        return PRIESTESS_WON;
    }
    public ImageIcon getHeroLostImage() {
        return PRIESTESS_LOST;
    }
    public ImageIcon getImageIcon() {
        return STANDING_PRIESTESS;
    }
    public ImageIcon getAttackImage() { return PRIESTESS_ATTACK; }
    public ImageIcon getBlockImage() { return PRIESTESS_BLOCK; }

    @Override
    public void specialAbility(final DungeonCharacter theOp) {
        int healAmount = (int) (Math.random() * (myMaxHeal - myMinHeal + 1)) + myMinHeal;
        System.out.println(getMyName() + "uses healing ability, restoring" + healAmount + " hp.");
        setMyHp(getMyHp() + healAmount);
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nClass: Model.Priestess" +
                "\nSpecial Skill: Heal (Restores " + myMinHeal + " - " + myMaxHeal + " HP)";
    }

}
