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
	
	@Override
	public String toString() {
		return xPos + "-" + yPos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + xPos;
		result = prime * result + yPos;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Plot other = (Plot) obj;
		if (xPos != other.xPos)
			return false;
		if (yPos != other.yPos)
			return false;
		return true;
	}
	
	
	
	
}
