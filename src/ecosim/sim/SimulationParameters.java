package ecosim.sim;


public class SimulationParameters {

	private final int numYears;
	private final int numRuns;
	
	
	public SimulationParameters(int numYears, int numRuns) {
		this.numYears = numYears;
		this.numRuns = numRuns;
	}


	public int getNumYears() {
		return numYears;
	}

	public int getNumRuns() {
		return numRuns;
	}
	
	
	
	
}
