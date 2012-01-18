package ecosim.model.loaders;

import ecosim.model.Forest;
import ecosim.model.Plot;
import ecosim.model.Tree;

public class ForestCloneLoader implements ForestLoader {

	Forest source;
	
	public ForestCloneLoader (Forest originalForest) {
		this.source = originalForest;
	}
	
	@Override
	public Forest loadForest() {
		Forest forest = new Forest(this.source.getPlotLayout());
		for ( Plot plot : source.getPlots() ) {
			forest.getPlots().add(new Plot(plot.getxPos(), plot.getyPos(), forest.getPlotLayout()));
			for ( Tree tree : plot.getTrees() ) {
				// Note that all properties themselves are immutable.
				Tree clonedTree = new Tree(tree.getSpeciesName(), tree.getLocation(), plot);
				clonedTree.setHealth(tree.getHealth());
				clonedTree.setStrata(tree.getStrata());
				clonedTree.setType(tree.getType());
				plot.getTrees().add(clonedTree);
				forest.getTrees().add(clonedTree);
			}
		}
		return forest;
	}

}
