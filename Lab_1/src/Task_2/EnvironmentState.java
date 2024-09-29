package Task_2;

import Task_2.Environment.LocationState;

public class EnvironmentState {
	private LocationState locAState;
	private LocationState locBState;
	private LocationState locCState;
	private LocationState locDState;
	private String agentLocation;

	public EnvironmentState(LocationState locAState, LocationState locBState, LocationState locCState,
			LocationState locDState) {
		this.locAState = locAState;
		this.locBState = locBState;
		this.locCState = locCState;
		this.locDState = locDState;
	}

	public String getAgentLocation() {
		return agentLocation;
	}

	public void setAgentLocation(String location) {
		agentLocation = location;
	}

	public LocationState getLocationState(String location) {
		switch (location) {
		case Environment.LOCATION_A:
			return locAState;
		case Environment.LOCATION_B:
			return locBState;
		case Environment.LOCATION_C:
			return locCState;
		case Environment.LOCATION_D:
			return locDState;
		default:
			return null;
		}
	}

	public void setLocationState(String location, LocationState state) {
		switch (location) {
		case Environment.LOCATION_A:
			locAState = state;
			break;
		case Environment.LOCATION_B:
			locBState = state;
			break;
		case Environment.LOCATION_C:
			locCState = state;
			break;
		case Environment.LOCATION_D:
			locDState = state;
			break;
		}
	}

	public boolean isClean() {
		return locAState == LocationState.CLEAN && locBState == LocationState.CLEAN && locCState == LocationState.CLEAN
				&& locDState == LocationState.CLEAN;
	}

	public void display() {
		System.out.println("Agent Location: " + agentLocation);
		System.out.println("Location A: " + locAState);
		System.out.println("Location B: " + locBState);
		System.out.println("Location C: " + locCState);
		System.out.println("Location D: " + locDState);
		System.out.println();
	}
}
