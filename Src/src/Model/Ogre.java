package Model;

import javax.swing.*;
import java.io.Serial;
import java.io.Serializable;

/**
 * Represents an Ogre that extends Monster.
 *
 * @author Evan Tran
 */
public class Ogre extends Monster implements Serializable {

    /** A generated serialization ID. */
    @Serial
    private static final long serialVersionUID = -2680791313423148912L;

    /** String path of an image of a standing Ogre. */
    private final static ImageIcon STANDING_OGRE = new ImageIcon("images/standingogre.png");

    /** String path of an image of an attacking Ogre. */
    private final static ImageIcon OGRE_ATTACK = new ImageIcon("images/ogreattack.png");

    /** String path of an image of a blocking Ogre. */
    private final static ImageIcon OGRE_BLOCK = new ImageIcon("images/ogreblock.png");

    /**
     * Creates an Ogre with its respective fields.
     */
    public Ogre() {
        super("Ogre", 120, 30, 30, 2, .6, 120);
    }

    /**
     * toString used for debugging.
     */
    @Override
    public String toString() {
        return super.toString() +
                "\nClass: Ogre" +
                "\nSpecial Ability: Chance to heal ";
    }

    /**
     * Returns Ogre's image according to its action currently.
     */
    public ImageIcon getImageIcon(final Action theAction) {
        ImageIcon imageIcon = switch (theAction) {
            case ATTACK -> OGRE_ATTACK;
            case BLOCK -> OGRE_BLOCK;
            default -> STANDING_OGRE;
        };
        return imageIcon;
    }
}
