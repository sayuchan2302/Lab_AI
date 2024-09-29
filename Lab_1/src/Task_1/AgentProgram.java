package Task_1;

public class AgentProgram {
	// Return an appropriate action based on the given percept
	public Action execute(Percept p) {
		String location = p.getAgentLocation();
		Environment.LocationState state = p.getLocationState();
		if (state == Environment.LocationState.DIRTY) {
			return Action.SUCK;
		} else if (location.equals(Environment.LOCATION_A)) {
			return Action.RIGHT;
		} else if (location.equals(Environment.LOCATION_B)) {
			return Action.LEFT;
		}
		return NoOpAction.NO_OP;
	}
}
