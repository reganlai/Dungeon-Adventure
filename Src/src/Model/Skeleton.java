package Model;

import javax.swing.*;
import java.io.Serial;
import java.io.Serializable;

public class Skeleton extends Monster implements Serializable {
    @Serial
    private static final long serialVersionUID = 7306928187856730197L;

    private final static ImageIcon STANDING_SKELETON = new ImageIcon("images/standingskeleton.png");
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

    public ImageIcon getImageIcon(final Action theAction) {
        ImageIcon imageIcon = new ImageIcon();
        switch (theAction) {
            case ATTACK:
                imageIcon = SKELETON_ATTACK;
                break;
            case BLOCK:
                imageIcon = SKELETON_BLOCK;
                break;
            default:
                imageIcon = STANDING_SKELETON;
        }
        return imageIcon;
    }
    public ImageIcon getAttackImage() {
        return SKELETON_ATTACK;
    }
    public ImageIcon getBlockImage() {
        return SKELETON_BLOCK;
    }
}