package ecosim.model.growth;

import ecosim.model.Species;
import ecosim.model.Tree;
import ecosim.model.TreeType;
import ecosim.model.architecture.DiameterBasedArchitectureCalculator;
import ecosim.model.architecture.HeightBasedArchitectureCalculator;
import ecosim.model.architecture.TreeArchitecture;

public class GrowthController {

	private final Tree tree;
	private final Species species;
	
	
	public GrowthController(Tree tree, Species species) {
		super();
		this.tree = tree;
		this.species = species;
	}

	public void growBy(double percent) {
		if ( isDiameterBased() ) {
			growDiameterBy(percent);
		}
		else {
			growHeightBy(percent);
		}
	}
	
	private void growDiameterBy(double percent) {
		double newTrunkDiameter = this.tree.getArchitecture().getTrunkDiameter() * ( 1 + percent);
		DiameterBasedArchitectureCalculator c = new DiameterBasedArchitectureCalculator(newTrunkDiameter, tree, species);
		tree.setArchitecture(new TreeArchitecture(c));
	}
	
	private void growHeightBy(double percent) {
		double newTrunkHeight = this.tree.getArchitecture().getTrunkHeight() * ( 1 + percent);
		HeightBasedArchitectureCalculator c = new HeightBasedArchitectureCalculator(newTrunkHeight, tree, species);
		tree.setArchitecture(new TreeArchitecture(c));
	}
	
	
	private boolean isDiameterBased() {
		return this.tree.getType() == TreeType.Adult; 
	}
}
