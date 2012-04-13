package ecosim.model;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.math3.stat.descriptive.rank.Percentile;

public class Neighborhood {
	private static final double RADIUS = 20;// 20 meter radius 
	private static final double EMERGENT = 95;
	private static final double CANAPY = 85;
	private static final double UPPER_MID = 50;
	private static final double LOWER_MID = 25;
	
	private final double emergentMinHeight;
	private final double canopyMinHeight;
	private final double upperMidMinHeight;
	private final double lowerMidMinHeight;
	private final List<Tree> neighbors;
	
	
	
	public Neighborhood (Tree tree, Forest forest) {
		neighbors = new LinkedList<Tree>();
		List<Double> heights = new LinkedList<Double>();
		// Find all the trees in the forest within RADIUS
		for ( Tree t : forest.getTrees() ) {
			if (t.getLocation().distanceFrom(tree.getLocation()) < RADIUS) {
				neighbors.add(t);
				heights.add(t.getArchitecture().getTrunkHeight());
			}
		}
		
		int i = 0;
		double [] heightArray = new double [heights.size()];
		for ( Double h : heights) {
			heightArray[i++] = h;
		}
		
		// Compute the appropriate heights
		Percentile percentile = new Percentile();
		emergentMinHeight = percentile.evaluate(heightArray, EMERGENT);
		canopyMinHeight = percentile.evaluate(heightArray, CANAPY);
		upperMidMinHeight = percentile.evaluate(heightArray, UPPER_MID);
		lowerMidMinHeight = percentile.evaluate(heightArray, LOWER_MID);
		
		
	}


	public List<Tree> getNeighbors() {
		return neighbors;
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
