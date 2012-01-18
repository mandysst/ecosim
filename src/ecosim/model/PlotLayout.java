package ecosim.model;

public class PlotLayout {

	private final int numPlotsX;
	private final int numPlotsY;
	private final double plotSizeX;
	private final double plotSizeY;
	
	public PlotLayout () {
		this.numPlotsX = 1;
		this.numPlotsY = 1;
		this.plotSizeX = 10;
		this.plotSizeY = 10;
	}

	public PlotLayout(int numPlotsX, int numPlotsY, double plotSizeX, double plotSizeY) {
		this.numPlotsX = numPlotsX;
		this.numPlotsY = numPlotsY;
		this.plotSizeX = plotSizeX;
		this.plotSizeY = plotSizeY;
	}

	public int getNumPlotsX() {
		return numPlotsX;
	}

	public int getNumPlotsY() {
		return numPlotsY;
	}

	public double getPlotSizeX() {
		return plotSizeX;
	}

	public double getPlotSizeY() {
		return plotSizeY;
	}
	
	
	
	
	
}
