package Task_3;

import java.util.Random;

public class AgentProgram {
    private Random random = new Random();

    public Action execute(Percept p) {
        if (p.getLocationState() == Environment.LocationState.DIRTY) {
            return Action.SUCK;
        }

        // Random movement if current cell is clean
        int action = random.nextInt(4); // 0: UP, 1: DOWN, 2: LEFT, 3: RIGHT
        switch (action) {
            case 0: return Action.UP;
            case 1: return Action.DOWN;
            case 2: return Action.LEFT;
            case 3: return Action.RIGHT;
            default: return NoOpAction.NO_OP;
        }
    }
}
