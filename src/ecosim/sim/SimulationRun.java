package ecosim.sim;

import java.util.Random;

import ecosim.model.Forest;
import ecosim.model.loaders.ForestLoader;

public class SimulationRun implements Runnable {

	private final int runNumber;
	private final int numYears;
	private final Forest forest;
	public final Random randgen;
	
	public SimulationRun(int runNumber, int numYears, ForestLoader loader) {
		this.runNumber = runNumber;
		this.numYears = numYears;
		this.forest = loader.loadForest();
		randgen = new Random(System.nanoTime() * runNumber+1);
	}

	@Override
	public void run() {
		
		
		/*  For Each Year
		 * 		For each tree:
		 * 
		 * 			Step 1:  	Apply Mortality Probability
								Query Species object for Mortality calculator, apply
								if dead, record the event

					Step 2:		Assign / Update growth rates
								Query Species object for Growth calculator
								Growth always returns % increase in diameter or height (trunk)
								
							
					Step 3:		Apply growth
								Determine which strategy (diameter based or trunk based)
								Calculate the new value
								Query the TreeArchitectureCalculator from species object based on strategy
								Reset the TreeArchitecture property of the Tree
					
					Step 4:		Update each tree’s strata designation by neighborhood.
								If strata changes, record
								
								
					Step 5:  	Record a snapshot for the year (all trees at once?)

		 * 
		 * 
		 * 
		 * 
		 */
		
		System.out.println("Running simulation run " + this.runNumber);
		
	}
	
	
	
	
}
