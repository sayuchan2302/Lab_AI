package Task_1;

public class Environment {
	public static final String LOCATION_A = "A";
	public static final String LOCATION_B = "B";

	public enum LocationState {
		CLEAN, DIRTY
	}

	private EnvironmentState model;
	private Agent agent = null;// single, for multi-agent using List<Agent>

	public Environment(LocationState locAState, LocationState locBState) {
		model = new EnvironmentState(locAState, locBState);
	}

	// add an agent into the environment
	public void addAgent(Agent agent, String location) {
		this.agent = agent;
		model.setAgentLocation(location);
	}

	public EnvironmentState getCurrentState() {
		return this.model;
	}

	// Update environment state when agent do an action
	public EnvironmentState executeAction(Action action) {
		String agentLocation = model.getAgentLocation();

		if (action == Action.SUCK) {
			model.setLocationState(agentLocation, LocationState.CLEAN);
		} else if (action == Action.RIGHT && agentLocation.equals(LOCATION_A)) {
			model.setAgentLocation(LOCATION_B);
		} else if (action == Action.LEFT && agentLocation.equals(LOCATION_B)) {
			model.setAgentLocation(LOCATION_A);
		}
		return model;
	}

	// get the percept<AgentLocation, LocationState> at the current location where
	// agent
	// is in.
	public Percept getPerceptSeenBy() {
		String agentLocation = model.getAgentLocation();
		LocationState locationState = model.getLocationState(agentLocation);
		return new Percept(agentLocation, locationState); // Create a percept with location and state
	}

	public void run() {
		while (!model.isClean()) {
			Percept percept = getPerceptSeenBy();
			Action action = agent.execute(percept);
			executeAction(action);
			model.display();
		}
		System.out.println("All locations are clean. Stopping simulation.");
	}
}