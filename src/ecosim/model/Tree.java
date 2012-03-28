package ecosim.model;

import ecosim.model.architecture.TreeArchitecture;

public class Tree {

	private final Location location;
	private final String speciesName;
	private final int id;
	
	private Stratum strata = Stratum.Unknown;
	private TreeHealth health = null;
	private TreeArchitecture architecture = null;
	private TreeType type;
	private Neighborhood neighborhood = null;
	
	
	public Neighborhood getNeighborhood() {
		return neighborhood;
	}



	public Tree (int id, String speciesName, Location location) {
		this.speciesName = speciesName;
		this.location = location;
		this.id = id;
	}
	
	

	public int getId() {
		return id;
	}


	public TreeType getType() {
		return type;
	}


	public void setType(TreeType type) {
		this.type = type;
	}


	public TreeArchitecture getArchitecture() {
		return architecture;
	}


	public void setArchitecture(TreeArchitecture architecture) {
		this.architecture = architecture;
	}
	
	public Stratum getStrata() {
		return strata;
	}


	public void setStrata(Stratum strata) {
		this.strata = strata;
		
	}


	public TreeHealth getHealth() {
		return health;
	}

	public boolean isDead() {
		return this.health == null || this.health.getHealthScore() <= 0;
	}
	
	public boolean isAlive() {
		return this.health != null && this.health.getHealthScore() >= 0;
	}
	
	public void kill() {
		this.health = null;
	}
	

	public void setHealth(TreeHealth health) {
		this.health = health;
	}


	public Location getLocation() {
		return location;
	}
	
	
	
	
	public void computeNeighborhood(Forest forest) {
		this.neighborhood = new Neighborhood(this, forest);
	}

	public String getSpeciesName() {
		return speciesName;
	}
	
	
	public void updateStrata() {
		// Determine the new strata designation using the pre-computed forest statistics
		this.setStrata(this.getNeighborhood().getStrata(this.getArchitecture().getTrunkHeight()));
		// Constrain the type (Adult, Sapling, Seedling) so it lines up with the new stratum.
		// For example, if the tree just reached the canapy, it cannot be a sapling any longer
		this.setType(this.getStrata().constrain(this.getType()));
	}
	
	
	
	
	
}
