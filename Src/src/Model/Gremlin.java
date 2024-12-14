package Model;

import javax.swing.*;
import java.io.Serial;
import java.io.Serializable;

public class Gremlin extends Monster implements Serializable {
    @Serial
    private static final long serialVersionUID = -7924722377231472270L;

    private final static ImageIcon STANDING_GREMLIN = new ImageIcon("images/standinggremlin.png");
    private final static ImageIcon GREMLIN_ATTACK = new ImageIcon("images/gremlinattack.png");
    private final static ImageIcon GREMLIN_BLOCK = new ImageIcon("images/gremlinblock.png");


    public Gremlin(final String theName, final int theHp, final int theMinAttack, final int theMaxAttack,
                final int theAttackSpd, final double theHitChance, final int theMaxHp,
                final double theHealChance, final int theMinHeal, final int theMaxHeal){
        super(theName, theHp, theMinAttack, theMaxAttack, theAttackSpd, theHitChance, theMaxHp, theHealChance, theMinHeal, theMaxHeal);
    }


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