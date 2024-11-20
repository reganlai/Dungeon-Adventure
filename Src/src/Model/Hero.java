package Model;

public abstract class Hero extends DungeonCharacter{

    private int myPotions;
    private int myVisionPotions;
    private final double myChanceToBlock;


    /**
     * Constructs a Model.DungeonCharacter with the specified attributes.
     *
     * @param theName      the name of the character
     * @param theHp        the initial health points
     * @param theMinAttack the minimum attack damage
     * @param theMaxAttack the maximum attack damage
     * @param theAttackSpd the attack speed of the character
     * @param theHitChance the probability (0-1) that an attack hits
     */
    Hero(String theName, int theHp, int theMinAttack, int theMaxAttack, int theAttackSpd, double theHitChance,
            double theChanceToBlock, int theMaxHp) {
        super(theName, theHp, theMinAttack, theMaxAttack, theAttackSpd, theHitChance, theMaxHp);
        myChanceToBlock = theChanceToBlock;
        myPotions = 2;
        myVisionPotions = 1;
    }


    public abstract void specialAbility(final DungeonCharacter theOp);

    public double getMyChanceToBlock(){
        return myChanceToBlock;
    }

    public int getMyPotions(){
        return myPotions;
    }

    public int getMyVisionPotions(){
        return myVisionPotions;
    }

    public void addPotion() {
        myPotions++;
    }
    public void addVisionPotion() {
        myVisionPotions++;
    }

    public void usePotion() {
        if(myPotions > 0) {
            myPotions--;
            int hp = getMyHp();
            hp += 25;
            if (hp > getMyMaxHp()) {
                setMyHp(getMyMaxHp());
            } else {
                setMyHp(hp);
            }
            System.out.println(getMyName() + " used a healing postion!");

        } else {
            System.out.println(getMyName() + " has no potions left!");
        }

    }

    public void useVisionPotion(){
        if(myVisionPotions > 0) {
             myVisionPotions--;
             System.out.println(getMyName() + " used a vision potion!");

        } else {
            System.out.println(getMyName() + " has no vision potions left");

        }
    }

    public String toString() {
        return "Model.Hero: " + getMyName() +
                "\nHp: " + getMyHp() +
                "\nHealing Potions: " + getMyPotions() +
                "\nVision Potions: " + getMyVisionPotions();
                // "\nPillars Found: " + pillars;
    }

}
