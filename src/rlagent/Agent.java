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

/**
 * @author deglavs
 */
public abstract class Agent {
    private State currentState;

    public Agent() {
    }

    public abstract Action observeStartState(State state);

    public abstract void observeGoalState(State state, int reward) throws MyException;

    public abstract Action observeState(State newState, int reward) throws MyException;

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }
}
