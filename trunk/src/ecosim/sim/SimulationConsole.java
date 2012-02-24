package ecosim.sim;

import ecosim.model.PlotLayout;

public class SimulationConsole {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SimulationParameters p = new SimulationParameters(10, 50, new PlotLayout());
		
		Simulation sim = new Simulation("Console Demonstration", p);
		
		sim.run();

	}

}
