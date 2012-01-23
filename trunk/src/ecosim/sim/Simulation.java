package ecosim.sim;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import ecosim.model.Forest;
import ecosim.model.Species;
import ecosim.model.loaders.DefaultSpeciesLoader;
import ecosim.model.loaders.ForestCloneLoader;
import ecosim.model.loaders.SpeciesLoader;
import ecosim.record.ConsoleRecorder;
import ecosim.record.Recorder;

public class Simulation {

	private final int MAX_THREADS = 10;
	private final int MAX_RUNTIME_MINUTES = 30;
	
	private Forest originalForest;
	private SpeciesMap speciesMap = new SpeciesMap();
	
	private ExecutorService executorService;
	private final SimulationParameters simParams;
	private final UUID id;
	private final String userLabel;
	private final Date createdTime;

	public Simulation(String userLabel, SimulationParameters simParams) {
		this.id = UUID.randomUUID();
		this.userLabel = userLabel;
		this.createdTime = new Date();
		this.simParams = simParams;
		executorService = Executors.newFixedThreadPool(Math.min(simParams.getNumRuns(), MAX_THREADS));
		
		SpeciesLoader sl = new DefaultSpeciesLoader();
		sl.loadSpecies(speciesMap);
		
	}
	
	public Forest getOriginalForest() {
		return originalForest;
	}

	public void setOriginalForest(Forest originalForest) {
		this.originalForest = originalForest;
	}
	
	public void run() {
		try {
			execute();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(this.userLabel + " [" + this.id.toString() + "] completed (started " + this.getCreatedTimestamp() + ")");
	}
	
	private String getCreatedTimestamp() {
		DateFormat f = new SimpleDateFormat();
		return f.format(this.createdTime);
	}
	
	private void execute () throws Throwable{
		ForestCloneLoader cloner = new ForestCloneLoader(this.originalForest);
		
		for ( int i = 0; i < simParams.getNumRuns(); i++ ) {
			SimulationRun simRun = new SimulationRun(i, simParams.getNumYears(), cloner, this.speciesMap);
			Recorder recorder = new ConsoleRecorder(this, simRun);
			simRun.setRecorder(recorder);
			executorService.execute(simRun);
		}
		this.executorService.shutdown();
		this.executorService.awaitTermination(MAX_RUNTIME_MINUTES, TimeUnit.MINUTES);
		
	}

	public SpeciesMap getSpeciesMap() {
		return speciesMap;
	}
	
	
	
}
