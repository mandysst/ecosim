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
		System.out.println("\tTrees");
		for ( Tree tree : forest.getTrees() ){
			snapshot(tree);
		}
	}
	
	private void snapshot(Tree tree) {
		System.out.println(getLabel(tree) + " at (" + tree.getLocation().getX() + ", " + tree.getLocation().getY() + ") -> " + 
				tree.getArchitecture().getTrunkHeight() + "m trunk diameter, " + tree.getArchitecture().getTrunkDiameter() + " m trunk diameter");
	}
	
	private void header() {
		System.out.print("Run " + run.getRunNumber() + " / Year " + run.getCurrentYear() + ":  " );
	}
	
	private String getLabel(Tree tree) {
		return "Tree " + tree.getId() + " (" + tree.getSpeciesName() + ")";
	}

}
