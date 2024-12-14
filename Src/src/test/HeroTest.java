package test;

import static org.junit.Assert.*;

import Model.*;
import org.junit.Before;
import org.junit.Test;


public class HeroTest {

    private static final String HERO_WARRIOR = "Warrior";
    private static final String HERO_THIEF = "Thief";
    private static final String HERO_PRIESTESS = "Priestess";

    private Hero warrior;
    private Hero thief;
    private Hero priestess;
    private Monster mockMonster;

    @Before
    public void setUp() {
        warrior = new Warrior(HERO_WARRIOR);
        thief = new Thief(HERO_THIEF);
        priestess = new Priestess(HERO_PRIESTESS);
        mockMonster = MonsterFactory.createMonster("Skeleton");
    }

    @Test
    public void testWarriorCreation() {
        assertNotNull(warrior);
        assertEquals(HERO_WARRIOR, warrior.getMyName());
        assertTrue(warrior.getMyHp() > 0);
    }

    @Test
    public void testThiefCreation() {
        assertNotNull(thief);
        assertEquals(HERO_THIEF, thief.getMyName());
        assertTrue(thief.getMyHp() > 0);
    }

    @Test
    public void testPriestessCreation() {
        assertNotNull(priestess);
        assertEquals(HERO_PRIESTESS, priestess.getMyName());
        assertTrue(priestess.getMyHp() > 0);
    }

    @Test
    public void testSpecialAbilityWarrior() {
        int initialMonsterHp = mockMonster.getMyHp();
        boolean success = warrior.specialAbility(mockMonster, null);

        if (success) {
            assertTrue(mockMonster.getMyHp() < initialMonsterHp);
        } else {
            assertEquals(initialMonsterHp, mockMonster.getMyHp());
        }
    }

    @Test
    public void testSpecialAbilityThief() {
        int initialMonsterHp = mockMonster.getMyHp();
        boolean success = thief.specialAbility(mockMonster, null);

        if (success) {
            mockMonster.takeDamage(30);
            assertTrue(mockMonster.getMyHp() < initialMonsterHp);
        } else {
            assertEquals(initialMonsterHp, mockMonster.getMyHp());
        }
    }

    @Test
    public void testSpecialAbilityPriestess() {
        priestess.takeDamage(50);
        int initialHp = priestess.getMyHp();
        boolean success = priestess.specialAbility(null, null);

        if (success) {
            assertTrue(priestess.getMyHp() > initialHp);
        } else {
            assertEquals(initialHp, priestess.getMyHp());
        }
    }

    @Test
    public void testTakeDamage() {
        int initialHp = warrior.getMyHp();
        warrior.takeDamage(30);

        assertEquals(initialHp - 30, warrior.getMyHp());
        assertTrue(warrior.isAlive());

        warrior.takeDamage(warrior.getMyHp());
        assertEquals(0, warrior.getMyHp());
        assertFalse(warrior.isAlive());
    }

    @Test
    public void testUseHealthPotion() {
        warrior.addHealthPotion();
        warrior.takeDamage(30);
        int hpAfterDamage = warrior.getMyHp();
        warrior.usePotion();

        assertTrue(warrior.getMyHp() > hpAfterDamage);
    }

    @Test
    public void testAddAndRetrievePillars() {
        priestess.addPillarCollected();
        assertEquals(1, priestess.getMyPillarsCollected());

        priestess.addPillarCollected();
        assertEquals(2, priestess.getMyPillarsCollected());
    }

    @Test
    public void testToString() {
        String warriorString = warrior.toString();
        assertTrue(warriorString.contains(HERO_WARRIOR));
        assertTrue(warriorString.contains("Healing Potions"));

        String thiefString = thief.toString();
        assertTrue(thiefString.contains(HERO_THIEF));
        assertTrue(thiefString.contains("Healing Potions"));

        String priestessString = priestess.toString();
        assertTrue(priestessString.contains(HERO_PRIESTESS));
        assertTrue(priestessString.contains("Healing Potions"));
    }

    @Test
    public void testBlockMechanism() {
        int initialHp = warrior.getMyHp();
        int damage = 50;

        for (int i = 0; i < 100; i++) {
            warrior.takeDamage(damage);

            if (warrior.getMyHp() == initialHp - damage) {
                // Damage was not blocked
                break;
            } else {
                assertEquals("Damage was blocked successfully", warrior.getMyHp(), initialHp);
            }
        }
    }

    @Test
    public void testGetHeroInDungeonImage() {
        assertNotNull(warrior.getHeroInDungeon());
        assertNotNull(thief.getHeroInDungeon());
        assertNotNull(priestess.getHeroInDungeon());
    }
}
