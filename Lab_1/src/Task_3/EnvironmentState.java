package Task_3;

import java.util.Random;

public class EnvironmentState {
    private Environment.LocationState[][] grid;
    private int agentRow;
    private int agentCol;

    public EnvironmentState(int rows, int cols, double dirtRate, double wallRate) {
        grid = new Environment.LocationState[rows][cols];
        Random random = new Random();

        // Initialize the grid with dirt and obstacles
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                double rand = random.nextDouble();
                if (rand < wallRate) {
                    grid[i][j] = Environment.LocationState.OBSTACLE;
                } else if (rand < dirtRate + wallRate) {
                    grid[i][j] = Environment.LocationState.DIRTY;
                } else {
                    grid[i][j] = Environment.LocationState.CLEAN;
                }
            }
        }
    }

    public int[] getAgentLocation() {
        return new int[]{agentRow, agentCol};
    }

    public void setAgentLocation(int row, int col) {
        agentRow = row;
        agentCol = col;
    }

    public Environment.LocationState getLocationState(int row, int col) {
        return grid[row][col];
    }

    public void setLocationState(int row, int col, Environment.LocationState state) {
        grid[row][col] = state;
    }

    public boolean isClean() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == Environment.LocationState.DIRTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public void display() {
        System.out.println("Agent is at: (" + agentRow + ", " + agentCol + ")");
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == Environment.LocationState.OBSTACLE) {
                    System.out.print("W "); // Wall
                } else if (grid[i][j] == Environment.LocationState.DIRTY) {
                    System.out.print("D "); // Dirt
                } else if (grid[i][j] == Environment.LocationState.CLEAN) {
                    System.out.print("C "); // Clean
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}