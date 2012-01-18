package ecosim.model;

import java.util.LinkedList;
import java.util.List;

public final class Forest {

	private final PlotLayout plotLayout;
	private final List<Tree> trees = new LinkedList<Tree>();
	private final List<Plot> plots = new LinkedList<Plot>();
		
	public Forest(PlotLayout plotLayout) {
		this.plotLayout = plotLayout;
	}
	
	public PlotLayout getPlotLayout() {
		return plotLayout;
	}
	
	public List<Tree> getTrees() {
		return trees;
	}

	public List<Plot> getPlots() {
		return plots;
	}
	
	
	
}
