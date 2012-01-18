package ecosim.model.architecture;

import ecosim.model.Species;
import ecosim.model.Tree;
import ecosim.model.architecture.functions.ArchitectureFunction;

public class HeightBasedArchitectureCalculator implements
		TreeArchitectureCalculator {

	final private float trunkHeight;
	final private float trunkDiameter;
	final private float canopyHeight;
	final private float canopyDiameter;
	
	public HeightBasedArchitectureCalculator(float newTrunkDiameter, Tree currentTree, Species species) {
		trunkHeight = newTrunkDiameter;
		trunkDiameter = evaluateTrunkDiameter(currentTree, species);
		canopyHeight = evaluateCanapyHeight(currentTree, species);
		canopyDiameter = evaluateCanapyDiameter(currentTree, species);
	}
	
	private float evaluateTrunkDiameter(Tree currentTree, Species species) {
		ArchitectureFunction f = species.getArchitectureFunctions().get(
				new ArchitectureKey(currentTree.getType(), currentTree.getStrata(),
						ArchitectureProperty.TrunkHeight, ArchitectureProperty.TrunkHeight));
		return  f.evaluate(trunkHeight);
	}
	private float evaluateCanapyHeight(Tree currentTree, Species species) {
		ArchitectureFunction f = species.getArchitectureFunctions().get(
				new ArchitectureKey(currentTree.getType(), currentTree.getStrata(),
						ArchitectureProperty.TrunkHeight, ArchitectureProperty.CanapyHeight));
		return  f.evaluate(trunkHeight);
	}
	private float evaluateCanapyDiameter(Tree currentTree, Species species) {
		ArchitectureFunction f = species.getArchitectureFunctions().get(
				new ArchitectureKey(currentTree.getType(), currentTree.getStrata(),
						ArchitectureProperty.TrunkHeight, ArchitectureProperty.CanapyDiameter));
		return  f.evaluate(trunkHeight);
	}
	
	
	
	@Override
	public float calculateTrunkHeight() {
		return trunkHeight;
	}

	@Override
	public float calculateTrunkDiameter() {
		return trunkDiameter;
	}

	@Override
	public float calculateCanopyHeight() {
		return canopyHeight;
	}

	@Override
	public float calculateCanopyDiameter() {
		return canopyDiameter;
	}

}
