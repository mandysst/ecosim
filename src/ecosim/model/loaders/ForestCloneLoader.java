package ecosim.model.loaders;

import ecosim.model.Forest;
import ecosim.model.Tree;
import ecosim.sim.SpeciesMap;

public class ForestCloneLoader implements ForestLoader {

	Forest source;
	
	public ForestCloneLoader (Forest originalForest) {
		this.source = originalForest;
	}
	
	@Override
	public Forest loadForest(SpeciesMap speciesMap) {
		Forest clonedForest = new Forest();
		for ( Tree tree : this.source.getTrees() ) {
			// Note that all properties themselves are immutable.
			Tree clonedTree = new Tree(tree.getId(), tree.getSpeciesName(), tree.getLocation());
			clonedTree.setHealth(tree.getHealth());
			clonedTree.setStrata(tree.getStrata());
			clonedTree.setType(tree.getType());
			clonedTree.setArchitecture(tree.getArchitecture());
			clonedForest.getTrees().add(clonedTree);
		}
		return clonedForest;
	}

}
