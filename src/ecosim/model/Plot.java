package ecosim.model;

import java.util.LinkedList;
import java.util.List;

public class Plot {

	private final PlotLayout plotLayout;
	private final int xPos;
	private final int yPos;
	private List<Tree> trees = new LinkedList<Tree>();
	
	public List<Tree> getTrees() {
		return trees;
	}

	public Plot(int xPos, int yPos, PlotLayout plotLayout) {
		this.plotLayout = plotLayout;
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	public int getxPos() {
		return xPos;
	}
	public int getyPos() {
		return yPos;
	}
	
	public Location localize(Location location) {
		return new Location (
				location.getX() - this.xPos * this.plotLayout.getPlotSizeX(), 
				location.getY() - this.yPos * this.plotLayout.getPlotSizeY());
	}
	
	
}
