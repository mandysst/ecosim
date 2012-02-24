package ecosim.sim;

import ecosim.model.PlotLayout;

public class SimulationParameters {

	private final int numYears;
	private final int numRuns;
	private final PlotLayout plotLayout;
	
	public SimulationParameters(int numYears, int numRuns, PlotLayout pLayout) {
		this.numYears = numYears;
		this.numRuns = numRuns;
		this.plotLayout = pLayout;
	}

	public PlotLayout getPlotLayout()
	{
		return plotLayout;
	}

	public int getNumYears() {
		return numYears;
	}

	public int getNumRuns() {
		return numRuns;
	}
	
	
	
	
}
