package ecosim.model;

public final class Location {

	private final double x;
	private final double y;
	
	public Location(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	public double distanceFrom(Location loc) {
		return Math.sqrt((this.getX() - loc.getX())*(this.getX() - loc.getX()) + (this.getY() - loc.getY())*(this.getY() - loc.getY()));
	}
}
