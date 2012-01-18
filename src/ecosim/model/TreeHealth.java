package ecosim.model;

public final class TreeHealth {

	private final float healthScore;

	
	public TreeHealth(float score) {
		this.healthScore = score;
	}
	
	public float getHealthScore() {
		return healthScore;
	}

	public TreeHealth adjust(float delta) {
		return new TreeHealth(this.getHealthScore() + delta);
	}
	
}
