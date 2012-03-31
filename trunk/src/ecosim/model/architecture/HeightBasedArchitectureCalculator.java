package ecosim.model.architecture;

import ecosim.model.Species;
import ecosim.model.Tree;
import ecosim.model.architecture.functions.ArchitectureFunction;

public class HeightBasedArchitectureCalculator implements
		TreeArchitectureCalculator {

	final private double trunkHeight;
	final private double trunkDiameter;
	final private double canopyHeight;
	final private double canopyDiameter;
	
	public HeightBasedArchitectureCalculator(double newHeight, Tree currentTree, Species species) {
		trunkHeight = newHeight;
		trunkDiameter = evaluateTrunkDiameter(currentTree, species);
		canopyHeight = evaluateCanapyHeight(currentTree, species);
		canopyDiameter = evaluateCanapyDiameter(currentTree, species);
	}
	
	private double evaluateTrunkDiameter(Tree currentTree, Species species) {
		ArchitectureFunction f = species.getArchitectureFunctions().get(
				new ArchitectureKey(currentTree.getType(), currentTree.getStrata(),
						ArchitectureProperty.TrunkHeight, ArchitectureProperty.TrunkDiameter));
		return  f.evaluate(trunkHeight);
	}
	private double evaluateCanapyHeight(Tree currentTree, Species species) {
		ArchitectureFunction f = species.getArchitectureFunctions().get(
				new ArchitectureKey(currentTree.getType(), currentTree.getStrata(),
						ArchitectureProperty.TrunkHeight, ArchitectureProperty.CanapyHeight));
		return  f.evaluate(trunkHeight);
	}
	private double evaluateCanapyDiameter(Tree currentTree, Species species) {
		ArchitectureFunction f = species.getArchitectureFunctions().get(
				new ArchitectureKey(currentTree.getType(), currentTree.getStrata(),
						ArchitectureProperty.TrunkHeight, ArchitectureProperty.CanapyDiameter));
		return  f.evaluate(trunkHeight);
	}
	
	
	
	@Override
	public double calculateTrunkHeight() {
		return trunkHeight;
	}

	@Override
	public double calculateTrunkDiameter() {
		return trunkDiameter;
	}

	@Override
	public double calculateCanopyHeight() {
		return canopyHeight;
	}

	@Override
	public double calculateCanopyDiameter() {
		return canopyDiameter;
	}

}
