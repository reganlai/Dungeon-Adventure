package Model;

import javax.swing.*;

public class Ogre extends Monster {

    private final static ImageIcon OGRE_ICON = new ImageIcon("images/standingogre.png");
    public Ogre() {
        super("Ogre", 200, 30, 30, 2, .6, 200, .1, 30, 60);
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nClass: Ogre" +
                "\nSpecial Ability: Chance to heal " +
                "(" + (getMyHealChance() * 100) + "%, Heal Range: " + getMyMinHeal() + " - " + getMyMaxHeal() + " HP)";
    }

    public ImageIcon getImageIcon() {
        return OGRE_ICON;
    }
}
