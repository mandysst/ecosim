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
	protected double calculateMortalityProbability(Tree tree, Forest forest) {
		return P;
	}
	

	

}
