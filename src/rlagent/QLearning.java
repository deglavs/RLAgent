package rlagent;

/**
 * Created by deglavs on 08/05/2014.
 */
public class QLearning {
    private static final int NUMBER_OF_EPISODES = 100;
    /* Discount factor. */
    private static final double GAMMA = 0.8;
    private Environment environment;
    private Agent agent;

    public QLearning(Environment e, Agent a) {
        environment = e;
        agent = a;
    }

    public void run() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void runEpisode() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
