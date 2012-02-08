package ecosim.model.loaders;

import java.io.File;

import ecosim.sim.SpeciesMap;

public class ExcelSpeciesLoader implements SpeciesLoader {

	private final File excelFile;
	
	public ExcelSpeciesLoader (File excelFile) {
		this.excelFile = excelFile;
	}
	
		
	@Override
	public void loadSpecies(SpeciesMap speciesMap) {

		/*  Take a look at DefaultSpeciesLoader.
		 * 
		 *  This code simply adds a SINGLE species.  Each species requires mortality rate calculators 
		    a number of parameters (encapsulated by a MortalityKey), growth calculators (similarly key'd), 
			and architecture functions.
				
			We need to create (offline) an Excel format to embed all these a parameters, and then 
			enhance this loader to read from that excel file.
				
			The excel file would have many species in it.
			
			Later, we could allow the user to upload a spreadsheet of similar format in order to manipulate these params
			but for now we'll just store it in WebContent/excel
		*/

	}

}
