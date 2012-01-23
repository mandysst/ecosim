package ecosim.record;

import ecosim.model.Forest;
import ecosim.model.Stratum;
import ecosim.model.Tree;
import ecosim.sim.Simulation;
import ecosim.sim.SimulationRun;

public class NullRecorder extends Recorder {

	public NullRecorder() {
		super(null, null);
	}

	@Override
	public void recordDeath(Tree tree, int year) {
		
	}

	@Override
	public void recordPromotion(Tree tree, Stratum previous) {
		
	}

	@Override
	public void recordSnapshot(Forest forest) {
		
	}

}
