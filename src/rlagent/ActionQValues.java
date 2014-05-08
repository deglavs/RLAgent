package rlagent;

import java.util.HashMap;
import java.util.List;

/**
 * Created by deglavs on 08/05/2014.
 */
public class ActionQValues {
    private HashMap<Action, Double> qValues;

    public ActionQValues(List<Action> actions) {
        qValues = new HashMap();
        for (Action action : actions) {
            qValues.put(action, (double) 0);
        }
    }

    public double getQValue(Action action) throws MyException {
        if (qValues.containsKey(action)) {
            return qValues.get(action).doubleValue();
        }
        throw new MyException("No such action " + action);
    }

    public void setQValue(Action action, double qValue) throws MyException {
        if (qValues.containsKey(action)) {
            qValues.put(action, qValue);
        }
        throw new MyException("No such action " + action);
    }
}
