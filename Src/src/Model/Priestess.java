package Model;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;
import java.io.Serializable;

public class Priestess extends Hero implements Serializable {
    @Serial
    private static final long serialVersionUID = 2956966238326498914L;

    private final static ImageIcon STANDING_PRIESTESS = new ImageIcon("images/standingpriestess.png");
    private final static ImageIcon PRIESTESS_ATTACK = new ImageIcon("images/priestessattack.png");
    private final static ImageIcon PRIESTESS_BLOCK = new ImageIcon("images/priestessblock.png");
    private final static ImageIcon PRIESTESS_WON = new ImageIcon("images/priestesswon.png");
    private final static ImageIcon PRIESTESS_LOST = new ImageIcon("images/priestesslost.png");

    private final int myMinHeal = 20;
    private final int myMaxHeal = 50;

    public Priestess(final String theName) {
        super(theName, 75, 25, 45, 5, .7, .3, 75);

    }

    @Override
    public ImageIcon getImageIcon(final Action theAction) {
        ImageIcon imageIcon;
        switch (theAction) {
            case ATTACK:
                imageIcon = PRIESTESS_ATTACK;
                break;
            case BLOCK:
                imageIcon = PRIESTESS_BLOCK;
                break;
            default:
                imageIcon = STANDING_PRIESTESS;
        }
        return imageIcon;
    }

    public ImageIcon getHeroWonImage() {
        return PRIESTESS_WON;
    }
    public ImageIcon getHeroLostImage() {
        return PRIESTESS_LOST;
    }

    @Override
    public boolean specialAbility(final Monster theOp, final Action theMonsterAction) {
        int healAmount = (int) (Math.random() * (myMaxHeal - myMinHeal + 1)) + myMinHeal;
        System.out.println(getMyName() + "uses healing ability, restoring" + healAmount + " hp.");
        setMyHp(getMyHp() + healAmount);

        return true;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nClass: Model.Priestess" +
                "\nSpecial Skill: Heal (Restores " + myMinHeal + " - " + myMaxHeal + " HP)";
    }

}
