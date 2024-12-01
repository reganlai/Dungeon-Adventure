package Model;

import javax.swing.*;

public class Ogre extends Monster {

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

    public ImageIcon getImageIcon() {
        return STANDING_OGRE;
    }
    public ImageIcon getAttackImage() {
        return OGRE_ATTACK;
    }
    public ImageIcon getBlockImage() {
        return OGRE_BLOCK;
    }
}
