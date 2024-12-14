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

    public Ogre(final String theName, final int theHp, final int theMinAttack, final int theMaxAttack,
                final int theAttackSpd, final double theHitChance, final int theMaxHp,
                final double theHealChance, final int theMinHeal, final int theMaxHeal){
        super(theName, theHp, theMinAttack, theMaxAttack, theAttackSpd, theHitChance, theMaxHp, theHealChance, theMinHeal, theMaxHeal);
    }

    public ImageIcon getImageIcon(final Action theAction) {
        ImageIcon imageIcon = switch (theAction) {
            case ATTACK -> OGRE_ATTACK;
            case BLOCK -> OGRE_BLOCK;
            default -> STANDING_OGRE;
        };
        return imageIcon;
    }
}
