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
    public Skeleton() {
        super("Skeleton", 80, 30, 50, 3, .8, 80);
    }

    /**
     * toString used for debugging.
     */
    @Override
    public String toString() {
        return super.toString() +
                "\nClass: Skeleton" +
                "\nSpecial Ability: Chance to heal ";
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
