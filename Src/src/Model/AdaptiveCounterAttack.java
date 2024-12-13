package Model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class AdaptiveCounterAttack implements Serializable {
    private final Map<Action, Integer> myFrequencyTracker;
    private Action myAction;
    public AdaptiveCounterAttack() {
        myFrequencyTracker = new HashMap<>();
        myFrequencyTracker.put(Action.ATTACK, 0);
        myFrequencyTracker.put(Action.BLOCK, 0);
        myFrequencyTracker.put(Action.SPECIAL, 0);
    }

    public void recordPlayerAction(final Action theAction) {
        myFrequencyTracker.put(theAction, myFrequencyTracker.get(theAction) + 1);
    }

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
                //myAction = Action.values()[rand.nextInt(Action.values().length)];
                myAction = getRandomAction();
        }
        return myAction;
    }
    private Action getRandomAction() {
        // Filter out the STANDING action
        List<Action> actions = Arrays.stream(Action.values())
                .filter(myAction -> myAction != Action.STANDBY)
                .toList();

        // Get a random value from the remaining list
        Random random = new Random();
        return actions.get(random.nextInt(actions.size()));
    }

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
