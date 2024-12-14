package Model;

import javax.swing.*;
import java.io.Serial;
import java.io.Serializable;


/**
 * Represents a Skeleton that extends Monster.
 *
 * @author Evan Tran
 */
public class Skeleton extends Monster implements Serializable {

    /** A generated serialization ID. */
    @Serial
    private static final long serialVersionUID = 7306928187856730197L;

    /** String path of an image of a standing Skeleton. */
    private final static ImageIcon STANDING_SKELETON = new ImageIcon("images/standingskeleton.png");

    /** String path of an image of an attacking Skeleton. */
    private final static ImageIcon SKELETON_ATTACK = new ImageIcon("images/skeletonattack.png");

    /** String path of an image of a blocking Skeleton. */
    private final static ImageIcon SKELETON_BLOCK = new ImageIcon("images/skeletonblock.png");

    /**
     * Creates a Skeleton with its respective fields.
     */
    public Skeleton(final String theName, final int theHp, final int theMinAttack, final int theMaxAttack,
                    final int theAttackSpd, final double theHitChance, final int theMaxHp,
                    final double theHealChance, final int theMinHeal, final int theMaxHeal){
        super(theName, theHp, theMinAttack, theMaxAttack, theAttackSpd, theHitChance, theMaxHp, theHealChance, theMinHeal, theMaxHeal);
    }


    /**
     * Returns Skeleton's image according to its action currently.
     */
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
