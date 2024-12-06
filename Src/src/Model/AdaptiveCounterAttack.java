package Model;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class AdaptiveCounterAttack {
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
        Random rand = new Random();
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
                myAction = Action.values()[rand.nextInt(Action.values().length)];;
        }
        return myAction;
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
