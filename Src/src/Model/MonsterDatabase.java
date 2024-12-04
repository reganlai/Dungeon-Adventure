package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.sqlite.SQLiteDataSource;

/**
 * The class is responsible for managing the SQLite database
 * that stores monster attributes for the game. It provides methods to create the database
 * schema, insert initial monster data, and retrieve attributes for a specific monster type.
 *
 * @author Evan Tran
 * @version 1.0
 */
public class MonsterDatabase {

    /** The URL for connecting to the SQLite database. */
    private static final String DB_URL = "jdbc:sqlite:monsters.db";

    /** The SQLiteDataSource used for managing database connections. */
    private static SQLiteDataSource ds;

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private MonsterDatabase() {
        super();
    }

    /**
     * Launches the database by initializing the connection pool,
     * creating the database schema, and populating it with base data.
     */
    public static void launchDatabase() {
        try {
            ds = new SQLiteDataSource();
            ds.setUrl(DB_URL);
            createMonsterTable();
            insertBaseData();
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates the "monsters" table in the database if it does not already exist.
     *
     * @throws SQLException if an error occurs while creating the table.
     */
    private static void createMonsterTable() throws SQLException {
        final String sql = "CREATE TABLE IF NOT EXISTS monsters ("
                + "theType TEXT PRIMARY KEY,"
                + "theHp INTEGER,"
                + "theMinDamage INTEGER,"
                + "theMaxDamage INTEGER,"
                + "theAtkSpd INTEGER,"
                + "theChanceToHit DOUBLE,"
                + "theMaxHp INTEGER,"
                + "theHealChance DOUBLE,"
                + "theMinHeal INTEGER,"
                + "theMaxHeal INTEGER)";
        try (Connection conn = ds.getConnection();
             Statement statement = conn.createStatement()) {
            statement.execute(sql);
        }
    }

    /**
     * Inserts the base monster data into the database.
     *
     * @throws SQLException if an error occurs while inserting data.
     */
    private static void insertBaseData() throws SQLException {
        final String sql = "INSERT OR REPLACE INTO monsters "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ds.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            insertMonstersData(pstmt, "Ogre", 200, 30, 30, 2, 0.6, 200, 0.1, 30, 60);
            insertMonstersData(pstmt, "Gremlin", 70, 15, 30, 5, 0.8, 70, 0.4, 20, 40);
            insertMonstersData(pstmt, "Skeleton", 100, 30, 50, 3, 0.8, 100, 0.3, 30, 50);
        }
    }

    /**
     * Helper method to insert a single monster's data into the database.
     *
     * @param thePstmt the prepared statement used for insertion.
     * @param theType the monster type.
     * @param theHp the monster's hit points.
     * @param theMinDamage the minimum damage the monster can deal.
     * @param theMaxDamage the maximum damage the monster can deal.
     * @param theAtkSpd the attack speed of the monster.
     * @param theChanceToHit the chance to hit for the monster.
     * @param theMaxHp the maximum hit points of the monster.
     * @param theHealChance the chance of the monster healing.
     * @param theMinHeal the minimum amount of healing the monster can perform.
     * @param theMaxHeal the maximum amount of healing the monster can perform.
     * @throws SQLException if an error occurs during insertion.
     */
    private static void insertMonstersData(final PreparedStatement thePstmt,
                                           final String theType, final int theHp,
                                           final int theMinDamage, final int theMaxDamage,
                                           final int theAtkSpd, final double theChanceToHit,
                                           final int theMaxHp, final double theHealChance,
                                           final int theMinHeal, final int theMaxHeal) throws SQLException {
        thePstmt.setString(1, theType);
        thePstmt.setInt(2, theHp);
        thePstmt.setInt(3, theMinDamage);
        thePstmt.setInt(4, theMaxDamage);
        thePstmt.setInt(5, theAtkSpd);
        thePstmt.setDouble(6, theChanceToHit);
        thePstmt.setInt(7, theMaxHp);
        thePstmt.setDouble(8, theHealChance);
        thePstmt.setInt(9, theMinHeal);
        thePstmt.setInt(10, theMaxHeal);
        thePstmt.executeUpdate();
    }

    /**
     * Retrieves the attributes of a specified monster from the database.
     *
     * @param theType the type of monster to retrieve.
     * @return an array of attributes for the specified monster.
     * @throws SQLException if an error occurs while retrieving the data.
     * @throws IllegalStateException if the database has not been launched.
     */
    public static Object[] getMonsterAttributes(final String theType) throws SQLException {
        if (ds == null) {
            throw new IllegalStateException("Database has not been launched");
        }

        final String sql = "SELECT * FROM monsters WHERE theType = ?";

        try (Connection conn = ds.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, theType);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Object[]{
                            rs.getString("theType"),         // Monster type
                            rs.getInt("theHp"),             // Hit points
                            rs.getInt("theMinDamage"),      // Minimum damage
                            rs.getInt("theMaxDamage"),      // Maximum damage
                            rs.getInt("theAtkSpd"),         // Attack speed
                            rs.getDouble("theChanceToHit"), // Chance to hit
                            rs.getInt("theMaxHp"),          // Maximum hit points
                            rs.getDouble("theHealChance"),  // Heal chance
                            rs.getInt("theMinHeal"),        // Minimum heal
                            rs.getInt("theMaxHeal")         // Maximum heal
                    };
                }
            }
        }
        throw new SQLException("Monster type not found: " + theType);
    }
}
