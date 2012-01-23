package ecosim.model.loaders;

import ecosim.model.Forest;
import ecosim.model.Plot;
import ecosim.model.Tree;
import ecosim.sim.SpeciesMap;

public class ForestCloneLoader implements ForestLoader {

	Forest source;
	
	public ForestCloneLoader (Forest originalForest) {
		this.source = originalForest;
	}
	
	@Override
	public Forest loadForest(SpeciesMap speciesMap) {
		Forest clonedForest = new Forest(this.source.getPlotLayout());
		for ( Plot plot : source.getPlots() ) {
			Plot clonedPlot = new Plot(plot.getxPos(), plot.getyPos(), clonedForest.getPlotLayout());
			clonedForest.getPlots().add(clonedPlot);
			for ( Tree tree : plot.getTrees() ) {
				// Note that all properties themselves are immutable.
				Tree clonedTree = new Tree(tree.getId(), tree.getSpeciesName(), tree.getLocation(), plot);
				clonedTree.setHealth(tree.getHealth());
				clonedTree.setStrata(tree.getStrata());
				clonedTree.setType(tree.getType());
				clonedTree.setArchitecture(tree.getArchitecture());
				clonedPlot.getTrees().add(clonedTree);
				clonedForest.getTrees().add(clonedTree);
			}
		}
		return clonedForest;
	}

}
