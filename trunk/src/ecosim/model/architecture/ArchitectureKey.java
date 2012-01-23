package ecosim.model.architecture;

import ecosim.model.Stratum;
import ecosim.model.TreeType;

public class ArchitectureKey {

	private final TreeType treeType;
	private final Stratum strata;
	private final ArchitectureProperty inputProperty;
	private final ArchitectureProperty resultProperty;
	
	
	public ArchitectureKey(TreeType treeType, Stratum strata,
			ArchitectureProperty inputProperty,
			ArchitectureProperty resultProperty) {
		super();
		this.treeType = treeType;
		this.strata = strata;
		this.inputProperty = inputProperty;
		this.resultProperty = resultProperty;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((inputProperty == null) ? 0 : inputProperty.hashCode());
		result = prime * result
				+ ((resultProperty == null) ? 0 : resultProperty.hashCode());
		result = prime * result + ((strata == null) ? 0 : strata.hashCode());
		result = prime * result
				+ ((treeType == null) ? 0 : treeType.hashCode());
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
		ArchitectureKey other = (ArchitectureKey) obj;
		if (inputProperty != other.inputProperty)
			return false;
		if (resultProperty != other.resultProperty)
			return false;
		if (strata != other.strata)
			return false;
		if (treeType != other.treeType)
			return false;
		return true;
	}
	
	
	
}
