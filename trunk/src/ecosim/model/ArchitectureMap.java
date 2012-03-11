package ecosim.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.jdom.Element;

import ecosim.model.architecture.ArchitectureKey;
import ecosim.model.architecture.functions.ArchitectureFunction;
import ecosim.model.growth.GrowthCalculator;
import ecosim.model.growth.GrowthFactorKey;

public class ArchitectureMap extends ConcurrentHashMap<ArchitectureKey, ArchitectureFunction> {

	public Element getXMLElement()
	{
		Element retVal = new Element("a_map");
		for(Map.Entry<ArchitectureKey, ArchitectureFunction> mapEnt:this.entrySet())
		{
			Element ent = new Element("a_entry");
			ent.addContent(mapEnt.getKey().getXMLElement());
			ent.addContent(mapEnt.getValue().getXMLElement());
			retVal.addContent(ent);
		}
		return retVal;
	}
}
