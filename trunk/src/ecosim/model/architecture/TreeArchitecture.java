package ecosim.model.architecture;


public final class TreeArchitecture {

	final private double trunkHeight;
	final private double trunkDiameter;
	final private double canopyHeight;
	final private double canopyDiameter;
	
	
	public TreeArchitecture(TreeArchitectureCalculator params) {
		this.trunkDiameter = params.calculateTrunkDiameter();
		this.trunkHeight = params.calculateTrunkHeight();
		this.canopyDiameter = params.calculateCanopyDiameter();
		this.canopyHeight = params.calculateCanopyHeight();
	}
	
	public double getTrunkHeight() {
		return trunkHeight;
	}

	public double getTrunkDiameter() {
		return trunkDiameter;
	}

	public double getCanopyHeight() {
		return canopyHeight;
	}

	public double getCanopyDiameter() {
		return canopyDiameter;
	}
	
	
	
	
	
}
