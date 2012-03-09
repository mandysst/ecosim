package ecosim.sim;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.jdom.Element;

import ecosim.model.Species;
import ecosim.model.mortality.MortalityKey;

public class SpeciesMap extends ConcurrentHashMap<String, Species> {

	public SpeciesMap () {
		super();
	}
	public Element getXMLElement()
	{
		Element retVal = new Element("species");
		
		for(Map.Entry<String, Species> mapEnt:this.entrySet())
		{
			retVal.addContent(mapEnt.getValue().getXMLElement());	
		}
		return retVal;
	}
}
