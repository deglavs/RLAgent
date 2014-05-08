package rlagent;

import java.util.ArrayList;

public class State {

    private final String name;
    private final ArrayList actions;

    public State(String name) {
        this.name = name;
        actions = new ArrayList();
    }

    public State(String name, ArrayList<Action> actions) {
        this.name = name;
        this.actions = actions;
    }

    public Boolean addAction(Action action) {
        return actions.add(action);
    }

    public ArrayList<Action> getActions() {
        return actions;
    }

    public String getName() {
        return name;
    }
}
