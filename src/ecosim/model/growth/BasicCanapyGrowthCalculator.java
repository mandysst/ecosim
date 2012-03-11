package ecosim.model.growth;

import java.util.Random;

import org.jdom.Element;

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

	@Override
	public Element getXMLElement()
	{
		Element retVal = new Element("g_calc");
		Element gVal = new Element("percent");
		gVal.setText(""+0);
		retVal.addContent(gVal);
		return retVal;
	}

}
