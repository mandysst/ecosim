package ecosim.model.growth;

import ecosim.model.Stratum;
import ecosim.model.TreeType;

public class GrowthFactorKey {

	private final TreeType type;
	private final Stratum strata;
	public GrowthFactorKey(TreeType type, Stratum strata) {
		super();
		this.type = type;
		this.strata = strata;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((strata == null) ? 0 : strata.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		GrowthFactorKey other = (GrowthFactorKey) obj;
		if (strata != other.strata)
			return false;
		if (type != other.type)
			return false;
		return true;
	}
	
	
	
}
