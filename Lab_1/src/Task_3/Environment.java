package Task_3;

import java.util.Random;

public class Environment {
	private final int rows;
	private final int cols;
	private EnvironmentState model;
	private Agent agent;
	private int score;
	private static final double DIRT_RATE = 0.2;
	private static final double WALL_RATE = 0.1;
	private Random random = new Random();

	public enum LocationState {
		CLEAN, DIRTY, OBSTACLE
	}

	public Environment(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		this.model = new EnvironmentState(rows, cols, DIRT_RATE, WALL_RATE);
		this.score = 0;
	}

	public void addAgent(Agent agent, int startRow, int startCol) {
		this.agent = agent;
		model.setAgentLocation(startRow, startCol);
	}

	public EnvironmentState getCurrentState() {
		return this.model;
	}

	public EnvironmentState executeAction(Action action) {
		int[] currentLocation = model.getAgentLocation();
		int row = currentLocation[0];
		int col = currentLocation[1];

		if (action == Action.SUCK) {
			if (model.getLocationState(row, col) == LocationState.DIRTY) {
				model.setLocationState(row, col, LocationState.CLEAN);
				score += 500;
			}
		} else {
			// Move in the appropriate direction
			int newRow = row;
			int newCol = col;

			switch (action.toString()) {
			case "UP":
				newRow = row > 0 ? row - 1 : row;
				break;
			case "DOWN":
				newRow = row < rows - 1 ? row + 1 : row;
				break;
			case "LEFT":
				newCol = col > 0 ? col - 1 : col;
				break;
			case "RIGHT":
				newCol = col < cols - 1 ? col + 1 : col;
				break;
			}

			if (model.getLocationState(newRow, newCol) != LocationState.OBSTACLE) {
				model.setAgentLocation(newRow, newCol);
				score -= 10;
			} else {
				score -= 100; // Can't move due to obstacle
			}
		}

		return model;
	}

	public Percept getPerceptSeenBy() {
		int[] location = model.getAgentLocation();
		LocationState state = model.getLocationState(location[0], location[1]);
		return new Percept(location[0], location[1], state);
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