package Model;

import javax.swing.*;

public class Gremlin extends Monster {
    private final static ImageIcon STANDING_GREMLIN = new ImageIcon("images/standinggremlin.png");
    private final static ImageIcon GREMLIN_ATTACK = new ImageIcon("images/gremlinattack.png");
    private final static ImageIcon GREMLIN_BLOCK = new ImageIcon("images/gremlinblock.png");
    public Gremlin() {
        super("Gremlin", 60, 10, 20, 5, .8, 60, .4, 20, 40);

    }

    // Override toString to display Skeleton-specific details
    @Override
    public String toString() {
        return super.toString() +
                "\nClass: Gremlin" +
                "\nSpecial Ability: Chance to heal " +
                "(" + (getMyHealChance() * 100) + "%, Heal Range: " + getMyMinHeal() + " - " + getMyMaxHeal() + " HP)";
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
