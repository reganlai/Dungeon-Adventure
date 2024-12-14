/*
 * TCSS 360 - Dungeon Adventure
 */
package Model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
/**
 * Monster's counterattack according to user's behaviors.
 *
 * @author George Njane
 * @version 1.0
 */
public class AdaptiveCounterAttack implements Serializable {

    /** Tracks the frequency of hero's actions. */
    private final Map<Action, Integer> myFrequencyTracker;

    /** Monster's action. */
    private Action myAction;

    /**
     * Initializes the class.
     */
    public AdaptiveCounterAttack() {
        myFrequencyTracker = new HashMap<>();
        myFrequencyTracker.put(Action.ATTACK, 0);
        myFrequencyTracker.put(Action.BLOCK, 0);
        myFrequencyTracker.put(Action.SPECIAL, 0);
    }

    /**
     * Adds one to the frequency of the user's chosen action.
     * @param theAction the action the user just used.
     */
    public void recordPlayerAction(final Action theAction) {
        myFrequencyTracker.put(theAction, myFrequencyTracker.get(theAction) + 1);
    }

    /**
     * Decides what to do for the monster according to user's behaviors.
     */
    public Action generateAttack() {
        myAction = null;
        final Action frequentAction = getMostFrequentAction();
        switch (frequentAction) {
            case ATTACK:
                myAction = Action.BLOCK;
                break;
            case BLOCK:
                myAction = Action.SPECIAL;
                break;
            case SPECIAL:
                myAction = Action.ATTACK;
                break;
            default:
                myAction = getRandomAction();
        }
        return myAction;
    }

    /**
     * Generates random action when user's activity is balanced.
     */
    private Action getRandomAction() {
        List<Action> actions = Arrays.stream(Action.values())
                .filter(myAction -> myAction != Action.STANDBY)
                .toList();
        Random random = new Random();
        return actions.get(random.nextInt(actions.size()));
    }

    /**
     * Returns the opponent's most frequent used attack.
     *
     * @return returns the most frequently used action by the user.
     */
    private Action getMostFrequentAction() {
        int maxFrequency = 0;
        Action frequentAction = Action.STANDBY;
        for (Map.Entry<Action, Integer> action : myFrequencyTracker.entrySet()) {
            if (action.getValue() > maxFrequency) {
                maxFrequency = action.getValue();
                frequentAction = action.getKey();
            }
        }
        return frequentAction;
    }
}
