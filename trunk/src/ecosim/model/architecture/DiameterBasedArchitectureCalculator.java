package ecosim.model.architecture;

import ecosim.model.Species;
import ecosim.model.Tree;
import ecosim.model.architecture.functions.ArchitectureFunction;

public class DiameterBasedArchitectureCalculator implements
		TreeArchitectureCalculator {

	final private float trunkHeight;
	final private float trunkDiameter;
	final private float canopyHeight;
	final private float canopyDiameter;
	
	public DiameterBasedArchitectureCalculator(float newTrunkDiameter, Tree currentTree, Species species) {
		trunkDiameter = newTrunkDiameter;
		trunkHeight = evaluateTrunkHeight(currentTree, species);
		canopyHeight = evaluateCanapyHeight(currentTree, species);
		canopyDiameter = evaluateCanapyDiameter(currentTree, species);
	}
	
	private float evaluateTrunkHeight(Tree currentTree, Species species) {
		ArchitectureFunction f = species.getArchitectureFunctions().get(
				new ArchitectureKey(currentTree.getType(), currentTree.getStrata(),
						ArchitectureProperty.TrunkDiameter, ArchitectureProperty.TrunkHeight));
		return  f.evaluate(trunkDiameter);
	}
	private float evaluateCanapyHeight(Tree currentTree, Species species) {
		ArchitectureFunction f = species.getArchitectureFunctions().get(
				new ArchitectureKey(currentTree.getType(), currentTree.getStrata(),
						ArchitectureProperty.TrunkDiameter, ArchitectureProperty.CanapyHeight));
		return  f.evaluate(trunkDiameter);
	}
	private float evaluateCanapyDiameter(Tree currentTree, Species species) {
		ArchitectureFunction f = species.getArchitectureFunctions().get(
				new ArchitectureKey(currentTree.getType(), currentTree.getStrata(),
						ArchitectureProperty.TrunkDiameter, ArchitectureProperty.CanapyDiameter));
		return  f.evaluate(trunkDiameter);
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
