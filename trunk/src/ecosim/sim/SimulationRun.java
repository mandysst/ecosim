package ecosim.sim;

import java.util.Random;

import ecosim.model.Forest;
import ecosim.model.Neighborhood;
import ecosim.model.Species;
import ecosim.model.Tree;
import ecosim.model.growth.GrowthCalculator;
import ecosim.model.growth.GrowthController;
import ecosim.model.growth.GrowthFactorKey;
import ecosim.model.loaders.ForestLoader;
import ecosim.model.mortality.MortalityCalculator;
import ecosim.model.mortality.MortalityKey;
import ecosim.record.NullRecorder;
import ecosim.record.Recorder;

public class SimulationRun implements Runnable {

	private final int runNumber;
	



	private final int numYears;
	private final Forest forest;
	private final SpeciesMap speciesMap;
	private Recorder recorder;
	public final Random randgen;
	
	
	/*  mutable state variables */
	private int currentYear;
	
	
	public SimulationRun(int runNumber, int numYears, ForestLoader loader, SpeciesMap speciesMap) {
		this.runNumber = runNumber;
		this.numYears = numYears;
		this.forest = loader.loadForest(speciesMap);
		this.speciesMap = speciesMap;
		randgen = new Random(System.nanoTime() * runNumber+1);
		this.recorder = new NullRecorder();
	}
	
	

	@Override
	public void run() {
		System.out.println("Started  simulation run " + this.runNumber);
		for ( this.currentYear = 0; this.currentYear < this.numYears; this.currentYear++ ) {
			runYear();
		}
		System.out.println("Completed simulation run " + this.runNumber);
	}
	
	void runYear() {
		
		for ( Tree tree : this.forest.getTrees() ) {
			if ( tree.isAlive() ) {
				tree.computeNeighborhood(forest);
			}
		}
		
		for ( Tree tree : this.forest.getTrees() ) {
			if ( tree.isAlive() ) {
				runTree(tree);
			}
		}
		
		
		updateStratum();
		this.recorder.recordSnapshot(forest);
	}
	
	void runTree(Tree tree ) {
		applyMortality(tree);
		if ( tree.isDead() ) {
			this.recorder.recordDeath(tree, this.currentYear);
			return;
		}
		applyGrowth(tree);
		
	}
	
	void applyMortality(Tree tree) {
		Species species = this.speciesMap.get(tree.getSpeciesName());
		MortalityKey mKey = new MortalityKey(tree.getType(), tree.getStrata());
		MortalityCalculator mortality = species.getMortality().get(mKey);
		if ( mortality == null ) {
			throw new RuntimeException("No mortality data for species [" + tree.getSpeciesName() + "] type [" + tree.getType() + "] strata [" + tree.getStrata() + "]");
		}
		if ( mortality.shouldDie(tree, forest, this.currentYear, this.randgen) ) {
			tree.kill();
		}
	}
	
	void applyGrowth(Tree tree) {
		Species species = this.speciesMap.get(tree.getSpeciesName());
		GrowthFactorKey gKey = new GrowthFactorKey(tree.getType(), tree.getStrata());
		GrowthCalculator growth = species.getGrowthCalculators().get(gKey);
		if ( growth == null  ){
			throw new RuntimeException("No growth data for species [" + tree.getSpeciesName() + "] type [" + tree.getType() + "] strata [" + tree.getStrata() + "]");
		}
		double growthFactor = growth.getPercentGrowth(tree, forest, this.currentYear, this.randgen);
		GrowthController gc = new GrowthController(tree, species);
		gc.growBy(growthFactor);
	}
	
	void updateStratum() {
		for ( Tree tree : this.forest.getTrees() ) {
			if ( tree.isAlive()) {
				tree.updateStrata();
			}
		}
	}
	
	
	
		
	public Recorder getRecorder() {
		return recorder;
	}



	public void setRecorder(Recorder recorder) {
		this.recorder = recorder;
	}
	
	
	public int getRunNumber() {
		return runNumber;
	}



	public int getCurrentYear() {
		return currentYear;
	}
	
	
	
	
	
}
