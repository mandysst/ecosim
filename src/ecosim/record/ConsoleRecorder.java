package ecosim.record;

import ecosim.model.Forest;
import ecosim.model.Stratum;
import ecosim.model.Tree;
import ecosim.sim.Simulation;
import ecosim.sim.SimulationRun;

public class ConsoleRecorder extends Recorder {

	public ConsoleRecorder(Simulation sim, SimulationRun run) {
		super(sim, run);
	}

	@Override
	public void recordDeath(Tree tree, int year) {
		header();
		System.out.println(getLabel(tree) + " died");
	}

	@Override
	public void recordPromotion(Tree tree, Stratum previous) {
		header();
		System.out.println(getLabel(tree) + " promoted from " + previous + " to " + tree.getStrata());
	}

	@Override
	public void recordSnapshot(Forest forest) {
		header();
		System.out.println("completed");
	}
	
	private void header() {
		System.out.print("Run " + run.getRunNumber() + " / Year " + run.getCurrentYear() + ":  " );
	}
	
	private String getLabel(Tree tree) {
		return "Tree " + tree.getId() + " in plot " + tree.getPlot() + " (" + tree.getSpeciesName() + ")";
	}

}
