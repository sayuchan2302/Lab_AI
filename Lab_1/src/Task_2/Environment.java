package Task_2;

public class Environment {
	public static final String LOCATION_A = "A";
	public static final String LOCATION_B = "B";
	public static final String LOCATION_C = "C";
	public static final String LOCATION_D = "D";

	public enum LocationState {
		CLEAN, DIRTY
	}

	private EnvironmentState model;
	private Agent agent = null;
	private int score = 0; // To track the performance score

	public Environment(LocationState locAState, LocationState locBState, LocationState locCState,
			LocationState locDState) {
		model = new EnvironmentState(locAState, locBState, locCState, locDState);
	}

	public void addAgent(Agent agent, String location) {
		this.agent = agent;
		model.setAgentLocation(location);
	}

	public EnvironmentState getCurrentState() {
		return this.model;
	}

	// Update environment state when agent performs an action
	public EnvironmentState executeAction(Action action) {
		String agentLocation = model.getAgentLocation();

		if (action == Action.SUCK) {
			model.setLocationState(agentLocation, LocationState.CLEAN);
			score += 500;
		} else if (action == Action.RIGHT && (agentLocation.equals(LOCATION_A) || agentLocation.equals(LOCATION_C))) {
			model.setAgentLocation(agentLocation.equals(LOCATION_A) ? LOCATION_B : LOCATION_D);
			score -= 10;
		} else if (action == Action.LEFT && (agentLocation.equals(LOCATION_B) || agentLocation.equals(LOCATION_D))) {
			model.setAgentLocation(agentLocation.equals(LOCATION_B) ? LOCATION_A : LOCATION_C);
			score -= 10;
		} else if (action == Action.UP && (agentLocation.equals(LOCATION_C) || agentLocation.equals(LOCATION_D))) {
			model.setAgentLocation(agentLocation.equals(LOCATION_C) ? LOCATION_A : LOCATION_B);
			score -= 10;
		} else if (action == Action.DOWN && (agentLocation.equals(LOCATION_A) || agentLocation.equals(LOCATION_B))) {
			model.setAgentLocation(agentLocation.equals(LOCATION_A) ? LOCATION_C : LOCATION_D);
			score -= 10;
		} else {
			score -= 100; // If the agent can't move
		}
		return model;
	}

	public Percept getPerceptSeenBy() {
		String agentLocation = model.getAgentLocation();
		LocationState locationState = model.getLocationState(agentLocation);
		return new Percept(agentLocation, locationState);
	}

	public void run() {
		while (!model.isClean()) {
			Percept percept = getPerceptSeenBy();
			Action action = agent.execute(percept);
			executeAction(action);
			model.display();
		}
		System.out.println("All locations are clean. Stopping simulation.");
		System.out.println("Final Score: " + score);
	}
}