package ecosim.model.mortality;

import ecosim.model.Forest;
import ecosim.model.Tree;

public class BasicMortalityCalculator extends MortalityCalculator{

	private final double P;
	
	public BasicMortalityCalculator(double p) {
		P = p;
	}
	public BasicMortalityCalculator() {
		P = 0.1;
	}
	@Override
	protected double calculateMortalityProbability(Tree tree, Forest forest, int year) {
		return P;
	}
	
	@Override
	public String getDescription() {
		return "Basic constant-probability mortality rate";
	}

	

}
