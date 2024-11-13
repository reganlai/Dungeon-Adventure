package Model;

public class Ogre extends Monster {

    public Ogre() {
        super("Ogre", 200, 30, 30, 2, .6, 200, .1, 30, 60);
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nClass: Ogre" +
                "\nSpecial Ability: Chance to heal " +
                "(" + (getMyHealChance() * 100) + "%, Heal Range: " + getMyMinHeal() + " - " + getMyMaxHeal() + " HP)";
    }
}
