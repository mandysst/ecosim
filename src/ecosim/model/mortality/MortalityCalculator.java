package ecosim.model.mortality;

import java.util.Random;

import ecosim.model.Forest;
import ecosim.model.Tree;
import ecosim.sim.SimulationRun;

public abstract class MortalityCalculator {

	protected abstract double calculateMortalityProbability(Tree tree, Forest forest, int year);
	public abstract String getDescription();
	
	public boolean shouldDie(Tree tree, Forest forest, int year, Random randgen) {
		// Returns 0.75 if there is a 75% chance that the tree will die
		double probability = calculateMortalityProbability(tree, forest , year);
		// 0.75 will be larger than a random number between 0 and 1 75% of the time.
		return probability > randgen.nextDouble();
	}
}
