package ecosim.sim;

import java.util.concurrent.ConcurrentHashMap;

import ecosim.model.Species;

public class SpeciesMap extends ConcurrentHashMap<String, Species> {

	public SpeciesMap () {
		super();
	}
}
