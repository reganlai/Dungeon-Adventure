package test;

import Model.Action;
import Model.DungeonCharacter;
import Model.Monster;
import Model.MonsterFactory;
import Model.Warrior;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.*;

/**
 * Unit tests for the DungeonCharacter class.
 *
 * @author Evan Tran
 */
public class DungeonCharacterTest {

    private DungeonCharacter mockCharacter;
    private Monster mockEnemy;

    @Before
    public void setUp() {
        // Initialize a Warrior as a concrete subclass of DungeonCharacter
        mockCharacter = new Warrior("TestWarrior");
        mockEnemy = MonsterFactory.createMonster("Skeleton");
    }

    @Test
    public void testDungeonCharacterInitialization() {
        assertEquals("TestWarrior", mockCharacter.getMyName());
        assertEquals(125, mockCharacter.getMyHp()); // Assuming default HP for Warrior
        assertEquals(35, mockCharacter.getMyMinAttack());
        assertEquals(60, mockCharacter.getMyMaxAttack());
        assertEquals(4, mockCharacter.getMyAttackSpd());
        assertEquals(0.8, mockCharacter.getMyHitChance(), 0.01);
        assertEquals(125, mockCharacter.getMyMaxHp());
    }

    @Test
    public void testAttackFail() {

        int initialHp = mockEnemy.getMyHp();
        mockCharacter.attack(mockEnemy, Action.BLOCK);

        assertTrue(mockEnemy.getMyHp() == initialHp);
    }

    @Test
    public void testAttackSuccess() {

        int initialHp = mockEnemy.getMyHp();
        mockCharacter.attack(mockEnemy, null);

        assertTrue(mockEnemy.getMyHp() == initialHp);
    }



    @Test
    public void testTakeDamage() {
        int initialHp = mockCharacter.getMyHp();
        mockCharacter.takeDamage(30);
        assertEquals(initialHp - 30, mockCharacter.getMyHp());

        // Test taking damage exceeding current HP
        mockCharacter.takeDamage(200);
        assertEquals(0, mockCharacter.getMyHp());
        assertFalse(mockCharacter.isAlive());
    }

    @Test
    public void testIsAlive() {
        assertTrue(mockCharacter.isAlive());

        mockCharacter.takeDamage(mockCharacter.getMyHp());
        assertFalse(mockCharacter.isAlive());
    }

    @Test
    public void testSetName() {
        mockCharacter.setMyName("NewName");
        assertEquals("NewName", mockCharacter.getMyName());
    }


    @Test
    public void testSetHp() {
        mockCharacter.setMyHp(80);
        assertEquals(80, mockCharacter.getMyHp());
    }


}
