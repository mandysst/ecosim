package ecosim.sim;


public class SimulationConsole {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SimulationParameters p = new SimulationParameters(10, 50);
		
		Simulation sim = new Simulation("Console Demonstration", p);
		
		sim.run();

	}

}
