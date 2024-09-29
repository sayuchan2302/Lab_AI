package Task_3;

public class TestVacuumCleaner {
	public static void main(String[] args) {
		int rows = 5;
		int cols = 5;

		Environment env = new Environment(rows, cols);
		Agent agent = new Agent(new AgentProgram());
		env.addAgent(agent, 0, 0); // Start the agent at the top-left corner
		env.run();
	}
}
