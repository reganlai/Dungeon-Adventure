package Model;

import javax.swing.*;

public class Skeleton extends Monster {
    private final static ImageIcon SKELETON_ICON = new ImageIcon("images/standingskeleton.png");
    private final static ImageIcon SKELETON_ATTACK = new ImageIcon("images/skeletonattack.png");
    private final static ImageIcon SKELETON_BLOCK = new ImageIcon("images/skeletonblock.png");
    public Skeleton() {
        super("Skeleton", 80, 30, 50, 3, .8, 80, .3, 30, 50);
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nClass: Skeleton" +
                "\nSpecial Ability: Chance to heal " +
                "(" + (getMyHealChance() * 100) + "%, Heal Range: " + getMyMinHeal() + " - " + getMyMaxHeal() + " HP)";
    }

    public ImageIcon getImageIcon() {
        return SKELETON_ICON;
    }
    public ImageIcon getAttackImage() {
        return SKELETON_ATTACK;
    }
    public ImageIcon getBlockImage() {
        return SKELETON_BLOCK;
    }
}
