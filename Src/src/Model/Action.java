package Model;

/**
 * Represents the action of the hero or monster.
 *
 * @author George Njane
 * @version 1.0
 */
public enum Action {
    /**
     * Hero or monster does nothing.
     */
    STANDBY,

    /**
     * User or monster uses attack.
     */
    ATTACK,

    /**
     * User or monster uses block.
     */
    BLOCK,

    /**
     * User uses special attack.
     */
    SPECIAL
}
