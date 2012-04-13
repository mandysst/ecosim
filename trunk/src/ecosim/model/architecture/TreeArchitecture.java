package ecosim.model.architecture;


public final class TreeArchitecture {

	final private double trunkHeight;
	final private double trunkDiameter;
	final private double canopyHeight;
	final private double canopyDiameter;
	
	
	public TreeArchitecture(TreeArchitectureCalculator params, TreeArchitecture prev) {
		
		this.trunkDiameter = Math.max(prev==null?0:prev.getTrunkDiameter(), params.calculateTrunkDiameter());
		this.trunkHeight = Math.max(prev==null?0:prev.getTrunkHeight(), params.calculateTrunkHeight());
		this.canopyDiameter = Math.max(prev==null?0:prev.getCanopyDiameter(), params.calculateCanopyDiameter());
		this.canopyHeight = Math.max(prev==null?0:prev.getCanopyHeight(), params.calculateCanopyHeight());
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
