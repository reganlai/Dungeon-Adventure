package test;

import Model.Monster;
import Model.MonsterDatabase;
import Model.MonsterFactory;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * Unit tests for the MonsterFactory and MonsterDatabase classes.
 *
 * @author Evan Tran
 */
public class MonsterFactoryDatabaseTest {

    @Before
    public void setUp() {
        // Ensure the database is initialized before running tests
        MonsterDatabase.launchDatabase();
    }

    /**
     * Tests the database initialization and retrieval of Ogre attributes.
     */
    @Test
    public void testLaunchDatabase() {
        try {
            // Retrieve attributes for a valid monster
            Object[] ogreAttributes = MonsterDatabase.getMonsterAttributes("Ogre");
            assertNotNull(ogreAttributes);
            assertEquals("Ogre", ogreAttributes[0]);
        } catch (SQLException e) {
            fail("Database launch or query failed: " + e.getMessage());
        }
    }

    /**
     * Tests retrieving valid monster attributes from the database.
     */
    @Test
    public void testGetMonsterAttributesValid() {
        try {
            Object[] skeletonAttributes = MonsterDatabase.getMonsterAttributes("Skeleton");
            assertNotNull(skeletonAttributes);
            assertEquals("Skeleton", skeletonAttributes[0]);
            assertEquals(100, skeletonAttributes[1]); // Hit points
        } catch (SQLException e) {
            fail("Failed to retrieve attributes for a valid monster: " + e.getMessage());
        }
    }

    /**
     * Tests retrieving invalid monster attributes from the database.
     */
    @Test
    public void testGetMonsterAttributesInvalid() {
        try {
            MonsterDatabase.getMonsterAttributes("InvalidMonster");
            fail("Expected SQLException for invalid monster type.");
        } catch (SQLException e) {
            assertTrue(e.getMessage().contains("Monster type not found"));
        }
    }


    /**
     * Tests creating a valid Ogre monster using the factory.
     */
    @Test
    public void testCreateMonsterOgre() {
        Monster ogre = MonsterFactory.createMonster("Ogre");
        assertNotNull(ogre);
        assertEquals("Ogre", ogre.getMyName());
        assertEquals(200, ogre.getMyHp()); // Ogre HP
    }

    /**
     * Tests creating a valid Skeleton monster using the factory.
     */
    @Test
    public void testCreateMonsterSkeleton() {
        Monster skeleton = MonsterFactory.createMonster("Skeleton");
        assertNotNull(skeleton);
        assertEquals("Skeleton", skeleton.getMyName());
        assertEquals(100, skeleton.getMyHp()); // Skeleton HP
    }

    /**
     * Tests creating a valid Gremlin monster using the factory.
     */
    @Test
    public void testCreateMonsterGremlin() {
        Monster gremlin = MonsterFactory.createMonster("Gremlin");
        assertNotNull(gremlin);
        assertEquals("Gremlin", gremlin.getMyName());
        assertEquals(70, gremlin.getMyHp()); // Gremlin HP
    }

    /**
     * Tests creating a monster with an invalid type using the factory.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateMonsterInvalid() {
        MonsterFactory.createMonster("InvalidMonster");
    }

    /**
     * Tests monster attributes retrieved from the factory.
     */
    @Test
    public void testMonsterAttributesFromFactory() {
        Monster ogre = MonsterFactory.createMonster("Ogre");
        assertNotNull(ogre);
        assertEquals(200, ogre.getMyHp());
        assertEquals(30, ogre.getMyMinAttack());
        assertEquals(30, ogre.getMyMaxAttack());
    }
}
