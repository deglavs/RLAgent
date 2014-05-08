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

import java.util.List;

/**
 * @author deglavs
 */
public interface Environment {

    public int getReward(State state, Action action) throws MyException;

    public State getNextState(State state, Action action)
            throws MyException;

    public boolean isGoalState(State state) throws MyException;

    public List<State> getStartStates();
}
