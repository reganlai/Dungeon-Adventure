package Model;

import javax.swing.*;
import java.io.Serial;
import java.io.Serializable;

public class Ogre extends Monster implements Serializable {
    @Serial
    private static final long serialVersionUID = -2680791313423148912L;

    private final static ImageIcon STANDING_OGRE = new ImageIcon("images/standingogre.png");
    private final static ImageIcon OGRE_ATTACK = new ImageIcon("images/ogreattack.png");
    private final static ImageIcon OGRE_BLOCK = new ImageIcon("images/ogreblock.png");


    public Ogre() {
        super("Ogre", 120, 30, 30, 2, .6, 120, .1, 30, 60);
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nClass: Ogre" +
                "\nSpecial Ability: Chance to heal " +
                "(" + (getMyHealChance() * 100) + "%, Heal Range: " + getMyMinHeal() + " - " + getMyMaxHeal() + " HP)";
    }

    public ImageIcon getImageIcon(final Action theAction) {
        ImageIcon imageIcon = new ImageIcon();
        switch (theAction) {
            case ATTACK:
                imageIcon = OGRE_ATTACK;
                break;
            case BLOCK:
                imageIcon = OGRE_BLOCK;
                break;
            default:
                imageIcon = STANDING_OGRE;
        }
        return imageIcon;
    }
    public ImageIcon getAttackImage() {
        return OGRE_ATTACK;
    }
    public ImageIcon getBlockImage() {
        return OGRE_BLOCK;
    }
}
