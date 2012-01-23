package ecosim.model.loaders;

import ecosim.model.ArchitectureMap;
import ecosim.model.GrowthFunctionMap;
import ecosim.model.MortalityMap;
import ecosim.model.Species;
import ecosim.model.Stratum;
import ecosim.model.TreeType;
import ecosim.model.architecture.ArchitectureKey;
import ecosim.model.architecture.ArchitectureProperty;
import ecosim.model.architecture.functions.ArchitectureFunction;
import ecosim.model.architecture.functions.LinearFunction;
import ecosim.model.growth.BasicCanapyGrowthCalculator;
import ecosim.model.growth.GrowthCalculator;
import ecosim.model.growth.GrowthFactorKey;
import ecosim.model.mortality.BasicMortalityCalculator;
import ecosim.model.mortality.MortalityCalculator;
import ecosim.model.mortality.MortalityKey;
import ecosim.sim.SpeciesMap;

public class DefaultSpeciesLoader implements SpeciesLoader {

	@Override
	public void loadSpecies(SpeciesMap speciesMap){
		MortalityCalculator m = new BasicMortalityCalculator(0.2);
		MortalityMap mMap = new MortalityMap();
		mMap.put(new MortalityKey(TreeType.Adult, Stratum.Canopy), m);
		
		GrowthCalculator canapyGrowth = new BasicCanapyGrowthCalculator(); 
		GrowthFunctionMap gMap = new GrowthFunctionMap();
		gMap.put(new GrowthFactorKey(TreeType.Adult, Stratum.Canopy), canapyGrowth);
		
		ArchitectureFunction a = new LinearFunction(1, 0.25);
		ArchitectureMap aMap = new ArchitectureMap();
		aMap.put(new ArchitectureKey(TreeType.Adult, Stratum.Canopy, ArchitectureProperty.TrunkDiameter, ArchitectureProperty.TrunkHeight), a);
		aMap.put(new ArchitectureKey(TreeType.Adult, Stratum.Canopy, ArchitectureProperty.TrunkDiameter, ArchitectureProperty.CanapyHeight), a);
		aMap.put(new ArchitectureKey(TreeType.Adult, Stratum.Canopy, ArchitectureProperty.TrunkDiameter, ArchitectureProperty.CanapyDiameter), a);
		
		Species oak = new Species("Oak", aMap, mMap, gMap);
		speciesMap.put(oak.getName(), oak);
	}


}
