package ecosim.model.architecture;


public final class TreeArchitecture {

	final private float trunkHeight;
	final private float trunkDiameter;
	final private float canopyHeight;
	final private float canopyDiameter;
	
	
	public TreeArchitecture(TreeArchitectureCalculator params) {
		this.trunkDiameter = params.calculateTrunkDiameter();
		this.trunkHeight = params.calculateTrunkHeight();
		this.canopyDiameter = params.calculateCanopyDiameter();
		this.canopyHeight = params.calculateCanopyHeight();
	}
	
	public float getTrunkHeight() {
		return trunkHeight;
	}

	public float getTrunkDiameter() {
		return trunkDiameter;
	}

	public float getCanopyHeight() {
		return canopyHeight;
	}

	public float getCanopyDiameter() {
		return canopyDiameter;
	}
	
	
	
	
	
}
