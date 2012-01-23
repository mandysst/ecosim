package ecosim.model.loaders;

import ecosim.model.Forest;
import ecosim.model.Location;
import ecosim.model.Plot;
import ecosim.model.PlotLayout;
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
		
		PlotLayout layout = new PlotLayout(2, 2, 10, 33);
		Forest forest = new Forest(layout);
		
		Plot p1 = new Plot(0, 0, layout);
		forest.getPlots().add(p1);
		
		Species oak = speciesMap.get("Oak");
		
		Tree tree = new Tree(forest.nextTreeId(), oak.getName(), new Location(5.4, 2.3),p1);
		tree.setHealth(new TreeHealth(8));
		tree.setType(TreeType.Adult);
		tree.setStrata(Stratum.Canopy);
		DiameterBasedArchitectureCalculator c = new DiameterBasedArchitectureCalculator(10, tree, oak);
		tree.setArchitecture(new TreeArchitecture(c));
		
		p1.getTrees().add(tree);
		forest.getTrees().add(tree);
		
		return forest;
	}
	


}
