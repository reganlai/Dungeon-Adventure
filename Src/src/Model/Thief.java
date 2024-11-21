package Model;

public class Thief extends Hero {
    private final double mySpecialChance = .4;
    private final double myCaughtChance = .2;

    public Thief (final String theName) {
        super(theName, 75, 20, 40, 6, .8,
                .4, 75,0 ,0);

    }

    @Override
    public void specialAbility(final DungeonCharacter theOp) {
        System.out.println(getMyName() + "attempts a Surprise Attack!");

        if(Math.random() < myCaughtChance) {
            System.out.println(getMyName() + " was caught!");
        } else if (Math.random() < mySpecialChance) {
            System.out.println("Surprise Attack successful! " + getMyName() + " lands an extra attack!");
            this.attack(theOp);
            this.attack(theOp);
        } else {
            System.out.println(getMyName() + " performs a regular attack.");
            attack(theOp);
        }

    }

    @Override
    public String toString() {
        return super.toString() +
                "\nClass: Model.Thief" +
                "\nSpecial Skill: Surprise Attack (Chance: " + (mySpecialChance * 100) + "% success, " +
                (myCaughtChance * 100) + "% chance of getting caught)";
    }
}
