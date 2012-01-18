package ecosim.model;

import java.util.concurrent.ConcurrentHashMap;

import ecosim.model.architecture.ArchitectureKey;
import ecosim.model.architecture.functions.ArchitectureFunction;
import ecosim.model.mortality.MortalityCalculator;
import ecosim.model.mortality.MortalityKey;


/*  IMMUTABLE  
 *  This object contains mortality rate calculators (immutable), 
 *  growth rate calculator (immutable), architecture calculator(immutable)
 *  for each tree stratum/type combination
 *  
 *  Once the simulation begins, the same species object is shared across ALL threads.
 * */
public final class Species {

	
	private final String name;
	
	// Populated before the simulation - contains functions for architecture calculations
	// effectively indexed by type (adult/sapling), strata, and input/output properties
	private final ConcurrentHashMap<ArchitectureKey, ArchitectureFunction> architectureFunctions; 
	
	// Populated before the simulation - contains mortality objects for calculating the probability of mortality
	// is indexed by type/strata.  
	private final ConcurrentHashMap<MortalityKey, MortalityCalculator> mortality; 
	
	
	
	public Species(String name, 
			ConcurrentHashMap<ArchitectureKey, ArchitectureFunction> architectureFunctions, 
			ConcurrentHashMap<MortalityKey, MortalityCalculator> mortality) {
		this.name = name;
		this.architectureFunctions = architectureFunctions;
		this.mortality = mortality;
	}


	public String getName() {
		return name;
	}
	
	public ConcurrentHashMap<ArchitectureKey, ArchitectureFunction> getArchitectureFunctions() {
		return architectureFunctions;
	}
	
}
