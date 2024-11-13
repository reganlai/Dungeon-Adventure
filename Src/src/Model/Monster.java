package Model;

import java.util.Random;

public abstract class Monster extends DungeonCharacter {

    private final double myHealChance;
    private final int myMinHeal;
    private final int myMaxHeal;
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
    Monster(final String theName, final int theHp, final int theMinAttack, final int theMaxAttack,
            final int theAttackSpd, final double theHitChance, final int theMaxHp,
            final double theHealChance, final int theMinHeal, final int theMaxHeal) {
        super(theName, theHp, theMinAttack, theMaxAttack, theAttackSpd, theHitChance, theMaxHp);
        myHealChance = theHealChance;
        myMinHeal = theMinHeal;
        myMaxHeal = theMaxHeal;
    }

    public double getMyHealChance() {
        return myHealChance;
    }

    public int getMyMinHeal(){
        return myMinHeal;
    }

    public int getMyMaxHeal() {
        return myMaxHeal;
    }

    public void heal(){
        Random rand = new Random();
        if (rand.nextDouble() <= this.myHealChance) {
            int healAmount = rand.nextInt(this.myMaxHeal - this.myMinHeal + 1) + this.myMinHeal;
            int hp = getMyHp() + healAmount;
            if(hp > getMyMaxHp()){
                setMyHp(getMyMaxHp());
                System.out.println(getMyName() + "healed for " + (hp - getMyMaxHp()) + " points!" );
            } else {
            setMyHp(hp);
            System.out.println(getMyName() + " healed for " + healAmount + " points!");
            }
        } else {
            System.out.println(getMyName() + " tried to heal but failed!");
        }
    }

    @Override
    public void attack(final DungeonCharacter theOp) {
        super.attack(theOp);
        if(getMyHp() > 0) {
            heal();
        }
    }
    public String toString() {
        return "Model.Monster: " + this.getMyName() +
                "\nHit Points: " + this.getMyHp() +
                "\nAttack Speed: " + this.getMyAttackSpd() +
                "\nChance to Heal: " + this.myHealChance +
                "\nHeal Range: " + this.myMinHeal + " - " + this.myMinHeal;
    }
}
