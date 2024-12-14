package test;

import static org.junit.Assert.*;

import Model.Monster;
import Model.MonsterDatabase;
import Model.MonsterFactory;
import org.junit.Before;
import org.junit.Test;


public class MonsterTest {

    private static final String OGRE = "Ogre";
    private static final String GREMLIN = "Gremlin";
    private static final String SKELETON = "Skeleton";

    private static final int TEST_ITERATIONS = 100;

    @Before
    public void setUpDatabase() {
        MonsterDatabase.launchDatabase();
    }

    @Test
    public void testOgreCreation() {
        final Monster ogre = MonsterFactory.createMonster(OGRE);
        assertNotNull(ogre);
        assertEquals(OGRE, ogre.getMyName());
        assertTrue(ogre.getMyHp() > 0);
    }

    @Test
    public void testGremlinCreation() {
        final Monster gremlin = MonsterFactory.createMonster(GREMLIN);
        assertNotNull(gremlin);
        assertEquals(GREMLIN, gremlin.getMyName());
        assertTrue(gremlin.getMyHp() > 0);
    }

    @Test
    public void testSkeletonCreation() {
        final Monster skeleton = MonsterFactory.createMonster(SKELETON);
        assertNotNull(skeleton);
        assertEquals(SKELETON, skeleton.getMyName());
        assertTrue(skeleton.getMyHp() > 0);
    }

    @Test
    public void testMonsterHealing() {
        final Monster ogre = MonsterFactory.createMonster(OGRE);
        final int initialHp = ogre.getMyHp();
        ogre.takeDamage(10);
        ogre.heal();

        assertTrue(ogre.getMyHp() >= initialHp - 10);
        assertTrue(ogre.getMyHp() <= ogre.getMyMaxHp());
    }

    @Test
    public void testMonsterDamage() {
        final Monster gremlin = MonsterFactory.createMonster(GREMLIN);
        final int initialHp = gremlin.getMyHp();
        gremlin.takeDamage(20);

        assertEquals(initialHp - 20, gremlin.getMyHp());
        assertTrue(gremlin.isAlive());

        gremlin.takeDamage(initialHp);
        assertEquals(0, gremlin.getMyHp());
        assertFalse(gremlin.isAlive());
    }

    @Test
    public void testToString() {
        final Monster skeleton = MonsterFactory.createMonster(SKELETON);
        String expectedString = "Monster: " + skeleton.getMyName() +
                "\nHit Points: " + skeleton.getMyHp() +
                "\nAttack Speed: " + skeleton.getMyAttackSpd() +
                "\nChance to Heal: " + skeleton.getMyHealChance() +
                "\nHeal Range: " + skeleton.getMyMinHeal() + " - " + skeleton.getMyMaxHeal();
        assertEquals(expectedString, skeleton.toString());

        skeleton.takeDamage(30);
        expectedString = "Monster: " + skeleton.getMyName() +
                "\nHit Points: " + skeleton.getMyHp() +
                "\nAttack Speed: " + skeleton.getMyAttackSpd() +
                "\nChance to Heal: " + skeleton.getMyHealChance() +
                "\nHeal Range: " + skeleton.getMyMinHeal() + " - " + skeleton.getMyMaxHeal();
        assertEquals(expectedString, skeleton.toString());
    }


}
