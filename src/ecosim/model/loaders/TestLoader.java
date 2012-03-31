package ecosim.model.loaders;

import ecosim.model.Forest;
import ecosim.model.Location;
import ecosim.model.Species;
import ecosim.model.Stratum;
import ecosim.model.Tree;
import ecosim.model.TreeHealth;
import ecosim.model.TreeType;
import ecosim.model.architecture.DiameterBasedArchitectureCalculator;
import ecosim.model.architecture.TreeArchitecture;
import ecosim.sim.SpeciesMap;

public class TestLoader implements ForestLoader {

	@Override
	public Forest loadForest(SpeciesMap speciesMap) {
		
		
		Forest forest = new Forest();
		
		
		
		Species oak = speciesMap.get("Oak");
		
		Tree tree = new Tree(forest.nextTreeId(), oak.getName(), "OK", new Location(5.4, 2.3));
		tree.setHealth(new TreeHealth(8));
		tree.setType(TreeType.Adult);
		tree.setStrata(Stratum.Canopy);
		DiameterBasedArchitectureCalculator c = new DiameterBasedArchitectureCalculator(10, tree, oak);
		tree.setArchitecture(new TreeArchitecture(c));
		
		forest.getTrees().add(tree);
		
		return forest;
	}
	


}
