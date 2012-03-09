package ecosim.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.jdom.Element;

import ecosim.model.mortality.MortalityCalculator;
import ecosim.model.mortality.MortalityKey;

public class MortalityMap extends ConcurrentHashMap<MortalityKey, MortalityCalculator>{
	
	public Element getXMLElement()
	{
		Element retVal = new Element("m_map");
		for(Map.Entry<MortalityKey, MortalityCalculator> mapEnt:this.entrySet())
		{
			Element ent = new Element("m_entry");
			ent.addContent(mapEnt.getKey().getXMLElement());
			ent.addContent(mapEnt.getValue().getXMLElement());
			retVal.addContent(ent);
		}
		return retVal;
	}

}
