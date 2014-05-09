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

import java.util.HashMap;

public class RLAgent extends Agent {
    private HashMap<State, ActionQValues> qMatrix;
    private State previousState;
    private Action lastAction;

    public RLAgent() {
        super();
        qMatrix = new HashMap();
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
    public void observeGoalState(State state, int reward) {
        if (!qMatrix.containsKey(state)) {
            qMatrix.put(state, new ActionQValues(state.getActions()));
        }
        setCurrentState(state);
        updateQMatrix(reward);
    }

    @Override
    public Action observeState(State newState, int reward) {
        if (!qMatrix.containsKey(newState)) {
            qMatrix.put(newState, new ActionQValues(newState.getActions()));
        }
        setCurrentState(newState);
        updateQMatrix(reward);
        lastAction = chooseAction();
        return lastAction;
    }

    private Action chooseAction() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void updateQMatrix(int reward) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
