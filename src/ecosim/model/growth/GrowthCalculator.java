package ecosim.model.growth;

import java.util.Random;

import ecosim.model.Forest;
import ecosim.model.Tree;

public interface GrowthCalculator {

	String getDescription();
	double getPercentGrowth(Tree tree, Forest forest, int year, Random randgen) ;
}
