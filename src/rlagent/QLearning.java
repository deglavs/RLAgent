package rlagent;

import java.util.List;
import java.util.Random;

/**
 * Created by deglavs on 08/05/2014.
 */
public class QLearning {
    private static final int NUMBER_OF_EPISODES = 100;
    /* Discount factor. */
    private static final double GAMMA = 0.8;
    private Environment environment;
    private Agent agent;

    private List<State> startStates;

    private Random random;

    public QLearning(Environment e, Agent a) {
        environment = e;
        agent = a;
        startStates = environment.getStartStates();
    }

    public void run() throws MyException {
        for (int i = 0; i < NUMBER_OF_EPISODES; i++) {
            runEpisode();
        }
    }

    public void runEpisode() throws MyException {
        int reward;
        State stateFrom = startStates.get(random.nextInt(startStates.size()));
        Action action = agent.observeStartState(stateFrom);
        State stateTo = environment.getNextState(stateFrom, action);
        while (!environment.isGoalState(stateTo)) {
            reward = environment.getReward(stateFrom, action);
            action = agent.observeState(stateTo, reward);
            stateFrom = stateTo;
            stateTo = environment.getNextState(stateFrom, action);
        }
        /* Goal state reached. Tell agent the good news. */
        reward = environment.getReward(stateFrom, action);
        agent.observeGoalState(stateTo, reward);
    }
}
