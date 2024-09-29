package Task_2;

import java.util.Random;

public class AgentProgram {
    private Random random = new Random();

    // Return an appropriate action based on the given percept
    public Action execute(Percept p) {
        String location = p.getAgentLocation();
        Environment.LocationState state = p.getLocationState();

        if (state == Environment.LocationState.DIRTY) {
            return Action.SUCK;
        }

        // If the current cell is clean, pick a random action (UP, DOWN, LEFT, RIGHT)
        int action = random.nextInt(4);
        switch (action) {
            case 0: return Action.UP;
            case 1: return Action.DOWN;
            case 2: return Action.LEFT;
            case 3: return Action.RIGHT;
            default: return NoOpAction.NO_OP;
        }
    }
}