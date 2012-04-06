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
	}, Subcanopy {
		@Override
		public TreeType constrain(TreeType currentType) {
			return currentType;
		}
	}, UpperMid {
		@Override
		public TreeType constrain(TreeType currentType) {
			if(currentType==TreeType.Adult)
			{return currentType;}
			return TreeType.Sapling;
		}
	}, LowerMid {
		@Override
		public TreeType constrain(TreeType currentType) {
			if(currentType==TreeType.Adult)
			{return currentType;}
			return TreeType.Sapling;
		}
	}, Unknown {
		@Override
		public TreeType constrain(TreeType currentType) {
			return currentType;
		}
	};
	

	public abstract TreeType constrain(TreeType currentType) ;
	
	
	public static Stratum parseString(String strat)
	{
		Stratum retVal=Stratum.Canopy;
		
		if(strat.equalsIgnoreCase("Canopy")){retVal=Stratum.Canopy;}
		else if(strat.equalsIgnoreCase("SubCanopy")){retVal=Stratum.Subcanopy;}
		else if(strat.equalsIgnoreCase("upper midstory")){retVal=Stratum.UpperMid;}
		else if(strat.equalsIgnoreCase("lower midstory")){retVal=Stratum.LowerMid;}
		else if(strat.equalsIgnoreCase("Emergent")){retVal=Stratum.Emergent;}
		
		return retVal;
	}
	
}
