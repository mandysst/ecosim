package ecosim.model.loaders;

import ecosim.model.Forest;
import ecosim.sim.SpeciesMap;

public interface ForestLoader {

	
	Forest loadForest(SpeciesMap speciesMap);
}
