public class Thief extends Hero {
    private final double mySpecialChance = .4;
    private final double myCaughtChance = .2;

    public Thief (final String theName) {
        super(theName, 75, 20, 40, 6, .8, .4, 75);

    }

    @Override
    public void specialAbility(final DungeonCharacter theOp) {
        System.out.println(getMyName() + "attempts a Surprise Attack!");

        if(Math.random() < mySpecialChance) {
            System.out.println("Surprise Attack successful!" + getMyName() + " lands gets and extra attack!");
            this.attack(theOp);
        }

    }

    @Override
    public String toString() {
        return super.toString() +
                "\nClass: Thief" +
                "\nSpecial Skill: Surprise Attack (Chance: " + (mySpecialChance * 100) + "% success, " +
                (myCaughtChance * 100) + "% chance of getting caught)";
    }
}
