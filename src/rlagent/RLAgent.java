/*
 * Copyright 2014 deglavs.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package rlagent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class RLAgent extends Agent {
    private HashMap<State, ActionQValues> qMatrix;
    private State previousState;
    private Action lastAction;
    private Policy policy;
    private Random random;
    /* Learning rate. */
    private double alpha;
    /* Discount factor. */
    private double gamma;
    private double epsilon;

    public RLAgent(double gamma, double alpha, double epsilon, Policy policy) {
        super();
        qMatrix = new HashMap();
        this.gamma = gamma;
        this.alpha = alpha;
        this.epsilon = epsilon;
        this.policy = policy;
    }

    @Override
    public Action observeStartState(State state) {
        if (!qMatrix.containsKey(state)) {
            qMatrix.put(state, new ActionQValues(state.getActions()));
        }
        setCurrentState(state);
        lastAction = chooseAction();
        return lastAction;
    }

    @Override
    public void observeGoalState(State state, int reward) throws MyException {
        if (!qMatrix.containsKey(state)) {
            qMatrix.put(state, new ActionQValues(state.getActions()));
        }
        setCurrentState(state);
        updateQMatrix(reward);
    }

    @Override
    public Action observeState(State newState, int reward) throws MyException {
        if (!qMatrix.containsKey(newState)) {
            qMatrix.put(newState, new ActionQValues(newState.getActions()));
        }
        setCurrentState(newState);
        updateQMatrix(reward);
        lastAction = chooseAction();
        return lastAction;
    }

    private Action chooseAction() {
        switch (policy) {
            case EPSILON_GREEDY:
                return chooseActionEpsilonGreedily();
            default:
                throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    private Action chooseActionEpsilonGreedily() {
        if (random.nextDouble() < epsilon) {
            return chooseActionRandomly();
        }
        return chooseActionGreedily();
    }

    private Action chooseActionGreedily() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private Action chooseActionRandomly() {
        ArrayList<Action> actions = getCurrentState().getActions();
        return actions.get(random.nextInt(actions.size()));
    }

    private void updateQMatrix(int reward) throws MyException {
        double oldQValue = getQValue(previousState, lastAction);
        double newQValue = reward + (gamma * getMaxQValue(getCurrentState()));
        double tdError = newQValue - oldQValue;
        double newEstimate = oldQValue + (alpha * tdError);
        setQValue(previousState, lastAction, newEstimate);
    }

    private double getMaxQValue(State fromState) throws MyException {
        /* All possible actions from the state. */
        ArrayList<Action> actions = fromState.getActions();

		/* Set maxQValue to the first action's value. */
        double maxQValue = getQValue(fromState, actions.get(0));

		/* Iterate through all the possible actions and reset the maxQValue if a
         * bigger value is found. */
        if (actions.size() > 1) {
            for (int i = 1; i < actions.size(); i++) {
                if (getQValue(fromState, actions.get(i)) > maxQValue) {
                    maxQValue = getQValue(fromState, actions.get(i));
                }
            }
        }
        return maxQValue;
    }

    private double getQValue(State state, Action action) throws MyException {
        if (qMatrix.containsKey(state)) {
            ActionQValues actionQValues = qMatrix.get(state);
            return actionQValues.getQValue(action);
        }
        throw new MyException("No state in qMatrix: " + state);
    }

    private void setQValue(State fromState, Action action, double qValue)
            throws MyException {
        if (qMatrix.containsKey(fromState)) {
            ActionQValues actionQValues = qMatrix.get(fromState);
            actionQValues.setQValue(action, qValue);
        }
        throw new MyException("No state in qMatrix: " + fromState);
    }
}