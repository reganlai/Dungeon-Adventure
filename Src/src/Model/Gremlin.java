package Model;

public class Gremlin extends Monster {

    public Gremlin() {
        super("Gremlin", 70, 15, 30, 5, .8, 70, .4, 20, 40);

    }

    // Override toString to display Skeleton-specific details
    @Override
    public String toString() {
        return super.toString() +
                "\nClass: Gremlin" +
                "\nSpecial Ability: Chance to heal " +
                "(" + (getMyHealChance() * 100) + "%, Heal Range: " + getMyMinHeal() + " - " + getMyMaxHeal() + " HP)";
    }
}
