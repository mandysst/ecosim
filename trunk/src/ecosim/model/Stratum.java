package ecosim.model;

public enum Stratum {
	Emergent {
		@Override
		public TreeType constrain(TreeType currentType) {
			return TreeType.Adult;
		}
	}, Canopy {
		@Override
		public TreeType constrain(TreeType currentType) {
			return TreeType.Adult;
		}
	}, SubCanapoy {
		@Override
		public TreeType constrain(TreeType currentType) {
			return currentType;
		}
	}, UpperMid {
		@Override
		public TreeType constrain(TreeType currentType) {
			return TreeType.Sapling;
		}
	}, LowerMid {
		@Override
		public TreeType constrain(TreeType currentType) {
			return TreeType.Sapling;
		}
	}, Unknown {
		@Override
		public TreeType constrain(TreeType currentType) {
			return currentType;
		}
	};
	
	
	
	
	public abstract TreeType constrain(TreeType currentType) ;
	
	
	
}
