package ecosim.model.growth;

import java.util.Random;

import org.jdom.Element;

import ecosim.model.Forest;
import ecosim.model.Tree;

public interface GrowthCalculator {

	String getDescription();
	double getPercentGrowth(Tree tree, Forest forest, int year, Random randgen) ;
	Element getXMLElement();
}
