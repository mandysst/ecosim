package ecosim.model.architecture;

import ecosim.model.Species;
import ecosim.model.Tree;
import ecosim.model.architecture.functions.ArchitectureFunction;

public class DiameterBasedArchitectureCalculator implements
		TreeArchitectureCalculator {

	final private double trunkHeight;
	final private double trunkDiameter;
	final private double canopyHeight;
	final private double canopyDiameter;
	
	public DiameterBasedArchitectureCalculator(double newTrunkDiameter, Tree currentTree, Species species) {
		trunkDiameter = newTrunkDiameter;
		trunkHeight = evaluateTrunkHeight(currentTree, species);
		canopyHeight = evaluateCanapyHeight(currentTree, species);
		canopyDiameter = evaluateCanapyDiameter(currentTree, species);
	}
	
	private double evaluateTrunkHeight(Tree currentTree, Species species) {
		ArchitectureFunction f = species.getArchitectureFunctions().get(
				new ArchitectureKey(currentTree.getType(), currentTree.getStrata(),
						ArchitectureProperty.TrunkDiameter, ArchitectureProperty.TrunkHeight));
		return  f.evaluate(trunkDiameter);
	}
	private double evaluateCanapyHeight(Tree currentTree, Species species) {
		ArchitectureFunction f = species.getArchitectureFunctions().get(
				new ArchitectureKey(currentTree.getType(), currentTree.getStrata(),
						ArchitectureProperty.TrunkDiameter, ArchitectureProperty.CanapyHeight));
		return  f.evaluate(trunkDiameter);
	}
	private double evaluateCanapyDiameter(Tree currentTree, Species species) {
		ArchitectureFunction f = species.getArchitectureFunctions().get(
				new ArchitectureKey(currentTree.getType(), currentTree.getStrata(),
						ArchitectureProperty.TrunkDiameter, ArchitectureProperty.CanapyDiameter));
		return  f.evaluate(trunkDiameter);
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
