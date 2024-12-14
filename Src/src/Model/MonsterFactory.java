package Model;

import java.sql.SQLException;

/**
 * Factory class for creating Monster instances.
 * Handles database interactions to retrieve Monster data.
 *
 * @author Evan Tran
 */
public class MonsterFactory {

/**
 * Creates a new {@code Monster} instance based on its type.
 * If the specified monster type is not found in the database or if a
 * database error occurs, the method will throw an exception.
 *
 * @param theType the type of the monster to create (e.g., "Ogre", "Skeleton").
 * @return a Monster instance with the specified type and attributes.
 * @throws IllegalArgumentException if the specified monster type is not found
 *                                  or if a database error occurs.
 */
public static Monster createMonster(final String theType) {
    MonsterDatabase.launchDatabase();
    try {
        final Object[] data = MonsterDatabase.getMonsterAttributes(theType);
        return switch (theType) {
            case "Ogre" -> new Ogre((String) data[0],  // Monster type
                    (int) data[1],     // Hit points
                    (int) data[2],     // Minimum damage
                    (int) data[3],     // Maximum damage
                    (int) data[4],     // Attack speed
                    (double) data[5],  // Chance to hit
                    (int) data[6],     // Maximum hit points
                    (double) data[7],  // Heal chance
                    (int) data[8],     // Minimum heal
                    (int) data[9]      // Maximum heal)
            );
            case "Skeleton" -> new Skeleton((String) data[0],  // Monster type
                    (int) data[1],     // Hit points
                    (int) data[2],     // Minimum damage
                    (int) data[3],     // Maximum damage
                    (int) data[4],     // Attack speed
                    (double) data[5],  // Chance to hit
                    (int) data[6],     // Maximum hit points
                    (double) data[7],  // Heal chance
                    (int) data[8],     // Minimum heal
                    (int) data[9]      // Maximum heal))
            );
            case "Gremlin" -> new Gremlin((String) data[0],  // Monster type
                    (int) data[1],     // Hit points
                    (int) data[2],     // Minimum damage
                    (int) data[3],     // Maximum damage
                    (int) data[4],     // Attack speed
                    (double) data[5],  // Chance to hit
                    (int) data[6],     // Maximum hit points
                    (double) data[7],  // Heal chance
                    (int) data[8],     // Minimum heal
                    (int) data[9]      // Maximum heal))
            );
            default -> throw new IllegalStateException("Unexpected value: " + theType);
        };
    } catch (final SQLException e) {
        throw new IllegalArgumentException("Failed to load the stats of: " + theType, e);
    }
}
}
