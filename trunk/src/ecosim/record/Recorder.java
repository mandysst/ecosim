package ecosim.record;

import ecosim.model.Forest;
import ecosim.model.Stratum;
import ecosim.model.Tree;
import ecosim.sim.Simulation;
import ecosim.sim.SimulationRun;

public abstract class Recorder {

	protected final Simulation sim;
	protected final SimulationRun run;
	
	
	protected Recorder(Simulation sim, SimulationRun run) {
		this.sim = sim;
		this.run = run;
	}
	
	
	
	public abstract void recordDeath(Tree tree, int year) ;
	public abstract void recordPromotion(Tree tree, Stratum previous) ;
	public abstract void recordSnapshot(Forest forest);
	
	
}

