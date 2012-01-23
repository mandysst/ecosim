package ecosim.model;

public class ForestStatistics {

	private final double emergentMinHeight;
	private final double canopyMinHeight;
	private final double upperMidMinHeight;
	private final double lowerMidMinHeight;
	
	
	public ForestStatistics (Forest forest) {
		emergentMinHeight = 50;
		canopyMinHeight = 7;
		upperMidMinHeight = 5;
		lowerMidMinHeight = 3;
		System.out.println("ALERT - using completely bogus statistics for stratum heights!");
	}


	public double getEmergentMinHeight() {
		return emergentMinHeight;
	}


	public double getCanopyMinHeight() {
		return canopyMinHeight;
	}


	public double getUpperMidMinHeight() {
		return upperMidMinHeight;
	}


	public double getLowerMidMinHeight() {
		return lowerMidMinHeight;
	}
	
	public Stratum getStrata(double height) {
		if ( height > this.emergentMinHeight ) return Stratum.Emergent;
		if ( height > this.canopyMinHeight ) return Stratum.Canopy;
		if ( height > this.upperMidMinHeight) return Stratum.UpperMid;
		return Stratum.LowerMid;
	}
}
