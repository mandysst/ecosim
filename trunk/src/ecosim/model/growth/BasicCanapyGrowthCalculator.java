package ecosim.model.growth;

import java.util.Random;

import ecosim.model.Forest;
import ecosim.model.Tree;

public class BasicCanapyGrowthCalculator implements GrowthCalculator{

	@Override
	public String getDescription() {
		return "Basic 0% growth for any canapy tree";
	}

	@Override
	public double getPercentGrowth(Tree tree, Forest forest, int year,
			Random randgen) {
		return 0;
	}

}
