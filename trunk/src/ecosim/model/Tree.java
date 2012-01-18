package ecosim.model;

import ecosim.model.architecture.TreeArchitecture;

public class Tree {

	private final Location location;
	private final Plot plot;
	private final String speciesName;
	
	private Stratum strata = Stratum.Unknown;
	private TreeHealth health = null;
	private TreeArchitecture architecture = null;
	private TreeType type;
	
	
	public Tree (String speciesName, Location location, Plot plot) {
		this.speciesName = speciesName;
		this.location = location;
		this.plot = plot;
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


	public void setHealth(TreeHealth health) {
		this.health = health;
	}


	public Location getLocation() {
		return location;
	}
	
	public Location getLocalLocation() {
		return this.plot.localize(this.location);
	}
	
	


	public String getSpeciesName() {
		return speciesName;
	}
	
	
	
	
	
	
}
