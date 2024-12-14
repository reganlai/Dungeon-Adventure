package Model;

import javax.swing.*;
import java.io.Serial;
import java.io.Serializable;

/**
 * Represents a Gremlin that extends Monster.
 *
 * @author Evan Tran
 */
public class Gremlin extends Monster implements Serializable {

    /** A generated serialization ID. */
    @Serial
    private static final long serialVersionUID = -7924722377231472270L;

    /** String path of an image of a standing Gremlin. */
    private final static ImageIcon STANDING_GREMLIN = new ImageIcon("images/standinggremlin.png");

    /** String path of an image of an attacking Gremlin. */
    private final static ImageIcon GREMLIN_ATTACK = new ImageIcon("images/gremlinattack.png");

    /** String path of an image of a blocking Gremlin. */
    private final static ImageIcon GREMLIN_BLOCK = new ImageIcon("images/gremlinblock.png");

    /**
     * Creates a Gremlin with its respective fields.
     */
    public Gremlin() {
        super("Gremlin", 60, 10, 20, 5, .8, 60);

    }

    /**
     * toString used for debugging.
     */
    @Override
    public String toString() {
        return super.toString() +
                "\nClass: Gremlin" +
                "\nSpecial Ability: Chance to heal ";
    }

    /**
     * Returns Gremlin's image according to its action currently.
     */
    @Override
    public ImageIcon getImageIcon(final Action theAction) {
        ImageIcon imageIcon = new ImageIcon();
        switch (theAction) {
            case ATTACK:
                imageIcon = GREMLIN_ATTACK;
                break;
            case BLOCK:
                imageIcon = GREMLIN_BLOCK;
                break;
            default:
                imageIcon = STANDING_GREMLIN;
        }
        return imageIcon;
    }
    public ImageIcon getAttackImage() {
        return GREMLIN_ATTACK;
    }
    public ImageIcon getBlockImage() {
        return GREMLIN_BLOCK;
    }
}