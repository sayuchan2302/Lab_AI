package Task_3;

public class Percept {
	private int agentRow;
	private int agentCol;
	private Environment.LocationState state;

	public Percept(int agentRow, int agentCol, Environment.LocationState state) {
		this.agentRow = agentRow;
		this.agentCol = agentCol;
		this.state = state;
	}

	public Environment.LocationState getLocationState() {
		return this.state;
	}

	public int getAgentRow() {
		return this.agentRow;
	}

	public int getAgentCol() {
		return this.agentCol;
	}
}
