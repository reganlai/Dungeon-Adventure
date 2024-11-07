package Model;

public class Priestess extends Hero {
    private final int myMinHeal = 20;
    private final int myMaxHeal = 50;

    public Priestess(final String theName) {
        super(theName, 75, 25, 45, 5, .7, .3, 75);

    }

    @Override
    public void specialAbility(final DungeonCharacter theOp) {
        int healAmount = (int) (Math.random() * (myMaxHeal - myMinHeal + 1)) + myMinHeal;
        System.out.println(getMyName() + "uses healing ability, restoring" + healAmount + " hp.");
        setMyHp(getMyHp() + healAmount);
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nClass: Model.Priestess" +
                "\nSpecial Skill: Heal (Restores " + myMinHeal + " - " + myMaxHeal + " HP)";
    }

}
