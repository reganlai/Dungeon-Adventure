package Model;

public class Skeleton extends Monster {

    public Skeleton() {
        super("Skeleton", 100, 30, 50, 3, .8, 100, .3, 30, 50);
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nClass: Skeleton" +
                "\nSpecial Ability: Chance to heal " +
                "(" + (getMyHealChance() * 100) + "%, Heal Range: " + getMyMinHeal() + " - " + getMyMaxHeal() + " HP)";
    }
}
