package ecosim.model;

import java.util.concurrent.ConcurrentHashMap;

import ecosim.model.architecture.ArchitectureKey;
import ecosim.model.architecture.functions.ArchitectureFunction;
import ecosim.model.growth.GrowthCalculator;
import ecosim.model.growth.GrowthFactorKey;
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
	private final ArchitectureMap architectureFunctions; 
	
	// Populated before the simulation - contains mortality objects for calculating the probability of mortality
	// is indexed by type/strata.  
	private final MortalityMap mortality; 
	
	private final GrowthFunctionMap growthCalculators;
	
	


	public Species(
			String name, 
			ArchitectureMap architectureFunctions, 
			MortalityMap mortality, 
			GrowthFunctionMap growthCalculators) {
		
		this.name = name;
		this.architectureFunctions = architectureFunctions;
		this.mortality = mortality;
		this.growthCalculators = growthCalculators;
	}


	public String getName() {
		return name;
	}
	
	public GrowthFunctionMap getGrowthCalculators() {
		return growthCalculators;
	}
	
	public MortalityMap getMortality() {
		return mortality;
	}


	public ArchitectureMap getArchitectureFunctions() {
		return architectureFunctions;
	}
	
}
