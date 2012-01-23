package ecosim.model.growth;

import java.util.Random;

import ecosim.model.Forest;
import ecosim.model.Tree;

public class BasicSaplingGrowthCalculator implements GrowthCalculator {

	@Override
	public String getDescription() {
		return "Applys 10% growth if no canopy is above the tree, otherwise 0% growth";
	}

	@Override
	public double getPercentGrowth(Tree tree, Forest forest, int year, Random randgen) {
		if ( forest.findTreesAbove(tree).size() < 1 ) {
			return 0.1;
		}
		else {
			return 0;
		}
	}

}
