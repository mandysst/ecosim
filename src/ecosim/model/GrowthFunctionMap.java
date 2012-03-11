package ecosim.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.jdom.Element;

import ecosim.model.growth.GrowthCalculator;
import ecosim.model.growth.GrowthFactorKey;
import ecosim.model.mortality.MortalityCalculator;
import ecosim.model.mortality.MortalityKey;

public class GrowthFunctionMap extends ConcurrentHashMap<GrowthFactorKey, GrowthCalculator>{
	
	public Element getXMLElement()
	{
		Element retVal = new Element("g_map");
		for(Map.Entry<GrowthFactorKey, GrowthCalculator> mapEnt:this.entrySet())
		{
			Element ent = new Element("g_entry");
			ent.addContent(mapEnt.getKey().getXMLElement());
			ent.addContent(mapEnt.getValue().getXMLElement());
			retVal.addContent(ent);
		}
		return retVal;
	}

}
