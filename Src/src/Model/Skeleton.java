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

    public Skeleton(final String theName, final int theHp, final int theMinAttack, final int theMaxAttack,
                final int theAttackSpd, final double theHitChance, final int theMaxHp,
                final double theHealChance, final int theMinHeal, final int theMaxHeal){
        super(theName, theHp, theMinAttack, theMaxAttack, theAttackSpd, theHitChance, theMaxHp, theHealChance, theMinHeal, theMaxHeal);
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
}
