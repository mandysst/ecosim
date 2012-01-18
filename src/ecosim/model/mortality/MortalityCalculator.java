package ecosim.model.mortality;

import ecosim.model.Forest;
import ecosim.model.Tree;
import ecosim.sim.SimulationRun;

public abstract class MortalityCalculator {

	protected abstract double calculateMortalityProbability(Tree tree, Forest forest);
	
	
	public boolean shouldDie(Tree tree, Forest forest, SimulationRun run) {
		// Returns 0.75 if there is a 75% chance that the tree will die
		double probability = calculateMortalityProbability(tree, forest);
		// 0.75 will be larger than a random number between 0 and 1 75% of the time.
		return probability > run.randgen.nextDouble();
	}
}
